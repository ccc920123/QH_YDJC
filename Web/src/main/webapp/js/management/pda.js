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

    if (justPressionBy("1118")){
        $("#queryBtn").removeAttr("style");
    } else {
        $("#queryBtn").css("display", "none");
    }
    if (justPressionBy("1116")){
        $("#addNew").removeAttr("style");
    } else {
        $("#addNew").css("display", "none");
    }

    loadAllDept();

    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadPDAManage();
    });

    $('#addNew').click(function () {
        toAddDept();
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

function loadAllDept() {
    doGet('management/dept/selectAll', {bmbh: '', zdid: ''}, function (response) {
        var zNodes = [];
        $.each(response.data, function (index, item) {
            var open = false;
            if (parseInt(item.levelnum) == 1)//&&parseInt(item.type)==0
            {
                open = true;
            }
            zNodes[index] = {
                id: item.id
                , pId: item.parentid
                , name: item.name
                , bmbh: item.bmbh
                , sjzdbmbh: item.sjzdbmbh
                , lev: item.levelnum
                , icon: '../../imgs/management/dept012.png'
                , open: open
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

    var rootid = zNodes[0].id;
    //初始化时，默认选中第一个节点
    var treeObj = $.fn.zTree.getZTreeObj("deptTree");
    var node = treeObj.getNodeByParam("id", rootid);
    treeObj.selectNode(node);
    config.bmbh = node.bmbh;
    config.levelnum = node.lev;
    config.name = node.name
    loadPDAManage();
}

/**
 * 点击选中显示机构的用户列表
 * @param event
 * @param treeId
 * @param treeNode
 */
function zTreeOnClick(event, treeId, treeNode) {
    config.bmbh = treeNode.bmbh;
    config.levelnum = treeNode.lev;
    config.name = treeNode.name;
    loadPDAManage();
}


/**
 * 加载页面
 */
function loadPDAManage() {
    $('#tbody').html('');
    pageClose();
    loadPDA();  //加载 PDA
}

function loadPDA() {
    // var checked = document.getElementById('includeLevDown').checked;
    var checked=false;
    /*包含下级*/
    var bhxj = "0";
    if (checked) bhxj = "1";
    $('#tbody').empty();
    doGet('pdas/management/pda/getpdas', {
        pdamc: $('#pdamc').val(),
        pdawym: $('#pdawym').val()
        , bhxj: bhxj
        , bmbh: config.bmbh
        , pageindex: config.pageNo
        , pagesize: config.pageSize
    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg('没有查到相关信息', 5);
            return;
        }
        loadPDATable(response.data);
    });
}

function loadPDATable(data) {

    var bianji = justPressionBy("1117");
    var shanchu = justPressionBy("1119");

    var tbody = '';
    $.each(data, function (rowIndex, pda) {
        if (parseInt(pda.id) == 0) {
            pageOpen(pda.rn, function () {
                loadPDA();
            });
            return true;
        }
        var ztname = pda.zt == "1" ? "正常" : "停用";
        var bjhtml = '<img id="edit" src="../../imgs/management/edit.png" title="编辑" zt="' + pda.zt + '" bmbh="' + pda.bmbh + '" bmmc="' + pda.bmmc + '" pda_id="' + pda.id + '"  mc="' + pda.mc + '"  pdawym="' + pda.wym + '"onclick="toEditUser(this)">&nbsp;&nbsp;&nbsp;';
        var schtml = '<img id="del" src="../../imgs/management/delete.png" title="删除" pda_id="' + pda.id + '" mc="' + pda.mc + '"   pdawym="' + pda.wym + '"onclick="deleteUser(this)">&nbsp;&nbsp;&nbsp;';

        var html = '';
        if (bianji){
            html += bjhtml;
        }
        if (shanchu){
            html += schtml;
        }

        tbody += ''+
            '<tr>' +
            '    <td>' + pda.id + '</td>' +
            '    <td>' + pda.mc + '</td>' +
            '    <td>' + pda.wym + '</td>' +
            '    <td>' + pda.bmmc + '</td>' +
            '    <td>' + ztname + '</td>' +
            '    <td>' + html + '</td>' +
            '</tr>';
    });
    $('#tbody').html(tbody);
}

function deleteUser(span) {
    var user_id = $(span).attr('pda_id');
    var str = '确定删除：' + $(span).attr('mc') + '？';
    layer.confirm(str, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        doDelete('pdas/management/pda/deletepda?pdaid=' + user_id, {}, function (response) {
            layer.closeAll();
            if (Boolean(response.meta.success)) {
                mymsg("删除PDA成功", 6);
                loadPDAManage();
            }
            else {
                mymsg(response.meta.message, 5);
            }

        });
    }, function () {
        layer.closeAll();
    });
}

/**
 * 添加机构
 */
function toAddDept() {
    emptyArgs();
    editDept();
}

function toEditUser(span) {
    emptyArgs();
    var user_id = $(span).attr('pda_id');
    editDept(user_id);
    $('#pda_id').val(user_id);
    $('#ssbmname').val($(span).attr('bmmc'));
    $('#ssbmbh').val($(span).attr('bmbh'));
    $('#addwym').val($(span).attr('pdawym'));
    $('#addmc').val($(span).attr('mc'));
    $('#zt').val($(span).attr('zt'));

}

function editDept(user_id) {
    //  alert(user_id);
    var url = 'pdas/management/pda/addpda';

    var title = "新增PDA信息";
    if (user_id != null) {
        title = "编辑PDA信息";
        url = 'pdas/management/pda/updatepda';

    } else {
        $('#ssbmname').val(config.name);
        $('#ssbmbh').val(config.bmbh);
    }
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
        },end: function () {
            $("#addNewDiv").hide();
        }
    });

    $('#confirmCancel').unbind('click');
    $('#confirmCancel').bind('click', function () {
        layer.closeAll();
    });
    $('#confirmSubmit').unbind('click');
    $('#confirmSubmit').bind('click', function () {

        if (String($('#addwym').val()).length < 4
            || String($('#addwym').val()).length > 20
        ) {
            mymsg('请输入长度范围为4~20的PDA唯一码', 5);
            return;
        }

        if (String($('#addmc').val()).length < 2
            || String($('#addmc').val()).length > 15
        ) {
            mymsg('请输入长度范围为2~15的PDA名称', 5);
            return;
        }


        doPost(url, JSON.stringify(readArgs()), function (response) {
            if (Boolean(response.meta.success)) {
                layer.closeAll();
                mymsg(title + "成功", 6);
                loadPDAManage();
            }
            else mymsg(response.meta.message, 5);
        });
    });


}


function emptyArgs() {

    $('#pda_id').val('');
    $('#addwym').attr('disabled', false);
    $('#addwym').val('');
    // $('#sfzhm').attr('disabled', false);
    $('#addmc').val('');
    $('#ssbmname').val('');

}


function readArgs() {

    return {
        id: $('#pda_id').val(),
        mc: $('#addmc').val(),
        bmbh: $('#ssbmbh').val(),
        wym: $('#addwym').val(),
        zt: $('#zt').val()

    }
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

