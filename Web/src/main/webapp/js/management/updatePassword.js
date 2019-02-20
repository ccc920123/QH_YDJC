$(function () {
    // 调用doGet方法，获取当前登录用户的真实姓名
    doGet(
        "/users/getThisUser",
        {}, function (response) {
            //用户信息
            $("#loginname").val(response.data.loginname);
            $("#realname").val(response.data.realname);
            $("#ssbm").val(response.data.ssbm);
            $("#sfzhm").val(response.data.sfzhm);
            $("#lxdh").val(response.data.lxdh);
            $("#lxdz").val(response.data.lxdz);
        }
    )

    //关闭窗口
    $("#btnClose").click(function () {
        window.close();
    });


    //保存修改密码
    $("#btnOK").click(function () {

        if ("" == $("#password").val()) {
            mymsg("原密码不能为空！",5);
            return;
        } else if($("#password").val().length<6||$("#password").val().length>20){
            mymsg("原始密码长度为6-20！",5);
            return;
        }
        else if ("" == $("#newPassword").val()) {
            mymsg("新密码不能为空！",5);
            return;
        }  else if(($("#newPassword").val()).length<6||($("#newPassword").val()).length>20){
            mymsg("新密码长度为6-20！",5);
            return;
        }
        else if ("" == $("#newPassword_again").val()) {
            mymsg("确认新密码不能为空！",5);
            return;
        }  else if(($("#newPassword_again").val()).length<6||($("#newPassword_again").val()).length>20){
            mymsg("确认新密码长度为6-20！",5);
            return;
        }
        else if ($("#newPassword").val() != $("#newPassword_again").val()) {
            mymsg("两次输入的新密码不一致！",5);
            return;
        }



            layer.confirm('确定修改密码?', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            doGet("/users/updatePassword", {
                oldpwd: $("#password").val(),
                newpwd: $("#newPassword").val()
            }, function (response) {
                if (!(Boolean(response.meta.success))) {
                    mymsg(response.meta.message,5);
                    return
                }
              //   mymsg(response.data,6);
                mymsg("修改密码成功！",6);

                window.close();

            });
        }, function () {
            layer.closeAll();
            });
    })


});



