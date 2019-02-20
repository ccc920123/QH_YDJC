/**
 *标题：
 *说明:
 *作者：武伟
 *日期：2017/8/7
 */

var ELEMENT;

var DATA;
var HEIGHT = 0;  //页面高度  全局变量
var WIDTH = 0;

$(function () {

    doGet("common/getUserQx", {}, function (response) {

        if (!Boolean(response.meta.success)) {
            layer.msg('登录异常，请重新登录', {icon: 5});
            return;
        }

        $("#realname").html(response.data.realname);
        $("#userSsbmbh").html(response.data.ssbmname);

    });

    doGet("menus/menu/menuPression", {},  function (response) {
        var menuids= response.data;
        var mapId = new Array();
        $.each(menuids, function (index, menuId) {
            // mapId[index] = menuId.MENU_ID.toString();
            mapId.push(menuId.MENU_ID.toString());
        });
        if(response.success == false){
            parent.location.href = "./login.html";
        }else{
            if (mapId==undefined||mapId.length==0){

            } else {
                layui.data('pression', {
                    key: 'menuIds', value: mapId
                });
            }
        }
    });

    // 调用doGet方法，获取菜单
    doGet("menus/menu/role", {}, function (response) {

            DATA = response.data;

            // 1、获取一级菜单
            var headHtml = "";
            var length = DATA.length;

            // 1、获取一级菜单
            var type = "";
            var img = "";

            for (var i = length - 1; i >= 0; i--) {

                type = DATA[i].menuType;

                if (1 == DATA[i].levelnum) {

                    if (1 == DATA[i].mid) {
                        headHtml += '<li class="current"  id="one_' + DATA[i].menuId + '" mid="' + DATA[i].mid + '" doPath="' + DATA[i].navigateurl + '" name = "' + DATA[i].name + '"><img src="' + DATA[i].ico + '" alt="' + DATA[i].name + '" class="first_nav_img"><br><span>' + DATA[i].name + '</span></li>';
                    } else {
                        headHtml += '<li class=""  id="one_' + DATA[i].menuId + '" mid="' + DATA[i].mid + '" doPath="' + DATA[i].navigateurl + '" name = "' + DATA[i].name + '"><img src="' + DATA[i].ico + '" alt="' + DATA[i].name + '" class="first_nav_img"><br><span>' + DATA[i].name + '</span></li>';
                    }
                }
            }

            $("#oneMenu>ul").html(headHtml);

            // 绑定事件
            $("#oneMenu").find("li[id^='one_']").each(function () {

                if ("one_1" == $(this).attr("id")) {

                    // 关闭左侧菜单
                    closeLeftMenu();
                }

                $(this).click(function () {

                    $(this).addClass("current").siblings().removeClass("current");
                    var oneId = $(this).attr("id");

                    // getTwoAndThreeMenu(oneId);

                    if (1 == $(this).attr("mid")) {

                        getTwoAndThreeMenu(oneId);

                        // 关闭左侧菜单
                        closeLeftMenu();

                        ELEMENT.tabChange('biTab', "0"); //切换

                    } else {

                        getTwoAndThreeMenu(oneId);

                        if (0 != $(this).attr("mid")) {
                            openTabFunc($(this).attr("id"), $(this).attr("doPath"), $(this).attr("name"));
                        }

                        // 打开左侧菜单
                        openLeftMenu();

                    }

                });
            });
        }
    );

    // showTime(); // 显示系统时间
    initView();  // 初始化页面

    // ========================layui begin===================
    layui.use('element', function () {

        var $ = layui.jquery; // , ELEMENT = layui.element() Tab的切换功能，切换事件监听等，需要依赖element模块
        ELEMENT = layui.element;

        //触发事件
        var active = {
            tabOpen: openTabFunc
        };

        //一些事件监听 .prevObject.prevObject.selector
        ELEMENT.on('tab(biTab)', function (data) {

            var tabId = $(this).attr("lay-id");

            if ("four" == tabId.split("_")[0]) {

                $("li[id^='four']").removeClass("fourth_current");
                $("#" + tabId.replace("_tab", "")).addClass("fourth_current");
                $("#" + tabId.replace("_tab", "")).children().eq(0).attr("src", "../../imgs/home/r2.png").parent().siblings().children("img").attr("src", "../../imgs/home/r3.png");
                $("#" + tabId.replace("_tab", "")).parent().parent().siblings().find("ul>li>img").attr("src", "../../imgs/home/r3.png");

                var parentId = $("#" + tabId.replace("_tab", "")).parent().parent().attr("id");

                if ($("#" + parentId).children("ul").is(':hidden')) {
                    $("#" + parentId).click();
                }

            } else if ("0" == tabId) {  // 处理首页
                $("li[id^='one']").removeClass("current");
                $("#one_1").addClass("current");

            } else if ("one" == tabId.split("_")[0]) {

                $("li[id^='one']").removeClass("current");
                $("#" + tabId.replace("_tab", "")).addClass("current");
                getTwoAndThreeMenu(tabId.replace("_tab", ""));
            }

        });

        $('.site-demo-active').on('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });
    // =======================================layui end==============================

    /**
     * 用户信息
     * */
    $(".user_pic_div").mouseover(function () {
        $(this).css("box-shadow", "0px 2px 12px 1px #fff");
        $(".user_setting").css("display", "block");
    }).mouseout(function () {
        $(this).css("box-shadow", "none");
        $(".user_setting").css("display", "none");
    });

    //个人中心
    $("#personal").click(function () {
        // window.location.href="./other/information.html";
        top.layer.open({
            type: 2,
            shade: [0.5, '#000', false],
            area: ['900px', '450px'],
            title:"用户中心",
            content:'other/information.html'
        })
    });

    //下载中心
    $("#download").click(function () {
        openwindow('other/downloadCenter.html', '下载中心', 800, 500);
    });

    //修改密码
   $("#updatePassword").click(function(){
       top.layer.open({
           type: 2,
           shade: [0.5, '#000', false],
           area: ['400px', '230px'],
           title:"用户密码修改",
           content:'other/updatePassword.html'
       })
   })

    // 退出系统
    $("#loginout").click(function () {

        layer.confirm('确定退出系统吗?', {icon: 3, title: '提示'}, function (index) {
            //do something

            doPost("login/loginOut", {}, function (response) {
                // console.log(response.data);
                window.location.href = "../login.html";

            });

            layer.close(index);
        });

    });

    $('.btn-fold').on('click', function () {
        if ($(this).hasClass("active")) {
            // 关闭左侧菜单
            closeLeftMenu();
            $(this).removeClass("active");
        } else {

            // 打开左侧菜单
            openLeftMenu();
            $(this).addClass("active");

        }
    });

    // TAB切换，当是首页时打开左侧菜单栏，当不是首页时关闭
    $("ul.layui-tab-title").on("click", "li", function () {
        //do something here
        if ("syTab" != $(this).attr("id")) {
            openLeftMenu();
        } else {

            closeLeftMenu();
        }
    });

    window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['900px', '600px'],
        title: "登录信息",
        content: '/Web/pages/management/loginShowMessage.html',
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);

        },
        cancel: function () {
        }

    })
//实时刷新时间单位为毫秒
   //  setInterval('getHomeData()',10000);
});    //最外面括号

/**
 * 功能描述：关闭左侧菜单
 * 作者：齐遥遥
 * 时间：2018-04-18
 */
function closeLeftMenu() {
    $(".conRight").css('width', WIDTH - 10);
    $("#leftMenu").addClass("hidden");
    $("#leftMenuImg").attr("src", "../../imgs/home/btn-unfold.png").css("left", 1);
}

/**
 * 功能描述：打开左侧菜单
 * 作者：齐遥遥
 * 时间：2018-04-18
 */
function openLeftMenu() {
    $(".conRight").css('width', WIDTH - 235);
    $("#leftMenu").removeClass("hidden");
    $("#leftMenuImg").attr("src", "../../imgs/home/btn-fold.png").css("left", 220);
}

/**
 * 功能描述：根据一级菜单ID，生成二、三级菜单
 * 作者：齐遥遥
 * 时间：2018-04-10
 */
function getTwoAndThreeMenu(oneId) {

    var html2and3 = "";   // 二级和三级菜单的HTML
    var length = DATA.length;

    var pid = oneId.split("_")[1];

    for (var i = 0; i < length; i++) {

        if (DATA[i].parentid == pid) {

            html2and3 += '<li id="three_' + DATA[i].menuId + '">' +
                '<img src="../../imgs/home/r1.png" alt="" class="third_img">' +
                '<span>' + DATA[i].name + '</span>' +
                '<img src="../../imgs/home/arrow02.png" alt="" class="arrow_img fr">' +
                '<ul class="fourth_menu_ul">';

            for (var j = 0; j < length; j++) {

                if (DATA[j].parentid == DATA[i].menuId) {

                    if ("_blank" == DATA[j].menuType) {

                        html2and3 += '<li id="four_' + DATA[j].menuId + '" type="' + DATA[j].menuType + '" title="' + DATA[j].name + '" ><img src="' + DATA[j].ico + '" alt="" class="fourth_img">' +
                            '<span class="fourth_span"><a href="' + DATA[i].navigateurl + '" target="_blank" style="width: 100%">' + DATA[j].name + '</a></span>' + '</li>';

                    } else {
                        html2and3 += '<li id="four_' + DATA[j].menuId + '" doPath="' + DATA[j].navigateurl + '" type="' + DATA[j].menuType + '" title="' + DATA[j].name + '"><img src="' + DATA[j].ico + '" alt="" class="fourth_img">' +
                            '<span class="fourth_span">' + DATA[j].name + '</span>' + '</li>';
                    }

                }
            }

            html2and3 += "</ul></li>";
        }
    }

    $(".left_menu>ul").html(html2and3);

    //三级菜单点击事件
    $(".third_menu_ul>li").click(function () {
        $(this).children().eq(3).slideToggle();
    });

    //四级菜单
    $(".fourth_menu_ul>li").click(function (event) {
        event.stopPropagation();//阻止事件冒泡即可,不触发父亲点击事件

        //先清空所有四级菜单的选中状态，再给当前点击的四级菜单添加选中状态
        $(".fourth_menu_ul>li").removeClass("fourth_current");
        $(this).addClass("fourth_current");

        $(this).children().eq(0).attr("src", "../../imgs/home/r2.png").parent().siblings().children("img").attr("src", "../../imgs/home/r3.png");
        $(this).parent().parent().siblings().find("ul>li>img").attr("src", "../../imgs/home/r3.png");

        var id = $(this).attr("id");
        var doPath = $(this).attr("doPath");
        var name = $(this).children().eq(1).html();
        var type = $(this).attr("type");

        if ("_blank" != type) {
            openTabFunc(id, doPath, name);
        }

    });
}


/**
 * 打开tab
 * 参数：ID：菜单ID;doPath:菜单路径;name:菜单名称
 * 作者：齐遥遥
 * 时间：2017-08-08
 */
function openTabFunc(id, doPath, name) {

    var tabId = id + "_tab";

    // element = layui.element();

    if ("" == doPath) {
        doPath = "/Web/pages/404.html"
    }

    var hei = HEIGHT - 70;

    if ("sy2" == doPath) {

        ELEMENT.tabChange('biTab', "0"); //切换

    } else {

        // 新增一个Tab项
        if ($("#" + tabId).length > 0) {
            // ELEMENT.tabChange('biTab', tabId);

            ELEMENT.tabDelete("biTab", tabId);

            // 新增一个Tab项
            ELEMENT.tabAdd('biTab', {
                title: name
                ,
                content: '<iframe id="' + tabId + '" name="iframe" src="' + doPath + '" frameborder="0" style="width: 100%;height:"' + hei + 'px"' + '></iframe>'
                ,
                id: tabId
            });

        } else {

            // 新增一个Tab项
            ELEMENT.tabAdd('biTab', {
                title: name
                ,
                content: '<iframe id="' + tabId + '" name="iframe" src="' + doPath + '" frameborder="0" style="width: 100%;height:"' + hei + 'px"' + '></iframe>'
                ,
                id: tabId
            });
        }

        // 设置高度
        $("#" + tabId).height(hei);

        ELEMENT.tabChange('biTab', tabId); //切换
    }

}


//设置1秒调用一次showTime函数
// setInterval("showTime()", 100);   //放在函数showTime()外面

/**页面高度自适应*/
function initView() {

    HEIGHT = $(window).height() - 80;   //获取浏览器可用高度 - header高度
    $('#contentHome').css('height', HEIGHT);
    $('#content').css('height', HEIGHT);

    WIDTH = $(window).width();
    var contentwidth = WIDTH - 235;
    var contentheight = HEIGHT - 50;
    // $(".conRight").css('width', contentwidth);
    // $(".conRight").css('height', HEIGHT - 50);
    //
    // $("#echart1").css('width', (7 * 9) * contentwidth / (12 * 12))
    // $("#echart1").css('height', contentheight * 0.6)
    // $("#echart2").css('width', (3 * 9) * contentwidth / (12 * 12))
    // $("#echart2").css('height', contentheight * 0.6)
    // $("#echart3").css('width', 3 * contentwidth / 12)
    // $("#echart3").css('height', 7 * contentheight / 10)
    // $("#echart4").css('width', 3 * contentwidth / 12)
    // $("#echart4").css('height', 3 * contentheight / 10)

  //  getHomeData()

}

function getHomeData() {
    //柱状图
    doGet("alarm/queryStatistics/Bargraph", {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        echarts1(response.data);
    });
    //饼状图
    doGet("alarm/queryStatistics/Piechart", {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        echarts2(response.data);
    });
    //竖直柱状图
    doGet("alarm/queryStatistics/TenDayData", {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        echarts3(response.data);

    });
    //文书
    doGet("alarm/queryStatistics/Docment", {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }


        docment(response.data);

    });
    //最新信息
    doGet("alarm/queryStatistics/LastTime", {}, function (response) {
        if (!Boolean(response.meta.success)) {
            return;
        }
        lastTime(response.data);
    });
}

function echarts1(data) {
    var myChart = echarts.init(document.getElementById("echart1"));
    var xaxis = new Array();
    var dataY = new Array();
    for (var i = 0; i < data.length; i++) {
        xaxis.push(data[i].NAME);
        dataY.push(data[i].TOTAL);
    }
    var option = {
        xAxis: {
            data: xaxis,
            axisLabel: {//坐标轴刻度标签的相关设置。
                interval: 0,
                rotate: "45"
            }
        },
        yAxis: {},
        grid: {//直角坐标系内绘图网格
            show: true,//是否显示直角坐标系网格。[ default: false ]
                y:'5%',

            bottom: "28%" //
        },
        series: [{
            name: '预警反馈数量',
            type: 'bar',
            itemStyle: {
                normal: {
                    label: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: 'black'
                            }
                    },
                    color: '#1e6eff'
                }
            },

            data: dataY
        }],

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}

function echarts2(data) {
    var myChart = echarts.init(document.getElementById("echart2"));
    var xaxis = new Array();
    var dataY = new Array();
    for (var i = 0; i < data.length; i++) {
        var t = data[i].NAME;
        xaxis.push(t);
        var d = new Object()
        d.value = data[i].TOTAL;
        d.name = data[i].NAME;
        dataY.push(d);
    }
    var option = {

        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: xaxis

        },
        grid: {
            y:'5%'
        },
        series: [
            {
                name: '嫌疑车辆类型',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: dataY,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(255, 0, 0, 0.5)'
                    },
                    normal: {
                        label: {
                            show: false   //隐藏标示文字
                        },
                        labelLine: {
                            show: false   //隐藏标示线
                        }
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function echarts3(data) {
    var myChart = echarts.init(document.getElementById("echart3"));
    var xaxis = new Array();
    var dataY = new Array();
    for (var i = 0; i < data.length; i++) {
        xaxis.push(data[i].NAME);
        dataY.push(data[i].TOTAL);
    }
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },

        grid: {
            x:'5%',
            y:'5%',
            x2:'5%',
            y2:'5%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            splitLine: {show: false},//去除网格线
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            splitLine: {show: false},//去除网格线
            type: 'category',
            data: xaxis,
            inverse: true//反向
        },
        series: [
            {
                name: '预警数量',
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: '#305dff'
                    }
                },
                data: dataY
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    echarts4(xaxis, dataY);
}

function echarts4(Xdata, Ydata) {
    var myChart = echarts.init(document.getElementById("echart4"));
    var option = {
        xAxis: {
            type: 'category',
            data: Xdata,
            axisLabel: {//坐标轴刻度标签的相关设置。
                interval: 0,
                rotate: "45"
            }
        },
        yAxis: {
            show: false,
            type: 'value',
            axisTick: {
                show: false
            }
        },
        grid: {
            y:'5%'
        },
        series: [{
            data: Ydata,
            name: '预警数量',
            itemStyle: {
                normal: {
                    label: {

                        show: true,
                        position: 'top',
                        textStyle: {
                            color: 'black'
                        }

                    },
                    color: '#1ca0ff'
                }
            },
            type: 'line'
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function docment(data) {
    var html = "";
    if (data != null && data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            html += "<div class=\"grid-demo grid-demo-bg1\"  style=\"padding:3px;height:33px;line-height:33px;border-bottom: 1px dashed #ccc;margin-left:5px;width: 95%;vertical-align: middle;color: #0b0e0e\">" +
                "" + data[i].NAME + ":   <span style='color: #af0000;font-weight: bold'> " + data[i].TOTAL + "</span></div>\n"
        }
    }
    $('#docment').html(html);
}

function lastTime(data) {
    var html = "";
    if (data != null && data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            var name=data[i].NAME==undefined?"":data[i].NAME;
            var  realname=data[i].REALNAME ==undefined?'':data[i].REALNAME;
            var wfxw=data[i].WFXW==undefined?"":"("+data[i].WFXW+")";
            html += "<div class=\"grid-demo grid-demo-bg1\"  style=\" border-bottom: 1px dashed #ccc;padding:3px;margin-left:5px;width: 95%;vertical-align: middle;height:30px;line-height:30px;color: #0b0e0e\">" +
                "" + data[i].SCSJ + " [" + name + "] " +realname + "提交" +
                "" + data[i].OPTYPE  + wfxw + "</div>\n"
        }
    }
    $('#lastTime').html(html);
}
