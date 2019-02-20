$(function () {
    //查询用户信息
    doPost("users/information",{},function (response) {
        console.log(response);

        $.each(response,function (key,value) {
            $('#'+key).val(value);
        })

        $.each(response.user,function (key,value) {
            $('#'+key).val(value);
        })

    })
})