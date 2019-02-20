/**
 *标题：用户管理
 *说明:
 *作者：zjc
 *日期：2017/10/14
 */
var config = {
    id: ""
    , pId: ""
    , name: ""
    , navigateurl: ""
    , state: ""
    , levelnum: ""
    , sortcode: ""
    , icon: '../../imgs/management/dept012.png'
    , sfhxyw: ""
    , menu_type: ""
    , sfsqcb: ""
    , sfxzjy: ""
    , sysMenuDetails: ""
    ,type:''
}
$(function () {
    initView();     //初始化高度
    // initLayui();  //初始化layui
    loadAllMenu();//加载菜单树
    loadQX();
    $("#addNexMenu").click(function () {


        addMenu();
    });
});

function loadQX() {
    doGet('menus/menu/selectqx', {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        var html = "";
        $.each(response.data, function (index, bean) {
            html += "<option value=\"" + bean.key + "\">" + bean.value + "</option>"
        });
        $("#qxid").html(html);
    });
}

/**获取页面高度*/
function initView() {
    var height = $(window).height();   //获取浏览器可用高度
    $('.deptLeft').css('height', height); //菜单树的高度
}

function loadAllMenu() {
    doGet('menus/menu/selectAllMenu', {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        var zNodes = [];
        var zindex=0;
        $.each(response.data, function (index, dept) {
                if (dept.levelnum < 4) {
                    zNodes[zindex++] = {
                        id: dept.menuId
                        , pId: dept.parentid
                        , name: dept.name
                        , navigateurl: dept.navigateurl
                        , state: dept.state
                        , levelnum: dept.levelnum
                        , sortcode: dept.sortcode//排序
                        , icon: '../../imgs/management/dept012.png'
                        , sfhxyw: dept.sfhxyw
                        , menu_type: dept.menu_type
                        , sfsqcb: dept.sfsqcb
                        , sfxzjy: dept.sfxzjy
                        , type: dept.type
                    }
                }
            }
        );
        $.each(zNodes, function (index, node) {
            if (node.levelnum == 2) {
                node['open'] = true;
                return false;
            }
            if (node.id == 0) {
                node['icon'] = "../../imgs/management/dept00.png";
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
                idKey: "id",
                pIdKey: "pId",
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
        config.id = node.id;
        config.pId = node.pId;
        config.name = node.name;
        config.navigateurl = node.navigateurl;
        config.state = node.state;
        config.levelnum = node.levelnum;
        config.sortcode = node.sortcode;
        config.icon = node.icon;
        config.sfhxyw = node.sfhxyw;
        config.menu_type = node.menu_type;
        config.sfsqcb = node.sfsqcb;
        config.sfxzjy = node.sfxzjy;
        config.type = node.type;
        showCurMenuMessage();
    }
}

function showCurMenuMessage() {
    doGet("menus/menu/selectMenuDetail", {menuId: config.id}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        var data = response.data;
        var tbody = "";
        if (data.length > 0) {
            $.each(data, function (rowIndex, params) {
                tbody += '' +
                    '<tr>' +
                    '    <td>' + params.rn + '</td>' +
                    '    <td>' + params.qxName + '</td>' +
                    '    <td>' + params.menuName + '</td>' +
                    "<td>" +
                    " <img  src=\"../../imgs/management/edit.png\" title=\"修改\" " +
                    "onclick='edit(this)' uid = '" + params.id + "' menuid = '" + params.menuId + "' " +
                    "qxid = '" + params.qxid + "'  sfxzjy = '" + params.sfxzjy + "' parentid = '" + config.id + "' />" +
                    " <img  src=\"../../imgs/management/delete.png\" title=\"删除\" " +
                    "onclick='deleteMenu(this)' uid = '" + params.id + "'/></td>";
                tbody += '</tr>';
            });
        } else {
            tbody = "<tr> <td colspan='4'>无数据</td></tr>"
        }
        $('#tbody').html(tbody);
    });
}

function deleteMenu(span) {
    var id = $(span).attr("uid");
    var index = layer.confirm('确定删除当前菜单权限？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        doGet("menus/menu/deleteMenuQx", {menuId: id}, function (response) {
            if (!Boolean(response.meta.success)) {
                mymsg(response.meta.message, 5);
                return;
            }
            showCurMenuMessage();
            layer.close(index);
        });
    }, function () {
        layer.close(index);
    });
}

function addMenu() {
    var menuID = config.id;
    var type=config.type;
    var levelnum = config.levelnum;
    if (levelnum != 3) {
        var index = layer.confirm('当前菜单无法新增功能权限', {
            btn: ['确定'] //按钮
        }, function () {
            layer.close(index);
        });
        return;
    }
    var id = ""

    layer.open({
        shade: [0.5, '#000', false],
        type: 1,
        title: "新增菜单权限",
        area: ['400px', '400px'],
        content: $("#addNewDiv"),
        btn: ['保存', '取消'],
        btnAlign: 'c',
        yes: function (index, layero) {
            doPost("menus/menu/saveChange", JSON.stringify({
                id: id,
                menuId: menuID,
                qxid: $("#qxid").val(),
                qxName: $("#qxid").find("option:selected").text(),
                sfxzjy: $("input[name='sfxzjy']:checked").val()
            }), function (response) {
                if (!Boolean(response.meta.success)) {
                    mymsg(response.meta.message, 5);
                    return;
                }
                mymsg("新增成功", 6);
                showCurMenuMessage();
                layer.close(index);
            });
        }, btn1: function (index, layero) {

        }
        , cancel: function () {
            //右上角关闭回调

            //return false 开启该代码可禁止点击该按钮关闭
        }, end: function () {   //  end - 层销毁后触发的回调  无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。

        }, success: function (layero, index) {

        }
    });
}

function edit(span) {
    //clearDiv();  // 清空弹框

    //新增时清除用户名称
    var menuID = $(span).attr("menuid")
    var id = $(span).attr("uid")
    var qxid = $(span).attr("qxid")
    var sfxzjy = $(span).attr("sfxzjy")
    var parentid = $(span).attr("parentid")

    layer.open({
        shade: [0.5, '#000', false],
        type: 1,
        title: "修改菜单信息",
        area: ['400px', '400px'],
        content: $("#addNewDiv"),
        btn: ['保存', '取消'],
        btnAlign: 'c',
        yes: function (index, layero) {
            doPost("menus/menu/saveChange", JSON.stringify({
                id: id,
                menuId: menuID,
                parentid: parentid,
                qxid: $("#qxid").val(),
                qxName: $("#qxid").find("option:selected").text(),
                sfxzjy: $("input[name='sfxzjy']:checked").val()
            }), function (response) {
                if (!Boolean(response.meta.success)) {
                    layer.msg(response.meta.message, {icon: 5});
                    return;
                }
                showCurMenuMessage();
                layer.close(index);
            });
        }, btn1: function (index, layero) {

        }
        , cancel: function () {
            //右上角关闭回调

            //return false 开启该代码可禁止点击该按钮关闭
        }, end: function () {   //  end - 层销毁后触发的回调  无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。

        }, success: function (layero, index) {
            $("#qxid").val(qxid);
            $("input[name='sfxzjy']").each(function (index) {
                if ($("input[name='sfxzjy']").get(index).value == sfxzjy) {
                    $("input[name='sfxzjy']").get(index).checked = true;
                }
            });
        }
    });
}

/**
 * 点击选中显示机构的用户列表
 * 1、保存选中的部门编号
 * 1、加载列表数据
 * @param event
 * @param treeId
 * @param treeNode
 */
function zTreeOnClick(event, treeId, node) {
    config.id = node.id;
    config.pId = node.pId;
    config.name = node.name;
    config.navigateurl = node.navigateurl;
    config.state = node.state;
    config.levelnum = node.levelnum;
    config.sortcode = node.sortcode;
    config.icon = node.icon;
    config.sfhxyw = node.sfhxyw;
    config.menu_type = node.menu_type;
    config.sfsqcb = node.sfsqcb;
    config.sfxzjy = node.sfxzjy;
    config.type = node.type;
    showCurMenuMessage();
}

// $.each(JSON.stringify(config), function (key, value) {
//     $("#" + key).val(value);
// });
// $.each(config.sysMenuDetails, function(key, value) {
//     $("input[name='cdzqx'][value='"+value+"']").attr("checked", true);
// });
// $("input[name='sfhxyw'][value='"+config.sfhxyw+"']").prop("checked", "checked");
// $("input[name='sfxzjy'][value='"+config.sfxzjy+"']").prop("checked", "checked");
// $("input[name='sfsqcb'][value='"+config.sfsqcb+"']").prop("checked", "checked");

var laypage = null;

