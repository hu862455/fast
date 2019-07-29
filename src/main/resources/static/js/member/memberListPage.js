var memberListPage = function () {

    function changeMoney(money) {
    debugger;
        $("#chargeAmount").val(money);
    }

    function queryMemberParams(params) {
        var param = {
            pageNumber: params.pageNumber,
            pageSize: params.pageSize,
            memberLevelId: $("#search-levelName").val(),
            name:$("#search-memberName").val()
        };
        return param;
    }

    function addFunctionAlty(value, row, index) {
        return [
            '<button id="memberChange" type="button" class="btn btn-default">修改</button>',
            '<button id="memberDel" type="button" class="btn btn-default">删除</button>',
            '<button id="memberRecharge" type="button" class="btn btn-default">充值</button>'
        ].join('');
    }

    window.operateMemberEvents = {
        'click #memberDel': function (e, value, row, index) {
            swal({
                    title: "你确定？",
                    text: "你将无法恢复这个会员信息！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "删除",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            url: contentPath + 'member/delMember',
                            data: {id: row.id},
                            method: 'POST',
                            async: false,
                            cache: false,
                            success: function (data) {
                                if (data.code == 1) {
                                    swal("删除", "您的会员信息删除失败！", "error");

                                } else {
                                    swal("删除", "您的会员信息已被删除！", "success");
                                    $("#memberLevelTable").memberInfoTable('refresh');
                                }
                            }
                        });
                    } else {
                        swal("取消", "已经取消删除会员信息！:)", "error");
                    }
                });
        },
        'click #memberChange': function (e, value, row, index) {
            $("#memberName").val(row.name);
            $("#memberAddress").val(row.address);
            $("#memberTelephone").val(row.telephone);
            if(row.sex === 1){
                $("#memberSex>option[value='1']").attr("selected","selected");
            }else if(row.sex === 0){
                $("#memberSex>option[value='0']").attr("selected","selected");
            }else{
                $("#memberSex>option[value='2']").attr("selected","selected");
            }
            $("#memberLevelName").val(row.levelName);
            $('#memberChangeModal').modal('show');
            $("#changeMemberButten").click(function () {
                var memberName = $("#memberName").val();
                var memberAddress = $("#memberAddress").val();
                var memberTelephone = $("#memberTelephone").val();
                var memberLevelName = $("#memberLevelName").val();
                var memberSex = $("#memberSex").val();
                var id = row.id;
                $.ajax({
                    url: contentPath + 'member/changeMember',
                    data: {
                        id: id,
                        name: memberName,
                        address: memberAddress,
                        telephone: memberTelephone,
                        levelName:memberLevelName,
                        sex:memberSex
                    },
                    method: 'POST',
                    async: false,
                    cache: false,
                    success: function (data) {
                        $('#memberChangeModal').modal('hide');
                        if (data.code == 1) {
                            // 失败
                            swal({
                                title: "修改失败！",
                                text: "请重试尝试修改会员信息！"
                            });
                        } else {
                            swal({
                                title: "修改成功！",
                                text: "已完成" + memberName + "的修改",
                                type: "success"
                            });
                            $("#memberInfoTable").bootstrapTable('refresh');
                        }
                    }
                })
            });

        },
        'click #memberRecharge' :function (e, value, row, index) {
            $("#recharge_memberName").val(row.name);
            $("#recharge_account").val(row.account);
            $("#recharge_totalAccount").val(row.totalAccount);
            $('#memberRechargeModal').modal('show');
            $("#rechargeButten").click(function () {
                var recharge_money = $("#recharge_money").val();
                var id = row.id;
                $.ajax({
                    url:contentPath + 'member/recharge',
                    method: 'POST',
                    async: false,
                    cache: false,
                    data:{id:id,recharge:recharge_money},
                    success:function (data) {
                        $('#memberRechargeModal').modal('hide');
                        if (data.code == 1) {
                            // 失败
                            swal({
                                title: "充值失败！",
                                text: data.msg
                            });
                        } else {
                            swal({
                                title: "充值成功！",
                                text: data.msg,
                                type: "success"
                            });
                            $("#memberInfoTable").bootstrapTable('refresh');
                        }
                    }
                })
            })

        }

    };


    function initPage() {
        // 获取所有的会员等级信息
        $.ajax({
            url: contentPath + "memberLevel/allMemberLevel",
            method: 'POST',
            async: false,
            cache: false,
            success: function (data) {
                var data = JSON.parse(data);
                var html = "<option></option>";
                var html2 = "<option></option>";
                for (var i = 0; i < data.length; i++) {
                    html += "<option value =" + data[i].id + " onclick=\"memberListPage.changeMoney('" + data[i].limit + "')\">" + data[i].levelName + "</option>";
                    html2 += "<option value =" + data[i].id + ">"+ data[i].levelName + "</option>"
                }
                $("#memberLevelName").html(html);
                $("#search-levelName").html(html2);
            }
        });
        $("#memberInfoTable").bootstrapTable('destroy');
        $("#memberInfoTable").bootstrapTable({
            url: contentPath + 'member/getMemberList',
            pagination: true, // 显示分页
            queryParamsType: "", // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
            queryParams:memberListPage.queryMemberParams,
            sidePagination: 'server',
            method: "GET",
            exportDataType: 'all',
            showExport: true,
            toolbar: '#memberToolbar',
            exportTypes: ['txt', 'excel', 'json'],
            buttonsAlign: "right",  //按钮位置
            exportOptions: {
                ignoreColumn: [0, 1],  //忽略某一列的索引
                fileName: '会员信息表',  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '会员信息表'
            },
            showPaginationSwitch: false,
            pageNumber: 1,
            pageSize: 10,
            locale: 'zh-CN',
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            striped: true, //是否显示行间隔色
            columns: [{
                checkbox: true,
                visible: true                  //是否显示复选框
            }, {
                field: 'id',
                title: 'id',
                visible: false
            }, {
                field: 'number',
                title: '序号',
                width: 5,
                align: 'center',
                switchable: false,
                formatter: function (value, row, index) {
                    //return index+1; //序号正序排序从1开始
                    var pageSize = $('#memberInfoTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                    var pageNumber = $('#memberInfoTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                    return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                }
            }, {
                field: 'name',
                title: '会员名字'
            }, {
                field: 'telephone',
                title: '电话号码'
            }, {
                field: 'count',
                title: '会员折扣'
            }, {
                field: 'address',
                title: '地址'
            }, {
                field: 'sex',
                title: '性别',
                formatter: function (value, row, index) {
                    if (value === 1) {
                        return "男";
                    } else if (value === 0) {
                        return "女";
                    } else {
                        return "未知";
                    }
                }
            }, {
                field: 'account',
                title: '账户余额'
            }, {
                field: 'levelName',
                title: '会员等级'
            }, {
                field: 'totalAccount',
                title: '充值总额'
            }, {
                title: '操作',
                width: 500,
                align: 'center',
                valign: 'middle',
                formatter: addFunctionAlty,
                addFunctionAlty:addFunctionAlty,
                events: operateMemberEvents
            }]
        })

        $("#Member-search").click(function () {
            $("#memberInfoTable").bootstrapTable('destroy');
            $("#memberInfoTable").bootstrapTable({
                url: contentPath + 'member/getMemberList',
                pagination: true, // 显示分页
                queryParamsType: "", // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
                queryParams:memberListPage.queryMemberParams,
                sidePagination: 'server',
                method: "GET",
                exportDataType: 'all',
                showExport: true,
                toolbar: '#memberToolbar',
                exportTypes: ['txt', 'excel', 'json'],
                buttonsAlign: "right",  //按钮位置
                exportOptions: {
                    ignoreColumn: [0, 1],  //忽略某一列的索引
                    fileName: '会员信息表',  //文件名称设置
                    worksheetName: 'sheet1',  //表格工作区名称
                    tableName: '会员信息表'
                },
                showPaginationSwitch: false,
                pageNumber: 1,
                pageSize: 10,
                locale: 'zh-CN',
                cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                striped: true, //是否显示行间隔色
                columns: [{
                    checkbox: true,
                    visible: true                  //是否显示复选框
                }, {
                    field: 'id',
                    title: 'id',
                    visible: false
                }, {
                    field: 'number',
                    title: '序号',
                    width: 5,
                    align: 'center',
                    switchable: false,
                    formatter: function (value, row, index) {
                        //return index+1; //序号正序排序从1开始
                        var pageSize = $('#memberInfoTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                        var pageNumber = $('#memberInfoTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                    }
                }, {
                    field: 'name',
                    title: '会员名字'
                }, {
                    field: 'telephone',
                    title: '电话号码'
                }, {
                    field: 'count',
                    title: '会员折扣'
                }, {
                    field: 'address',
                    title: '地址'
                }, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                        if (value === 1) {
                            return "男";
                        } else if (value === 0) {
                            return "女";
                        } else {
                            return "未知";
                        }
                    }
                }, {
                    field: 'account',
                    title: '账户余额'
                }, {
                    field: 'levelName',
                    title: '会员等级'
                }, {
                    field: 'totalAccount',
                    title: '充值总额'
                }, {
                    title: '操作',
                    width: 500,
                    align: 'center',
                    valign: 'middle',
                    formatter: addFunctionAlty,
                    addFunctionAlty:addFunctionAlty,
                    events: operateMemberEvents
                }]
            })
        });
    }

    return {
        initPage:initPage,
        queryMemberParams:queryMemberParams,
        addFunctionAlty:addFunctionAlty,
        changeMoney:changeMoney
    }
}();

$().ready(function () {
    memberListPage.initPage();
});