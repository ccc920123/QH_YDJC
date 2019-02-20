/**
 *标题：部门管理
 *说明:
 *作者：武伟
 *日期：2017/10/11
 */
var config = {
    id: 1
    , parentid: 0
    , bmbh: '630100000000'//360000000000
    , name: '青海省公安厅交通警察支队'
    , levelnum: "11"
    , pageNo: 1
    , pageSize: 10
    , typeList: null
    , deptToAdd: null
    , type: 0
};

$(function () {
    justPression();
    initView();     //初始化高度
    initLayui();  //初始化layui

    if (justPressionBy("1108")){
        $("#queryBtn").removeAttr("style");
    } else {
        $("#queryBtn").css("display", "none");
    }
    if (justPressionBy("1106")){
        $("#addNew").removeAttr("style");
    } else {
        $("#addNew").css("display", "none");
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
        loadDeptDetail();
    });

    // layer.msg("layer")
    //  $('#queryBtn').click();

    /**
     * 新增
     */
    $('#addNew').click(function () {
        //toAddDept();
        emptyArgs();
        /**
         * 加载type代码配置
         */
        loadTypeList();
        editDept();
    });




    /**`
     * 输入限制
     */
    $('#bmbh').keyup(function () {
        $('#bmbh').val($('#bmbh').val().replace(/[^\d]/g, '').substring(0, 12));
    });
    /**`
     * 输入限制
     */
    $('#code').keyup(function () {
        $('#code').val($('#code').val().replace(/[^\d]/g, '').substring(0, 10));
    });

    $('#lxrdh').keyup(function () {
        $('#lxrdh').val($('#lxrdh').val().replace(/[^\d]/g, '').substring(0, 13));
    });

    $('#xzqh').keyup(function () {
        $('#xzqh').val($('#xzqh').val().replace(/[^\d]/g, '').substring(0, 12));
    })

});


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



/**
 * type代码、层级
 */
function loadTypeList() {
    doGet('management/dept/typeList', null, function (response) {
        config.typeList = response.data;

        var option = '';
        $.each(response.data, function (rowIndex, row) {
            if (rowIndex == 0) {
                return true;
            }
            //总队，可以创建大队支队，只能创建下级部门的
            if (parseInt(row[3])>parseInt(config.levelnum)){
                option += '<option value="' + row[3] + '">' + row[2] + '</option>';
            }

        });
        option += '<option value=""></option>';
        $('#levelnum').html(option);
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

    if(zNodes.length > 0) {

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
        $('#parentid').val(config.id);
        $('#type').val('1');
        $('#zt').val(1);
        $('#sfzsbm').val(0);
        $('#zsbmbhs').val(config.bmbh);
        $('#sjzdbmbh').val(config.bmbh);
    }

    loadDeptDetail();
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

    loadDeptDetail();
}

/**
 * 加载dept列表
 */
function loadDeptDetail() {
    $('#tbody').html('');
    pageClose();
    var checked = document.getElementById('includeLevDown').checked;
    if (checked) {
        loadDeptDetailLevDown();
    } else {
        loadDeptDetailOne();
    }
}

/**
 * 包含下级dept列表
 */
function loadDeptDetailLevDown() {
    doGet('management/dept/selectNextLev', {
        bmbh: config.bmbh
        , pageNo: config.pageNo
        , pageSize: config.pageSize
        , searchBh: $('#searchBh').val()
        , searchName: $('#searchName').val()
    }, function (response) {
        if (response.data.length == 1) {
            // layer.msg('没有查到相关信息', {icon: 5});
            mymsg('没有查到相关信息', 5);
            return;
        }
        loadDeptTable(response.data);
    });
}

/**
 * 不包含下级dept
 */
function loadDeptDetailOne() {
    doGet('management/dept/select', {
        bmbh: config.bmbh
        , searchBh: $('#searchBh').val()
        , searchName: $('#searchName').val()
    }, function (response) {
        if (response.data == null){
            mymsg('查询信息不存在', 5);
            return;
        }
        var data = [];
        data[0] = response.data;
        loadDeptTable(data);
    });
}

/**
 * dept加载到页面
 * @param depts
 */
function loadDeptTable(depts) {
    if (depts.length == 0) {
        return;
    }

    var bianji = justPressionBy("1107");
    var shanchu = justPressionBy("1109");

    var tbody = '';
    $.each(depts, function (rowIndex, dept) {
        if (dept == null) {
            return true;
        }
        if (parseInt(dept.id) == 0) {
            pageOpen(dept.rn, function () {
                loadDeptDetail();
            });
            return true;
        }

        var bjhtml ='<img id="edit" src="../../imgs/management/edit.png" title="编辑" bmbh="' + dept.bmbh + '" onclick="toEditDept(this)">&nbsp;&nbsp;&nbsp;';
        var schtml ='<img id="del" src="../../imgs/management/delete.png" title="删除" name="' + dept.name + '" bmbh="' + dept.bmbh + '" onclick="deleteDept(this)">';

        var html = '';
        if (bianji){
            html += bjhtml;
        }
        if (shanchu){
            html += schtml;
        }
        tbody += '' +
            '<tr>' +
            '    <td>' + dept.bmbh + '</td>' +
            '    <td>' + dept.name + '</td>' +
            '    <td>' + dept.lxr + '</td>' +
            '    <td>' + dept.lxrdh + '</td>' +
            '    <td>' + html + '</td>' +
            '</tr>';
    });
    $('#tbody').html(tbody);


}

function deleteDept(span) {
    var name = $(span).attr('name');
    var bmbh = $(span).attr('bmbh');
    var str = '确定删除：' + name + '（' + bmbh + '）？';
    layer.confirm(str, {
        btn: ['确定', '取消'] //按钮
    }, function () {

        doDelete('management/dept/delete?bmbh=' + bmbh, {}, function (response) {
            layer.closeAll();
            if (Boolean(response.meta.success)) {
                mymsg("删除部门成功", 6);
                loadAllDept();
                // $('#queryBtn').click();
                return;
            }
            else {
                mymsg(response.meta.message, 5);
            }
        });

    }, function () {
        layer.closeAll();
    });
}





var deptInfoTitle = '';
var isAdd = 0;

function toEditDept(span) {
    emptyArgs();
    var bmbh = $(span).attr('bmbh');
    deptInfoTitle = '编辑部门信息';
    isAdd = 0;
    editDept(bmbh);
}

function editDept(bmbh) {

    if (bmbh != null) {

        loadDeptInfo(bmbh);

    } else {
        $('#prent').val(config.name);
        $('#bmbh').removeAttr('disabled');
        $('#bmbh').val('');
    }

    layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['800px', '400px'],
        title: deptInfoTitle,
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


        $('#message').html('');
        if (!/[0-9]{1,12}/.test($('#bmbh').val())) {
            mymsg('请输入正确的部门编号（1-12位数字）', 5);
            return;
        }
        var bmmc = String($('#name').val());
        if (bmmc == null || bmmc.length < 4 || bmmc.length > 50) {
            mymsg('请输入部门名称（4-50位）', 5);
            return;
        }

        var xzqh = $('#xzqh').val();
        if (xzqh != null && xzqh.length != 0 && !/[0-9]{1,6}/.test(xzqh)) {
            mymsg('行政区划格式为（6位数字）', 5);
            return;
        }
        var lxr = $('#lxr').val();
        if (lxr == null || lxr.length < 2 || lxr.length > 20) {
            mymsg('联系人为必填项，长度（2-20位）', 5);
            return;
        }
        var lxrdh = $('#lxrdh').val();
        if (lxrdh == null || !/[0-9]{8,15}/.test(lxrdh)) {
            mymsg('请输入正确的电话号码', 5);
            return;
        }
        var bz = $('#bz').text();
        if (bz != null && bz.length > 500) {
            mymsg('备注不能大于500位', 5);
            return;
        }
        var fzjg = $('#fzjg').val();
        if (fzjg == null || fzjg.length == 0) {
            mymsg('发证机构不能为空', 5);
            return null;
        }

        var code = $('#code').val();
        if (code != null && code.length > 20) {
            mymsg('机构代码不能大于20位', 5);
            return;
        }

        doPut('management/dept/update', JSON.stringify(readArgs()), function (response) {
            if (Boolean(response.meta.success)) {
                layer.closeAll();
                mymsg(deptInfoTitle + '成功', 6);
                loadAllDept();
                // $('#queryBtn').click();
            }
            else {
                mymsg(response.meta.message, 5);
            }
        });
    });
}

function loadDeptInfo(bmbh) {
    doGet('management/dept/select', {
        bmbh: bmbh
        , searchBh: ''
        , searchName: ''
    }, function (response) {
        var dept = response.data;
        if (dept == null) {
            return;
        }
        $('#id').val(dept.id);

        $('#parentid').val(dept.parentid);
        $('#sjzdbmbh').val(dept.sjzdbmbh);
        $('#type').val(dept.type);
        $('#zt').val(dept.zt);
        $('#sfzsbm').val(dept.sfzsbm);
        $('#zsbmbhs').val(dept.zsbmbhs);
        $('#levelnum').val(dept.levelnum);

        $('#bmbh').val(dept.bmbh);
        $('#bmbh').attr('disabled', 'disabled');

        $('#name').val(dept.name);
        $('#fax').val(dept.fax);
        $('#lxr').val(dept.lxr);
        $('#lxrdh').val(dept.lxrdh);
        $('#lxdz').val(dept.lxdz);
        $('#bz').val(dept.bz);
        $('#fzjg').val(dept.fzjg);
        $('#fzr').val(dept.fzr);
        $('#code').val(dept.code);
        $('#xzqh').val(dept.xzqh);

        $('#prent').val(dept.prent);
    });
}

function emptyArgs() {
    $('#prent').val('');

    $('#message').html('');

    // $('#id').val('');
    //
    // $('#parentid').val('');
    // $('#sjzdbmbh').val('');
    // $('#type').val('');
    // $('#zt').val('');
    // $('#sfzsbm').val('');
    // $('#zsbmbhs').val('');
    $('#levelnum').val('');

    $('#name').val('');
    $('#fax').val('');
    $('#lxr').val('');
    $('#lxrdh').val('');
    $('#lxdz').val('');
    $('#bz').val('');
    $('#fzjg').val('');
    $('#fzr').val('');
    $('#code').val('');
    $('#xzqh').val('');
}

function readArgs() {
    return {
        id: $('#id').val(),
        bmbh: $('#bmbh').val(),
        parentid: config.id,//$('#parentid').val(),
        sjzdbmbh: config.bmbh,//$('#sjzdbmbh').val(),
        levelnum: $('#levelnum').val(),
        type: $('#type').val(),
        name: $('#name').val(),
        fax: $('#fax').val(),
        lxr: $('#lxr').val(),
        lxrdh: $('#lxrdh').val(),
        lxdz: $('#lxdz').val(),
        zt: $('#zt').val(),
        bz: $('#bz').val(),
        czryzh: '',//操作人员账号
        fzjg: $('#fzjg').val(),
        sfzsbm: $('#sfzsbm').val(),
        fzr: $('#fzr').val(),
        zsbmbhs: $('#zsbmbhs').val(),
        code: $('#code').val(),
        xzqh: $('#xzqh').val()
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
















