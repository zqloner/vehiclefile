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
        , url = "/storeMaterialList/list",
        treeData = [ ];

    form.verify({
        pass: [
            // /^[\u4e00-\u9fffa-zA-Z]{1,25}$/
            /^.{1,25}$/
            , '标题不能为控且不可超过25个字'
        ],
        mycheck:[
            /^[0-9]+([.]{1}[0-9]+){0,1}$/,
            '只能输入数字'
        ]
        ,
        desc:[
            // /^[\u4e00-\u9fffa-zA-Z]{1,500}$/
            /^.{0,500}$/
            , '说明不可超过500个字'
        ],
        months:function (value) {
            var checkLenth = $("[name='month']:checked");
            if(checkLenth.length<=0){
                return "请选择月份";
            }
        },
        comparetime:function () {
            var start = $("[name=start]").val();
            var end = $("[name=end]").val();
            if(start>=end){
                return "开始报名时间必须小于报名结束时间";
            }
        }
    });


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
                    {field: 'materialName', title: '物料名称', width: '10%'},
                    {field: 'materialCode', title: '物料编码', width: '10%'},
                    {field: 'specification', title: '物料规格', width: '10%'},
                    {field: 'materialModel', title: '物料型号', width: '10%'},
                    {field: 'count', title: '数量', width: '10%'},
                    {field: 'minCount', title: '最小备货量', width: '10%'},
                    {field: 'unitPrice', title: '单价', width: '10%'},
                    {field: 'materialType', title: '物料类型', width: '10%'}, {
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
        searchObj.storeId = $("[name='storeId']").val();
        // 数据加载中loading
        // parent.tools.load();
        var params = new Object();
        params.pageNum = pageNumber;
        params.pageSize = pageSize;
        params.storeId = searchObj.storeId;
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
            url: "/storeMaterialList/toUpdate",
            type: "GET",
            data: {"id":data},
            success: function (res) {
                if (res.code == 200) {
                    $("[name=materialName]").val(res.data.materialName);
                    $("[name=materialCode]").val(res.data.materialCode);
                    $("[name=materialModel]").val(res.data.materialModel);
                    $("[name=materialType]").val(res.data.materialType);
                    $("[name=specification]").val(res.data.specification);
                    $("[name=count]").val(res.data.count);
                    $("[name=minCount]").val(res.data.minCount);
                    $("[name=unit]").val(res.data.unit);
                    $("[name=unitPrice]").val(res.data.unitPrice);
                    $("[name=description]").val(res.data.description);
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
                    params = data.field;
                    params.currentStoreId = searchObj.storeId;
                    $.ajax({
                        url: "storeMaterialList/addOrUpdate",
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
                return false;
            });
            return false;
        } else if (obj.event === 'delete') { // 操作—删除
            layer.confirm('确定要删除该类别吗？', function (index) {
                $.ajax({
                    url: "/storeMaterialList/delete",
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
        window.location.href="route?name=store/storeOrganization";
    });

    // 批量添加
    $(".addMore").click(function () {
        window.location.href = "route?name=store/storeMaterialListAddMore&storeId=" + searchObj.storeId;
    });

    // 添加组织架构弹窗
    $("#add").click(function () {
        $("[name=materialName]").val("");
        $("[name=materialCode]").val("");
        $("[name=materialModel]").val("");
        $("[name=materialType]").val("");
        $("[name=specification]").val("");
        $("[name=count]").val("");
        $("[name=minCount]").val("");
        $("[name=unit]").val("");
        $("[name=unitPrice]").val("");
        $("[name=id]").val("");
        form.render();
        projectile.elastic({title: " ", content: $("#popup"), area: ['800px', '700px']}, function () {
            // 监听提交
            form.on('submit(submitData)', function (data) {
                var params = new Object();
                params = data.field;
                params.currentStoreId = searchObj.storeId;
                $.ajax({
                    url: "storeMaterialList/addOrUpdate",
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