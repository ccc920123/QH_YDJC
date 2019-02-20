/**
 * 部门参数配置
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

/**
 * 开始
 */
$(function () {

    justPression();
    if (justPressionBy("1132")){
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

    clickQuery();

})

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
 * 查询所有部门信息
 */
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
    }
    initFwz();
}

/**
 * 点击选中显示机构的用户列表
 * 1、保存选中的部门编号
 * 1、加载列表数据
 */
function zTreeOnClick(event, treeId, treeNode) {
    config.bmbh = treeNode.bmbh;
    $("#kkmc").text('');
    initFwz();
}

/**
 * 初始化查询条件中所属执法站的下拉列表
 */
var initFwz = function () {
    doGet('lawStation/queryByBmbh', {
        bmbh: config.bmbh,

    }, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        var list = response.data;
        var html = " <option value=\"\"></option>";
        for (var i = 0; i < list.length; i++) {
            html += ' <option value=\"' + list[i].key + '\">' + list[i].value + '</option>'
        }
        $("#zfzmc").html(html);
        queryRoadblock();

    })
}

/**
 * 点击页面查询按钮
 */
var clickQuery = function () {

    $("#queryBtn").click(function () {
        pageNo = 1;
        queryRoadblock();
    })
}

/**
 * 查询卡口信息
 */
var queryRoadblock = function () {

    //判断卡口名称输入的字符串长度
    if (($("#kkmc").val()).length > 50) mymsg("输入卡口名称字符长度不得大于50", 5);

    doPost("/roadblock/getInfo", JSON.stringify({
        bmbh: config.bmbh,
        kkmc: $("#kkmc").val(),
        fwzbh: $('#zfzmc option:selected').val(),//选中的值,
        page: pageNo
    }), function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }
        console.log(response.data);
        $("#total").text(response.data.total);
        if (response.data.total == 0 || response.data == null) {
            mymsg('当前条件下查询信息为空', 5);
        }
        page(response.data.total);
        showRoadblock(response.data.result);
    })
}

/**
 * 分页页码
 */
var pageNo = 1;
var page = function (count) {
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        laypage.render({
            elem: 'page',
            count: count,
            limit: 10,
            curr: pageNo,
            jump: function (obj, first) {
                if (!first) {
                    pageNo = obj.curr;
                    listRoadblock = undefined;
                    queryRoadblock();
                    return;
                }
            }
        })
    })
}

/**
 * 卡口信息显示
 */
var listRoadblock;
var showRoadblock = function (roadblock) {
    var tbody = "";
    $("#tbody").empty();
    listRoadblock = roadblock;
    $.each(roadblock, function (n, value) {
        var str = "";
        var xh = (pageNo - 1) * 10 + n + 1;
        str += "<tr>" +
            "<td>" + xh + "</td>" +
            "<td>" + value.kkbh + "</td>" +
            "<td>" + value.kkmc + "</td>" +
            "<td>" + value.sxfxlxmc + "</td>" +
            "<td>" + value.xxfxlxmc + "</td>" +
            "<td>" + value.fwzmc + "</td>" +
            "<td onclick='look(this)' index = '" + n + "'style='cursor: pointer;color:blue;'><img src='../../imgs/management/chakan.png' title='查看'></td>" +
            "</tr>";
        tbody += str;
    })
    $("#tbody").append(tbody);
}

/**
 * 查看详情
 */
var look = function (dom) {
    var rb = listRoadblock[$(dom).attr("index")];
    top.layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['920px', '260px'],
        title: "卡口详情",
        content: "<table style='width: 99%;margin: 10px auto 0;background:#f5f5f5;'>" +
        "<tr>" +
        "<td class=\"s_head\">卡口编号</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.kkbh + "'></td>" +
        "<td class=\"s_head\">卡口名称</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.kkmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">上行方向名称</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.sxfxlxmc + "'></td>" +
        "<td class=\"s_head\">下行方向名称</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.xxfxlxmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">道路代码</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.dldm + "'></td>" +
        "<td class=\"s_head\">道路名称</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.dlmc + "'></td>" +
        "</tr>" +
        "<td class=\"s_head\">经度</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.kkjd + "'></td>" +
        "<td class=\"s_head\">纬度</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.kkwd + "'></td>" +
        "</tr>" +
        "<td class=\"s_head\">管理部门</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.bmmc + "'></td>" +
        "<td class=\"s_head\">行政区划</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.xzqh + "'></td>" +
        "</tr>" +
        "<td class=\"s_head\">执法站名称</td>" +
        "<td colspan='3'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + rb.fwzmc + "'></td>" +
        "</tr></table>"
    })
}