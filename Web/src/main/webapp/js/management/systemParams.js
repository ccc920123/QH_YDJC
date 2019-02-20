/**
 *标题：用户管理
 *说明:
 *作者：zjc
 *日期：2017/10/14
 */
var config = {
    id: 1
    , parentid: 0
    , bmbh: '630100000000'//360000000000
    , name: '青海省公安厅交通警察支队'
    , levelnum: 11
    , pageNo: 1
    , pageSize: 10
    , typeList: null
    , deptToAdd: null
}
$(function () {
    justPression();
    initView();     //初始化高度
    initLayui();  //初始化layui

    if (justPressionBy("1126")){
        $("#queryBtn").removeAttr("style");
    } else {
        $("#queryBtn").css("display", "none");
    }

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
        loadParamManage();
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        //日期选择器
        laydate.render({
            elem: '#mmyxq'
            , type: 'date' //默认，可不填
            //,format: "YYYY-MM-DD"
        });
        laydate.render({
            elem: '#yhyxq'
            , type: 'date' //默认，可不填
            //,format: "YYYY-MM-DD"
        });

    });


});


/**
 * 加载页面
 */
function loadParamManage() {
    $('#tbody').html('');
    pageClose();
    loadParam();  //加载 PDA
}

function loadParam() {
    doGet('params/management/system/params', {
        bmbh: config.bmbh,
        csmc: $('#params').val(),
        page: config.pageNo,
        pagesize: config.pageSize
    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg('没有查到相关信息', 5);
            return;
        }
        pageOpen(response.data.total, function () {
            loadParam();
        });

        loadParamTable(response.data.datas);
    });
}

function loadParamTable(data) {

    var bianji = justPressionBy("1125");

    var tbody = '';
    $.each(data, function (rowIndex, params) {
        var bjhtml = '<img id="edit" src="../../imgs/management/edit.png" title="操作" ' +
            'uid="' + params.id + '" ' +
            'bmbh="' + params.bmbh + '" ' +
            'bmmc="' + params.bmmc + '"   ' +
            'gjz="' + params.gjz + '"' +
            'csm="' + params.csmc + '"' +
            'cssm="' + params.csmc + '"' +
            'csz="' + params.csz + '"' +
            'onclick="editParam(this)">&nbsp;&nbsp;&nbsp;';
        var html = '';
        if (bianji){
            html += bjhtml;
        }

        tbody += '' +
            '<tr>' +
            '    <td>' + params.rn + '</td>' +
            '    <td>' + params.gjz + '</td>' +
            '    <td>' + params.csmc + '</td>' +
            '    <td style="word-wrap:break-word;word-break:break-all;";>' + params.csz + '</td>' +
            // '    <td>' + params.sfjc + '</td>' +
            '    <td>' + params.czsj + '</td>' +
            '    <td>' + html + '</td>' +
            '</tr>';
    });
    $('#tbody').html(tbody);
}

function editParam(span) {

    var title = "部门参数配置";
    $('#bmdm').val($(span).attr("bmbh"));
    $('#bmmc').val($(span).attr("bmmc"));
    $('#gjz').val($(span).attr("gjz"));
    $('#csm').val($(span).attr("csm"));
    $('#cssm').val($(span).attr("cssm"));
    $('#csz').val($(span).attr("csz"));

    var id = $(span).attr("uid");
    layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['800px', '400px'],
        title: title,
        // maxmin: true,
        content: $("#addNewDiv")
        , zIndex: layer.zIndex //重点1
        , success: function (layero) {
            layer.setTop(layero); //重点2
        }, end: function () {
            $("#addNewDiv").hide();
        }
    });

    $('#confirmCancel').unbind('click');
    $('#confirmCancel').bind('click', function () {
        layer.closeAll();
    });
    $('#confirmSubmit').unbind('click');
    $('#confirmSubmit').bind('click', function () {

        updateParam(id);

    });


}

function updateParam(id) {

    doGet('params/management/system/updateParams', {

        bmbh: $('#bmdm').val(),
        id: id,
        csz: $('#csz').val(),
        //  gjz: $('#gjz').val(),
        // csmc: '',
        // bmmc: '',
        // sfjc: '',
        // czsj: '',
        // rn: ''
    }, function (response) {
        layer.closeAll();
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }
        loadParamManage();
    });

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
    loadParamManage();
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

    loadParamManage();
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

