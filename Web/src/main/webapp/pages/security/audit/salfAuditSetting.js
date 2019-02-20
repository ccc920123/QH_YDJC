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
    loadPDAManage();
    /**
     * 点击查询事件处理
     */
    $('#queryBtn').click(function () {
        loadPDAManage();
    });

    $('#addNew').click(function () {
        toAddDept();
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        //日期选择器
        laydate.render({
            elem: '#mmyxq'
            , type: 'date' //默认，可不填
            //,format: "YYYY-MM-DD"
        });
        laydate.render({
            elem: '#yhyxq'
            , type: 'date' //默认，可不填
            //,format: "YYYY-MM-DD"
        });

    });


});


/**
 * 加载页面
 */
function loadPDAManage() {
    $('#tbody').html('');
    pageClose();
    loadPDA();  //加载 PDA
}

function loadPDA() {

    doGet('salfaudit/getSalfAuditList', {
        salfType: $("#salfType").val()
        , pageNo: config.pageNo
        , pageSize: config.pageSize
    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg(response.meta.message, 5);
            return;
        }
        pageOpen(response.data.total, function () {
            loadPDA();
        });
        // dictionary = response.data.dictionary;
        loadPDATable(response.data.list);
    });
}

function loadPDATable(data) {


    var tbody = '';
    $.each(data, function (rowIndex, pda) {
        var type = '';
        if (pda.SAFETYPE == 1){
            type="高频访问";
        } else if (pda.SAFETYPE == 2){
            type="规定时间外访问";
        } else if (pda.SAFETYPE == 3){
            type="账户长期未使用";
        }
        tbody += '<tr>';
        tbody += '<td>' + type + '</td>' ;
        tbody += '<td>' + pda.SAFECHILDTYPE + '</td>' ;
        tbody += '<td>' + pda.SAFEVALUE + '</td>' ;
        tbody += '<td>' + pda.REMARKS + '</td>' ;
        if (pda.STATE == 1){
            tbody += '<td>是</td>' ;
        } else {
            tbody += '<td>否</td>' ;
        }
        tbody += '<td><img id="del" src="../../../imgs/management/edit.png" title="编辑" pid="' + pda.ID + '" onclick="editSalf(this)"> </td>';
        tbody += '</tr>';
    });
    $('#tbody').html(tbody);
}

function editSalf(span) {
    var id=$(span).attr("pid");
    window.top.layer.open({
        type: 2,
        shade: [0.5, '#000', false],
        area: ['900px', '600px'],
        title: "安全审计详情",
        content: '/Web/pages/security/audit/salfAuditEdit.html?id=' + id,
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);

        },
        cancel: function () {
        }

    })
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

