/**
 * 电子监控决定书
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
    // console.log(wsbh + zqmj);
    doPost("/writ/surve",wsbh,function (response) {
        $.each(response,function (key,value) {
            $('#'+key).val(value);
        })
        $("#zqmj").val(response.lrrname);
        var html = '';
        if (response.zps != null && response.zps.length > 0) {
            for (var i = 0; i < response.zps.length; i++) {
                html += "<img id=\"ljzp" + i + "\"  onclick='lookBig(this)' style=\"width: 100px;height: 80px\" " +
                    "src=\"" + response.zps[i] + "\" " +
                    "layer-src='" + response.zps[i] + "'" +
                    "alt=\"拦截照片\"/>";
                // $("#ljzp"+(i+1)).attr("layer-src",response.data.ljzp[i]);
                // $("#ljzp"+(i+1)).attr("src",response.data.ljzp[i]);
            }
        } else {
            html = "无照片"
        }
        $('#layer-zps-ljzp').html(html);
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
var lookBig = function (span) {
    var image = $(span).attr("src");

    window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['800px', '600px'],
        title: '照片',
        content: '/Web/pages/imageBig.html?url='+image,
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);

        },
        cancel: function () {
            yjxh = undefined;
        }

    })

}
