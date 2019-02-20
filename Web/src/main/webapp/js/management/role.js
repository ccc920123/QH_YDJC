/**
 *标题：角色管理
 *说明:
 *作者：武伟
 *日期：2017/10/11
 */

var config_role = {
    bmbh: 360000000000,
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

    initLayui();  //初始化layui

    /**
     * 加载菜单
     */
    loadMenu();
    loadroleManage();//角色列表

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
        $("#roleName2").val('');
        $('#roleName2').removeAttr('disabled');
        $("#roleType02").removeAttr('disabled');
        $("#roleHierarchyDept02").removeAttr('disabled');
        $("#roleHierarchyAgency02").removeAttr('disabled');
        $('#menuContentDiv').find('input').each(function (index, input) {
            $(input).removeAttr('checked');
        });

        layer.open({
            shade: [0.5, '#000', false],
            type: 1,
            title: "新增角色信息",
            area: ['800px', '400px'],
            content: $("#addNewDiv"),
            btn: ['保存', '取消'],
            btnAlign: 'c',
            yes: function (index, layero) {
                var roleName = $("#roleName2").val();//角色名称
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
                addMenuRole(index);
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

function loadMenu() {

    // 加载菜单
    doGet("menus/menu/all", {}, function (response) {
        var menus = response.data;
        $.each(menus, function (i, menu) {
            if (menu.levelnum == 1) {
                $('#menuContentLi').append('<li>' + menu.name + '</li>');
                var div = '' +
                    '<div class="layui-tab-item" id="' + menu.menuId + '">' +
                    '   <table class="menuTable">' +
                    '   </table>' +
                    '</div>';
                $('#menuContentDiv').append(div);
                return true;
            }
        });
        $('#menuContentLi').find('li:first').addClass('layui-this');
        $('#menuContentDiv').find('div:first').addClass('layui-show');

        $.each(menus, function (index, menu) {
            if (menu.levelnum == 2) {
                var tr = '' +
                    '<tr>' +
                    '   <td rowspan="0" id="' + menu.menuId + '" levelnum="' + menu.levelnum + '">' + menu.name + '<br><input type="checkbox"  id="' + menu.menuId + '" pId="' + menu.parentid + '" levelnum="' + menu.levelnum + '"><span>全选</span></td>' +
                    '   <td></td>' +
                    '   <td></td>' +
                    '</tr>';
                $('#' + menu.parentid).find('table').append(tr);
                return true;
            }
        });
        $.each(menus, function (index, menu) {

            if (menu.levelnum == 3) {
                var rowspan = parseInt($('#' + menu.parentid).attr('rowspan'));
                if (rowspan == 0) {
                    $('#' + menu.parentid).next().remove();
                    $('#' + menu.parentid).after('<td rowspan="0" id="' + menu.menuId + '" levelnum="' + menu.levelnum + '"><input type="checkbox" id="' + menu.menuId + '" pId="' + menu.parentid + '" levelnum="' + menu.levelnum + '">' + menu.name + '</td>');

                } else {
                    $('#' + menu.parentid).parent().after('<tr><td rowspan="0" id="' + menu.menuId + '" levelnum="' + menu.levelnum + '"><input type="checkbox" id="' + menu.menuId + '" pId="' + menu.parentid + '" levelnum="' + menu.levelnum + '">' + menu.name + '</td></tr>');
                    var pId = $('#' + menu.parentid).attr('pId');
                    var rowspan2 = parseInt($('#' + pId).attr('rowspan'));
                    $('#' + pId).attr('rowspan', ++rowspan2);
                }
                $('#' + menu.parentid).attr('rowspan', ++rowspan);
                return true;
            }
        });
        //寻找子权限
        $.each(menus, function (index, menu) {

            if (menu.levelnum == 3) {
                if (menu.sysMenuDetails != null && menu.sysMenuDetails.length > 0) {
                    $.each(menu.sysMenuDetails, function (index, sysmenuDetail) {
                        $('#' + menu.menuId).after('<label rowspan="0" id="' + sysmenuDetail.qxid + '" levelnum="' + 4 + '"><input type="checkbox" id="' + sysmenuDetail.qxid + '" pId="' + menu.menuId + '" levelnum="' + 4 + '">' + sysmenuDetail.qxName + '</label>');
                    });

                }
                return true;
            }
        });
        $('#menuContentDiv').find('td').each(function (index, td) {
            var rowspan = parseInt($(td).attr('rowspan'));
            if (rowspan == 0) {
                $(td).attr('rowspan', 1);
            }
        });


        /**
         * chekbox 选中一类，不选一类
         */
        //二级菜单全选
        $('#menuContentDiv').find('input[levelnum=2]').each(function (index, input) {
            $(input).bind("click", function () {
                var threePid = $(input).attr("id");

                $('#menuContentDiv').find('input[levelnum=3]').each(function (index, inputThree) {

                    if ($(inputThree).attr('pId') == threePid) {
                        var fourPid = $(inputThree).attr("id");
                        if ($(input).is(':checked')) {
                            $(inputThree).prop('checked', 'checked');
                        } else {
                            $(inputThree).removeAttr('checked');
                        }
                        $('#menuContentDiv').find('input[levelnum=4]').each(function (index, inputFour) {
                            if ($(inputFour).attr('pId') == fourPid) {
                                if ($(input).is(':checked')) {
                                    $(inputFour).prop('checked', 'checked');
                                } else {
                                    $(inputFour).removeAttr('checked');
                                }

                            }
                        })

                    }
                });

            })
        });
        //三级菜单全选
        $('#menuContentDiv').find('input[levelnum=3]').each(function (index, input) {
            $(input).bind("click", function () {
                var fourPid = $(input).attr("id");

                $('#menuContentDiv').find('input[levelnum=4]').each(function (index, inputFour) {
                    if ($(inputFour).attr('pId') == fourPid) {
                        if ($(input).is(':checked')) {
                            $(inputFour).prop('checked', 'checked');
                        } else {
                            $(inputFour).removeAttr('checked');
                        }

                    }
                })
            })
        });
        //子权限勾选时选中三级菜单
        $('#menuContentDiv').find('input[levelnum=4]').each(function (index, input) {
            $(input).bind("click", function () {
                var threeId = $(input).attr("pId");


                $('#menuContentDiv').find('input[levelnum=3]').each(function (index, inputThree) {

                    if ($(inputThree).attr('id') == threeId) {
                        if ($(input).is(':checked')) {
                            $(inputThree).prop('checked', 'checked');
                        }

                    }
                });

            })
        });
    });

}


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
    var typeValue = $('#roleType option:selected').val();
        levelnum = $('#roleHierarchyDept02 option:selected').val();


    var params = {
        pId: config_role.pId,
        dept_id: config_role.dept_id,
        pageNo: config_role.pageNo,
        pageSize: config_role.pageSize,
        NAME: $('#roleName').val(),
        TYPE: typeValue,
        LEVELNUM: levelnum
    };
    $('#tbody').empty();

    doGet("management/Role/getRole", params, function (response) {
        console.log(response.data)
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
                var levelnum,type;
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
                tbody += '    <td>';
                tbody += '       <img id="edit" src="../../imgs/management/edit.png" title="编辑" levelnum="' + levelnum + '" levelnum2="' + secData.levelnum + '" name="' + secData.name + '" type="' + secData.type + '" state="' + secData.state + '" czryzh="' + secData.czryzh + '" role_id="' + secData.role_id + '" onclick="toEditDept(this)">&nbsp;&nbsp;&nbsp;';
                tbody += '       <img id="del" src="../../imgs/management/delete.png" title="删除" name="' + secData.name + '" role_id="' + secData.role_id + '" onclick="deleteMenuRole(this)">';
                tbody += '    </td>';
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

function addMenuRole(index2) {
    /**
     * chekbox 选中一类，不选一类
     * <input type="checkbox" id="57" pid="55" levelnum="4">
     */
    var i = 0;
    var j = 0;
    $('#menuContentDiv').find('input[levelnum=3]').each(function (index, input) {
        if ($(input).is(':checked')) {
            var value = $(input).attr("id");
            var value2 = $(input).attr("pid");
            array[i++] = value;
            array2[j++] = value2;
            array[i++] = value2;
        }
    });

    var param = {
        id: array2.unique3()
    };

    var type;
    var roleName = $("#roleName2").val();//角色名称
    var roleState = $("#roleState option:selected").val();//角色状态
    var roleType = $("#roleType02 option:selected").val();//角色类型
    var roleLevel;//角色层级


    if (roleType == 'dept02') {
        roleLevel = $("#roleHierarchyDept02 option:selected").val();
        type = 1;
    } else if (roleType == 'agency02') {
        roleLevel = $("#roleHierarchyAgency02 option:selected").val();
        type = 2;
    } else {
        type = 0;
    }
    if (array.length == 0) {
        layer.msg("新增角色权限不能为空！", {icon: 5});
        return false;
    }
    var params2;
    doPost("menus/menu/getMenuId", JSON.stringify(param), function (response) {
        if (response.data.length > 0) {
            $.each(response.data, function (index, row) {
                array[i++] = row + "";
            })
        }
        array = array.unique3();
        params2 = {
            id: array,
            role: {"name": roleName, "levelnum": roleLevel, "type": type, "state": roleState}
        };

        doPost("management/Role/addMenuRole", JSON.stringify(params2), function (response) {
            console.log(response.meta.message)
            var layerMsg = "";
            var iconNum;

            if (response.data == "1") {
                layerMsg = "增加角色成功";
                iconNum = 6;
            } else if (response.data == "0") {

                layerMsg = "角色添加失败!";
                iconNum = 5;
            } else if (response.data = "-1") {
                layerMsg = "角色名重复！";
                iconNum = 5;
            } else {
                layerMsg = "角色添加失败!";
                iconNum = 5;
            }

            // 关闭
            layer.close(index2);

            // 弹出提示
            mymsg(layerMsg, iconNum);

            loadroleManage();

        });
    });


}

function getMenuRole(roleId) {
    //获取角色信息
    doGet("management/Role/menuRoleList", {"roleId": roleId}, function (response) {
        if (response.data == null || response.data.length == 0 || response.data.length == 1) {
            return true;
        }


        $('#menuContentDiv').find('input[levelnum=4]').each(function (index, input) {
            var id = $(input).attr('id');
            $.each(response.data, function (rowIndex, row) {
                if (rowIndex == 0) {
                    return true;
                }
                if (id == row[1]) {
                    $(input).prop('checked', 'checked');
                    return true;
                }
            });
        })

    });
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

    $('#roleName2').val(name);
    if (state == 1) {
        $('#roleState option:selected').html("正常");
    } else {
        $('#roleState option:selected').html("停用");
    }
    console.log(type)
    if (type == 1) {
        $('#roleType02 option:first-child').prop('selected', 'selected');
        $("#roleHierarchyDept02").css("display", "block").siblings().css("display", "none");

    } else {
        $('#roleType02 option:nth-child(2)').prop('selected', 'selected');
        $("#roleHierarchyAgency02").css("display", "block").siblings().css("display", "none");
    }
    //添加数据到选择框
    switch (levelnum2) {
        case 1:
            // $('#roleHierarchyDept02 option:first-child').attr('selected', 'selected');
            $('#roleHierarchyDept02 option:selected').html("总队");
            break;
        case 2:
            // $('#roleHierarchyDept02 option:nth-child(2)').attr('selected', 'selected');
            $('#roleHierarchyDept02 option:selected').html("支队");
            break;
        case 3:
            // $('#roleHierarchyDept02 option:nth-child(3)').attr('selected', 'selected');
            $('#roleHierarchyDept02 option :selected').html("大队");
            break;

    }
    $('#roleName2').attr("disabled", "disabled");
    $("#roleType02").attr("disabled", "disabled");
    $("#roleHierarchyDept02").attr("disabled", "disabled");
    $("#roleHierarchyAgency02").attr("disabled", "disabled");
    layer.open({
        shade: [0.5, '#000', false],
        type: 1,
        title: "编辑角色信息",
        area: ['800px', '400px'],
        content: $("#addNewDiv"),
        btn: ['保存', '取消'],
        btnAlign: 'c',
        yes: function (index, layero) {
           // modifyMenuRole(roleId, index);
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

function deleteMenuRole(obj) {

    var roleId = $(obj).attr('role_id');
    var name = $(obj).attr('name');
    var msg = '确定删除角色' + name + '吗？';

    layer.open({
        content: msg
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //按钮【按钮一】的回调
            doGet("management/Role/deleteMenuRole", {"roleId": roleId}, function (response) {


                var layerMsg = "";
                var iconNum;

                if (response.data == "1") {
                    layerMsg = "删除角色成功";
                    iconNum = 6;
                } else {

                    layerMsg = response.meta.message;
                    iconNum = 5;
                }

                // 关闭
                layer.close(index);

                // 弹出提示
                mymsg(layerMsg, iconNum);

                loadroleManage();
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

function modifyMenuRole(roleId, index2) {
    /**
     * chekbox 选中一类，不选一类
     * <input type="checkbox" id="57" pid="55" levelnum="4">
     */
    var i = 0;
    var j = 0;
    $('#menuContentDiv').find('input[levelnum=3]').each(function (index, input) {
        if ($(input).is(':checked')) {
            var value = $(input).attr("id");
            var value2 = $(input).attr("pid");
            array[i++] = value;
            array2[j++] = value2;
            array[i++] = value2;
        }
    });

    var param = {
        id: array2.unique3()
    };

    var type;
    var roleName = $("#roleName2").val();//角色名称
    var roleState = $("#roleState option:selected").val();//角色状态
    var roleType = $("#roleType02 option:selected").val();//角色类型
    var roleLevel;//角色层级


    if (roleType == 'dept02') {
        roleLevel = $("#roleHierarchyDept02 option:selected").val();
        type = 1;
    } else if (roleType == 'agency02') {
        roleLevel = $("#roleHierarchyAgency02 option:selected").val();
        type = 2;
    } else {
        type = 0;
    }
    if (array.length == 0) {
        layer.msg("角色权限不能为空！", {icon: 5});
        return false;
    }
    var params2;
    doPost("menus/menu/getMenuId", JSON.stringify(param), function (response) {
        if (response.data.length > 0) {
            $.each(response.data, function (index, row) {
                array[i++] = row + "";
            })
        }
        array = array.unique3();
        params2 = {
            id: array,
            role: {"name": roleName, "levelnum": roleLevel, "type": type, "state": roleState, "role_id": roleId}
        };

        doPost("management/Role/modifyMenuRole", JSON.stringify(params2), function (response) {
            var layerMsg = "";
            var iconNum;

            if (response.data == "1") {
                layerMsg = "角色修改成功！";
                iconNum = 6;
            } else {

                layerMsg = "角色修改失败！";
                iconNum = 5;
            }

            // 关闭
            layer.close(index2);

            // 弹出提示
            mymsg(layerMsg, iconNum);

            loadroleManage();
        });
    });
}