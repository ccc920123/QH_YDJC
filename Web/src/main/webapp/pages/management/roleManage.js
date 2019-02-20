/**
 *标题：角色管理
 *说明:
 *作者：武伟
 *日期：2017/10/11
 */

var config_role = {
    bmbh: 630000000000,
    pId: 0,
    dept_id: 1,
    pageNo: 1,
    pageSize: 10,
    dateType: 1   // 1 包含下级数据

};

// 点击的TAB的ID
var TAB_ID = "";

var array = [];
var array2 = [];
$(function () {
    justPression();
    initLayui();  //初始化layui

    /**
     * 加载菜单
     */
    loadroleManage();//角色列表

    if (justPressionBy("1112")){
        $("#queryBtn").removeAttr("style");
    } else {
        $("#queryBtn").css("display", "none");
    }
    if (justPressionBy("1110")){
        $("#addNew").removeAttr("style");
    } else {
        $("#addNew").css("display", "none");
    }

    $("#roleType").change(function () {

        // var option = $("option:selected", this).val();
        var option = $("#roleType option:selected").val();

        if (option == 'dept') {
            $("#roleHierarchyDept").css("display", "block").siblings().css("display", "none");

        } else if (option == 'agency') {
            $("#roleHierarchyAgency").css("display", "block").siblings().css("display", "none");

        } else if (option == 'alldept') {
            $("#all").css("display", "block").siblings().css("display", "none");
        }

    });
    $("#roleType02").change(function () {

        var option = $("option:selected", this).val();

        if (option == 'dept02') {
            $("#roleHierarchyDept02").css("display", "block").siblings().css("display", "none");

        } else if (option == 'agency02') {
            $("#roleHierarchyAgency02").css("display", "block").siblings().css("display", "none");

        } else if (option == 'alldept') {
            $("#all").css("display", "block").siblings().css("display", "none");
        }

    });

    //新增
    $("#addNew").click(function () {

        //clearDiv();  // 清空弹框

        //新增时清除用户名称
        $("#roleName").val('');
        $('#roleName').removeAttr('disabled');
        $("#roleType").removeAttr('disabled');
        $("#roleLev").removeAttr('disabled');
        $("#roleState").removeAttr('disabled');

        layer.open({
            shade: [0.5, '#000', false],
            type: 1,
            title: "新增角色信息",
            area: ['800px', '400px'],
            content: $("#addNewDiv"),
            btn: ['保存', '取消'],
            btnAlign: 'c',
            yes: function (index, layero) {
                var roleName = $("#roleName").val();//角色名称
                var length = roleName.replace(/[\u0391-\uFFE5]/g, "aa").length;  //先把中文替换成两个字节的英文，在计算长度
                if (length > 20) {
                    layer.msg("角色名称长度不超过20字符", {icon: 5});
                    $("#roleName2").val("");
                    return;
                } else if (length < 2) {
                    layer.msg("角色名称长度不能少于2个字符", {icon: 5});
                    $("#roleName2").val("");
                    return;
                }
                addRole(index);
            }, btn1: function (index, layero) {
                //按钮【按钮二】的回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
            , cancel: function () {
                //右上角关闭回调

                //return false 开启该代码可禁止点击该按钮关闭
            }, end: function () {   //  end - 层销毁后触发的回调  无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。

            }, success: function (layero, index) {

            }
        });

    });


    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadroleManage();
    });

});
var element;

/**
 * 加载菜单
 * <div class="layui-tab">
 *  <ul class="layui-tab-title">
 *      <li class="layui-this">网站设置</li>
 *      <li>用户管理</li>
 *      <li>权限分配</li>
 *      <li>商品管理</li>
 *      <li>订单管理</li>
 *  </ul>
 *  <div class="layui-tab-content">
 *      <div class="layui-tab-item layui-show">内容1</div>
 *      <div class="layui-tab-item">内容2</div>
 *      <div class="layui-tab-item">内容3</div>
 *      <div class="layui-tab-item">内容4</div>
 *      <div class="layui-tab-item">内容5</div>
 *  </div>
 * </div>
 */


/**初始化layui*/
function initLayui() {
    layui.use('element', function () {   //tab切换
        element = layui.element;
    });
    layui.use('laypage', function () {
        laypage = layui.laypage;
    });
}


/**
 * 角色列表
 */
function loadroleManage() {
    pageClose();
    loadrole();  //加载 部门管理
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
        , limit: config_role.pageSize
        , count: count //数据总数，从服务端得到
        , curr: config_role.pageNo
        , jump: function (obj, first) {
            //obj包含了当前分页的所有参数，比如：
            // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            // console.log(obj.limit); //得到每页显示的条数

            //首次不执行
            if (!first) {
                //do something
                config_role.pageNo = obj.curr;
                callback();
                return;
            }
        }
    });
    config_role.pageNo = 1;
}

function pageClose() {
    $('#page').css('display', 'none');
}

var laypage = null;


function loadrole() {
    var levelnum;
    var typeValue = $('#search_roleType option:selected').val();
    levelnum = $('#search_roleLev option:selected').val();


    var params = {
        pId: config_role.pId,
        dept_id: config_role.dept_id,
        pageNo: config_role.pageNo,
        pageSize: config_role.pageSize,
        NAME: $('#search_roleName').val(),
        TYPE: typeValue,
        LEVELNUM: levelnum
    };
    $('#tbody').empty();

    doGet("management/Role/getRole", params, function (response) {
        console.log(response.data)

        var shouquan = justPressionBy("11113");
        var bianji = justPressionBy("1111");
        var shanchu = justPressionBy("1113");

        var conut = 0;
        if (response.data != null && response.data.length != 0) {
            count = response.data[0]['total'];
        }

        if (response.data == null || response.data.length == 0) {
            layer.msg("没有查到相关信息", {icon: 5});
            return;
        }

        var data = [];
        data[0] = response.data;
        var tbody = '';

        $.each(data, function (rowIndex, role) {

            $.each(role, function (col_index, secData) {
                var levelnum, type;
                switch (secData.levelnum) {
                    case 1:
                        levelnum = "总队";
                        break;
                    case 2:
                        levelnum = "支队";
                        break;
                    case 3:
                        levelnum = "大队";
                        break;

                }
                switch (secData.type) {
                    case 1:
                        type = "业务管理";
                        break;
                    case 2:
                        type = "系统管理";
                        break;
                    case 4:
                        type = "审计管理";
                        break;
                    case 3:
                        type = "安全管理";
                        break;

                }
                tbody += '<tr>';
                tbody += '    <td>' + secData.name + '</td>';
                tbody += '    <td>' + type + '</td>';
                tbody += '    <td>' + levelnum + '</td>';
                tbody += '    <td>' + (secData.state == 1 ? '正常' : '停用') + '</td>';
                tbody += '    <td>' + secData.czryzh + '</td>';
                tbody += '    <td>' + secData.czsj + '</td>';

                var sqhtml ='       <img id="shezhi" src="../../imgs/management/shezhi.png" title="授权" type="' + secData.type + '" role_id="' + secData.role_id + '" onclick="setMenu(this)">';
                var bjhtml = '       <img id="edit" src="../../imgs/management/edit.png" title="编辑" levelnum="' + levelnum + '" levelnum2="' + secData.levelnum + '" name="' + secData.name + '" type="' + secData.type + '" state="' + secData.state + '" czryzh="' + secData.czryzh + '" role_id="' + secData.role_id + '" onclick="toEditDept(this)">&nbsp;&nbsp;&nbsp;';
                var schtml = '       <img id="del" src="../../imgs/management/delete.png" title="删除" name="' + secData.name + '" role_id="' + secData.role_id + '" onclick="deleteMenuRole(this)">';

                var html = '';
                if (shouquan){
                    html += sqhtml;
                }
                if (bianji){
                    html += bjhtml;
                }
                if (shanchu){
                    html += schtml;
                }

                tbody += '    <td>' + html + '</td>';
                tbody += '</tr>';
            });
        });

        $('#tbody').html(tbody);

        pageOpen(count, function () {
            loadrole();
        });

    });
    /**新增*/
}

Array.prototype.unique3 = function () {
    var res = [];
    var json = {};
    for (var i = 0; i < this.length; i++) {
        if (!json[this[i]]) {
            res.push(this[i]);
            json[this[i]] = 1;
        }
    }
    return res;
}

/**
 * 授权
 * @param span
 */
function setMenu(span) {
    var roleid = $(span).attr("role_id")
    var type = $(span).attr("type");
    getMenuRole(roleid, type);
    layer.open({
        shade: [0.5, '#000', false],
        type: 1,
        title: "授权",
        area: ['400px', '400px'],
        content: $("#addNewDiv2"),
        btn: ['保存', '取消'],
        btnAlign: 'c',
        yes: function (index, layero) {


            // modifyMenuRole(roleId, index);
            saveMenuIDandRole(roleid, index);
        }, btn1: function (index, layero) {
            //按钮【按钮二】的回调

            //return false 开启该代码可禁止点击该按钮关闭
        }
        , cancel: function () {
            //右上角关闭回调

            //return false 开启该代码可禁止点击该按钮关闭
        }, end: function () {   //  end - 层销毁后触发的回调  无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。

        }, success: function (layero, index) {

        }
    });

}

/**
 * 授权保存
 * @param roleid
 * @param index
 */
function saveMenuIDandRole(roleid, index) {
    var menuIDs = onCheck();
    doPost("management/Role/modifyMenuRole", JSON.stringify({
        roleId: roleid,
        menuIds: menuIDs
    }), function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
        } else {
            // 关闭
            layer.close(index);
            // 弹出提示
            mymsg("授权成功", 6);
        }
    });
}

/**
 * 新加角色
 * @param index2
 */
function addRole(index2) {


    var roleName = $("#roleName").val();//角色名称
    var roleState = $("#roleState option:selected").val();//角色状态
    var roleType = $("#roleType option:selected").val();//角色类型
    var roleLevel = $("#roleLev option:selected").val();//角色类型

    var params2 = {
        name: roleName,
        levelnum: roleLevel,
        type: roleType,
        state: roleState

    }
    doPost("management/Role/addRole", JSON.stringify(params2), function (response) {

        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
        } else {
            // 关闭
            layer.close(index2);
            // 弹出提示
            mymsg("增加成功", 6);
            loadroleManage();
        }
    });
}

function getMenuRole(roleId, type) {
    //获取角色信息
    doGet("management/Role/menuRoleList", {
        roleId: roleId,
        type: type
    }, function (response) {
        if (response.data == null || response.data.length == 0 || response.data.length == 1) {
            return true;
        }
        var zNodes = [];
        var zindex = 0
        $.each(response.data, function (index, dept) {
                zNodes[zindex++] = {
                    id: dept.ID
                    , pId: dept.PID
                    , name: dept.NAME
                    , checked: dept.FLAG==1?true:false
                    , icon: '../../imgs/management/dept012.png'
                    , open: true

                }
            }
        );

        loadZtree(zNodes);

    });
}

function loadZtree(zNodes) {
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: null
            }
        },
        key: {
            name: "name"
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {"Y": "ps", "N": "ps"}
        }
        ,
        callback: {
            //onClick: zTreeOnClick
            // beforeCheck: true,
            // onCheck: onCheck
        }
    };
    $.fn.zTree.init($("#deptTree"), setting, zNodes);

}

function onCheck(e, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("deptTree"),
        nodes = treeObj.getCheckedNodes(true);
    var menuids = new Array();
    for (var i = 0; i < nodes.length; i++) {
        menuids.push(nodes[i].id);
    }
    return menuids;
}

// var roleId;
function toEditDept(obj) {
    var name = $(obj).attr('name');
    var roleId = $(obj).attr('role_id');
    var state = $(obj).attr('state');
    var type = $(obj).attr('type');
    var levelnum = $(obj).attr('levelnum');
    var levelnum2 = $(obj).attr('levelnum2');
    var czryzh = $(obj).attr('czryzh');

    $('#roleName').val(name);
    if (state == 1) {
        $('#roleState option:selected').html("正常");
    } else {
        $('#roleState option:selected').html("停用");
    }
    $("#roleType").val(type);
    $("#roleLev").val(levelnum2);
    //添加数据到选择框
    layer.open({
        shade: [0.5, '#000', false],
        type: 1,
        title: "编辑角色信息",
        area: ['800px', '400px'],
        content: $("#addNewDiv"),
        btn: ['保存', '取消'],
        btnAlign: 'c',
        yes: function (index, layero) {
            modifyRole(roleId, index);
        }, btn1: function (index, layero) {
            //按钮【按钮二】的回调
            //return false 开启该代码可禁止点击该按钮关闭
        }
        , cancel: function () {
            //右上角关闭回调
            //return false 开启该代码可禁止点击该按钮关闭
        }, end: function () {   //  end - 层销毁后触发的回调  无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。

        }, success: function (layero, index) {

        }
    });


}

function modifyRole() {
    var roleName = $("#roleName").val();//角色名称
    var roleState = $("#roleState option:selected").val();//角色状态
    var roleType = $("#roleType option:selected").val();//角色类型
    var roleLevel = $("#roleLev option:selected").val();//角色类型

    var params2 = {
        name: roleName,
        levelnum: roleLevel,
        type: roleType,
        state: roleState

    }
    doPost("management/Role/modifyRole", JSON.stringify(params2), function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
        } else {
            // 关闭
            layer.close(index2);
            // 弹出提示
            mymsg("修改成功", 6);
            loadroleManage();
        }
    });
}

function deleteMenuRole(obj) {

    var roleId = $(obj).attr('role_id');
    var name = $(obj).attr('name');
    var msg = '确定删除角色' + name + '吗？';

    layer.open({
        content: msg
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //按钮【按钮一】的回调
            doGet("management/Role/deleteMenuRole", {roleId: roleId}, function (response) {

                if (!Boolean(response.meta.success)) {
                    mymsg(response.meta.message, 5);
                } else {
                    // 关闭
                    layer.close(index);
                    // 弹出提示
                    mymsg("删除成功", 6);
                    loadroleManage();
                }
            });


        }
        , btn2: function (index, layero) {
            //按钮【按钮二】的回调

            //return false 开启该代码可禁止点击该按钮关闭
        }, cancel: function () {
            //右上角关闭回调

            //return false 开启该代码可禁止点击该按钮关闭
        }
    });

}
