/**
 *标题：ajax封装
 *说明:
 * GET/POST/PUT/DELETE
 * GET：查询资源
 * POST:创建资源
 * PUT：更新资源
 * DELETE：删除资源
 */

// var urlPrefix = 'http://10.192.12.84:8080/Web/';
// var urlPrefix = 'http://localhost:8087/Web/';
var urlPrefix = '/Web/';

/**
 * GET：查询资源
 * @param url
 * @param data
 * @param callback
 */
function doGet(url, data, callback) {
    doRequest('GET', url, data, callback);
}

/**
 * POST:创建资源
 * @param url
 * @param data
 * @param callback
 */
function doPost(url, data, callback) {
    doRequest('POST', url, data, callback);
}

/**
 * PUT：更新资源
 * @param url
 * @param data
 * @param callback
 */
function doPut(url, data, callback) {
    doRequest('PUT', url, data, callback);
}

/**
 * DELETE：删除资源
 * @param url
 * @param data
 * @param callback
 */
function doDelete(url, data, callback) {
    doRequest('DELETE', url, data, callback);
}

/**
 * GET：查询资源 不返回弹窗提示
 * @param url
 * @param data
 * @param callback
 */
function doGet2(url, data, callback) {
    doRequest('GET', url, data, callback);
}



/**
 * GET/POST/PUT/DELETE
 * @param type
 * @param url
 * @param data
 * @param callback
 */
function doRequest(type, url, data, callback) {
    // var index = layer.load(0, {
    //     shade: [0.1, '#fff'] //0.1透明度的白色背景
    // });
    $.ajax({
        type: type,
        async : true,
        url: urlPrefix + url,
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        },
        data: data,
        dataType: 'json',
        success: function (response) {
            // layer.close(index);
           // if (!Boolean(response.meta.success)) {
             // layer.msg(response.meta.message, {icon: 5});
             //   return;
            //}
            callback(response);
        },
        beforeSend: function (xhr) {
            // index = layer.load(0, {shade: false});
        },
        complete: function () {
             // layer.close(index);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // layer.close(index);
            if (XMLHttpRequest.status == 403) {
                 layer.msg('登录失效，请重新登录', {icon:5});
                setTimeout(function () {
                    window.top.location.href = "/Web/pages/login.html";
                }, 1000);
                return;
            }
            if (XMLHttpRequest.status != 200){
                layer.msg('#ERROR', {icon: 5});
            }


        }
    });
}

/**
 * 功能描述：弹出框提示
 * @param msg  消息
 * @param icon  图标NUMB
 */
function mymsg(msg,iconNum) {
    top.layer.msg(msg, {icon: iconNum});
}

/**
 * 功能描述：千位分隔符，即 1344444 ---> 13,444,444
 * @param num
 * @returns {*|string|XML|void}
 */
function thousandBitSeparator(num) {
    return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
}

/**
 * 功能描述：获取URL中的参数
 * @param name
 * @returns {null}
 * @constructor
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}

/**
 * 导出excel
 * @param url
 */
function exportTableData(url) {
    //var token = $.cookie('X-Token');
    window.open(urlPrefix+url);
}

/**
 * 将时间戳转化为日期时间格式
 */
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y+M+D+h+m+s;
}
function timestampToTime2(timestamp) {
    var date = new Date(timestamp*1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y+M+D+h+m+s;
}