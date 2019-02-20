/**
 *标题：用户管理
 *说明:
 *作者：zjc
 *日期：2017/10/14
 */
var config = {
    id: 1
    , parentid: 0
    , bmbh: '630000000000'//360000000000
    , name: '青海省公安厅交通警察总队'
    , levelnum: 11
    , pageNo: 1
    , pageSize: 10
    , typeList: null
    , deptToAdd: null
}
$(function () {
    initView();     //初始化高度
    initLayui();  //初始化layui

    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadList();
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        laydate.render({
            elem: '#ksrq',//开始日期
            type: 'date',
            value: new Date(),
            isInitValue: true
        });
        laydate.render({
            elem: '#jsrq', //结束日期
            type: 'date',
            value: new Date(),
            isInitValue: true
        });

    });
    loadList();

});



/**
 * 根据参数加载列表
 */
function loadList() {
    doGet('Operation/queryLoginStatisticsList', {
        kssj: $("#ksrq").val(),
        jssj: $("#jsrq").val()

    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }

        pageOpen(response.data.total, function () {
            loadList();
        });
        loadTable(response.data);
        echarts1(response.data);
    });
}
function echarts1(data) {
    var myChart = echarts.init(document.getElementById("statistics"));
    var xaxis = new Array();
    var dataY = new Array();
    var dataZ = new Array();
    for (var i = 0; i < data.length; i++) {
        xaxis.push(data[i].UNAME);
        dataY.push(data[i].CGCS);
        dataZ.push(data[i].SBCS);
    }
    var option = {
        title: {
            text: '登录日志统计'
        },
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
            name: '成功次数',
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
                    color: '#456ced'
                }
            },

            data: dataY
        },{
            name: '失败次数',
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
                    color: '#0b0e0e'
                }
            },

            data: dataZ
        }],

    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}

function loadTable(data) {
    var tbody = '';
    $.each(data, function (rowIndex, params) {
        tbody += '' +
            '<tr>' +
            '    <td>' + params.RN + '</td>' +
            '    <td>' + params.UNAME + '</td>' +
            '    <td>' + params.DLCS + '</td>' +
            '    <td>' + params.DCCS + '</td>' ;
        tbody += '</tr>';
    });
    $('#tbody').html(tbody);
}



/**获取页面高度*/
function initView() {
    var height = $(window).height();   //获取浏览器可用高度
    $('.deptLeft').css('height', height); //菜单树的高度
}

/**初始化layui*/
function initLayui() {
    layui.use('element', function () {
        // var element = layui.element;
    });
    layui.use('laypage', function () {
        laypage = layui.laypage;
    });
}

/**
 * 显示分页
 * 每次执行后当前页初始化为1
 * @param callback
 * @param count
 */
function pageOpen(count, callback) {
    $('#page').css('display', 'block');
    laypage.render({
        elem: 'page'
        , limit: config.pageSize
        , count: count //数据总数，从服务端得到
        , curr: config.pageNo
        , jump: function (obj, first) {
            //obj包含了当前分页的所有参数，比如：
            // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            // console.log(obj.limit); //得到每页显示的条数
            //首次不执行
            if (!first) {
                //do something
                config.pageNo = obj.curr;
                callback();
                return;
            }
        }
    });
    config.pageNo = 1;
}

function pageClose() {
    $('#page').css('display', 'none');
}

var laypage = null;



