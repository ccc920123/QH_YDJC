var userid = '';

$(function () {
    var user_id = getQueryString("user_id");
    var ssbmbh = getQueryString("ssbmbh");
    var ssbmname = decodeURI(decodeURI(getQueryString("ssbmname")));
    getRoleType();
    emptyArgs();
    userid = user_id;
    if (user_id != undefined && user_id != null && user_id != "") {

        loadDeptInfo(user_id);
        $('#resetSubmit').show();
    } else {
        $('#resetSubmit').hide();
        $('#ssbmbh').val(ssbmbh);
        $('#ssbmname').val(ssbmname);
        // loadRoles($('#ssbmbh').val(), "");
    }
    $('input[type=radio][name=rylx]').change(function () {
        if (this.value == '1') {
            $("#rybhSpan").html("警员编号");

        }
        else if (this.value == '0') {
            $("#rybhSpan").html("人员编号");
        }
    });
    layui.use(['laydate', 'layer'], function () {
        var laydate = layui.laydate;
        var layer = layui.layer;

        //执行一个laydate实例
        //日期选择器
        laydate.render({
            elem: '#mmyxq'
            , type: 'date' //默认，可不填
            , format: "yyyy-MM-dd"
            , value: getNowFormatDate(0, 3, 0)

        });
        laydate.render({
            elem: '#zhyxq'
            , type: 'date' //默认，可不填
            , format: "yyyy-MM-dd"
            , value: getNowFormatDate(0, 3, 0)

        });
        laydate.render({
            elem: '#dlkssj'
            , type: 'time' //默认，可不填
            , format: "HH:mm"
            , value: '08:00'
        });
        laydate.render({
            elem: '#dljssj'
            , type: 'time' //默认，可不填
            , format: "HH:mm"
            , value: '20:00'

        });

    });

    $('#confirmCancel').unbind('click');
    $('#confirmCancel').bind('click', function () {
        var index = parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);
    });
    $('#confirmSubmit').unbind('click');
    $('#confirmSubmit').bind('click', function () {

        if (String($('#loginname').val()).length < 4
            || String($('#loginname').val()).length > 20
        ) {
            mymsg('请输入长度范围为4~20的登录账号', 5);
            return;
        }

        if (String($('#realname').val()).length < 2
            || String($('#realname').val()).length > 15
        ) {
            mymsg('请输入长度范围为2~15的真实姓名', 5);
            return;
        }
        var zhyxq = $('#zhyxq').val();
        var mmyxq = $('#mmyxq').val();
        if (String(zhyxq).length == 0) {
            mymsg('请选择账号有效期', 5);
            return;
        }
        if (String(mmyxq).length == 0) {
            mymsg('请选择密码有效期', 5);
            return;
        }
        if (CompareDate(getNowFormatDate(0, 0, 0), zhyxq)) {
            mymsg('账号有效期不能小于当前日期', 5);
            return;
        }
        if (CompareDate(getNowFormatDate(0, 0, 0), mmyxq)) {
            mymsg('密码有效期不能小于当前日期', 5);
            return;
        }
        var dlkssj = $("#dlkssj").val();
        var dljssj = $("#dljssj").val();
        if (String(dlkssj).length == 0 || String(dljssj).length == 0) {
            mymsg('请选择登录时间', 5);
            return;
        }
        var nowdata = new Date(Date.parse(dlkssj));
        var nowdata2 = new Date(Date.parse(dljssj));
        if (nowdata >= nowdata2) {
            mymsg('开始时间不能大于结束时间', 5);
            return;
        }
        var regSfzhm = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (String($('#sfzhm').val()).length == 0
            || (regSfzhm.test($('#sfzhm').val()) === false)
        ) {
            mymsg('请输入正确的身份证号码', 5);
            return;
        }
        if (String($("#rybh").val()).length == 0) {
            mymsg('警员/人员编号不能为空', 5);
            return;
        }
        var regLxdh = /((^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[34578]\d{9})$))/;
        if (String($('#lxdh').val()).length == 0
            || (regLxdh.test($('#lxdh').val()) === false)
        ) {
            mymsg('请输入正确联系电话', 5);
            return;
        }

        // $("#cbkqipxz :checkbox").prop("checked", true);
        //$('input:radio[name="btg"]:checked').attr('checked', true);

        var cdkd = document.getElementsByName('cbkqipxz'); //选择所有name="'test'"的对象，返回数组
        if (cdkd[0].checked) {
            $("#kqipxz").val('1');
        }
        else $("#kqipxz").val('0');


        var regIp = /((?!^0{1,3}(\.0{1,3}){3}$)(?!^255(\.255){3}$)((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?))/;
        if ($("#kqipxz").val() == "1" && (
                $.trim($("#ipaddress1").val()).length == 0
                || (regIp.test($('#ipaddress1').val()) === false)
            )
        ) {
            mymsg('请输入允许的正确IP地址1', 5);
            return;
        }

        if (String($('#lxdz').val()).length > 0
            && (            String($('#lxdz').val()).length < 5
                || String($('#lxdz').val()).length > 120
            )
        ) {
            mymsg('联系地址长度范围为5~120', 5);
            return;
        }


        if (String($('#bz').val()).length > 0
            && (String($('#bz').val()).length > 500
            )
        ) {
            mymsg('备注长度不大于500', 5);
            return;
        }


        var obj2 = document.getElementsByName('role'); //选择所有name="'test'"的对象，返回数组
        //取到对象数组后，我们来循环检测它是不是被选中
        var stryeqx = '';
        for (var i = 0; i < obj2.length; i++) {
            if (obj2[i].checked) {
                stryeqx += $(obj2[i]).attr("value") + ','; //如果选中，将value添加到变量s中
            }
        }
        if (stryeqx != '') {
            stryeqx = stryeqx.substr(0, stryeqx.length - 1);
        }
        $("#roles").val(stryeqx);

        if (String($("#jsmc").val()).length == 0) {
            mymsg('所属角色不能为空', 5);
            return;
        }

        //判断是否使用pda的复选框被选中
        if ($('.sfsypda').is(':checked')) {
            (document.getElementById('sfsypda')).value = $('.sfsypda').val();
        }
        if (userid != undefined && userid != null && userid != "") {
            doPut('users/management/user/update', JSON.stringify(readArgs()), function (response) {
                if (Boolean(response.meta.success)) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    mymsg( "修改成功", 6);
                    parent.reflush();
                }
                else mymsg(response.meta.message, 5);
            });
        } else {
            doPut('users/management/user/add', JSON.stringify(readArgs()), function (response) {
                if (Boolean(response.meta.success)) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    mymsg( "新增成功", 6);
                    parent.reflush();//访问父页面方法
                }
                else mymsg(response.meta.message, 5);
            });
        }
    });

    $('#resetSubmit').unbind('click');
    $('#resetSubmit').bind('click', function () {
        var str = '确定重置该账号的密码？';
        parent.layer.confirm(str, {//弹出层上面再弹出一层

            btn: ['确定', '取消'] //按钮
        }, function () {
            doGet('users/management/user/ResetPassWord?user_id=' + user_id, {}, function (response) {
                if (Boolean(response.meta.success)) {
                    mymsg("重置密码成功", 6);
                }
                else {
                    mymsg(response.meta.message, 5);
                }
            });
        }, function () {
            var index = parent.layer.getFrameIndex(window.name);

            parent.layer.close(index);
        });
    });

    function loadDeptInfo(user_id) {
        doGet('users/management/user/getUserById', {
            user_id: user_id
        }, function (response) {
            var dept = response.data;
            if (dept == null) {
                return;
            }
            $('#user_id').val(userid);

            $('#loginname').attr('disabled', 'disabled');
            $('#ssbmname').attr('disabled', 'disabled');


            $('#ssbmbh').val(dept.ssbmbh);
            $('#ssbmname').val(dept.ssbmname);

            $('#loginname').val(dept.loginname);
            $('#realname').val(dept.realname);
            $('#sfzhm').val(dept.sfzhm);
            var xinfeiradio = document.getElementsByName("rylx");
            for (var i = 0; i < xinfeiradio.length; i++) {
                if (xinfeiradio[i].value == dept.sfmj) { //条件判断，哪个设置为选中状态
                    xinfeiradio[i].checked = true;
                }
                else {
                    xinfeiradio[i].checked = false;
                }
            }
            $('#mmyxq').val(ChangeDateFormat(dept.mmyxq));
            $('#rybh').val(dept.ygbh);
            $('#lxdh').val(dept.lxdh);
            $('#kqipxz').val(dept.kqipxz);
            $('#cbkqipxz').prop('checked', false);
            if (dept.kqipxz == 1) {
                // $('#cbkqipxz').attr('checked', true);
                $("#cbkqipxz").prop("checked", true);
            }
            $('#ipaddress1').val(dept.ipaddress1);
            $('#ipaddress2').val(dept.ipaddress2);
            $('#ipaddress3').val(dept.ipaddress3);
            $('#zt').val(dept.zt);
            $('#lxdz').val(dept.lxdz);
            $('#bz').val(dept.bz);
            $('#zhyxq').val(ChangeDateFormat(dept.yhyxq));

            // $('#roles').val(dept.roles);

            // loadRoles($('#ssbmbh').val(), $('#roles').val());

            doGet2('users/management/user/getSysUserRolesByID', {
                roleId: dept.roles
            }, function (response) {
                $('#jslx').val(response.data.type);
                var jsmc = response.data.roleId;
                doGet2('users/management/user/getSysUserRoles', {
                    type: response.data.type
                }, function (response) {
                    var tjsmc = "<option value=''>---选择角色名称---</option>";
                    $.each(response.data,function(n,value){
                        tjsmc += "<option value='"+ value.roleId +"'>"+ value.name +"</option>";
                    })
                    $('#jsmc').html(tjsmc);

                    $('#jsmc').val(jsmc);
                });
            });


        });

    }

    /**
     *
     * 所属角色
     * @param bmbh
     * @param roles
     */
    function loadRoles(bmbh, roles) {
        $("#tdRole").html("");
        doGet2('users/management/user/getRolesByBmbh', {
            bmbh: bmbh

        }, function (response) {
            if (!Boolean(response.meta.success)) {
            }
            else loadRolesTable(response.data, roles);
        });
    }

    function getRoleType() {
        $("#jslx").change(function(){
            $('#jsmc').empty();
            var jslx=$(this).children('option:selected').val();
            if (jslx == undefined || jslx == ''){

            }  else {
                doGet2('users/management/user/getSysUserRoles', {
                    type: jslx
                }, function (response) {
                    var tjsmc = "<option value=''>---选择角色名称---</option>";
                    $.each(response.data,function(n,value){
                        tjsmc += "<option value='"+ value.roleId +"'>"+ value.name +"</option>";
                    })
                    $('#jsmc').html(tjsmc);
                });
            }
        });
    }



    function loadRolesTable(depts, roles) {
        $("#tdRole").html("");
        var tbody = '';
        $.each(depts, function (rowIndex, dept) {
            if (roles.indexOf(dept.role_id) >= 0) {
                tbody += ' <span class="cb_span"><input type="checkbox" width="140px;" name="role" checked="true" value="' + dept.role_id + '">' + dept.name + '</span>';
            }
            else tbody += ' <span class="cb_span"><input type="checkbox" width="140px;" name="role" value="' + dept.role_id + '">' + dept.name + '</span>';
        });
        $('#tdRole').html(tbody);
    }

    function readArgs() {

        return {
            userId: userid,
            ssbmname: $('#ssbmname').val(),
            ssbmbh: $('#ssbmbh').val(),
            loginname: $('#loginname').val(),
            realname: $('#realname').val(),
            sfzhm: $('#sfzhm').val(),
            mmyxq: $('#mmyxq').val(),
            lxdh: $('#lxdh').val(),
            kqipxz: $('#kqipxz').val(),
            ipaddress1: $('#ipaddress1').val(),
            ipaddress2: $('#ipaddress2').val(),
            ipaddress3: $('#ipaddress3').val(),
            zt: $('#zt').val(),
            czryzh: '',//操作人员账号
            lxdz: $('#lxdz').val(),
            bz: $('#bz').val(),
            yhyxq: $("#zhyxq").val(),
            roleId: $('#jsmc').val(),
            sfmj: $('input[name="rylx"]:checked').val(),
            ygbh: $('#rybh').val(),
            sfsypda: $('#sfsypda').val(),
            dlkssj: $('#dlkssj').val(),
            dljssj: $('#dljssj').val()
        }
    }

    function CompareDate(d1, d2) {
        return ((new Date(d1.replace(/-/g, "\/"))) > (new Date(d2.replace(/-/g, "\/"))));
    }

    function emptyArgs() {

        $('#user_id').val('');
        $('#loginname').attr('disabled', false);
        // $('#sfzhm').attr('disabled', false);
        $('#ssbmname').val('');
        $('#ssbmbh').val('');
        $('#loginname').val('');
        $('#realname').val('');
        $('#sfzhm').val('');
        $('#mmyxq').val(getNowFormatDate(0, 3, 0));
        $('#lxdh').val('');
        $('#cbkqipxz').prop('checked', false);
        $('#kqipxz').val('0');
        $('#ipaddress1').val('');
        $('#ipaddress2').val('');
        $('#ipaddress3').val('');
        $('#zt').val('1');
        $('#lxdz').val('');
        $('#bz').val('');
        $('#dlkssj').val('08:00');
        $('#dljssj').val('20:00');
        $('#rybh').val('');
        $('#zhyxq').val(getNowFormatDate(0, 3, 0));
        // $('#roles').val('');
        // $('#tdRole').html('');
        $('#sfsypda').val('');
        $("input[type='checkbox']").attr("checked", false);
    }
})