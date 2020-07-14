/**
 * Created by zhuxq on 2019/9/4.
 */
layui.config({
    base: "/bjzgh/js/"
}).use(['jquery', 'table', 'layer', 'form', 'pagination', 'projectile', 'tree'], function () {
    var $ = layui.jquery
        , tree = layui.tree
        , util = layui.util
        , table = layui.table
        , layer = layui.layer
        , form = layui.form
        , projectile = layui.projectile //自定义弹窗
        , pagination = layui.pagination //自定义分页
        , pageFlag = true
        , url = "/repairProjectNames/list",
        treeData = [ ];
    // 列表渲染
    var searchObj = {};

    function tablePlay(data) {
        table.render({
            id: "enquiry",
            elem: "#table",
            data: data,
            // 设置表头参数
            cols: [
                [
                    {field: 'projectName', title: '项目名称', width: '20%'},
                    {field: 'unit', title: '单位', width: '20%'},
                    {field: 'firstRepairHours', title: '初次维修工时', width: '20%'},
                    {field: 'repeatOverlayHours', title: '重复叠加工时', width: '20%'}, {
                    title: '操作', width: '25%', templet: function (d) {
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs table-btn" lay-event="edit">编辑</a>'
                            + '<span class="table-cut" lay-separator="">|</span>'
                            + '<a class="layui-btn layui-btn-primary layui-btn-xs table-btn" lay-event="delete">删除</a>';
                    }
                }
                ]
            ]
        });
    }

    // 分页渲染
    function laypageCurr(res) {
        var nums = 10;
        if (pageFlag) {
            pagination.paging({data: res, num: nums}, function (obj, first) {
                // obj包含了当前分页的所有参数，比如：
                console.log(obj.curr); // 得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); // 得到每页显示的条数
                if (!first) {
                    pageFlag = false;
                    getCadreList(obj.curr, obj.limit)
                }
            });
        }
    }

    // 加载数据
    function getCadreList(pageNumber, pageSize) {
        searchObj.hourId = $("[name='hourId']").val();
        // 数据加载中loading
        // parent.tools.load();
        var params = new Object();
        params.pageNum = pageNumber;
        params.pageSize = pageSize;
        params.hourId = searchObj.hourId;
        $.ajax({
            url: url,
            type: "GET",
            data: params,
            //dataType:"json",
            success: function (res) {
                // {field: 'name', title: '角色名称', width: '20%'}
                if (res.code == "200") {
                    tablePlay(res.data.list); //列表渲染
                    laypageCurr(res.data); //分页渲染
                } else {
                    layer.msg(res.data.msg);
                }
            }
        });
    }

    // 初始化加载
    getCadreList();


    // 监听表格操作按钮点击
    function toUpdate(data) {
        $.ajax({
            url: "/repairProjectNames/toUpdate",
            type: "POST",
            data: {"id":data},
            success: function (res) {
                if (res.code == 200) {
                    $("[name=projectName]").val(res.data.projectName);
                    $("[name=unit]").val(res.data.unit);
                    $("[name=firstRepairHours]").val(res.data.firstRepairHours);
                    $("[name=repeatOverlayHours]").val(res.data.repeatOverlayHours);
                    $("[name=id]").val(res.data.id);
                    form.render();
                }
            },
            dataType: "json"
        });
    }

    // toUpdate();
    table.on('tool(test)', function (obj) {
        if (obj.event === 'edit') { // 操作—编辑
            toUpdate(obj.data.id);
            projectile.elastic({title: " ", content: $("#popup"), area: ['800px', '486px']}, function () {
                // 监听提交
                form.on('submit(submitData)', function (data) {
                    var params = new Object();
                    params.projectName = data.field.projectName;
                    params.unit = data.field.unit;
                    params.firstRepairHours = data.field.firstRepairHours;
                    params.repeatOverlayHours = data.field.repeatOverlayHours;
                    params.id = data.field.id;
                    $.ajax({
                        url: "repairProjectNames/addOrUpdate",
                        type: "POST",
                        data: params,
                        success: function (res) {
                            if (res.code == 200) {
                                layer.closeAll();
                                layer.msg("修改成功");
                                getCadreList();
                            } else {
                                layer.msg("修改失败");
                            }
                        },
                        dataType: "json"
                    });
                    return false;
                });
                return false;
            });
            return false;
        } else if (obj.event === 'delete') { // 操作—删除
            layer.confirm('确定要删除该类别吗？', function (index) {
                $.ajax({
                    url: "/repairProjectNames/delete",
                    type: "GET",
                    data: {"id": obj.data.id},
                    success: function (res) {
                        if (res.code == 200) {
                            layer.msg("删除成功");
                            getCadreList();
                        } else {
                            layer.msg(res.message);
                        }
                    },
                    dataType: "json"
                });
                return false;
            });
        }
        return false;
    });

    //返回
    $("#cenCelBtn").click(function () {
        window.location.href="route?name=repair/repairHourName";
    });

    // 批量添加
    $(".addMore").click(function () {
        window.location.href = "route?name=repair/repairProjectAddMore&hourId=" + searchObj.hourId;
    });

    // 添加组织架构弹窗
    $("#add").click(function () {
        $("[name=projectName]").val("");
        $("[name=unit]").val("");
        $("[name=firstRepairHours]").val("");
        $("[name=repeatOverlayHours]").val("");
        $("[name=id]").val("");
        form.render();
        projectile.elastic({title: " ", content: $("#popup"), area: ['800px', '300px']}, function () {
            // 监听提交
            form.on('submit(submitData)', function (data) {
                var params = new Object();
                params.projectName = data.field.projectName;
                params.unit = data.field.unit;
                params.firstRepairHours = data.field.firstRepairHours;
                params.repeatOverlayHours = data.field.repeatOverlayHours;
                params.hourId = searchObj.hourId;
                $.ajax({
                    url: "repairProjectNames/addOrUpdate",
                    type: "POST",
                    data: params,
                    success: function (res) {
                        if (res.code == 200) {
                            layer.closeAll();
                            layer.msg("新增成功");
                            getCadreList();
                        } else {
                            layer.alert("新增失败");
                        }
                    },
                    dataType: "json"
                });
                return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    });
});