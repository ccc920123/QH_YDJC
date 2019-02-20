/**
 * 电子监控决定书
 */

$(function () {

    var id = getQueryString("id");
    $('#close').click(function () {
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);
    });
    $('#submit').click(function () {
        // 获得frame索引
        doPost("/salfaudit/saveSalfAudit", JSON.stringify({
            id: id,
            safevalue: $("#safevalue").val(),
            remarks: $("#remarks").val(),
            state: $("input[name='state']:checked").val()
        }),function (response) {
            if (!Boolean(response.meta.success)) {
                mymsg(response.data, 5);
                return;
            }
            mymsg(response.data, 6);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        })

    });
    doGet("/salfaudit/getSalfAuditDetail", {id: id}, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }
        $.each(response.data, function (key, value) {
            $("#" + key).val(value);
        });
        $("input:radio[value='" + response.data.STATE + "']").attr('checked', 'true');
    })
})

