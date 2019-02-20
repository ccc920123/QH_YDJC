/**
 * 入口函数
 */
$(function () {
    showCalendar();
    writQ();
    clickWritQ();
})

/**
 * 日历显示
 */
   var showCalendar = function(){
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#ksrq' ,//开始日期
            type: 'date',
            value:new Date(),
            isInitValue:true
        });
        laydate.render({
            elem: '#jsrq', //结束日期
            type: 'date',
            value:new Date(),
            isInitValue:true
        });
    })
}

/**
 * 查询文书事件
 */
var writQ = function(){
    doPost("/writ/select",JSON.stringify({
        wslb : $("#wslb").val(),
        wsbh : $("#wsbh").val(),
        ksrq : $("#ksrq").val(),
        jsrq : $("#jsrq").val(),
        page : pageNo
    }),function (response) {

        $("#total").text(response.total);
        if (response.total == 0){
            mymsg('当前时段无该文书信息', 5);
            return;
        }
        showWrit(response.result);
        page(response.total);
    })
}

/**
  *点击条件查询文书事件
  */
var clickWritQ = function(){$("#queryBtn").click(function () {
    console.log($("#ksrq").val());
    //若开始日期大于结束日期，则弹出提示信息
    if ($("#ksrq").val() > $("#jsrq").val() && $("#jsrq").val() != ''){
        mymsg('日期输入错误，起始日期不能大于结束日期', 5);
        return;
    }
   /* if ($("#ksrq").val() == '' && $("#jsrq").val() != ''){
        mymsg('请输入开始日期', 5);
        return;
    }
    if ($("#ksrq").val() != '' && $("#jsrq").val() == ''){
        mymsg('请输入结束日期', 5);
        return;
    }*/
   writQ();
})}

/**
 *显示查询文书的结果
 */
var showWrit = function(write){
var tbody = "";
$("#tbody").empty();
$.each(write,function (n,value) {
    var str = "";
    str += "<tr>" +
        "<td>" + value.xh + "</td>" +
        "<td>" + value.wsbh + "</td>" +
        "<td>" + value.glbm + "</td>" +
        "<td>" + value.scsj + "</td>" +
        "<td>" + value.wslb + "</td>" +
        "<td>" + value.cjry + "</td>" +
        "<td>" +
        "<img src='../../imgs/management/chakan.png' title='查看' wsbh = '" + value.wsbh +"' zqmj = '" + value.cjry + "' wslb = '" + value.wslb+ "' id = '" + value.id + "'style='cursor: pointer;color:blue;'" + " onclick='check(this)'></img>"
        + "</td></tr>";
    tbody += str;
})
    $("#tbody").append(tbody);
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
              writQ();
              return;
          }
        }
    })
})
}

/**
 *删除文书内容
 */
var remove = function(dom){

    doPost("/writ/remove",JSON.stringify({id:$(dom).attr("id"),wslb:$(dom).attr("wslb")}),
        function (response) {//删除成功则重新获取页面信息
        console.log(response);
        if (response === true){
            writQ();
            mymsg('删除成功', 5);
        }
    })
}

/**
 *查看文书详情
 */
var check = function (dom) {

          //根据文书类别弹出详情弹窗
          switch ($(dom).attr("wslb")){
              case "违停告知单":
                  surve($(dom).attr("wsbh"),$(dom).attr("zqmj"));
                  break;
              case "强制措施处罚决定书":
                  force($(dom).attr("wsbh"),$(dom).attr("zqmj"));
                  break;
              case "简易处罚决定书":
                  violation($(dom).attr("wsbh"),$(dom).attr("zqmj"));
                  break;
              default: mymsg('查询数据失败', 5);
          }

}

/**
 *电子监控弹出层
 */
var surve = function(wsbh,zqmj){
    top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['920px', '500px'],
        title:"违停通知单",
        content:'../writ/surve.html?zqmj='+ encodeURI(zqmj) + '&wsbh=' + wsbh
    })
}

/**
 *强制措施文书弹出层
 */
var force = function(wsbh,zqmj){
    top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['920px', '500px'],
        title:"强制措施决定书",
        content:'../writ/force.html?zqmj='+ encodeURI(zqmj) + '&wsbh=' + wsbh
    })
}

/**
 *简易处罚决定书弹出层
 */
var violation = function (wsbh,zqmj) {
    top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['920px', '500px'],
        title:"简易处罚决定书",
        content:'../writ/violation.html?zqmj='+ encodeURI(zqmj) + '&wsbh=' + wsbh
    })
}