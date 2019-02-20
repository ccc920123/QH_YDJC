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
    if (justPressionBy("1131")){
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
    initFwz();
}

/**
 * 点击选中显示机构的用户列表
 * 1、保存选中的部门编号
 * 1、加载列表数据
 */
function zTreeOnClick(event, treeId, treeNode) {
    config.bmbh = treeNode.bmbh;
    $("#xm").text('');
    $("#rybh").text('');
    initFwz();
}

/**
 * 初始化查询条件中所属执法站的下拉列表
 */
var initFwz = function(){
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
        queryPolice();

    })
}


/**
* 点击页面查询按钮
*/
var clickQuery = function(){

    $("#queryBtn").click(function () {
        pageNo = 1;
        queryPolice();
    })
}

/**
 * 查询警员信息
 */
var queryPolice = function () {

    //查询之前先判断字符串长度
    if (($("#xm").val()).length > 20) mymsg('输入警员姓名长度大于20', 5);

    if (($("#rybh").val()).length > 20) mymsg('输入警号长度大于20', 5);

    doPost("/police/getInfo",JSON.stringify({
        bmbh:config.bmbh,
        xm:$("#xm").val(),
        rybh:$("#rybh").val(),
        fwzbh: $('#zfzmc option:selected').val(),//选中的值
        pageNo:pageNo
    }),function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }
        console.log(response.data);
        $("#total").text(response.data.total);
        if (response.data.total == 0 || response.data == null){
            mymsg('当前条件下查询信息为空', 5);
        }
        page(response.data.total);
        showPolice(response.data.police);

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
                    listPolice = undefined;
                    queryPolice();
                    return;
                }
            }
        })
    })
}

/**
 * 警员信息显示
 */
var listPolice;
var showPolice = function (police) {
    var tbody = "";
    $("#tbody").empty();
    listPolice = police;
    $.each(police,function (n,value) {
        var str = "";
        var xh = (pageNo - 1) * 10 + n + 1;
        str += "<tr>" +
            "<td>" + xh +"</td>" +
            "<td>" + value.xm +"</td>" +
            "<td>" + value.rybh +"</td>" +
            "<td>" + value.rylx +"</td>" +
            "<td>" + value.sfzmhm + "</td>" +
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
    var pol = listPolice[$(dom).attr("index")];
    top.layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['920px', '200px'],
        title: "警员详情",
        content:"<table  style='width: 99%;margin: 10px auto 0;background:#f5f5f5;'>" +
        "<tr>" +
        "<td class=\"s_head\" style=' border-right: 1px solid #e9e9e9;'>姓名</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.xm + "'></td>" +
        "<td class=\"s_head\"  style=' border-right: 1px solid #e9e9e9;'>警号</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.rybh + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">人员类型</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.rylx + "'></td>" +
        "<td class=\"s_head\">身份证明号码</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.sfzmhm + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">联系电话</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.sjhm + "'></td>" +
        "<td class=\"s_head\">管理部门</td>" +
        "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.bmmc + "'></td>" +
        "</tr>" +
        "<tr>" +
        "<td class=\"s_head\">执法站名称</td>" +
        "<td colspan='3'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + pol.fwzmc + "'></td>" +
        "</tr></table>"
    })
}
