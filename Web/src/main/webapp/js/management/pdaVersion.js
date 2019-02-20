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
    justPression();
    initView();     //初始化高度
    initLayui();  //初始化layui

    if (justPressionBy("1122")){
        $("#queryBtn").removeAttr("style");
    } else {
        $("#queryBtn").css("display", "none");
    }
    if (justPressionBy("1120")){
        $("#addNew").removeAttr("style");
    } else {
        $("#addNew").css("display", "none");
    }

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

    doGet('pdas/management/pdaVersion/getPdaVersion', {
        bmbh: config.bmbh,
        version: $("#pdaversion").val(),
        des: $("#pdadesc").val()
        , pageindex: config.pageNo
        , pagesize: config.pageSize
    }, function (response) {
        if (!Boolean(response.meta.success)) {
            mymsg('没有查到相关信息', 5);
            return;
        }
        pageOpen(response.data.total, function () {
            loadPDA();
        });
        dictionary = response.data.dictionary;
        loadPDATable(response.data.datas);
    });
}

function loadPDATable(data) {

    var shanchu = justPressionBy("1123");

    var tbody = '';
    $.each(data, function (rowIndex, pda) {

        var schtml = '<img id="del" src="../../imgs/management/delete.png" title="删除" pda_id="' + pda.id + '" mc="' + pda.version + '"   pdawym="' + pda.wym + '"onclick="deleteUser(this)">&nbsp;&nbsp;&nbsp;';
        var html = '';
        if (shanchu){
            html += schtml;
        }

        tbody += '' +
            '<tr>' +
            '    <td>' + pda.version + '</td>' +
            '    <td>' + pda.dictionary + '</td>' +
            '    <td>' + pda.description + '</td>' +
            '    <td>' + pda.czryzh + '</td>' +
            '    <td>' + pda.czsj + '</td>' +
            '    <td>' + html + '    </td>' +
            '</tr>';
    });
    $('#tbody').html(tbody);
}

function deleteUser(span) {
    var user_id = $(span).attr('pda_id');
    var str = '确定删除：' + $(span).attr('mc') + '？';
    layer.confirm(str, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        doDelete('pdas/management/pdaVersion/deletepda?pdaversionid=' + user_id, {}, function (response) {
            layer.closeAll();
            if (Boolean(response.meta.success)) {
                mymsg("删除PDA成功", 6);
                loadPDAManage();
            }
            else {
                mymsg(response.meta.message, 5);
            }

        });
    }, function () {
        layer.closeAll();
    });
}

/**
 * 添加机构
 */
function toAddDept() {
    emptyArgs();
    editDept();
}


function editDept() {
    //  alert(user_id);


    var title = "新增PDA版本信息";
    $('#ssbmbh').val(config.bmbh);
    layer.open({
        type: 1,
        shade: [0.5, '#000', false],
        area: ['800px', '400px'],
        title: title,
        // maxmin: true,
        content: $("#addNewDiv")
        , zIndex: layer.zIndex //重点1
        , success: function (layero) {
            layer.setTop(layero); //重点2
        },
        end: function () {
            $("#addNewDiv").hide();
        }
    });
    $("#dicVersion").val(dictionary);
    $('#confirmCancel').unbind('click');
    $('#confirmCancel').bind('click', function () {
        layer.closeAll();
    });
    $('#confirmSubmit').unbind('click');
    $('#confirmSubmit').bind('click', function () {

        if (String($('#addVersion').val()).length < 4
            || String($('#addVersion').val()).length > 10
        ) {
            mymsg('请输入长度范围为4~10的PDA版本号', 5);
            return;
        }
        if (String($('#dicVersion').val()).length < 4
            || String($('#dicVersion').val()).length > 10
        ) {
            mymsg('请输入长度范围为4~10的字典版本号', 5);
            return;
        }

        if (String($('#addDesc').val()).length < 2

        ) {
            mymsg('请输入长度范围为>2的PDA描述', 5);
            return;
        }
        if(document.getElementById("file").value==null||document.getElementById("file").value==""
            ||document.getElementById("file").files[0]==null){
            mymsg('请选择上传文件', 5);
            return;
        }
        Upload();


    });


}

function Upload() {
    var url = urlPrefix + 'pdas/management/pdaVersion/uploadapk';
    var form = new FormData($("test")[0]);
    form.append("file", document.getElementById("file").files[0]);
    var vesion = $('#addVersion').val();
    var dictionary = $('#dicVersion').val();
    var desction = $('#addDesc').val();
    form.append("version", vesion);
    form.append("bmbh", config.bmbh);
    form.append("desction", desction);
    form.append("dictionary", dictionary);
    var index = layer.load(0, {shade: false});
    $.ajax({
        url: url,
        data: form,
        type: 'post',
        processData: false,
        contentType: false,
        cache: false,//上传文件无需缓存
        success: function (response) {
            layer.close(index);
            if (Boolean(response.meta.success)) {
                layer.closeAll();
                loadPDAManage();
            }
            else mymsg(response.meta.message, 5);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.close(index);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function emptyArgs() {

    $('#pda_id').val('');
    $('#addwym').attr('disabled', false);
    $('#addwym').val('');
    // $('#sfzhm').attr('disabled', false);
    $('#addmc').val('');
    $('#ssbmname').val('');

}


function readArgs() {

    return {
        id: $('#pda_id').val(),
        mc: $('#addmc').val(),
        bmbh: $('#ssbmbh').val(),
        wym: $('#addwym').val(),
        zt: $('#zt').val()

    }
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

