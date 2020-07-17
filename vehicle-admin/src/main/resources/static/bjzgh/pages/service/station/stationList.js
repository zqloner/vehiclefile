/**
 * Created by zhuxq on 2019/9/9.
 */
layui.config({
    base: "/bjzgh/js/"
}).use(['jquery', 'table', 'layer', 'form', 'element', 'pagination', 'projectile'], function () {
    var $ = layui.jquery
        , table = layui.table
        , layer = layui.layer
        , form = layui.form
        , element = layui.element
        , projectile = layui.projectile //自定义弹窗
        , pagination = layui.pagination //自定义分页
        , pageFlag = true
        , url = "/serviceStationList/list";

    var searchObj = {};
    // 表单查询
    form.on('submit(submitData)', function (data) {
        pageFlag = true;
        searchObj.stationName = $("[name='stationName']").val();
        getCadreList();
        return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可
    });
    // 劳模职工
    // 列表渲染
    function tablePlay(data) {
        table.render({
            id: "enquiry",
            elem: "#table",
            data: data,
            // 设置表头参数
            cols: [
                [
                    {field: 'stationName', title: '服务站名称', width: '15%'}
                    , {field: 'stationType', title: '服务站类型', width: '10%'}
                    , {
                    field: 'isCoreStation', title: '是否为核心站', width: '10%', templet: function (d) {
                        if (d.isCoreStation == '0') {
                            return '<span class="layui-badge-dot layui-bg-blue"></span> 核心站';
                        } else if (d.isCoreStation == '1') {
                            return '<span class="layui-badge-dot layui-bg-gray"></span> 非核心站';
                        }
                    }
                }
                    , {field: 'createUsername', title: '创建人', width: '10%'}
                    , {field: 'stationStoreName', title: '备件库名字', width: '10%'}
                    , {field: 'belongTreeAddress', title: '所属街道', width: '20%'}
                    , {field: 'stationCreateDate', title: '建站时间', width: '15%'}
                    , {
                    title: '操作', width: '10%', templet: function (d) {
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs table-btn" lay-event="edit">编辑</ a>'
                            + '<span class="table-cut" lay-separator="">|</span>'
                            + '<a class="layui-btn layui-btn-primary layui-btn-xs table-btn" lay-event="delete">删除</ a>';
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

    // // 初始化加载
    getCadreList();

    // 加载数据
    function getCadreList(pageNum, pageSize) {
        // 数据加载中loading
        // parent.tools.load();
        var params = searchObj;
        params.pageNum = pageNum;
        params.pageSize = pageSize;
        $.ajax({
            url: url,
            type: "GET",
            data: params,
            dataType: "json",
            success: function (res) {
                console.log(res);
                if (res.code == "200") {
                    tablePlay(res.data.list); //列表渲染
                    laypageCurr(res.data); //分页渲染
                } else {
                    layer.msg(res.message);
                }
            }
        });
    }

    // 监听表格操作按钮点击
    table.on('tool(test)', function (obj) {
            if (obj.event === 'edit') { // 操作—编辑
                window.location.href = "/route?name=service/station/stationListAdd&stationId="+obj.data.id;
            } else if (obj.event === 'delete') { // 操作—删除
                layer.confirm('确定要删除该服务站吗？', function (index) {
                    // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url: "/serviceStationList/delete",
                        type: "GET",
                        data: {"id": obj.data.id},
                        dataType: "json",
                        success: function (res) {
                            console.log(res);
                            if (res.code == "200") {
                                layer.alert(res.message);
                                // window.location.href="/route?name=prediction/line&userType="+$(".layui-this").val();
                                getCadreList();
                            } else {
                                // 已有企业填报,不可删除
                                projectile.elastic({
                                    title: " ",
                                    content: $("#popup_small"),
                                    area: ['520px', '288px']
                                }, function () {
                                    $(".sureBtn").click(function () {
                                        layer.closeAll();
                                    });
                                    $(".cancelBtn").click(function () {
                                        layer.closeAll();
                                    });
                                });
                            }
                        }
                    });
                    return false;
                });
            }
            return false;
        }
    );

    // 添加
    $(".add").click(function () {
        console.log($(".layui-this").val());
        window.location.href = "/route?name=service/station/stationListAdd";
    });
});