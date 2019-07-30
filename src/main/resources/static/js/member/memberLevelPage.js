var memberLevelPage = function () {


    function initPage() {
        $('#addMemberlevelmodel').modal('hide');
        $("#addMemberLevelButton").click(function () {
            $("#myModalLabel").html("新增会员等级");
            $("#levelName").val();
            $("#count").val();
            $("#limit").val();
            $("#addButten").show();
            $("#changeButten").hide();
            $('#addMemberlevelmodel').modal('show');
        });

        $("#search").click(function () {
            $("#memberLevelTable").bootstrapTable('destroy');
            $("#memberLevelTable").bootstrapTable({
                url: contentPath+'memberLevel/memberLevelList',
                pagination: true, // 显示分页
                queryParamsType: "", // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
                queryParams: queryParams,
                sidePagination: 'server',
                method: "GET",
                exportDataType: 'all',
                showExport: true,
                exportTypes: ['txt', 'excel', 'json'],
                buttonsAlign: "right",  //按钮位置
                exportOptions: {
                    ignoreColumn: [0, 1],  //忽略某一列的索引
                    fileName: '会员等级信息表',  //文件名称设置
                    worksheetName: 'sheet1',  //表格工作区名称
                    tableName: '会员等级信息表'
                },
                showPaginationSwitch: false,
                toolbar:'#toolbar',
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
                        var pageSize = $('#memberLevelTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                        var pageNumber = $('#memberLevelTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                    }
                }, {
                    field: 'levelName',
                    title: '等级名称'
                }, {
                    field: 'count',
                    title: '会员折扣'
                }, {
                    field: 'limit',
                    title: '最少充值限额'
                }, {
                    title: '操作',
                    width: 500,
                    align: 'center',
                    valign: 'middle',
                    formatter: addFunctionAlty,
                    events: operateEvents
                }]

            });
        });
        function addFunctionAlty(value, row, index) {
            return [
                '<button id="change" type="button" class="btn btn-default">修改</button>',
                '<button id="del" type="button" class="btn btn-default">删除</button>'
            ].join('');
        }

        window.operateEvents = {
            'click #del': function (e, value, row, index) {
                swal({
                        title: "你确定？",
                        text: "你将删除所有属于这个等级的会员且无法恢复这个会员等级信息！",
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
                                url: contentPath + 'memberLevel/delMemberLevel',
                                data: {id: row.id},
                                method: 'POST',
                                async: false,
                                cache: false,
                                success: function (data) {
                                    if (data.code == 1) {
                                        swal("删除", "您的会员等级信息删除失败！", "error");

                                    } else {
                                        swal("删除", "您的会员等级信息已被删除！", "success");
                                        $("#memberLevelTable").bootstrapTable('refresh');
                                    }
                                }
                            });
                        } else {
                            swal("取消", "已经取消删除会员等级信息！:)", "error");
                        }
                    });
            }, 'click #change': function (e, value, row, index) {
                $("#myModalLabel").html("修改会员等级");
                $("#levelName").val(row.levelName);
                $("#count").val(row.count);
                $("#limit").val(row.limit);
                $("#addButten").hide();
                $("#changeButten").show();
                $('#addMemberlevelmodel').modal('show');
                $("#changeButten").click(function () {
                    var levelName = $("#levelName").val();
                    var count = $("#count").val();
                    var limit = $("#limit").val();
                    var id = row.id;
                    $.ajax({
                        url: contentPath + 'memberLevel/updateMemberLevel',
                        data: {id: id, levelName: levelName, count: count, limit: limit},
                        method: 'POST',
                        async: false,
                        cache: false,
                        success: function (data) {
                            $('#addMemberlevelmodel').modal('hide');
                            if (data.code == 1) {
                                // 失败
                                swal({
                                    title: "修改失败！",
                                    text: "请重试尝试修改会员等级信息！"
                                });
                            } else {
                                swal({
                                    title: "修改成功！",
                                    text: "已完成" + levelName + "的修改",
                                    type: "success"
                                });
                                $("#memberLevelTable").bootstrapTable('refresh');
                            }
                        }
                    })
                });

            }
        };

        $("#addButten").click(function () {
            var levelName = $("#levelName").val();
            var count = $("#count").val();
            var limit = $("#limit").val();
            $.ajax({
                url: contentPath + 'memberLevel/addMemberLevel',
                data: {levelName: levelName, count: count, limit: limit},
                method: 'POST',
                async: false,
                cache: false,
                success: function (data) {
                    $('#addMemberlevelmodel').modal('hide');
                    if (data.code == 1) {
                        // 失败
                        swal({
                            title: "新增失败！",
                            text: "请重试尝试新增会员等级信息！"
                        });
                    } else {
                        swal({
                            title: "新增成功！",
                            text: "已完成" + levelName + "的新增",
                            type: "success"
                        });
                        $("#memberLevelTable").bootstrapTable('refresh');
                    }
                }
            })
        });

        function queryParams(params) {
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                levelName: $("#memberLevelName").val()
            };
            return param;
        }

        $("#memberLevelTable").bootstrapTable('destroy');
        $("#memberLevelTable").bootstrapTable({
            url: contentPath+'memberLevel/memberLevelList',
            pagination: true, // 显示分页
            queryParamsType: "", // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
            queryParams: queryParams,
            sidePagination: 'server',
            method: "GET",
            exportDataType: 'all',
            showExport: true,
            toolbar:'#toolbar',
            exportTypes: ['txt', 'excel', 'json'],
            buttonsAlign: "right",  //按钮位置
            exportOptions: {
                ignoreColumn: [0, 1],  //忽略某一列的索引
                fileName: '会员等级信息表',  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '会员等级信息表',
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
                    var pageSize = $('#memberLevelTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                    var pageNumber = $('#memberLevelTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                    return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                }
            }, {
                field: 'levelName',
                title: '等级名称'
            }, {
                field: 'count',
                title: '会员折扣'
            }, {
                field: 'limit',
                title: '最少充值限额'
            }, {
                title: '操作',
                width: 500,
                align: 'center',
                valign: 'middle',
                formatter: addFunctionAlty,
                events: operateEvents
            }]

        });
    }

    return {
        initPage:initPage,

    }
}();

$().ready(function () {
    memberLevelPage.initPage();
});