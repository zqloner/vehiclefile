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
        , url = "/storeOrganizationStructure/list",
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
                    {field: 'parentName', title: '上级库房名称', width: '20%'},
                    {field: 'name', title: '库房名称', width: '20%'}
                    , {field: 'managerName', title: '库房管理人名称', width: '30%'}
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

    // 分页渲染
    function laypageCurr(res) {
        var nums = 10;
        if (pageFlag) {
            pagination.paging({data: res, num: nums}, function (obj, first) {
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
                            if (oldList[i].id == 1) {
                                continue;
                            }
                            var param = {};
                            param.name = oldList[i].name;
                            param.parentName = oldList[i].parentName;
                            param.managerName = oldList[i].managerName;
                            param.id = oldList[i].id;
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

    var adminUsers = [];

    // 获取管理人
    function getAdminUser() {
        $.ajax({
            url: "/sysAdmin/listNoPage",
            type: "GET",
            success: function (res) {
                if (res.code == "200") {
                    var html = '<option value="">请选择管理人</option>';
                    for (var i = 0; i <res.data.length ; i++) {
                        html += '<option value="'+res.data[i].id+'">'+res.data[i].name+'('+res.data[i].organizationalStructureName+')'+'</option>'
                    }
                    $("[name = 'managerId']").html(html);
                    adminUsers = res.data;
                    form.render();
                } else {
                    layer.msg(res.data.msg);
                }
            }
        });
    }

    getAdminUser();
    // 监听多选框事件（点击一个得到其附属权限）
    form.on('checkbox(myPar)', function (data) {
        if ($(this).prop("checked")) {
            $(this).parent("div").next().find(".child").prop("checked", true);
            form.render();
        } else {
            $(this).parent("div").next().find(".child").prop("checked", false);
            form.render();
        }
    });
    form.on('checkbox(myChild)', function (data) {
        if ($(this).parent("div").find(".child:checked").length >= 1) {
            $(this).parent("div").prev("div").find(".par").prop("checked", true);
            form.render();
        } else {
            $(this).parent("div").prev("div").find(".par").prop("checked", false);
            form.render();
        }
    });

    // 监听多选框事件（点击一个得到其附属权限）
    form.on('checkbox(formEvent)', function (data) {
    });

    // 监听表格操作按钮点击
    function toUpdate() {
        $.ajax({
            url: "/storeOrganizationStructure/toUpdate",
            type: "POST",
            data: {"id": searchObj.id},
            success: function (res) {
                if (res.code == 200) {
                    $("[name=parentName]").val(res.data.parentName);
                    $("[name=pid]").val(res.data.pid);
                    $("[name=name]").val(res.data.name);
                    $("[name=id]").val(res.data.id);
                    $("[name=managerId]").val(res.data.managerId);
                    form.render();
                }
            },
            dataType: "json"
        });
    }

    // toUpdate();
    table.on('tool(test)', function (obj) {
        searchObj.id = obj.data.id;
        if (obj.event === 'edit') { // 操作—编辑
            toUpdate();
            projectile.elastic({title: " ", content: $("#popup"), area: ['800px', '486px']}, function () {
                // 监听提交
                form.on('submit(submitData)', function (data) {
                    var params = new Object();
                    params.name = data.field.name;
                    params.parentName = data.field.parentName;
                    params.pid = data.field.pid;
                    params.id = data.field.id;
                    params.managerId = $("select[name='managerId']").val();
                    for (var i = 0; i < adminUsers.length;i++) {
                        if (params.managerId == adminUsers[i].id) {
                            params.managerName = adminUsers[i].name;
                        }
                    }
                    $.ajax({
                        url: "storeOrganizationStructure/addOrUpdate",
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
            layer.confirm('确定要删除该组织架构吗？', function (index) {
                $.ajax({
                    url: "/storeOrganizationStructure/delete",
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
        getTreeData();
        $("[name='parentName']").val("");
        $("[name='pid']").val("");
        $("[name='id']").val("");
        $("[name='name']").val("");
        $("[name='managerName']:selected").text("");
        $("[name='managerName']:selected").val("");
        form.render();
        projectile.elastic({title: " ", content: $("#popup"), area: ['800px', '486px']}, function () {
            // 监听提交
            form.on('submit(submitData)', function (data) {
                var params = new Object();
                params.name = data.field.name;
                params.parentName = data.field.parentName;
                params.pid = data.field.pid;
                params.id = data.field.id;
                params.managerId = $("select[name='managerId']").val();
                for (var i = 0; i < adminUsers.length;i++) {
                    if (params.managerId == adminUsers[i].id) {
                        params.managerName = adminUsers[i].name;
                    }
                }
                $.ajax({
                    url: "storeOrganizationStructure/addOrUpdate",
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

    //表单校验
    form.verify({
        illegalChar: function (value) {
            var reg = /^[\@\#\$\%\^\&\*\(\)\{\}\:\"\<\>\?\[\]]$/;
            var split = value.split("");
            for (var i = 0; i < split.length; i++) {
                if (reg.test(split[i])) {
                    return "非法字符";
                }
            }
        },
        socialCreditCode: function (value) {
            if (!CheckSocialCreditCode(value)) {
                return "请输入正确的社会统一信用代码";
            }
        },
        account: function (value) {
            var reg = /^\w{6,20}$/;
            if (!reg.test(value)) {
                return "用户名格式为6-20个字符,包括字母、数字、下划线";
            }
        },
        contacts: function (value) {
            value = value.trim();
            if (!(value.length >= 2 && value.length <= 20)) {
                return "请输入正确的姓名";
            }
        },
        QAUrl: function (value) {
            if (imageUrl.trim().length == 0) {
                return "请上传资质证明图片";
            }
        },
        myPerm: function (value) {
            var checkLenth = $("[name='myPerMissions']:checked");
            if (checkLenth.length <= 0) {
                return "请选择权限";
            }
        },
    });

//    获取树子数据
    function getTreeData() {
        $.ajax({
            url: "storeOrganizationStructure/getListNoPage",
            type: "GET",
            success: function (res) {
                var myArray = [];
                for(var i= 0;i<res.data.length;i++){
                    var params = {};
                    params.title = res.data[i].name;
                    params.pid = res.data[i].pid;
                    params.id = res.data[i].id;
                    myArray.push(params);
                }
                treeData = toTree(myArray);
                treeRender(treeData);
            },
            dataType: "json"
        });
    }
//    渲染树子组装数据
    function toTree(data) {
        // 删除 所有 children,以防止多次调用
        data.forEach(function (item) {
            delete item.children;
        });

        // 将数据存储为 以 id 为 KEY 的 map 索引数据列
        var map = {};
        data.forEach(function (item) {
            map[item.id] = item;
        });
        var val = [];
        data.forEach(function (item) {
            // 以当前遍历项，的pid,去map对象中找到索引的id
            var parent = map[item.pid];
            // 好绕啊，如果找到索引，那么说明此项不在顶级当中,那么需要把此项添加到，他对应的父级中
            if (parent) {
                (parent.children || (parent.children = [])).push(item);
            } else {
                //如果没有在map中找到对应的索引ID,那么直接把 当前的item添加到 val结果集中，作为顶级
                val.push(item);
            }
        });
        return val;
    }

    /*===================》树子*/

    //点击节点新窗口跳转
    //开启复选框
    function treeRender(treeData) {
        tree.render({
            elem: '#test5'
            , data: treeData
            , isJump: true  //link 为参数匹配
            , showCheckbox: false,
            id: 'test5'
            , click: function (obj) {
                $("[name='parentName']").val(obj.data.title);
                $("[name='pid']").val(obj.data.id);
            }
        });
    }
});