$(function () {
    var yjxh = getQueryString("yjxh");
    $('#close').click(function () {
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);
    });
    doGet("/alarm/queryDetail", {yjxh: yjxh}, function (response) {
        if (response.meta.success) {
            $.each(response.data, function (key, value) {
                $('#' + key).val(value);
            });
            if (response.data.yjzp != undefined && response.data.yjzp != '') {
                var h = " <img layer-pid=\"yjzps\"  onclick='lookBig(this)' id=\"yjzps\" style=\"width: 150px;height: 100px\"\n" +
                    "                     layer-src=" + response.data.yjzp + "" +
                    "                     src=" + response.data.yjzp + ">"

                $('#layer-photos-yjzp').html(h);
            } else {
                $('#layer-photos-yjzp').html("无照片");
            }
            // $("#yjzps").attr("layer-src",response.data.yjzp);
            // $("#yjzps").attr("src",response.data.yjzp);

            $("#wsbh").attr("wslb", response.data.wslbnum);
            var html = '';
            if (response.data.ljzp != null && response.data.ljzp.length > 0) {
                for (var i = 0; i < response.data.ljzp.length; i++) {
                    html += "<img id=\"ljzp" + i + "\"  onclick='lookBig(this)' style=\"width: 100px;height: 80px\" " +
                        "src=\"" + response.data.ljzp[i] + "\" " +
                        "layer-src='" + response.data.ljzp[i] + "'" +
                        "alt=\"拦截照片\"/>";
                    // $("#ljzp"+(i+1)).attr("layer-src",response.data.ljzp[i]);
                    // $("#ljzp"+(i+1)).attr("src",response.data.ljzp[i]);
                }
            } else {
                html = "无照片"
            }
            $('#layer-photos-ljzp').html(html);
            // layui.use('layer', function () {
            //     layer.photos({
            //         photos: '#layer-photos-yjzp'
            //         , anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            //         , full: true // 是否全屏预览（大图可以拖动）
            //     });
            //     layer.photos({
            //         photos: '#layer-photos-ljzp'
            //         , anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            //         , full: true // 是否全屏预览（大图可以拖动）
            //     });
            // });
        }
    });
});
var lookWrit = function (span) {
    var wslb = $(span).attr("wslb");
    var wsbh = $(span).val();
    var url = "/Web/pages/writ/violation.html"
    var title = "";
    if ("1" == wslb) {
        url += "?wsbh=" + wsbh;
        title = '简易处罚书详情';
    } else if ("3" == wslb) {
        url = "/Web/pages/writ/force.html?wsbh=" + wsbh;
        title = '强制处罚书详情';
    }
    window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['800px', '600px'],
        title: title,
        content: url,
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);

        },
        cancel: function () {
            yjxh = undefined;
        }

    })

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
