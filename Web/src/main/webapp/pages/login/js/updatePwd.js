
var updatePwd = function () {

    //判断新密码与确认密码是否一致
    // if(($("#newpwd").val().test("/^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[_])[\\da-zA-Z~!@#$%^&*]{8,}$/")))
    if ( checkpassword($("#newpwd").val())){
        // { mymsg('密码长度不小于8位,且必须包含英文字符、数字及特殊符号“_”', 5);}
        // else
        if (!($("#newpwd").val() === $("#surepwd").val())) {
            mymsg('新密码与确认密码不一致', 5);
        } else if ($("#oldpwd").val() === $("#newpwd").val()) {
            mymsg('新密码与旧密码不能相同', 5);
        }
        else {
            var oldpwd = encryptByDES($("#oldpwd").val());
            var newpwd = encryptByDES($("#newpwd").val());
            doPost("users/updatepwd", JSON.stringify({
                oldpwd: oldpwd,
                newpwd: newpwd
            }), function (response) {
                if (response == true) {
                    window.top.location.href = "../../login.html";
                    layer.alert("修改密码成功");
                } else {
                    layer.alert("密码错误，修改密码失败");
                }
            })
        }
    }
}

checkpassword = function(v){
    var numasc = 0;
    var charasc = 0;
    var otherasc = 0;
    if(0==v.length){
        return "密码不能为空";
    }else {
        for (var i = 0; i < v.length; i++) {
            var asciiNumber = v.substr(i, 1).charCodeAt();
            if (asciiNumber >= 48 && asciiNumber <= 57) {
                numasc += 1;
            }
            if ((asciiNumber >= 65 && asciiNumber <= 90)||(asciiNumber >= 97 && asciiNumber <= 122)) {
                charasc += 1;
            }
            if ((asciiNumber >= 33 && asciiNumber <= 47)||(asciiNumber >= 58 && asciiNumber <= 64)||(asciiNumber >= 91 && asciiNumber <= 96)||(asciiNumber >= 123 && asciiNumber <= 126)) {
                otherasc += 1;
            }
        }
        if(0==numasc || 0==charasc || 0==otherasc) {
            mymsg('密码长度不小于8位,且必须包含英文字符、数字及特殊符号“_”', 5);
            return;
        }else{
            return true;
        }
    }
};
