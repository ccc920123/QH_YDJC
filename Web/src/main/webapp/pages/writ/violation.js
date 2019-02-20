/**
 * 简易处罚决定书详情
 */

$(function () {
   /* var wsbh = getQueryString("wsbh");
    var zqmj = encodeURI(getQueryString("zqmj"));*/
    var postData = GetRequest();
    var wsbh = postData.wsbh;
    // var zqmj = postData.zqmj;
    // console.log(wsbh + zqmj);
    $('#close').click(function () {
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    doPost("/writ/violation",wsbh,function (response) {
        $.each(response,function (key,value) {
            $('#'+key).val(value);
        })
        $("#zqmj").val(response.lrrname);
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