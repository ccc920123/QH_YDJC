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
    if (justPressionBy("1128")){
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

    if(zNodes.length > 0) {

        var rootid = zNodes[0].id;
        //初始化时，默认选中第一个节点
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        var node = treeObj.getNodeByParam("id", rootid);
        treeObj.selectNode(node);
        config.bmbh = node.bmbh;
    }
    queryLawStation();
}

/**
 * 点击选中显示机构的用户列表
 * 1、保存选中的部门编号
 * 1、加载列表数据
 */
function zTreeOnClick(event, treeId, treeNode) {
    config.bmbh = treeNode.bmbh;
    $("#searchBh").text('');
    $("#searchName").text('');
    queryLawStation();
}

/**
 * 点击页面查询按钮
 */
var clickQuery = function(){

    $("#queryBtn").click(function () {
        pageNo = 1;
        queryLawStation();
    })
}

/**
 * 查询执法站信息
 */
var queryLawStation = function () {

    //执法站编号和名称长度限制
    if (($("#searchBh").val()).length > 20) mymsg('执法站编号长度不得大于20', 5);

    if (($("#searchName").val()).length > 20) mymsg('执法站名称长度不得大于20', 5);

    doPost("/lawStation/query",JSON.stringify({
        bmbh:config.bmbh,
        fwzbh:$("#searchBh").val(),
        fwzmc:$("#searchName").val(),
        pageNo:pageNo
    }),function (response) {
          console.log(response);
        $("#total").text(response.total);
          if (response.total == 0 || response == null){
              mymsg('当前条件下查询信息为空', 5);
          }
          page(response.total);
          showLawStation(response.result);

    })
}

/**
 * 分页页码
 */
var pageNo = 1;
var page = function(count){
    layui.use('laypage',function () {
        var laypage = layui.laypage;
        laypage.render({
            elem:'page',
            count:count,
            limit:10,
            curr:pageNo,
            jump: function(obj,first){
                if (!first){
                    pageNo = obj.curr;
                    listLawStation = undefined;
                   queryLawStation();
                    return;
                }
            }
        })
    })
}

/**
 * 执法站信息显示
 * @param lawStation
 */
var listLawStation;
var showLawStation = function (lawStation) {
    var tbody = "";
    $("#tbody").empty();
    listLawStation = lawStation;
    $.each(lawStation,function (n,value) {
        var str = "";
        var xh = (pageNo - 1) * 10 + n + 1;
        str += "<tr>" +
            "<td>" + xh +"</td>" +
            "<td>" + value.fwzbh +"</td>" +
            "<td>" + value.fwzmc +"</td>" +
            "<td>" + value.fwzlxmc +"</td>" +
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
    var law = listLawStation[$(dom).attr("index")];
    console.log(law);
    top.layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['920px', '310px'],
        title: "执法站详情",
        content:"<table style='width: 99%;margin: 10px auto 0;background:#f5f5f5;'>" +
        "<tr>" +
        "<td class=\"s_head\">执法站编号</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.fwzbh + "'></td>" +
        "<td class=\"s_head\">执法站名称</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.fwzmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">执法站类型</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.fwzlxmc + "'></td>" +
        "<td class=\"s_head\">管理部门</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.glbmmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">道路类型</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.dllxmc + "'></td>" +
        "<td  class=\"s_head\">公路行政等级</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.glxzdjmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td  class=\"s_head\">道路代码</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.dldm + "'></td>" +
        "<td  class=\"s_head\">道路名称</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.dlmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td  class=\"s_head\">公里数</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.lkh + "'></td>" +
        "<td  class=\"s_head\">米数</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.dlms + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td  class=\"s_head\">检查方向</td>" +
        "<td colspan='3'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.jcfxmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td  class=\"s_head\">所在地点</td>" +
        "<td colspan='3'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + law.szdd + "'></td>" +
        "</tr>" +
        "</table>"

    })
}








