/**
 *入口函数
 */
$(function () {
    initView();
    showCalendar();


    /**
     * 拉取用户信息、加载机构树
     */
    getUserQx(function () {
        loadAllDept();
    });


    clickQuery();
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

/**获取页面高度*/
function initView() {
    var height = $(window).height();   //获取浏览器可用高度
    $('.deptLeft').css('height', height); //菜单树的高度
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
    loadAlarmManage();
}

/**
 * 加载页面
 */
function loadAlarmManage() {
    $('#tbody').html('');
    // pageClose();
    queryAlarm();
}

// function pageClose() {
//     $('#page').css('display', 'none');
// }


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

    queryAlarm();
}

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
/**
 * 日历显示
 */
var showCalendar = function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#ksrq',//开始日期
            type: 'date',
            value: new Date(),
            isInitValue: true
        });
        laydate.render({
            elem: '#jsrq', //结束日期
            type: 'date',
            value: new Date(),
            isInitValue: true
        });
    })
}

/**
 * 预警查询
 * listAlarm:预警信息集合
 */
var listAlarm;
var queryAlarm = function () {
    doPost("/alarm/queryTable", JSON.stringify({
        yjzt: $("#yjzt").val(),
        ksrq: $("#ksrq").val(),
        jsrq: $("#jsrq").val(),
        hphm: $("#hphm").val(),
        yjlx: $("#yjlx").val(),
        bmbh: config.bmbh,
        page: pageNo
    }), function (response) {
        if (response.meta.success) {
            $("#total").text(response.data.total);
            if (response.total === 0) {
                mymsg('当前时段无该预警信息', 5);
                return;
            }
            listAlarm = response.data.list;
            showAlarm(listAlarm);
            page(response.data.total);
        }

    })
}

/**
 * 点击查询预警事件
 */
var clickQuery = function () {
    $("#queryBtn").click(function () {
        console.log($("#ksrq").val());
        //若开始日期大于结束日期，则弹出提示信息
        // if ($("#ksrq").val() > $("#jsrq").val() && $("#jsrq").val() != '') {
        //     mymsg('日期输入错误，起始日期不能大于结束日期', 5);
        //     return;
        // }
        // if ($("#ksrq").val() == '' && $("#jsrq").val() != '') {
        //     mymsg('请输入开始日期', 5);
        //     return;
        // }
        // if ($("#ksrq").val() != '' && $("#jsrq").val() == '') {
        //     mymsg('请输入结束日期', 5);
        //     return;
        // }
        //条件查询时应该从第一页开始查询
        pageNo = 1;
        queryAlarm();
    })
}


/**
 * 显示预警信息
 */
var showAlarm = function (alarm) {
    var tbody = "";
    $("#tbody").empty();
    if (alarm.length > 0) {
        $.each(alarm, function (n, value) {
            var str = "";
            str += "<tr>" +
                "<td>" + value.rn + "</td>" +
                "<td>" + value.hphm + "</td>" +
                "<td>" + value.hpzl + "</td>" +
                "<td>" + value.bklx + "</td>" +
                "<td>" + value.kkmc + "</td>" +
                "<td>" + value.yjsj + "</td>" +
                "<td>" + value.zt + "</td>" +
                "<td>" +
                " <img id=\"look\" src=\"../../imgs/management/chakan.png\" title=\"查看\" " +
                "onclick='lookk(this)' index = '" + value.yjxh + "' /></td>" +
                "</tr>";
            tbody += str;
        })
    } else {
        tbody = "<tr><td colspan='8'>无数据</td></tr>";
    }
    $("#tbody").append(tbody);
}

/**
 *分页
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

                    queryAlarm();
                    return;
                }
            }
        })
    })
}

//查询详情
var lookk = function (dom) {
    var yjxh = $(dom).attr("index");

    window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['900px', '600px'],
        title: "预警信息详情",
        content: '/Web/pages/management/alarmDetail.html?yjxh=' + yjxh,
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);

        },
        cancel: function () {
            yjxh = undefined;
        }

    })


}

//当时间为Null返回""
var testNull = function (date) {
    if (date == null || date == "null") {
        return "";
    } else return date;
}

/**
 * 通过文书编号文书类型查看文书详情
 */
var lookWrit = function (dom) {
    doPost("/writ/look", JSON.stringify({
        wsbh: $(dom).attr("wsbh"),
        wslb: $(dom).attr("wslb")
    }), function (response) {
        console.log(response);
        //根据wslb弹出弹出层
        switch ($(dom).attr("wslb")) {
            case '1'://简易处罚决定书
                violation(response.result, $(dom).attr("czr"));
                break;
            case '3'://强制措施决定书
                force(response.result, $(dom).attr("czr"));
                break;
            case '6'://电子监控通知书
                surve(response.result, $(dom).attr("czr"));
                break;
        }
    })
}


/**
 *电子监控弹出层
 */
var surve = function (s, zqmj) {
    layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['900px', '430px'],
        title: "电子监控决定书",
        content: "<table border='1' style='text-align: center;table-layout:fixed;word-break: break-all;word-wrap: break-word;'>" +
        "<tr><td style='background-color:#A4D3EE;width: 150px;height:40px;'>机动车所有人</td><td style='width: 300px;'>" + s.jdcsyr + "</td>" +
        "<td style='background-color:#A4D3EE;width:150px;'>文书编号</td><td style='width: 290px;'>" + s.jdsbh + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法代码</td><td>" + s.wfxw + "</td>" +
        "<td style='background-color:#A4D3EE'>违法时间</td><td>" + s.wfsj + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法内容</td><td colspan='3'>" + s.wfnr + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法地址</td><td colspan='3'>" + s.wfdz + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>号牌种类</td><td>" + s.hpzlname + "</td>" +
        "<td style='background-color:#A4D3EE'>号牌号码</td><td>" + s.hphm + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>采集机关</td><td>" + s.cjjg + "</td>" +
        "<td style='background-color:#A4D3EE'>采集方式</td><td>" + s.cjfs + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>机动车所有人电话</td><td colspan='3'>" + s.dh + "</td>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>设备编号</td><td colspan='3'>" + s.sbbh + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>录入人</td><td>" + s.lrrname + "</td>" +
        "<td style='background-color:#A4D3EE'>执勤民警</td><td>" + zqmj + "</td></tr>" +
        "</table>"
    })
}

/**
 *强制措施文书弹出层
 */
var force = function (f, zqmj) {
    layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['900px', '380px'],
        title: "强制措施决定书",
        content: "<table border='1' style='text-align: center;table-layout:fixed;word-break: break-all;word-wrap: break-word;'>" +
        "<tr><td style='background-color:#A4D3EE;width:150px;height:40px;'>当事人姓名</td><td style='width: 300px;'>" + f.dsr + "</td>" +
        "<td style='background-color:#A4D3EE;width:150px;'>文书编号</td><td style='width: 290px;'>" + f.pzbh + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>驾驶证号</td><td colspan='3'>" + f.jszh + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法代码</td><td>" + f.wfxw1 + "</td>" +
        "<td style='background-color:#A4D3EE'>违法时间</td><td>" + f.wfsj + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法内容</td><td colspan='3'>" + f.wfnr + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法地址</td><td colspan='3'>" + f.wfdz + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>号牌种类</td><td>" + f.hpzlname + "</td>" +
        "<td style='background-color:#A4D3EE;'>号牌号码</td><td>" + f.hphm + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法人电话</td><td>" + f.dh + "</td>" +
        "<td style='background-color:#A4D3EE'>联系方式</td><td>" + f.lxfs + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>录入人</td><td>" + f.lrrname + "</td>" +
        "<td style='background-color:#A4D3EE'>执勤民警</td><td>" + zqmj + "</td></tr>" +
        "</table>"
    })
}

/**
 *简易处罚决定书弹出层
 */
var violation = function (v, zqmj) {
    layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['9', '430px'],
        title: "简易处罚决定书",
        content: "<table border='1' style='text-align: center;table-layout:fixed;word-break: break-all;word-wrap: break-word;'>" +
        "<tr><td style='background-color:#A4D3EE;width: 150px;height:40px;'>当事人姓名</td><td style='width: 300px;'>" + v.dsr + "</td>" +
        "<td style='background-color:#A4D3EE;width: 150px;'>文书编号</td><td style='width:290px;'>" + v.jdsbh + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>驾驶证号</td><td colspan='3'>" + v.jszh + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法代码</td><td>" + v.wfxw + "</td>" +
        "<td style='background-color:#A4D3EE'>违法时间</td><td>" + v.wfsj + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法内容</td><td colspan='3'>" + v.wfxq + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法地址</td><td colspan='3'>" + v.wfdz + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>号牌种类</td><td>" + v.hpzlname + "</td>" +
        "<td style='background-color:#A4D3EE'>号牌号码</td><td>" + v.hphm + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>罚款金额</td><td>" + v.fkje + "</td>" +
        "<td style='background-color:#A4D3EE'>违法记分</td><td>" + v.wfjf + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>违法人电话</td><td>" + v.dh + "</td>" +
        "<td style='background-color:#A4D3EE'>联系方式</td><td>" + v.lxfs + "</td></tr>" +
        "<tr><td style='background-color:#A4D3EE;height:40px;'>录入人</td><td>" + v.lrrname + "</td>" +
        "<td style='background-color:#A4D3EE;height:40px;'>执勤民警</td><td>" + zqmj + "</td></tr>" +
        "</table>"
    })
}