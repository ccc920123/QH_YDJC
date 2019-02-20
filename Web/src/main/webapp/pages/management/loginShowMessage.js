$(function () {
    $('#close').click(function () {
        var index = parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);
    });
    doGet("login/getUserMessage", {}, function (response) {

        if (!Boolean(response.meta.success)) {
            layer.msg('登录异常，请重新登录', {icon: 5});
            return;
        }
        $.each(response.data, function (key, value) {
            $("#" + key).val(value);
        });
        var czrzs = response.data.czrz;
        var html = "";
        $.each(czrzs, function (index, czrz) {
            html += czrz.opTime + "       " + czrz.opIp + "        " + czrz.opResultInfo + "\n"
        });
        $("#layer-photos-tzzp").value = html;
        if ((response.data.scdlcgip==null||response.data.scdlcgip=='')&&
            (response.data.scdlcgsj==null||response.data.scdlcgsj=='')){
            layer.alert("增加成功", {
                icon: 6
            }, function () {
                top.layer.open({
                    type: 2,
                    shade: [0.5, '#000', false],
                    area: ['400px', '230px'],
                    title:"用户密码修改",
                    content:'/Web/pages/login/other/updatePassword.html'
                })
            });
        }
    });
});