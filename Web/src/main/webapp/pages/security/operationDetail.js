$(function () {
    var id = getQueryString("id");
    var logType = getQueryString("logType");

    if (logType == '3') {

        doGet("Operation/queryScyLogDetail", {id: id}, function (response) {
            if (response.meta.success) {
                $.each(response.data, function (key, value) {
                    $('#' + key).val(value);
                });
            }
        });

    } else {

    doGet("Operation/queryOperationDetail", {id: id}, function (response) {
        if (response.meta.success) {
            $.each(response.data, function (key, value) {
                $('#' + key).val(value);
            });
        }
    });

}

})