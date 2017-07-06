/**
 * 分离JSONArray数据对象
 * 获取X轴显示项目名称
 */
function separateDataToX(jsonArray, itemName) {
    var arrayX = new Array();

    if (jsonArray.length == 0) {
        arrayX.push("没有相关数据");
    } else {
        for (var i in jsonArray) {
            arrayX.push(jsonArray[i][itemName]);
        }
    }
    return arrayX;
}
/**
 * 分离JSONArray数据对象
 * 重新组合成饼图数据格式
 */
function separateDataToPie(jsonArray, itemName, itemCount) {
    var pieArray = new Array();
    if (jsonArray.length > 0) {
        for (var i in jsonArray) {
            var arrayObj = {
                "name": jsonArray[i][itemName],
                "value": jsonArray[i][itemCount]
            };
            pieArray.push(arrayObj);
        }
    }
    return pieArray;
}

/**
 * 绘制echarts柱状和折线图
 *
 * @author laiwei
 * @date 2016年2月2日
 * @param node
 * @param title
 *            图标题
 * @param data
 *            数据
 * @param colorList
 *            颜色
 * @param types
 *            类型集合，对应于不同的数据结果，可选[bar,line]
 * @param labels
 *            标签 {x:{unit:"周"}, y:[{name:"使用时长(小时)",unit:""}], unit:["小时"] }
 *            x:x轴信息 y：y轴标题和单位 unit:tooltip提示单位
 * @param theme
 *            主题
 * @param yAxisIndex
 *            line折线图y轴在图表中的位置，0左1右，引用为柱状折线混合图时设置为"1"
 * @param toolTipData
 *            单个数据tooltip展示
 */
function drawBarOrLine(node, title, data, colorList, types, labels, theme, yAxisIndex, toolTipData) {

    $(node).css("height", "400px");

    var type = "bar";
    var myChart = echarts.init(node, theme ? theme : "");
    var option = {
        title: {
            text: title,
            x: 'left',
            y: 'top',
            textStyle: {
                color: '#333',
                fontStyle: 'normal',
                fontWeight: 700,
                //fontFamily: 'sans-serif',
                fontSize: 16
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: type == 'bar' ? 'shadow' : 'line',
                shadowStyle: {
                    color: 'rgba(150,150,150,0.3)',
                    width: 'auto',
                    type: 'default'
                }
            },
            formatter: function (params, ticket, callback) {
                var str = '';
                var colonIndex = ticket.lastIndexOf("_");
                var index = Number(ticket.substring(colonIndex + 1));
                str += params[0].name + "";
                if (labels.x) {
                    str += labels.x.unit;
                }
                for (var i = 0, l = params.length; i < l; i++) {
                    str += "<br/>" + params[i].seriesName + "：";
                    str += params[i].data + "" + labels.unit[i];
                }
                if (toolTipData) {
                    str += "<br/>" + toolTipData.itemName + "：";
                    str += toolTipData.itemCount[index] + "" + "次";
                }
                return str;
            }
        },
        legend: {
            show: true,
            data: [],
            selected: {},
            x: "center",
            y: 5,
            textStyle: {
                color: "#333",
                fontWeight: "bold"
            }
        },
        calculable: false,

        grid: {
            show: false,
            zlevel: 0,
            z: 2,
            left: "8%",
            top: 60,
            right: '8%',
            bottom: 60
        },

        xAxis: [{
            type: 'category',
            axisLabel: {
                show: true,
                //interval : 0,   //这里横坐标坐标轴标记可压缩显示
                //rotate : limitNumAndRotate(data.timeList,10,20),
                textStyle: {
                    color: '#333'
                },
                formatter: function (v) {
                    if (labels.x) {
                        return v + labels.x.unit;
                        if (labels.x.q) {
                            return labels.x.q + v + labels.x.unit;
                        }
                    } else {
                        return v;
                    }
                }
            },
            data: data.timeList
        }],
        yAxis: [],
        /*dataZoom: [{				//图标缩放
         type: 'inside',
         start: 0,
         end: 30
         }, {
         start: 0,
         end: 10
         }],*/
        series: []
    };
//
    for (var i = 0, l = labels.y.length; i < l; i++) {
        if (l >= 1) {
            option.yAxis.push({
                type: 'value',
                name: labels.y[i].name,
                axisLabel: {
                    formatter: '{value}' + labels.y[i].unit,
                    show: true,
                    interval: 1
                }
            });
        }
    }

    var lineYIndex = 0;
    /* 折线图Y */
    var $w = $(node).width();
    for (var i = 0, l = data.itemData.length; i < l; i++) {
        if (l > 0) {
            if (types[i] == "bar") {
                option.legend.data.push(data.itemData[i].itemName);
                option.series.push({
                    name: data.itemData[i].itemName,
                    type: "bar",
                    itemStyle: {
                        normal: {
                            color: colorList[i]
                        }
                    },
                    data: data.itemData[i].itemCount
                });
            } else if (types[i] == "line") {
                lineYIndex++;
                option.legend.data.push(data.itemData[i].itemName);
                option.series.push({
                    name: data.itemData[i].itemName,
                    type: 'line',
                    yAxisIndex: yAxisIndex,     //1,
                    smooth: true,
                    symbolSize: 0.2,
                    itemStyle: {
                        normal: {
                            color: colorList[i]
                        }
                    },
                    data: data.itemData[i].itemCount
                });
                /*option.yAxis.push({
                 min:'dataMin'+10000,
                 max:'dataMax'+10000
                 });*/
            }
        }
    }


    // 为echarts对象加载数据
    myChart.setOption(option, true);
    $(window).on("resize", function () {
        myChart.resize();
    });
}


/**
 * 绘制echarts饼图
 *
 * @author zhangyang
 * @date 2016年5月30日
 * @param node
 * @param title
 *            图标题
 * @param data
 *            数据
 * @param type
 *            类型集合，对应于不同的数据结果，选[pie]
 * @param tooltipName
 *            坐标轴小标记的名称
 * @param theme
 *            主题
 * @param xItemName
 *            饼图的图例名字
 * @param yItemCount
 *              饼图里各个块的数值
 * @param colorList
 *              饼图里各个块的颜色
 */
function drawPie(node, title, data, type, tooltipName, theme, xItemName, yItemCount, colorList) {

    $(node).css("height", "400px");

    //var type = "pie";
    var myChart = echarts.init(node, theme ? theme : "");
    var option = {
        title: {
            text: title,
            x: 'center',
            y: 'top'
        },
        tooltip: {
            trigger: 'item',
//            formatter: "{a} <br/>{b} : {c} ({d}%)"
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            show: false,
            orient: 'vertical',
            x: 'right',
            data: separateDataToX(data, xItemName)
        },
        calculable: false,
        series: [
            {
                name: tooltipName,
                type: type,
                clockWise: false,
                radius: '64%',
                center: ['51%', '55%'],
                data: separateDataToPie(data, xItemName, yItemCount),
                color: colorList,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option, true);
    $(window).on("resize", function () {
        myChart.resize();
    });
}

/**
 * 绘制echarts嵌套多环饼图
 *
 * @author zhangyang
 * @date 2016年9月8日
 * @param node
 * @param title
 *            图标题
 * @param data
 *            数据
 * @param type
 *            类型集合，对应于不同的数据结果，选[pie]
 * @param tooltipName
 *            坐标轴小标记的名称
 * @param theme
 *            主题
 * @param xItemName
 *            饼图的图例名字
 * @param yItemCount
 *              饼图里各个块的数值
 * @param colorList
 *              饼图里各个块的颜色
 */
function drawNestPies(node, theme, data1, data2, data3, data4, legend) {
    $(node).css("height", "400px");

    var myChart = echarts.init(node, theme ? theme : "");
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: legend
            //['购买人数','未购买人数','B端购买人数','B端未购买人数','B带C端购买人数','B带C端未购买人数','C端购买人数','C端未购买人数']
        },
        series: [
            {
                name: '用户',
                type: 'pie',
                selectedMode: 'single',
                radius: [0, '37%'],
                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data1
                /*[
                 {value:335, name:'购买人数'},
                 {value:1548, name:'未购买人数'}
                 ]*/
            },
            {
                name: '用户',
                type: 'pie',
                radius: ['46%', '55%'],
                label: {
                    normal: {
                        show: false,
                        position: 'inner',
                        textStyle: {
                            color: '#333',
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: "'Microsoft Yahei','sans-serif'",
                            fontSize: 12
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data2
                /*[
                 {value:335, name:'B端购买人数'},
                 {value:1310,name:''}
                 ]*/
            },
            {
                name: '用户',
                type: 'pie',
                radius: ['66%', '75%'],
                clockWise: false,
                label: {
                    normal: {
                        show: false,
                        position: 'inner',
                        textStyle: {
                            color: '#333',
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: "'Microsoft Yahei','sans-serif'",
                            fontSize: 12
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data3
                /*[
                 {value:335, name:'B带C端购买人数'},
                 {value:1310,name:''}
                 ]*/
            },
            {
                name: '用户',
                type: 'pie',
                radius: ['85%', '94%'],
                label: {
                    normal: {
                        show: false,
                        position: 'inner',
                        textStyle: {
                            color: '#333',
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: "'Microsoft Yahei','sans-serif'",
                            fontSize: 12
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data4
                /*[
                 {value:335, name:'C端购买人数'},
                 {value:1310,name:''}
                 ]*/
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option, true);
    $(window).on("resize", function () {
        myChart.resize();
    });
}

// 颜色转化
function transformer(color, op) {
    var splitArr = [], tempColor, map = {
        0: 0,
        1: 1,
        2: 2,
        3: 3,
        4: 4,
        5: 5,
        6: 6,
        7: 7,
        8: 8,
        9: 9,
        a: 10,
        b: 11,
        c: 12,
        d: 13,
        e: 14,
        f: 15,
        A: 10,
        B: 11,
        C: 12,
        D: 13,
        E: 14,
        F: 15
    };
    if (/(rgb){1}/.test(color)) {
        if (/(rgba){1}/.test(color)) {// 有透明度
            splitArr = (color.split("("))[1].split(",");
            // tempColor =
            // 'rgba('+(+splitArr[0])+','+(+splitArr[1])+','+(+splitArr[2])+','+(+splitArr[3])+')';
            tempColor = 'rgba(' + (+splitArr[0]) + ',' + (+splitArr[1]) + ','
                + parseInt(splitArr[2]) + ',' + op + ')';
        } else {// 无透明度
            splitArr = (color.split("("))[1].split(",");
            tempColor = 'rgba(' + (+splitArr[0]) + ',' + (+splitArr[1]) + ','
                + parseInt(splitArr[2]) + ',' + op + ')';
        }
    } else if (/#{1}/.test(color)) {
        color = color.replace("#", "");
        if (color.length == 6) {
            tempColor = 'rgba(' + (map[(color[0])] * 16 + map[(color[1])])
                + ',' + (map[(color[2])] * 16 + map[(color[3])]) + ','
                + (map[(color[4])] * 16 + map[(color[5])]) + ',' + op + ')';
        } else if (color.length == 3) {
            tempColor = 'rgba(' + (map[(color[0])] * 16 + map[(color[0])])
                + ',' + (map[(color[1])] * 16 + map[(color[1])]) + ','
                + (map[(color[2])] * 16 + map[(color[2])]) + ',' + op + ')';
        }
    }
    return tempColor;
}
// 超过一定数目 X轴标签旋转
function limitNumAndRotate(list, num, ang) {
    num = num || 14;
    ang = ang || 45;
    if (list.length >= num) {
        return ang;
    } else {
        return 0;
    }
}

//省份地图
function provenice(node, title, data) {
    var chart = echarts.init(document.getElementById('main'));
    chart.setOption(option = {
        title: {
            text: title,
            x: 'left'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>{c} (测试)'
        },
        visualMap: {
            min: 800,
            max: 50000,
            text: ['High', 'Low'],
            realtime: false,
            calculable: true,
            inRange: {
                color: ['lightskyblue', 'yellow', 'orangered']
            }
        },
        series: [
            {
                name: '测试',
                type: 'map',
                mapType: '四川', // 自定义扩展图表类型
                itemStyle: {
                    normal: {label: {show: true}},
                    emphasis: {label: {show: true}}
                },
                data: data,
                /*[
                 {name: '成都', value: 20057.34},
                 {name: '自贡', value: 15477.48},
                 {name: '攀枝花', value: 31686.1},
                 {name: '泸州', value: 6992.6},
                 {name: '德阳', value: 44045.49},
                 {name: '绵阳', value: 40689.64},
                 {name: '广元', value: 37659.78},
                 {name: '遂宁', value: 45180.97},
                 {name: '内江', value: 55204.26},
                 {name: '乐山', value: 21900.9},
                 {name: '南充', value: 4918.26},
                 {name: '眉山', value: 5881.84},
                 {name: '宜宾', value: 4178.01},
                 {name: "广安", value: 2227.92},
                 {name: '达州', value: 2180.98},
                 {name: '雅安', value: 9172.94},
                 {name: '巴中', value: 3368},
                 {name: '资阳', value: 806.98},
                 {name: '阿坝', value: 400.32},
                 {name: '甘孜', value: 246.48},
                 {name: '凉山', value: 382.9}
                 ],*/
                // 自定义名称映射
                nameMap: {
                    '成都市': '成都',
                    '自贡市': '自贡',
                    '攀枝花市': '攀枝花',
                    '泸州市': '泸州',
                    '德阳市': '德阳',
                    '绵阳市': '绵阳',
                    '广元市': '广元',
                    '遂宁市': '遂宁',
                    '内江市': '内江',
                    '乐山市': '乐山',
                    '南充市': '南充',
                    '眉山市': '眉山',
                    '宜宾市': '宜宾',
                    '广安市': '广安',
                    '达州市': '达州',
                    '雅安市': '雅安',
                    '巴中市': '巴中',
                    '资阳市': '资阳',
                    '阿坝藏族羌族自治州': '阿坝',
                    '甘孜藏族自治州': '甘孜',
                    '凉山彝族自治州': '凉山'
                }
            }
        ]
    });
}
//热力地图
function drawHeatMap(data) {
    var chart = echarts.init(document.getElementById('heatmap'));
    var geoCoordMap = {
        "海门": [121.15, 31.89],
        "鄂尔多斯": [109.781327, 39.608266],
        "招远": [120.38, 37.35],
        "舟山": [122.207216, 29.985295],
        "齐齐哈尔": [123.97, 47.33],
        "盐城": [120.13, 33.38],
        "赤峰": [118.87, 42.28],
        "青岛": [120.33, 36.07],
        "乳山": [121.52, 36.89],
        "金昌": [102.188043, 38.520089],
        "泉州": [118.58, 24.93],
        "莱西": [120.53, 36.86],
        "日照": [119.46, 35.42],
        "胶南": [119.97, 35.88],
        "南通": [121.05, 32.08],
        "拉萨": [91.11, 29.97],
        "云浮": [112.02, 22.93],
        "梅州": [116.1, 24.55],
        "文登": [122.05, 37.2],
        "上海": [121.48, 31.22],
        "攀枝花": [101.718637, 26.582347],
        "威海": [122.1, 37.5],
        "承德": [117.93, 40.97],
        "厦门": [118.1, 24.46],
        "汕尾": [115.375279, 22.786211],
        "潮州": [116.63, 23.68],
        "丹东": [124.37, 40.13],
        "太仓": [121.1, 31.45],
        "曲靖": [103.79, 25.51],
        "烟台": [121.39, 37.52],
        "福州": [119.3, 26.08],
        "瓦房店": [121.979603, 39.627114],
        "即墨": [120.45, 36.38],
        "抚顺": [123.97, 41.97],
        "玉溪": [102.52, 24.35],
        "张家口": [114.87, 40.82],
        "阳泉": [113.57, 37.85],
        "莱州": [119.942327, 37.177017],
        "湖州": [120.1, 30.86],
        "汕头": [116.69, 23.39],
        "昆山": [120.95, 31.39],
        "宁波": [121.56, 29.86],
        "湛江": [110.359377, 21.270708],
        "揭阳": [116.35, 23.55],
        "荣成": [122.41, 37.16],
        "连云港": [119.16, 34.59],
        "葫芦岛": [120.836932, 40.711052],
        "常熟": [120.74, 31.64],
        "东莞": [113.75, 23.04],
        "河源": [114.68, 23.73],
        "淮安": [119.15, 33.5],
        "泰州": [119.9, 32.49],
        "南宁": [108.33, 22.84],
        "营口": [122.18, 40.65],
        "惠州": [114.4, 23.09],
        "江阴": [120.26, 31.91],
        "蓬莱": [120.75, 37.8],
        "韶关": [113.62, 24.84],
        "嘉峪关": [98.289152, 39.77313],
        "广州": [113.23, 23.16],
        "延安": [109.47, 36.6],
        "太原": [112.53, 37.87],
        "清远": [113.01, 23.7],
        "中山": [113.38, 22.52],
        "昆明": [102.73, 25.04],
        "寿光": [118.73, 36.86],
        "盘锦": [122.070714, 41.119997],
        "长治": [113.08, 36.18],
        "深圳": [114.07, 22.62],
        "珠海": [113.52, 22.3],
        "宿迁": [118.3, 33.96],
        "咸阳": [108.72, 34.36],
        "铜川": [109.11, 35.09],
        "平度": [119.97, 36.77],
        "佛山": [113.11, 23.05],
        "海口": [110.35, 20.02],
        "江门": [113.06, 22.61],
        "章丘": [117.53, 36.72],
        "肇庆": [112.44, 23.05],
        "大连": [121.62, 38.92],
        "临汾": [111.5, 36.08],
        "吴江": [120.63, 31.16],
        "石嘴山": [106.39, 39.04],
        "沈阳": [123.38, 41.8],
        "苏州": [120.62, 31.32],
        "茂名": [110.88, 21.68],
        "嘉兴": [120.76, 30.77],
        "长春": [125.35, 43.88],
        "胶州": [120.03336, 36.264622],
        "银川": [106.27, 38.47],
        "张家港": [120.555821, 31.875428],
        "三门峡": [111.19, 34.76],
        "锦州": [121.15, 41.13],
        "南昌": [115.89, 28.68],
        "柳州": [109.4, 24.33],
        "三亚": [109.511909, 18.252847],
        "自贡": [104.778442, 29.33903],
        "吉林": [126.57, 43.87],
        "阳江": [111.95, 21.85],
        "泸州": [105.39, 28.91],
        "西宁": [101.74, 36.56],
        "宜宾": [104.56, 29.77],
        "呼和浩特": [111.65, 40.82],
        "成都": [104.06, 30.67],
        "大同": [113.3, 40.12],
        "镇江": [119.44, 32.2],
        "桂林": [110.28, 25.29],
        "张家界": [110.479191, 29.117096],
        "宜兴": [119.82, 31.36],
        "北海": [109.12, 21.49],
        "西安": [108.95, 34.27],
        "金坛": [119.56, 31.74],
        "东营": [118.49, 37.46],
        "牡丹江": [129.58, 44.6],
        "遵义": [106.9, 27.7],
        "绍兴": [120.58, 30.01],
        "扬州": [119.42, 32.39],
        "常州": [119.95, 31.79],
        "潍坊": [119.1, 36.62],
        "重庆": [106.54, 29.59],
        "台州": [121.420757, 28.656386],
        "南京": [118.78, 32.04],
        "滨州": [118.03, 37.36],
        "贵阳": [106.71, 26.57],
        "无锡": [120.29, 31.59],
        "本溪": [123.73, 41.3],
        "克拉玛依": [84.77, 45.59],
        "渭南": [109.5, 34.52],
        "马鞍山": [118.48, 31.56],
        "宝鸡": [107.15, 34.38],
        "焦作": [113.21, 35.24],
        "句容": [119.16, 31.95],
        "北京": [116.46, 39.92],
        "徐州": [117.2, 34.26],
        "衡水": [115.72, 37.72],
        "包头": [110, 40.58],
        "绵阳": [104.73, 31.48],
        "乌鲁木齐": [87.68, 43.77],
        "枣庄": [117.57, 34.86],
        "杭州": [120.19, 30.26],
        "淄博": [118.05, 36.78],
        "鞍山": [122.85, 41.12],
        "溧阳": [119.48, 31.43],
        "库尔勒": [86.06, 41.68],
        "安阳": [114.35, 36.1],
        "开封": [114.35, 34.79],
        "济南": [117, 36.65],
        "德阳": [104.37, 31.13],
        "温州": [120.65, 28.01],
        "九江": [115.97, 29.71],
        "邯郸": [114.47, 36.6],
        "临安": [119.72, 30.23],
        "兰州": [103.73, 36.03],
        "沧州": [116.83, 38.33],
        "临沂": [118.35, 35.05],
        "南充": [106.110698, 30.837793],
        "天津": [117.2, 39.13],
        "富阳": [119.95, 30.07],
        "泰安": [117.13, 36.18],
        "诸暨": [120.23, 29.71],
        "郑州": [113.65, 34.76],
        "哈尔滨": [126.63, 45.75],
        "聊城": [115.97, 36.45],
        "芜湖": [118.38, 31.33],
        "唐山": [118.02, 39.63],
        "平顶山": [113.29, 33.75],
        "邢台": [114.48, 37.05],
        "德州": [116.29, 37.45],
        "济宁": [116.59, 35.38],
        "荆州": [112.239741, 30.335165],
        "宜昌": [111.3, 30.7],
        "义乌": [120.06, 29.32],
        "丽水": [119.92, 28.45],
        "洛阳": [112.44, 34.7],
        "秦皇岛": [119.57, 39.95],
        "株洲": [113.16, 27.83],
        "石家庄": [114.48, 38.03],
        "莱芜": [117.67, 36.19],
        "常德": [111.69, 29.05],
        "保定": [115.48, 38.85],
        "湘潭": [112.91, 27.87],
        "金华": [119.64, 29.12],
        "岳阳": [113.09, 29.37],
        "长沙": [113, 28.21],
        "衢州": [118.88, 28.97],
        "廊坊": [116.7, 39.53],
        "菏泽": [115.480656, 35.23375],
        "合肥": [117.27, 31.86],
        "武汉": [114.31, 30.52],
        "大庆": [125.03, 46.58]
    };

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push(geoCoord.concat(data[i].value));
            }
        }
        return res;
    };

    chart.setOption(option = {
        title: {
            text: '全国主要城市空气质量',
            subtext: 'data from PM25.in',
            sublink: 'http://www.pm25.in',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        backgroundColor: '#404a59',
        visualMap: {
            min: 0,
            max: 500,
            splitNumber: 5,
            inRange: {
                color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
            },
            textStyle: {
                color: '#fff'
            }
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: true
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        },
        series: [{
            name: 'AQI',
            type: 'heatmap',
            coordinateSystem: 'geo',
            data: convertData(data
                /*[
                 {name: "海门", value: 9},
                 {name: "鄂尔多斯", value: 12},
                 {name: "招远", value: 12},
                 {name: "舟山", value: 12},
                 {name: "齐齐哈尔", value: 14},
                 {name: "盐城", value: 15},
                 {name: "赤峰", value: 16},
                 {name: "青岛", value: 18},
                 {name: "乳山", value: 18},
                 {name: "金昌", value: 19},
                 {name: "泉州", value: 21},
                 {name: "莱西", value: 21},
                 {name: "日照", value: 21},
                 {name: "胶南", value: 22},
                 {name: "南通", value: 23},
                 {name: "拉萨", value: 24},
                 {name: "云浮", value: 24},
                 {name: "梅州", value: 25},
                 {name: "文登", value: 25},
                 {name: "上海", value: 25},
                 {name: "攀枝花", value: 25},
                 {name: "威海", value: 25},
                 {name: "承德", value: 25},
                 {name: "厦门", value: 26},
                 {name: "汕尾", value: 26},
                 {name: "潮州", value: 26},
                 {name: "丹东", value: 27},
                 {name: "太仓", value: 27},
                 {name: "曲靖", value: 27},
                 {name: "烟台", value: 28},
                 {name: "福州", value: 29},
                 {name: "瓦房店", value: 30},
                 {name: "即墨", value: 30},
                 {name: "抚顺", value: 31},
                 {name: "玉溪", value: 31},
                 {name: "张家口", value: 31},
                 {name: "阳泉", value: 31},
                 {name: "莱州", value: 32},
                 {name: "湖州", value: 32},
                 {name: "汕头", value: 32},
                 {name: "昆山", value: 33},
                 {name: "宁波", value: 33},
                 {name: "湛江", value: 33},
                 {name: "揭阳", value: 34},
                 {name: "荣成", value: 34},
                 {name: "连云港", value: 35},
                 {name: "葫芦岛", value: 35},
                 {name: "常熟", value: 36},
                 {name: "东莞", value: 36},
                 {name: "河源", value: 36},
                 {name: "淮安", value: 36},
                 {name: "泰州", value: 36},
                 {name: "南宁", value: 37},
                 {name: "营口", value: 37},
                 {name: "惠州", value: 37},
                 {name: "江阴", value: 37},
                 {name: "蓬莱", value: 37},
                 {name: "韶关", value: 38},
                 {name: "嘉峪关", value: 38},
                 {name: "广州", value: 38},
                 {name: "延安", value: 38},
                 {name: "太原", value: 39},
                 {name: "清远", value: 39},
                 {name: "中山", value: 39},
                 {name: "昆明", value: 39},
                 {name: "寿光", value: 40},
                 {name: "盘锦", value: 40},
                 {name: "长治", value: 41},
                 {name: "深圳", value: 41},
                 {name: "珠海", value: 42},
                 {name: "宿迁", value: 43},
                 {name: "咸阳", value: 43},
                 {name: "铜川", value: 44},
                 {name: "平度", value: 44},
                 {name: "佛山", value: 44},
                 {name: "海口", value: 44},
                 {name: "江门", value: 45},
                 {name: "章丘", value: 45},
                 {name: "肇庆", value: 46},
                 {name: "大连", value: 47},
                 {name: "临汾", value: 47},
                 {name: "吴江", value: 47},
                 {name: "石嘴山", value: 49},
                 {name: "沈阳", value: 50},
                 {name: "苏州", value: 50},
                 {name: "茂名", value: 50},
                 {name: "嘉兴", value: 51},
                 {name: "长春", value: 51},
                 {name: "胶州", value: 52},
                 {name: "银川", value: 52},
                 {name: "张家港", value: 52},
                 {name: "三门峡", value: 53},
                 {name: "锦州", value: 54},
                 {name: "南昌", value: 54},
                 {name: "柳州", value: 54},
                 {name: "三亚", value: 54},
                 {name: "自贡", value: 56},
                 {name: "吉林", value: 56},
                 {name: "阳江", value: 57},
                 {name: "泸州", value: 57},
                 {name: "西宁", value: 57},
                 {name: "宜宾", value: 58},
                 {name: "呼和浩特", value: 58},
                 {name: "成都", value: 58},
                 {name: "大同", value: 58},
                 {name: "镇江", value: 59},
                 {name: "桂林", value: 59},
                 {name: "张家界", value: 59},
                 {name: "宜兴", value: 59},
                 {name: "北海", value: 60},
                 {name: "西安", value: 61},
                 {name: "金坛", value: 62},
                 {name: "东营", value: 62},
                 {name: "牡丹江", value: 63},
                 {name: "遵义", value: 63},
                 {name: "绍兴", value: 63},
                 {name: "扬州", value: 64},
                 {name: "常州", value: 64},
                 {name: "潍坊", value: 65},
                 {name: "重庆", value: 66},
                 {name: "台州", value: 67},
                 {name: "南京", value: 67},
                 {name: "滨州", value: 70},
                 {name: "贵阳", value: 71},
                 {name: "无锡", value: 71},
                 {name: "本溪", value: 71},
                 {name: "克拉玛依", value: 72},
                 {name: "渭南", value: 72},
                 {name: "马鞍山", value: 72},
                 {name: "宝鸡", value: 72},
                 {name: "焦作", value: 75},
                 {name: "句容", value: 75},
                 {name: "北京", value: 79},
                 {name: "徐州", value: 79},
                 {name: "衡水", value: 80},
                 {name: "包头", value: 80},
                 {name: "绵阳", value: 80},
                 {name: "乌鲁木齐", value: 84},
                 {name: "枣庄", value: 84},
                 {name: "杭州", value: 84},
                 {name: "淄博", value: 85},
                 {name: "鞍山", value: 86},
                 {name: "溧阳", value: 86},
                 {name: "库尔勒", value: 86},
                 {name: "安阳", value: 90},
                 {name: "开封", value: 90},
                 {name: "济南", value: 92},
                 {name: "德阳", value: 93},
                 {name: "温州", value: 95},
                 {name: "九江", value: 96},
                 {name: "邯郸", value: 98},
                 {name: "临安", value: 99},
                 {name: "兰州", value: 99},
                 {name: "沧州", value: 100},
                 {name: "临沂", value: 103},
                 {name: "南充", value: 104},
                 {name: "天津", value: 105},
                 {name: "富阳", value: 106},
                 {name: "泰安", value: 112},
                 {name: "诸暨", value: 112},
                 {name: "郑州", value: 113},
                 {name: "哈尔滨", value: 114},
                 {name: "聊城", value: 116},
                 {name: "芜湖", value: 117},
                 {name: "唐山", value: 119},
                 {name: "平顶山", value: 119},
                 {name: "邢台", value: 119},
                 {name: "德州", value: 120},
                 {name: "济宁", value: 120},
                 {name: "荆州", value: 127},
                 {name: "宜昌", value: 130},
                 {name: "义乌", value: 132},
                 {name: "丽水", value: 133},
                 {name: "洛阳", value: 134},
                 {name: "秦皇岛", value: 136},
                 {name: "株洲", value: 143},
                 {name: "石家庄", value: 147},
                 {name: "莱芜", value: 148},
                 {name: "常德", value: 152},
                 {name: "保定", value: 153},
                 {name: "湘潭", value: 154},
                 {name: "金华", value: 157},
                 {name: "岳阳", value: 169},
                 {name: "长沙", value: 175},
                 {name: "衢州", value: 177},
                 {name: "廊坊", value: 193},
                 {name: "菏泽", value: 194},
                 {name: "合肥", value: 229},
                 {name: "武汉", value: 273},
                 {name: "大庆", value: 279}
                 ]*/
            )
        }]
    });
}
//关系图简单示例
function drawGraph(data) {
    var mychart = echarts.init(document.getElementById('graph'));
    var option = {
        title: {
            text: 'Graph 简单示例'
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
            {
                type: 'graph',
                layout: 'none',
                symbolSize: 50,
                roam: true,
                label: {
                    normal: {
                        show: true
                    }
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 20
                        }
                    }
                },
                data: data.data
                /*[
                 {name: '节点1',value:100, x: 300, y: 300},
                 {name: '节点2',value:200, x: 800, y: 300},
                 {name: '节点3',value:300, x: 550, y: 100},
                 {name: '节点4',value:400, x: 550, y: 500}
                 ]*/,
                // links: [],
                links: data.links
                /*[
                 {source: '节点2', target: '节点1'},
                 {source: '节点1', target: '节点3'},
                 {source: '节点2', target: '节点3'},
                 {source: '节点2', target: '节点4'},
                 {source: '节点1', target: '节点4'}
                 ]*/,
                lineStyle: {
                    normal: {
                        opacity: 0.9,
                        width: 2,
                        curveness: 0
                    }
                }
            }
        ]
    };
    mychart.setOption(option, true)

}


//地图下钻实例
function mapView(countryData, cityData, maxData) {
    var myChart = echarts.init(document.getElementById('maps'), '');

    option = {
        //backgroundColor: '#404a59',
        title: {
            show: false,
            text: '用户地域分布',
            subtext: '用户所在地分布数据',
            x: 'center',
            textStyle: {
                color: '#404a59'
            }
        },
        tooltip: {//提示框组件
            trigger: 'item',
            formatter: '{b} : {c}人<br/>{d} : {e}人'
        },
        toolbox: {//工具栏
            show: false,
            orient: 'vertical',
            x: 'right',
            y: 'center',
            feature: {
                saveAsImage: {show: true},
                dataView: {show: true, readOnly: false}
            }
        },
        visualMap: {
            type: 'continuous',
            orient: 'vertical',
            align: 'top',
            x: 'right',
            min: 0,
            max: maxData,
            color: ['#d94e5d', '#eac736', '#50a3ba', '#e0eaf1'],
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true,
            splitNumber: 0
        },
        series: [
            {
                tooltip: {
                    trigger: 'item',
                    formatter: function (params, ticket, callback) {
                        //console.log(params,'kkkkkk');
                        var str = '';
                        //var colonIndex = ticket.lastIndexOf("_");
                        //var index = Number(ticket.substring(colonIndex + 1));
                        //str += params[0].name + "";

                        str += params.data.name + "：";
                        str += params.data.value + "" + "人" + "<br/>";
                        str += params.data.worker + "：";
                        str += params.data.value1 + "" + "人" + "<br/>";
                        str += params.data.parent + "：";
                        str += params.data.value2 + "" + "人" + "<br/>";
                        str += params.data.person + "：";
                        str += params.data.value3 + "" + "人" + "<br/>";
                        return str;
                    }
                },
                name: '全国',
                type: 'map',
                map: 'china',
                left: 'center',
                //top: 'top',
                height: '100%',
                roam: false,//地图不允许拖动
                selectedMode: 'single',
                label: {
                    normal: {show: false},//是否显示标签
                    emphasis: {show: true}
                },
                itemStyle: {
                    normal: {
                        label: {show: false},
                        //areaColor: '#219cd8',
                        borderColor: '#219cd8'
                    },
                    emphasis: {areaColor: '#00BFFF'}
                },
                data: countryData
                /*[
                 {name: '北京', value: 1243, selected:true},
                 {name: '天津', selected:false},
                 {name: '上海', selected:false},
                 {name: '重庆', selected:false},
                 {name: '河北', selected:false},
                 {name: '河南', selected:false},
                 {name: '云南', selected:false},
                 {name: '辽宁', selected:false},
                 {name: '黑龙江', selected:false},
                 {name: '湖南', selected:false},
                 {name: '安徽', selected:false},
                 {name: '山东', selected:false},
                 {name: '新疆', selected:false},
                 {name: '江苏', selected:false},
                 {name: '浙江', selected:false},
                 {name: '江西', selected:false},
                 {name: '湖北', selected:false},
                 {name: '广西', selected:false},
                 {name: '甘肃', selected:false},
                 {name: '山西', selected:false},//默认选中
                 {name: '内蒙古',selected:false},
                 {name: '陕西', selected:false},
                 {name: '吉林', selected:false},
                 {name: '福建', selected:false},
                 {name: '贵州', selected:false},
                 {name: '广东', selected:false},
                 {name: '青海', selected:false},
                 {name: '西藏', selected:false},
                 {name: '四川', selected:false},
                 {name: '宁夏', selected:false},
                 {name: '海南', selected:false},
                 {name: '台湾', selected:false},
                 {name: '香港', selected:false},
                 {name: '澳门', selected:false}
                 ]*/
            }
        ],
        animation: false
    };

    /*for (var i = 0, l = data.itemData.length; i < l; i++) {
     if (l > 0) {
     option.series.push({
     tooltip: {
     trigger: 'item',
     formatter: '{b} : {c}人'
     },
     name: '全国',
     type: 'map',
     map: 'china',
     left: 'left',
     top: 'top',
     roam: false,//地图不允许拖动
     selectedMode : 'single',
     label:{
     normal:{show:false},//是否显示标签
     emphasis:{show:true}
     },
     itemStyle: {
     emphasis: {areaColor: '#00BFFF'}
     },
     data: [{}]
     });

     }
     }*/

    myChart.setOption(option, true); //显示国家地图


    /*myChart.on('mapselectchanged', function (param) {//单击省份事件
     //console.log(param.selected['北京']);
     var selected = param.selected;
     var selectedProvince;
     var name;
     for (var i = 0, l = option.series[0].data.length; i < l; i++) {
     name = option.series[0].data[i].name;
     option.series[0].data[i].selected = selected[name];
     if (selected[name]) {
     selectedProvince = name;
     }
     }
     //alert(selectedProvince);//所选择省的名字
     if (typeof selectedProvince == 'undefined') {
     option.series.splice(1);
     option.legend = null;
     option.dataRange = null;
     myChart.setOption(option, true);//如果选择省份不存在则显示国家
     return;
     }
     option.series[1] = {
     name: '省份',
     type: 'map',
     map: selectedProvince,
     left: '50%',
     top: '15%',
     //width: '20%',
     height: '60%',
     roam: true,//地图允许拖动
     label: {
     emphasis: {
     show: true
     }
     },
     itemStyle: {
     normal: {
     label: {show: false},
     areaColor: '#219cd8',
     borderColor: '#219cd8'
     },
     emphasis: {
     label: {show: true},
     areaColor: '#CAFF70'
     }
     },
     tooltip: {
     trigger: 'item',
     formatter: function (params, ticket, callback) {
     var str = '';
     //var colonIndex = ticket.lastIndexOf("_");
     //var index = Number(ticket.substring(colonIndex + 1));
     //str += params[0].name + "";

     str += params.data.name + "：";
     str += params.data.value + "" + "人" + "<br/>";
     str += params.data.worker + "：";
     str += params.data.value2 + "" + "人" + "<br/>";
     str += params.data.parent + "：";
     str += params.data.value3 + "" + "人" + "<br/>";
     str += params.data.person + "：";
     str += params.data.value4 + "" + "人" + "<br/>";
     return str;
     }
     },
     data: cityData,
     /!*[//这里仅仅展示部分地区，完整代码请看下文
     {name: '重庆市', value: Math.round(Math.random() * 1000)},
     {name: '四川', value: Math.round(Math.random() * 1000)},
     {name: '北京市', value: Math.round(Math.random() * 1000)},
     {name: '海淀区', value: 4918.26},
     {name: '东城区', value: 4918.26},
     {name: '西城区', value: 4918.26},
     {name: '石景山区', value: 4918.26},
     {name: '丰台区', value: 4918.26},
     {name: '朝阳区', value: 4918.26},
     {name: '怀柔区', value: 4918.26}
     ]*!/
     // 自定义名称映射
     nameMap: {
     '成都市': '成都',
     '自贡市': '自贡',
     '攀枝花市': '攀枝花',
     '泸州市': '泸州',
     '德阳市': '德阳',
     '绵阳市': '绵阳',
     '广元市': '广元',
     '遂宁市': '遂宁',
     '内江市': '内江',
     '乐山市': '乐山',
     '南充市': '南充',
     '眉山市': '眉山',
     '宜宾市': '宜宾',
     '广安市': '广安',
     '达州市': '达州',
     '雅安市': '雅安',
     '巴中市': '巴中',
     '资阳市': '资阳',
     '阿坝藏族羌族自治州':'阿坝',
     '甘孜藏族自治州':'甘孜',
     '凉山彝族自治州':'凉山'
     }
     };
     /!*option.legend={
     show: false,
     orient: 'vertical',
     left: 'right',
     top: 'top',
     //align: 'top',
     data: [{
     name: '****数据',
     // 强制设置图形为圆
     icon: 'circle',
     // 设置文本为红色
     textStyle: {
     color: 'red'
     }
     }]
     };*!/
     option.visualMap = {
     type: 'continuous',
     orient: 'vertical',
     align: 'top',
     x: 'right',
     min: 0,
     max: 5000,
     color: ['#50a3ba', '#eac736', '#d94e5d'],
     text: ['高', '低'],           // 文本，默认为数值文本
     calculable: true,
     splitNumber: 0
     };
     myChart.setOption(option, true);
     });*/

    //选择地级市的单击事件
    myChart.on('click', function (param) {  //注释掉的部分为'mapselectchanged'方法
        console.log(param, "mmmmmmmm");
        /*var seriesName = param.seriesName;
         if (seriesName.trim() == "省份" || seriesName.trim() == "") {//由于全国地图和省地图都要触发这个事                                                                                                         件，所以要判断是省还是地级市
         alert(param.name);//浏览器提示地级市的名字
         }*/

        //var selected = param.selected;
        var selectedProvince;
        var seriesName;
        //var name;
        /*for (var i = 0, l = option.series[0].data.length; i < l; i++) {
         name = option.series[0].data[i].name;
         option.series[0].data[i].selected = selected[name];
         if (selected[name]) {
         selectedProvince = name;
         }
         }*/
        selectedProvince = param.name;
        seriesName = param.seriesName;
        //alert(selectedProvince);//所选择省的名字
        console.log(selectedProvince);
        if (typeof selectedProvince == 'undefined' || selectedProvince == '台湾省' || seriesName == '省份' ||
            selectedProvince == '' || selectedProvince == '上海市' || selectedProvince == '重庆市' ||
            selectedProvince == '北京市' || selectedProvince == '天津市') {
            //option.series.splice(1);
            option.series = {
                tooltip: {
                    trigger: 'item',
                    formatter: function (params, ticket, callback) {
                        //console.log(params,'kkkkkk');
                        var str = '';
                        //var colonIndex = ticket.lastIndexOf("_");
                        //var index = Number(ticket.substring(colonIndex + 1));
                        //str += params[0].name + "";

                        str += params.data.name + "：";
                        str += params.data.value + "" + "人" + "<br/>";
                        str += params.data.worker + "：";
                        str += params.data.value1 + "" + "人" + "<br/>";
                        str += params.data.parent + "：";
                        str += params.data.value2 + "" + "人" + "<br/>";
                        str += params.data.person + "：";
                        str += params.data.value3 + "" + "人" + "<br/>";
                        return str;
                    }
                },
                name: '全国',
                type: 'map',
                map: 'china',
                left: 'center',
                //top: 'top',
                height: '100%',
                roam: false,//地图不允许拖动
                selectedMode: 'single',
                label: {
                    normal: {show: false},//是否显示标签
                    emphasis: {show: true}
                },
                itemStyle: {
                    normal: {
                        label: {show: false},
                        //areaColor: '#219cd8',
                        borderColor: '#219cd8'
                    },
                    emphasis: {areaColor: '#00BFFF'}
                },
                data: countryData
                /*[
                 {name: '北京', value: 1243, selected:true},
                 {name: '天津', selected:false},
                 {name: '上海', selected:false},
                 {name: '重庆', selected:false},
                 {name: '河北', selected:false},
                 {name: '河南', selected:false},
                 {name: '云南', selected:false},
                 {name: '辽宁', selected:false},
                 {name: '黑龙江', selected:false},
                 {name: '湖南', selected:false},
                 {name: '安徽', selected:false},
                 {name: '山东', selected:false},
                 {name: '新疆', selected:false},
                 {name: '江苏', selected:false},
                 {name: '浙江', selected:false},
                 {name: '江西', selected:false},
                 {name: '湖北', selected:false},
                 {name: '广西', selected:false},
                 {name: '甘肃', selected:false},
                 {name: '山西', selected:false},//默认选中
                 {name: '内蒙古',selected:false},
                 {name: '陕西', selected:false},
                 {name: '吉林', selected:false},
                 {name: '福建', selected:false},
                 {name: '贵州', selected:false},
                 {name: '广东', selected:false},
                 {name: '青海', selected:false},
                 {name: '西藏', selected:false},
                 {name: '四川', selected:false},
                 {name: '宁夏', selected:false},
                 {name: '海南', selected:false},
                 {name: '台湾', selected:false},
                 {name: '香港', selected:false},
                 {name: '澳门', selected:false}
                 ]*/
            };
            option.visualMap = {
                type: 'continuous',
                orient: 'vertical',
                align: 'top',
                x: 'right',
                min: 0,
                max: maxData,
                dimension: 0,
                color: ['#d94e5d', '#eac736', '#50a3ba', '#e0eaf1'],
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true,
                splitNumber: 0
            };
            //option.dataRange = null;
            myChart.setOption(option, true);//如果选择省份不存在则显示国家
            return;
        } else if (seriesName == '全国') {
            option.series = {
                name: '省份',
                type: 'map',
                map: selectedProvince,
                left: 'center',
                //top: '15%',
                height: '100%',
                roam: false,//地图不允许拖动
                label: {
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        label: {show: false},
                        //areaColor: '#219cd8',
                        borderColor: '#219cd8'
                    },
                    emphasis: {
                        label: {show: true},
                        areaColor: '#CAFF70'
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params, ticket, callback) {
                        //console.log(params,'cccccccc');
                        var str = '';
                        //var colonIndex = ticket.lastIndexOf("_");
                        //var index = Number(ticket.substring(colonIndex + 1));
                        //str += params[0].name + "";

                        str += params.data.name + "：";
                        str += params.data.value + "" + "人" + "<br/>";
                        str += params.data.worker + "：";
                        str += params.data.value1 + "" + "人" + "<br/>";
                        str += params.data.parent + "：";
                        str += params.data.value2 + "" + "人" + "<br/>";
                        str += params.data.person + "：";
                        str += params.data.value3 + "" + "人" + "<br/>";
                        return str;
                    }
                },
                data: cityData
                /*[//这里仅仅展示部分地区，完整代码请看下文
                 {name: '重庆市', value: Math.round(Math.random() * 1000)},
                 {name: '四川', value: Math.round(Math.random() * 1000)},
                 {name: '北京市', value: Math.round(Math.random() * 1000)},
                 {name: '海淀区', value: 4918.26},
                 {name: '东城区', value: 4918.26},
                 {name: '西城区', value: 4918.26},
                 {name: '石景山区', value: 4918.26},
                 {name: '丰台区', value: 4918.26},
                 {name: '朝阳区', value: 4918.26},
                 {name: '怀柔区', value: 4918.26}
                 ]*/
                // 自定义名称映射
                /* nameMap: {
                 '成都市': '成都',
                 '自贡市': '自贡',
                 '攀枝花市': '攀枝花',
                 '泸州市': '泸州',
                 '德阳市': '德阳',
                 '绵阳市': '绵阳',
                 '广元市': '广元',
                 '遂宁市': '遂宁',
                 '内江市': '内江',
                 '乐山市': '乐山',
                 '南充市': '南充',
                 '眉山市': '眉山',
                 '宜宾市': '宜宾',
                 '广安市': '广安',
                 '达州市': '达州',
                 '雅安市': '雅安',
                 '巴中市': '巴中',
                 '资阳市': '资阳',
                 '阿坝藏族羌族自治州': '阿坝',
                 '甘孜藏族自治州': '甘孜',
                 '凉山彝族自治州': '凉山'
                 }*/
            };
            /*option.legend={
             show: false,
             orient: 'vertical',
             left: 'right',
             top: 'top',
             //align: 'top',
             data: [{
             name: '****数据',
             // 强制设置图形为圆
             icon: 'circle',
             // 设置文本为红色
             textStyle: {
             color: 'red'
             }
             }]
             };*/
            option.visualMap = {
                type: 'continuous',
                orient: 'vertical',
                align: 'top',
                x: 'right',
                min: 0,
                max: maxData,
                dimension: 0,
                seriesIndex: 0,
                hoverLink: false,
                color: ['#d94e5d', '#eac736', '#50a3ba', '#e0eaf1'],
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true,
                splitNumber: 0
            };
            myChart.setOption(option, true);
            return;
        }


    });
}