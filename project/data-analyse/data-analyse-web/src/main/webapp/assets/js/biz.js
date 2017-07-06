/**
 * 示例图表demo
 *
 * @author zhangyang
 * @date 2016年7月29日
 */
var localhost = '.';
//把"月、周、天"转换为"monthly、weekly、daily"
function GetDateModel(cate) {
    switch (cate) {
        case "月":
            return "monthly";
            break;
        case "周":
            return "weekly";
            break;
        case "天":
            return "daily";
            break;
    }

}

//获取商品列表下拉框
function goodsList() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: localhost + "/conversions/goodsList",
        async: false,
        success: function (res) {
            var len = res.data.length;
            $('#sectionName-b').attr("length", '0');
            for (i = 0; i < len; i++) {
                $("#sectionName-b").append($('<option value=' + res.data[i].goodsId + '>' + res.data[i].goodsName + '</option>'));
            }
        }
    })
}

//echarts图表

//交易转化率B端
function converB() {
    var node = $('#b');
    var cate = $(".selectLength:eq(0)").html();
    var data = {
        userType: "员工",
        goodsId: $('#sectionName-b').val(),
        category: GetDateModel(cate),
        startTime: $('#setYear7').val(),
        endTime: $('#setYear8').val()
    };
    //折线图1
    $.ajax({
        type: "post",
        url: localhost + "/conversions/bussinessCR",   //"/assets/lib/echarts/jsondata/converRate.json",
        data: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
        success: function (result) {
            if (result.code == 200) {
                drawBarOrLine(node[0], "", result.data, [
                    '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                    '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                    "line", "line", "line", "line"], {
                    x: {
                        unit: ''
                    },
                    y: [{
                        name: "转化率",
                        unit: "%"
                    }],
                    unit: ["%", "", "", ""]
                }, null, 0);
            } else if (result.code == 0) {
                showError('b');
            }
        },
        error: function (err) {
            //alert('交易转化率B端请求不成功');
            showError('b');
        }
    });
}
//交易转化率B2C端
function converB2c() {
    var node = $('#b2c');
    var cate = $(".selectLength:eq(0)").html();
    var data = {
        userType: "员工亲友",
        goodsId: $('#sectionName-b').val(),
        category: GetDateModel(cate),
        startTime: $('#setYear7').val(),
        endTime: $('#setYear8').val()
    };
    //折线图2
    $.ajax({
        type: "post",
        url: localhost + "/conversions/bussinessCR",   //"/assets/lib/echarts/jsondata/converRate.json",
        data: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
        success: function (result) {
            if (result.code == 200) {
                drawBarOrLine(node[0], "", result.data, [
                    '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                    '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                    "line", "line", "line", "line"], {
                    x: {
                        unit: ''
                    },
                    y: [{
                        name: "转化率",
                        unit: "%"
                    }],
                    unit: ["%", "", "", ""]
                }, null, 0);
            } else if (result.code == 0) {
                showError('b2c');
            }
        },
        error: function (err) {
            //alert('交易转化率B2C端请求不成功');
            showError('b2c');
        }
    });

}
//交易转化率C端
function converC() {
    var node = $('#c');
    var cate = $(".selectLength:eq(0)").html();
    var data = {
        userType: "个人",
        goodsId: $('#sectionName-b').val(),
        category: GetDateModel(cate),
        startTime: $('#setYear7').val(),
        endTime: $('#setYear8').val()
    };
    //折线图3
    $.ajax({
        type: "post",
        url: localhost + "/conversions/bussinessCR",   //"/assets/lib/echarts/jsondata/converRate.json",
        data: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
        success: function (result) {
            if (result.code == 200) {
                drawBarOrLine(node[0], "", result.data, [
                    '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                    '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                    "line", "line", "line", "line"], {
                    x: {
                        unit: ''
                    },
                    y: [{
                        name: "转化率",
                        unit: "%"
                    }],
                    unit: ["%", "", "", ""]
                }, null, 0);
            } else if (result.code == 0) {
                showError('c');
            }
        },
        error: function (err) {
            //alert('交易转化率C端请求不成功');
            showError('c');
        }
    });
}
//交易转化率ALL
function converAll() {
    var node = $('#all');
    var cate = $(".selectLength:eq(0)").html();
    var data = {
        userType: "",
        goodsId: $('#sectionName-b').val(),
        category: GetDateModel(cate),
        startTime: $('#setYear7').val(),
        endTime: $('#setYear8').val()
    };
    //折线图4
    $.ajax({
        type: "post",
        url: localhost + "/conversions/bussinessCR",   //"/assets/lib/echarts/jsondata/converRate.json",
        data: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
        success: function (result) {
            if (result.code == 200) {
                drawBarOrLine(node[0], "", result.data, [
                    '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                    '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                    "line", "line", "line", "line"], {
                    x: {
                        unit: ''
                    },
                    y: [{
                        name: "转化率",
                        unit: "%"
                    }],
                    unit: ["%", "", "", ""]
                }, null, 0);
            } else if (result.code == 0) {
                showError('all');
            }
        },
        error: function (err) {
            //alert('交易转化率ALL请求不成功');
            showError('all');
        }
    });
}
//用户转化率
function converUser() {
    var node = $('#converUser');
    $.ajax({
        type: "get",
        url: localhost + "/conversions/userCR",     //"/assets/lib/echarts/jsondata/nest-pies.json",
        success: function (res) {
            if (res.code == 200) {
            drawNestPies(node[0], null, res.data1, res.data2, res.data3, res.data4, res.legend);
            } else if (res.code == 0) {
                showError('converUser');
            }
        },
        error: function (err) {
            //alert('用户转化率数据请求不成功');
            showError('converUser');
        }
    });
}
//平均消费金额
function cash() {
    var node = $('#consume');

    var cate = $(".selectLength:eq(1)").html();
    var data = {
        category: GetDateModel(cate),
        startTime: $('#setYear9').val(),
        endTime: $('#setYear10').val()
    };
    //柱状图
    $.ajax({
        type: "post",
        url: localhost + "/averageSpend",      //"/assets/lib/echarts/jsondata/money.json",
        data: JSON.stringify(data),
        headers: {'Content-Type': 'application/json'},
        success: function (res) {
            if (res.code == 200) {
                drawBarOrLine(node[0], "", res.data, [
                    '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                    '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                    "bar", "bar", "bar", "bar", "bar", "bar", "bar", "bar"], {
                    x: {
                        unit: ''
                    },
                    y: [{
                        name: "金额",
                        unit: ""
                    }],
                    unit: ["", "", "", "", "", "", "", ""]
                }, null, 0);
            } else if (res.code == 404) {
                showError('consume');
            }
        },
        error: function (err) {
            showError('consume');
        }
    });
}

$(function () {

    //初始化第四组时间控件
    var dp7 = $("#dp7"),
        dp8 = $('#dp8');
    initSelect($(".btnSelect:eq(0)"), [
        {"name": "月", "value": "yyyy-mm"}, {"name": "周", "value": "yyyy-mm-dd"}, {"name": "天", "value": "yyyy-mm-dd"}
    ], function (data, i) {
        dp7.off("changeDate");
        dp8.off("changeDate");
        initDatePicker(dp7, dp8, data[i].value, data[i].name, 'startTime');//根据选择的 修改日期插件 的日期格式
        //initDatePicker(dp5, data[i].value, data[i].name,'endTime');//根据选择的 修改日期插件 的日期格式
        if (data[i].name == "周") {//按周来选的
            dp7.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
            dp8.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
        }
        dp7.datepicker('show');
        dp7.on("changeDate", function () {
            //console.log(dp1.children("input").val());
        });
    }, null, "天");
    initDatePicker(dp7, dp8, "yyyy-mm-dd", "天", 'startTime');//初始化datePicker 按天 默认显示最近的星期一

    //初始化第五组时间控件
    var dp9 = $("#dp9"),
        dp10 = $('#dp10');
    initSelect($(".btnSelect:eq(1)"), [
        {"name": "月", "value": "yyyy-mm"}, {"name": "周", "value": "yyyy-mm-dd"}, {"name": "天", "value": "yyyy-mm-dd"}
    ], function (data, i) {
        dp9.off("changeDate");
        dp10.off("changeDate");
        initDatePicker(dp9, dp10, data[i].value, data[i].name, 'startTime');//根据选择的 修改日期插件 的日期格式
        //initDatePicker(dp5, data[i].value, data[i].name,'endTime');//根据选择的 修改日期插件 的日期格式
        if (data[i].name == "周") {//按周来选的
            dp9.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
            dp10.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
        }
        dp9.datepicker('show');
        dp9.on("changeDate", function () {
            //console.log(dp1.children("input").val());
        });
    }, null, "天");
    initDatePicker(dp9, dp10, "yyyy-mm-dd", "天", 'startTime');//初始化datePicker 按天 默认显示最近的星期一



    $("#btnQuery4").click(function () {
        converB();
        converB2c();
        converC();
        converAll();
    });
    $("#btnQuery5").click(function () {
        cash();
    });


    /*初始化页面选择下拉框*/
    goodsList();
    /**
     * 初始化所有图表
     */
    converB();    //交易转化率B端
    converB2c();  //交易转化率B2C端
    converC();    //交易转化率C端
    converAll();  //交易转化率ALL
    converUser(); //用户转化率
    cash();       //平均消费金额
});