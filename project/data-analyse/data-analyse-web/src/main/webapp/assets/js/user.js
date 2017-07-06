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

//截至当前的注册用户数
function userCnt() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: localhost + "/allUserCount",
        success: function (res) {
            /*$("#bUser").text(res.data[0].userCount);
             $("#b2cUser").text(res.data[1].userCount);
             $("#cUser").text(res.data[2].userCount);
             $("#allUser").text(res.data[3].userCount);*/
            var len = res.data.length;
            $('#regCount').attr("length", '0');
            for (i = 0; i < len; i++) {
                $("#regCount").append($('<tr>' + '<td>' + res.data[i].userType + '</td>' + '<td>' + res.data[i].userCount + '</td>' + '</tr>'));
                //$("#regCount").append($('<td>' + res.data[i].userCount + '</td>'));
            }
        }
    })
}
//获取访问页面下拉框
function getMissiveType() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: localhost + "/pageList",        //"/assets/lib/echarts/jsondata/page-list.json",
        async: false,
        success: function (res) {
            var len = res.data.length;
            $('#sectionName-s').attr("length", '0');
            for (i = 0; i < len; i++) {
                $("#sectionName-s").append($('<option value=' + res.data[i].key + '>' + res.data[i].sectionName + '</option>'));
            }
        }
    });
    console.log($('#sectionName-s').val(), 'pppppp')
}

//echarts图表
//注册用户数
function reg() {
    var node = $('#reg');

    var cate = $(".selectLength:eq(0)").html();
    var data = {
        category: GetDateModel(cate),
        startTime: $('#setYear1').val(),
        endTime: $('#setYear2').val()
    };
    //柱状图
    $.ajax({
        type: "post",
        url: localhost + "/registerCount",          //"/assets/lib/echarts/jsondata/money.json",
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
                        name: "人数",
                        unit: ""
                    }],
                    unit: ["", "", "", "", "", "", "", ""]
                }, null, 0);
            } else if (res.code == 404) {
                showError('reg');
            }
        },
        error: function (err) {
            showError('reg');
        }
    });
}
//页面访问趋势
function viewCnt() {
    var node = $('#line');

    /*var start = '';
     var end = '';
     //日期值去掉"-"
     function RegExp_start(){
     var _start = $('#setYear3').val();
     var _end = $('#setYear4').val();
     if(_start.indexOf("-")>=0){
     start ='';
     var ff = _start.split("-");
     for(var i in ff){
     start += ff[i];
     }
     }else {
     start = _start;
     }
     if(_end.indexOf("-")>=0){
     end = '';
     var en = _end.split("-");
     for(var k in en){
     end += en[k];
     }
     }else {
     end = _end;
     }

     }
     RegExp_start();

     var change = [];*/

    var cate = $(".selectLength:eq(1)").html();
    var page = $('#sectionName-s').val();
    console.log(page);
    var data = {
        pageName: page,
        category: GetDateModel(cate),
        startTime: $('#setYear3').val(),
        endTime: $('#setYear4').val()
    };
    //折线图
    $.ajax({
        type: "post",
        url: localhost + "/pageBrowseTrend",  //"/assets/lib/echarts/jsondata/page-stay.json",
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
                        name: "次数",
                        unit: ""
                    }],
                    unit: ["", "", "", ""]
                }, null, 0);
            } else if (result.code == 404) {
                showError('line');
            }
        },
        error: function () {
            showError('line');
        }
    });

}
//活跃用户数
function actUser() {
    var node = $('#bar');

    var cate = $(".selectLength:eq(2)").html();
    var data = {
        category: GetDateModel(cate),
        startTime: $('#setYear5').val(),
        endTime: $('#setYear6').val()
    };
    //柱状图
    $.ajax({
        type: "post",
        url: localhost + "/active/userCount",   //"/assets/lib/echarts/jsondata/act-user.json",
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
                        name: "人数",
                        unit: ""
                    }],
                    unit: ["", "", "", "", "", "", "", ""]
                }, null, 0);
            } else if (res.code == 404) {
                showError('bar');
            }

        },
        error: function (err) {
            showError('bar');
        }
    });

    /*//热力地图
     $.ajax({
     type: "get",
     url: "/draw/drawheatchart",
     success: function (result) {
     drawHeatMap(result.data.listData);
     },
     error: function (err) {
     alert('热力地图数据请求不成功')
     }
     });
     //关系图
     $.ajax({
     type: "get",
     url: "/draw/drawrelationchart",
     success: function (res) {
     console.log(res);
     drawGraph(res.data);
     },
     error: function (err) {
     alert('关系图数据请求不成功')
     }
     });*/
}
//用户分布情况
function userLocal() {
    //var node = $('#maps');
    $.ajax({
        type: "get",
        url: localhost + "/map",  //"/assets/lib/echarts/jsondata/maps.json",
        success: function (res) {
            if (res.code == 200) {
                mapView(res.countryData, res.cityData, res.maxData);
            } else if (res.code == 0) {
                showError('user');
            }
        },
        error: function (err) {
            //alert('用户转化率数据请求不成功');
            showError('user');
        }
    });
}

$(function () {

    //初始化第一组时间控件
    var dp1 = $("#dp1"),
        dp2 = $('#dp2');
    initSelect($(".btnSelect:eq(0)"), [
        {"name": "月", "value": "yyyy-mm"}, {"name": "周", "value": "yyyy-mm-dd"}, {"name": "天", "value": "yyyy-mm-dd"}
    ], function (data, i) {
        dp1.off("changeDate");
        dp2.off("changeDate");
        initDatePicker(dp1, dp2, data[i].value, data[i].name, 'startTime');//根据选择的 修改日期插件 的日期格式
        //initDatePicker(dp2, data[i].value, data[i].name,'endTime');//根据选择的 修改日期插件 的日期格式
        if (data[i].name == "周") {//按周来选的
            dp1.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
            dp2.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
        }
        dp1.datepicker('show');
        dp1.on("changeDate", function () {
            //console.log(dp1.children("input").val());
        });
    }, null, "天");
    initDatePicker(dp1, dp2, "yyyy-mm-dd", "天", 'startTime');//初始化datePicker 按天 默认显示最近的星期一


    //初始化第二组时间控件
    var dp3 = $("#dp3"),
        dp4 = $('#dp4');
    initSelect($(".btnSelect:eq(1)"), [
        {"name": "月", "value": "yyyy-mm"}, {"name": "周", "value": "yyyy-mm-dd"}, {"name": "天", "value": "yyyy-mm-dd"}
    ], function (data, i) {
        dp3.off("changeDate");
        dp4.off("changeDate");
        initDatePicker(dp3, dp4, data[i].value, data[i].name, 'startTime');//根据选择的 修改日期插件 的日期格式
        //initDatePicker(dp3, data[i].value, data[i].name,'endTime');//根据选择的 修改日期插件 的日期格式
        if (data[i].name == "周") {//按周来选的
            dp3.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
            dp4.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
        }
        dp3.datepicker('show');
        dp3.on("changeDate", function () {
            //console.log(dp1.children("input").val());
        });
    }, null, "天");
    initDatePicker(dp3, dp4, "yyyy-mm-dd", "天", 'startTime');//初始化datePicker 按天 默认显示最近的星期一

    //初始化第三组时间控件
    var dp5 = $("#dp5"),
        dp6 = $('#dp6');
    initSelect($(".btnSelect:eq(2)"), [
        {"name": "月", "value": "yyyy-mm"}, {"name": "周", "value": "yyyy-mm-dd"}, {"name": "天", "value": "yyyy-mm-dd"}
    ], function (data, i) {
        dp5.off("changeDate");
        dp6.off("changeDate");
        initDatePicker(dp5, dp6, data[i].value, data[i].name, 'startTime');//根据选择的 修改日期插件 的日期格式
        //initDatePicker(dp5, data[i].value, data[i].name,'endTime');//根据选择的 修改日期插件 的日期格式
        if (data[i].name == "周") {//按周来选的
            dp5.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
            dp6.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
        }
        dp5.datepicker('show');
        dp5.on("changeDate", function () {
            //console.log(dp1.children("input").val());
        });
    }, null, "天");
    initDatePicker(dp5, dp6, "yyyy-mm-dd", "天", 'startTime');//初始化datePicker 按天 默认显示最近的星期一


    $("#btnQuery1").click(function () {
        reg();
    });
    $("#btnQuery2").click(function () {
        viewCnt();
    });
    $("#btnQuery3").click(function () {
        actUser();
    });


    /*初始化页面选择下拉框*/
    getMissiveType();
    /**
     * 初始化所有图表
     */
    userCnt();    //截至当前的注册用户数
    reg();        //注册用户数
    viewCnt();    //页面访问趋势
    actUser();    //活跃用户数
    userLocal();  //用户分布
});