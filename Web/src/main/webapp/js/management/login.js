/**
 * Created by hdfs on 2017/10/16.
 */
var HEIGHT = 0;  //页面高度

$(function () {

    HEIGHT = $(window).height();   //获取浏览器可用高度
    $('body').css('height', HEIGHT);

    /**
     * 记住密码
     */
    if ($.cookie("rmbUser") == "true") {
        $("#rmbUser").prop("checked", true);
        $("#username").val($.cookie("userName"));
        $("#password").val($.cookie("passWord"));
    }

    $("#btnLogin").click(function () {

        if("" == $("#username").val()) {
            layer.alert("用户名不能为空！");
        } else if("" == $("#password").val()) {
            layer.alert("密码不能为空！");
        } else {
            //  + $("#username").val() + "&passwd=" + $("#password").val()

            /**
             * 记住密码
             */
            if ($("#rmbUser").prop("checked") == true) {
                var userName = $("#username").val();
                var passWord = $("#password").val();

                $.cookie("rmbUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
                $.cookie("userName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
                $.cookie("passWord", passWord, { expires: 7 }); // 存储一个带7天期限的 cookie
            }
            else {
                $.cookie("rmbUser", "false", { expires: -1 });        // 删除 cookie
                $.cookie("userName", '', { expires: -1 });
                $.cookie("passWord", '', { expires: -1 });
            }
            var username=encryptByDES($("#username").val());
            var password=encryptByDES($("#password").val());

            doPost("login/loginIn",JSON.stringify({
                loginname : username,
                password :password,
            }), function (response) {
                if (!Boolean(response.meta.success)) {
                    layer.alert(response.meta.message);
                    return ;
                }
                if(response.data) {
                    window.location.href = "../pages/login/home.html";
                } else {
                    layer.alert("密码不正确");
                }

            });


        }

    });
    $('body').keydown(function () {
        if(event.keyCode == '13'){
            $('#btnLogin').click();
        }
    })

});
