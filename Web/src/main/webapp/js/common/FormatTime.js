
/**
 *@标题:字符串日期格式化
 *@Author:zjc
 *@Date:2017/10/25
 *
 */

/**
 * 将字符串转为日期格式(不包含时间)
 * @param cellval
 * @returns {*}
 * @constructor
 */
function ChangeDateFormat(cellval) {
    if (cellval == null || cellval == "" || cellval == " ") {
        return "";
    }
    //字符串转日期格式，cellval要转为日期格式的字符串
    var date = eval('new Date(' + cellval.replace(/\d+(?=-[^-]+$)/,
        function (a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');

    // var date = new Date((cellval.replace("/Date(", "").replace(")/", ""), 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    return date.getFullYear() + "-" + month + "-" + currentDate;
}

/**
 * 将字符串转为日期格式(包含时间)
 * @param cellval
 * @returns {*}
 * @constructor
 */
function ChangeDateFormatHMS(cellval) {
    if (cellval == null || cellval == "" || cellval == " ") {
        return "";
    }
    //字符串转日期格式，cellval要转为日期格式的字符串
    var date = eval('new Date(' + cellval.replace(/\d+(?=-[^-]+$)/,
        function (a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');

    // var date = new Date((cellval.replace("/Date(", "").replace(")/", ""), 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hour=date.getHours()<10?"0" + date.getHours() : date.getHours();
    var min=date.getMinutes()<10?"0" + date.getMinutes() : date.getMinutes();
    var sec=date.getSeconds()<10?"0" + date.getSeconds() : date.getSeconds()
    console.log( date.getFullYear() + "-" + month + "-" + currentDate+" "+hour+":"+min+":"+sec);
    return date.getFullYear() + "-" + month + "-" + currentDate+" "+hour+":"+min+":"+sec;
}