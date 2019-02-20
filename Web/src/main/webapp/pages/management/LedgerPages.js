$(function () {
    var id=getQueryString("id");
    $('#close').click(function () {
        var index=parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);
    });
    doGet("Ledger/queryLeadgerDetail",{id:id},function (response) {
        if (response.meta.success) {
            $.each(response.data,function (key ,value) {
                $('#'+key.toLowerCase()).val(value);
            });
            if(parseInt(response.data.ljjf)>0){
                $("#ljjf").addClass("font_red")
            }
            if("正常"!=response.data.jsrzt){
                $("#jsrzt").addClass("font_red")
            }

            var html="";
            if (response.data.photos!=null&&response.data.photos.length>0){
                for(var i=0;i<response.data.photos.length;i++){
                    html+="<img id=\"tzzp"+i+"\" onclick='lookBig(this)' style=\"width: 100px;height: 80px\" src=\""+response.data.photos[i]+"\"" +
                        "ayer-src='"+response.data.photos[i]+"'" +
                        " alt=\"台账照片\">"
                }
            }else{
                html="无照片";
            }
            $('#layer-photos-tzzp').html(html);
            // layui.use('layer',function(){
            //     layer.photos({
            //         photos: '#layer-photos-tzzp'
            //         ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            //     });
            //
            // });
        }
    });





})

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