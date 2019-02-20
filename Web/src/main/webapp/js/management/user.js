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

    if (justPressionBy("1103")){
        $("#queryBtn").removeAttr("style");
    } else {
        $("#queryBtn").css("display", "none");
    }
    if (justPressionBy("1101")){
        $("#addNew").removeAttr("style");
    } else {
        $("#addNew").css("display", "none");
    }

    loadAllDept();
    loadDeptManage();
    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadDeptManage();
    });

    $('#addNew').click(function () {
        toAddDept();
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
function reflush() {
    loadDeptManage();
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
    loadDeptManage();
}


/**
 * 加载页面
 */
function loadDeptManage() {
    $('#tbody').html('');
    pageClose();
    loadDept();  //加载 部门管理
}

function loadDept() {
    var checked = document.getElementById('includeLevDown').checked;
    var bhxj = "0";
    if (checked) bhxj = "1";
    $('#tbody').empty();
    doGet('users/management/user/getUsers', {
        yhxm: $('#searchYhxm').val()
        , dlzh: $('#searchDlzh').val()
        , bhxj: bhxj
        , jgbh: config.bmbh
        , pageindex: config.pageNo
        , pagesize: config.pageSize
    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg('没有查到相关信息', 5);
            return;
        }
        loadDeptTable(response.data);
    });
}




/**
 * dept加载到页面
 * @param depts
 */
function loadDeptTable(depts) {
    var tbody = '';

    var bianji = justPressionBy("1102");
    var shanchu = justPressionBy("1104");
    var chongzhi = justPressionBy("1105");

    $.each(depts, function (rowIndex, dept) {
        if (dept.loginname == '') {
            pageOpen(dept.rn, function () {
                loadDept();
            });
            return true;
        }
        // if (depts.length - 1 == rowIndex){
        //     return false;
        // }
        var ztname = dept.zt == "1" ? "正常" : dept.zt == "2" ? "锁定" : "停用";
        var bjhtml = '<img id="edit" src="../../imgs/management/edit.png" title="编辑" user_id="' + dept.user_id + '"  loginname="' + dept.loginname + '" onclick="toEditUser(this)">&nbsp;&nbsp;&nbsp;';
        var schtml = '<img id="del" src="../../imgs/management/delete.png" title="删除" user_id="' + dept.user_id + '" loginname="' + dept.loginname + '"  onclick="deleteUser(this)">&nbsp;&nbsp;&nbsp;';
        var czhtml = '<img id="del" src="../../imgs/management/shezhi.png" title="重置密码" user_id="' + dept.user_id + '" loginname="' + dept.loginname + '"  onclick="RestPwd(this)">';

        var html = '';
        if (bianji){
            html += bjhtml;
        }
        if (shanchu){
            if (dept.loginname == "admin") {
            } else {
                html += schtml;
            }
        }
        if (chongzhi){
            html += czhtml;
        }

        tbody += '' +
            '<tr>' +
            '    <td>' + dept.realname + '</td>' +
            '    <td>' + dept.loginname + '</td>' +
            '    <td>' + dept.ssbmname + '</td>' +
            '    <td>' + dept.rolesname + '</td>' +
            '    <td>' + ztname + '</td>' +
            '    <td>' + html + '</td>' +
            '</tr>';
    });
    $('#tbody').html(tbody);

}

function RestPwd(span) {
    var user_id = $(span).attr('user_id');
    var loginname = $(span).attr('loginname');
    var str = '确定重置该账号：' + loginname + ' 的密码？';
    layer.confirm(str, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        doGet('users/management/user/ResetPassWord?user_id=' + user_id, {}, function (response) {
            layer.closeAll();
            if (Boolean(response.meta.success)) {
                mymsg("重置密码成功", 6);
            }
            else {
                mymsg(response.meta.message, 5);
            }
        });
    }, function () {
        layer.closeAll();
    });
}

function deleteUser(span) {
    var user_id = $(span).attr('user_id');
    var loginname = $(span).attr('loginname');
    var str = '确定删除账号：' + loginname + '？';
    layer.confirm(str, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        doDelete('users/management/user/delete?user_id=' + user_id, {}, function (response) {
            layer.closeAll();
            if (Boolean(response.meta.success)) {
                mymsg("删除账号成功", 6);
                loadDeptManage();
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
    editDept();
}

function toEditUser(span) {

    var user_id = $(span).attr('user_id');
    editDept(user_id);
}

function editDept(user_id) {
    //  alert(user_id);
    var title = "新增用户信息";
    if (user_id != null) {
        title = "编辑用户信息";
    } else {
        user_id="";

    }
   window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['1000px', '600px'],
        title: title,
        // maxmin: true,
        content:'/Web/pages/management/addUser.html?user_id='+user_id+'&ssbmbh='+config.bmbh+'&ssbmname='+encodeURI(encodeURI(config.name))
        , zIndex: layer.zIndex //重点1
        , success: function (layero,index) {

        }
    });

}







// //IP限制点击事件
// function clickip(obj) {
//     if ($("#kqipxz").val() == "1") {
//         $("#kqipxz").val("0");
//     }
//     else {
//         $("#kqipxz").val("1");
//     }
//     if ($("#kqipxz").val() == "1") {
//         $("#kqipxz").val("1");
//     }
//     else {
//         $("#kqipxz").val("0");
//     }
// }




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



