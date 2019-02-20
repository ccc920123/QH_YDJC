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
    doGet('salfaudit/salfAuditLogList', {
        safetype:$("#salfType").val(),
        pageNo: config.pageNo,
        pageSize: config.pageSize

    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }

        pageOpen(response.data.total, function () {
            loadList();
        });

        loadTable(response.data.list);
    });
}


function loadTable(data) {
    var tbody = '';
    $.each(data, function (rowIndex, params) {

        tbody += '' +
            '<tr>' +
            '    <td width="15%">' + params.safechildtype + '</td>' +
            '    <td width="10%">' + params.userid + '</td>' +
            '    <td width="55%">' + params.safecontent + '</td>' +
            '    <td width="10%">' + params.cip + '</td>' +
            '    <td width="10%">' + timestampToTime(params.ctime) + '</td>' ;
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

