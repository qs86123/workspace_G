/**
 * Created by Me1kro on 16/2/25.
 * 用于支持日期的运算, 为了实现 减12天,减12周,减12个月;
 */
if (!window.console || !console.firebug){
    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];

    window.console = {};
    for (var i = 0; i < names.length; ++i)
        window.console[names[i]] = function() {}
}
var dateMath = {
    "月": function (date, value) {
        "use strict";
        date.setMonth(date.getMonth() + value);
        return date;
    },
    "周": function (date, value) {
        "use strict";
        var wValue = value * 7;
        return dateMath["天"](date, wValue);
    },
    "天": function (date, value) {
        "use strict";
        date.setDate(date.getDate() + value);
        return date;
    },
    //执行日期类型校验
    _validate: function (date) {
        "use strict";
        if (date instanceof Date) {
            return true;
        } else {
            throw "can not process object that is not date";
        }
    }
};

// 获取第几周
function getWeek (date) {
    var yyyy = date.getFullYear();
    var mm = date.getMonth();
    var dd = date.getDate();
    var start = new Date(yyyy, 0, 1);
    var startDay = start.getDay();
    var end = new Date(yyyy, mm, dd);
    var duration = end - start;
    duration = duration / (1000 * 3600 * 24);
    duration += 1;  //因为是减的一年的第一天，duration需要加一
    // 如果当年的第一天从周五、六、日中的一天开始，开始几天不纳入今年的周数计算
    if (startDay >= 5 || startDay == 0) {
        duration = duration - (startDay == 0 ? 1 : 7 - startDay + 1);
    } else {
        // 当年的第一天从周二、三、四开始，开始几天补足一周，作为今年的第一周
        duration = duration + startDay - 1;
    }
    // 如果duration为负数，返回上一年最后一天的周数
    if (duration < 1) {
        return getWeek(new Date(yyyy-1, 11, 31));
    } else {
        duration = Math.ceil(duration / 7);
        duration = yyyy + '' + (duration + 100).toString().substring(1);
        return duration;
    }
}