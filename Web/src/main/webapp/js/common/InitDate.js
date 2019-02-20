
/**
*@标题:初始化时间
*@Author:zjc
*@Date:2017/10/18 16:46
*
*/

/**
 * 初始化日期(不包含时间)
 * 设置在当前时间上增加或者减少时间，如：
 * 当前时间是2018年9月25日 调用方法getNowFormatDate（0,3,0）后得到的时间是2018年12月25日
 *
 * @param years
 * @param months
 * @param days
 * @returns {string}
 */
function getNowFormatDate(years, months, days) {
    var date = new Date();

    date.setFullYear(date.getFullYear() + years);
    date.setMonth(date.getMonth() + months);
    date.setDate(date.getDate() + days);

    var seperator1 = "-";
    // var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }

    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

/**
 * 初始化日期(包含时间)
 * 设置在当前时间上增加或者减少时间，如：
 * 当前时间是2018年9月25日11:39:41 调用方法getNowFormatDate（0,3,0）后得到的时间是2018年12月25日11:39:41
 * @param years
 * @param months
 * @param days
 * @returns {string}
 */
function getNowFormatTime(years, months, days) {
    var date = new Date();

    date.setFullYear(date.getFullYear() + years);
    date.setMonth(date.getMonth() + months);
    date.setDate(date.getDate() + days);

    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }

    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
       + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

