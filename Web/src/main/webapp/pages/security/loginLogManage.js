/**
 *标题：用户管理
 *说明:
 *作者：zjc
 *日期：2017/10/14
 */
var config = {
    id: 1
    , parentid: 0
    , bmbh: '630000000000'//360000000000
    , name: '青海省公安厅交通警察总队'
    , levelnum: 11
    , pageNo: 1
    , pageSize: 10
    , typeList: null
    , deptToAdd: null
}
$(function () {
    initView();     //初始化高度
    initLayui();  //初始化layui
    /**
     * 拉取用户信息、加载机构树
     */
    getUserQx(function () {
        loadAllDept();
    });

    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadList();
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        laydate.render({
            elem: '#ksrq',//开始日期
            type: 'date',
            value: new Date(),
            isInitValue: true
        });
        laydate.render({
            elem: '#jsrq', //结束日期
            type: 'date',
            value: new Date(),
            isInitValue: true
        });

    });


});


/**
 * 加载页面
 */
function loadParamManage() {
    $('#tbody').html('');
    // pageClose();
    loadList();
}


/**
 * 根据参数加载列表
 */
function loadList() {
    doGet('Operation/queryOperationList', {
        bmbh: config.bmbh,
        ksrq: $("#ksrq").val(),
        jsrq: $("#jsrq").val(),
        czlx: $('#czlx option:selected').val(),//选中的值
        czr: $("#czr").val(),
        pageNo: config.pageNo,
        logType:1,
        pageSize: config.pageSize

    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }

        pageOpen(response.data.total, function () {
            loadList();
        });

        loadTable(response.data.list);
    });
}


function loadTable(data) {
    var tbody = '';
    $.each(data, function (rowIndex, params) {
        tbody += '' +
            '<tr>' +
            '    <td>' + params.RN + '</td>' +
            '    <td>' + params.OPTYPE + '</td>' +
            '    <td>' + params.REALNAME + '</td>' +
            '    <td>' + params.BMMC + '</td>' +
            '    <td>' + timestampToTime(params.OP_TIME) + '</td>' +
            '    <td >' + params.OPRESULT + '</td>' +
            // "<td> onclick='lookOperation(this)' uid = '" +  params.id+ "'style='cursor: pointer;color:blue;'>查看</td>" +
            // '       ';
            "<td>" +
            " <img id=\"look\" src=\"../../imgs/management/chakan.png\" title=\"查看\" " +
            "onclick='lookOperation(this)' uid = '" + params.ID + "' /></td>";

        tbody += '</tr>';
    });
    $('#tbody').html(tbody);
}

function lookOperation(span) {

    var title = "台账详情";

    // $("input[name=status]").attr("disabled", true);
    var id = $(span).attr("uid");
    window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['900px', '400px'],
        title: "日志详情",
        content: '/Web/pages/security/operationDetail.html?id=' + id,
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);

        },
        cancel: function () {
        }

    })

}


/**获取页面高度*/
function initView() {
    var height = $(window).height();   //获取浏览器可用高度
    $('.deptLeft').css('height', height); //菜单树的高度
}

/**初始化layui*/
function initLayui() {
    layui.use('element', function () {
        // var element = layui.element;
    });
    layui.use('laypage', function () {
        laypage = layui.laypage;
    });
}

/**
 * 拉取用户信息
 */
function getUserQx(callback) {
    doGet("common/getUserQx", {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        config.bmbh = response.data.deptId;
        callback();
    });
}

function loadAllDept() {
    doGet('management/dept/selectAll', {bmbh: '', zdid: ''}, function (response) {
        var zNodes = [];
        $.each(response.data, function (index, dept) {
            zNodes[index] = {
                id: dept.id
                , pId: dept.parentid
                , name: dept.name
                , bmbh: dept.bmbh
                , sjzdbmbh: dept.sjzdbmbh
                , lev: dept.levelnum
                , icon: '../../imgs/management/dept012.png'
                , type: dept.type
            }
        });
        $.each(zNodes, function (index, node) {
            if (node.lev == 1) {
                node['open'] = true;
                return false;
            }
        });
        loadZtree(zNodes);
    });
}

function loadZtree(zNodes) {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "bmbh",
                pIdKey: "sjzdbmbh",
                rootPId: null
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    $.fn.zTree.init($("#deptTree"), setting, zNodes);

    if (zNodes.length > 0) {

        var rootid = zNodes[0].id;
        //初始化时，默认选中第一个节点
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        var node = treeObj.getNodeByParam("id", rootid);
        treeObj.selectNode(node);
        config.bmbh = node.bmbh;
        config.levelnum = node.lev;
        config.name = node.name;
        config.type = node.type;
        config.id = node.id;

    }
    loadList();
}


/**
 * 点击选中显示机构的用户列表
 * 1、保存选中的部门编号
 * 1、加载列表数据
 * @param event
 * @param treeId
 * @param treeNode
 */
function zTreeOnClick(event, treeId, treeNode) {
    config.bmbh = treeNode.bmbh;
    config.levelnum = treeNode.lev;
    config.name = treeNode.name;
    config.id = treeNode.id;
    config.type = treeNode.type;

    loadList();
}

/**
 * 显示分页
 * 每次执行后当前页初始化为1
 * @param callback
 * @param count
 */
function pageOpen(count, callback) {
    $('#page').css('display', 'block');
    laypage.render({
        elem: 'page'
        , limit: config.pageSize
        , count: count //数据总数，从服务端得到
        , curr: config.pageNo
        , jump: function (obj, first) {
            //obj包含了当前分页的所有参数，比如：
            // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            // console.log(obj.limit); //得到每页显示的条数
            //首次不执行
            if (!first) {
                //do something
                config.pageNo = obj.curr;
                callback();
                return;
            }
        }
    });
    config.pageNo = 1;
}

function pageClose() {
    $('#page').css('display', 'none');
}

var laypage = null;

