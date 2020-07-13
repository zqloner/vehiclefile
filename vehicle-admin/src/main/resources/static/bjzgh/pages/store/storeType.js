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
        , url = "/storeTypeDict/list",
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
                    {field: 'value', title: '类别', width: '20%'}
                    , {
                    title: '操作', width: '20%', templet: function (d) {
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs table-btn" lay-event="edit">编辑</a>'
                            + '<span class="table-cut" lay-separator="">|</span>'
                            + '<a class="layui-btn layui-btn-primary layui-btn-xs table-btn" lay-event="delete">删除</a>';
                    }
                }
                ]
            ]
        });
    }

    function renderForm() {
        var myValue = $("[name='type']").val();
        if (myValue == 1) {
            $("[name='typeName']").html("入库");
        }else if (myValue == 2) {
            $("[name='typeName']").html("出库");
        }else if (myValue == 3) {
            $("[name='typeName']").html("转库");
        }
        form.render();
    }

    renderForm();

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
        // 数据加载中loading
        // parent.tools.load();
        var params = new Object();
        params.pageNum = pageNumber;
        params.pageSize = pageSize;
        params.type = $("[name='type']").val();
        params.value = $("[name='value']").val();
        $.ajax({
            url: url,
            type: "GET",
            data: params,
            //dataType:"json",
            success: function (res) {
                // {field: 'name', title: '角色名称', width: '20%'}
                if (res.code == "200") {
                    var oldList = res.data.list;
                    var myList = [];
                    if (oldList != undefined && oldList.length > 0) {
                        for (var i = 0; i < oldList.length; i++) {
                            var param = {};
                            param.id = oldList[i].id;
                            param.value = oldList[i].value;
                            myList.push(param)
                        }
                    }
                    tablePlay(myList); //列表渲染
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
            url: "/storeTypeDict/toUpdate",
            type: "POST",
            data: {"id":data},
            success: function (res) {
                if (res.code == 200) {
                    $("[name=value]").val(res.data.value);
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
                    params.value = data.field.value;
                    params.id = data.field.id;
                    $.ajax({
                        url: "storeTypeDict/addOrUpdate",
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
                    url: "/storeTypeDict/delete",
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

    // 添加组织架构弹窗
    $("#add").click(function () {
        $("[name='id']").val("");
        $("[name='value']").val("");
        form.render();
        projectile.elastic({title: " ", content: $("#popup"), area: ['800px', '200px']}, function () {
            // 监听提交
            form.on('submit(submitData)', function (data) {
                var params = new Object();
                params.value = data.field.value;
                params.id = data.field.id;
                params.type = $("[name='type']").val();
                $.ajax({
                    url: "storeTypeDict/addOrUpdate",
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