/**
 * 强制措施决定书
 */

$(function () {
   /* var wsbh = getQueryString("wsbh");
    var zqmj = encodeURI(getQueryString("zqmj"));*/
    var postData = GetRequest();
   var wsbh = postData.wsbh;
    $('#close').click(function () {
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);
    });
   // var zqmj = postData.zqmj;
   //  console.log(wsbh + zqmj);
    doPost("/writ/force",wsbh,function (response) {
        $.each(response,function (key,value) {
            $('#'+key).val(value);
        })
        $("#zqmj").val(response.lrrname);

        var wfbody = "<table class=\"addNewTable\">";
        $("#wfbody").empty();
        $.each(response.wf,function (n,value) {
            var str = "";
            str += "<tr>" +
                "<td class=\"s_head\" style='width: 150px;'>违法代码</td>" +
                "<td style='width: 290px;'><input type=\"text\"  class=\"inputtext\" readonly=\"readonly\" placeholder='" + value.wfxw + "'></td>" +
                "<td class=\"s_head\" style='width: 150px;'>法律依据</td>" +
                "<td style='width: 320px;'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + value.fltw + "'></td>" +
                "</tr>" +
                "<tr>" +
                " <td class=\"s_head\">违法内容</td>" +
                "<td colspan='2'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + value.wfnr + "'></td>" +
                "</tr>" +
                "<tr>" +
                " <td class=\"s_head\">违法行为</td>" +
                "<td colspan='2'><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + value.wfms + "'></td>" +
                "</tr>" +
                "<tr>" +
                "<td class=\"s_head\">标准值</td>" +
                "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + value.bzz + "'></td>" +
                "<td class=\"s_head\">实测值</td>" +
                "<td><input type=\"text\" class=\"inputtext\" readonly=\"readonly\" placeholder='" + value.scz + "'></td>" +
                "</tr>";
            wfbody += str;
        })
        $("#wfbody").append(wfbody + "</table>");


    })
})

function GetRequest() {
    var url =decodeURI(decodeURI(location.search)); //获取url中"?"符后的字串，使用了两次decodeRUI解码
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
        return theRequest;
    }
}
