/**
 * 入口函数
 */
$(function () {
     cycle();//过一分钟 才调用 所以这里要先调用一次
    setInterval(cycle,10000);//循环调用，间隔时间1分钟,
})

/**
 *  需要周期性执行的函数
 */
function cycle() {
    getIntegratedCommand();//支队信息
    getTableSpace();//表空间
    getPoliceService();//警务通
    interfaceMonitoring();//接口测试
}

/**
 * 获取移动集成指挥接入支队信息
 */
function getIntegratedCommand() {
    doPost("/monitoring/IC",JSON.stringify(pageNo),function (response) {
        console.log(response);
        $("#icbody").empty();
        var icbody = "<table class='addNewTable'>";
       $.each(response,function (index,value) {
           var str = "";
           var color = "chartreuse";
           if (value.TOTAL == undefined){
               value.TOTAL = 0;
               color = "red";
           }
           str = "<tr><td style='color: " + color + ";font-size: 15px;width:150px;text-align:right'>●</td><td>" + value.NAME + "</td>&nbsp;" +
               "<td style='color: red;text-align: center;width: 200px;'>" + "(" + value.TOTAL + ")" + "</td></tr>";
           icbody += str;
       })
        $("#icbody").append(icbody + "</table>");
        page(response[0].bmzs);//展示页码
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
            limit:5,
            curr:pageNo,
            jump: function(obj,first){
                if (!first){
                    pageNo = obj.curr;
                    getIntegratedCommand();
                    return;
                }
            }
        })
    })
}


/**
 * 获取表空间信息
 */
function getTableSpace() {
    doPost("/monitoring/space",{},function (response) {
        console.log(response);
        $.each(response,function (key,value) {
            $("#" + key).html(value);
        })
    })
}

/**
 * 获取警务通信息
 */
function getPoliceService() {
    doPost("/monitoring/ps",{},function (response) {
        console.log(response);
        $("#psbody").empty();
        var psbody = "<table class='addNewTable'><tr>" +
            "<td style='width: 250px;'>操作时间</td>" +
            "<td style='width: 150px;'>警务通名称</td>" +
            "<td style='width: 100px;'>用户名</td></tr>";
        $.each(response,function (index,value) {
            var str = "";
            str = "<tr><td>" + value.OPTIME + "&nbsp;&nbsp;</td>" +
                "<td>"+ value.MC +"&nbsp;&nbsp;</td>" +
                "<td>" + "(" + value.REALNAME + ")" + "</td></tr>";
            psbody += str;
        })
        $("#psbody").append(psbody +"</table>");
    })
}

/**
 * 接口监控
 */
function interfaceMonitoring() {
    testJCZHPT();
    testPDA();
    testZHPT();
}

/**
 * 综合平台接口测试
 */
function testZHPT() {
doPost("/monitoring/zhpt",{},function(response){
    console.log(response);
    if (response == true){
        $("#zhpt").html("正常");
        $("#zhpt").attr("style","color:#00FF00");
    }else{
        $("#zhpt").html("异常");
        $("#zhpt").attr("style","color:red");
    }
})
}

/**
 * 集成指挥平台接口测试
 */
function testJCZHPT() {
doPost("/monitoring/jczhpt",{},function (response) {
    if (response == true){
        $("#jczhpt").html("正常");
        $("#jczhpt").attr("style","color:#00FF00");
    }else{
        $("#jczhpt").html("异常");
        $("#jczhpt").attr("style","color:red");
    }
})
}

/**
 * PDA接口测试
 */
function testPDA() {
 doPost("/monitoring/pdajk",{},function (response) {
     if (response == true){
         $("#pdajk").html("正常");
         $("#pdajk").attr("style","color:#00FF00");
     }else{
         $("#pdajk").html("异常");
         $("#pdajk").attr("style","color:red");
     }
 })
}
