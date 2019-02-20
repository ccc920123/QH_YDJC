/**
 *标题：用户管理
 *说明:
 *作者：zjc
 *日期：2017/10/14
 */
var config = {
    id: 1
    , parentid: 0
    , bmbh: '630100000000'//360000000000
    , name: '青海省公安厅交通警察支队'
    , levelnum: 11
    , pageNo: 1
    , pageSize: 10
    , typeList: null
    , deptToAdd: null
}
var dictionary = '1.0.0';
$(function () {
    initView();     //初始化高度
    initLayui();  //初始化layui
    loadBlackList();
    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadBlackList();
    });


});


/**
 * 加载页面
 */
function loadBlackList() {
    $('#tbody').html('');
    pageClose();
    loadBlack();  //加载 PDA
}

function loadBlack() {

    doGet('black_list/management/blacklist/getBlackList', {
        bmbh: config.bmbh,
        type: $("#black_type").val(),
        name: $("#name").val()
        , pageindex: config.pageNo
        , pagesize: config.pageSize
    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg('没有查到相关信息', 5);
            return;
        }
        pageOpen(response.data.total, function () {
            loadBlack();
        });

        loadBlackTable(response.data.list);
    });
}

function loadBlackTable(data) {
    var tbody = '';
    $.each(data, function (rowIndex, pda) {
        tbody += '' +
            '<tr>' +
            '    <td>' + pda.id + '</td>' +
            '    <td>' + pda.type + '</td>' +
            '    <td>' + pda.value + '</td>' +
            '    <td>' + pda.xrsj + '</td>' +
            '    <td>' + pda.ms + '</td>' +
            '    <td>' +
            '       ';
        tbody += '       <img id="del" src="../../imgs/management/delete.png" title="删除" pda_id="' + pda.id + '" mc="' + pda.version + '"   pdawym="' + pda.wym + '"onclick="deleteUser(this)">&nbsp;&nbsp;&nbsp;';
        tbody += '    </td>' + '</tr>';
    });
    $('#tbody').html(tbody);
}

function deleteUser(span) {
    var user_id = $(span).attr('pda_id');
    var str = '确定删除：' + $(span).attr('mc') + '？';
    layer.confirm(str, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        doDelete('black_list/management/blacklist/deleteBlack?blackId=' + user_id, {}, function (response) {
            layer.closeAll();
            if (Boolean(response.meta.success)) {
                mymsg("删除PDA成功", 6);
                loadBlackList();
            }
            else {
                mymsg(response.meta.message, 5);
            }

        });
    }, function () {
        layer.closeAll();
    });
}


/**获取页面高度*/
function initView() {
    var height = $(window).height();   //获取浏览器可用高度
    $('.deptLeft').css('height', height); //菜单树的高度
}

/**初始化layui*/
function initLayui() {
    layui.use('element', function () {
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

