/**
 * Created by zhuxq on 2019/9/6.
 */

layui.config({
    base: "/bjzgh/js/"
}).use(['jquery', 'table', 'layer', 'form','hour', 'tree'], function () {
    var $ = layui.jquery
        , table = layui.table
        , layer = layui.layer
        , form = layui.form
        , tree = layui.tree
        , hour = layui.hour;//自定义时间选择

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


    //获取所有用户
    var adminUsers = [];

    // 获取管理人
    function getAdminUser() {
        $.ajax({
            url: "/sysAdmin/listNoPage",
            type: "GET",
            success: function (res) {
                if (res.code == "200") {
                    var htmlOne = '<option value="">请选择一管理人</option>';
                    var htmlTwo = '<option value="">请选择二管理人</option>';
                    var htmlThree = '<option value="">请选择三管理人</option>';
                    var htmlFour = '<option value="">请选择四管理人</option>';
                    for (var i = 0; i <res.data.length ; i++) {
                        htmlOne += '<option value="'+res.data[i].id+'">'+res.data[i].name+'('+res.data[i].organizationalStructureName+')'+'</option>'
                        htmlTwo += '<option value="'+res.data[i].id+'">'+res.data[i].name+'('+res.data[i].organizationalStructureName+')'+'</option>'
                        htmlThree += '<option value="'+res.data[i].id+'">'+res.data[i].name+'('+res.data[i].organizationalStructureName+')'+'</option>'
                        htmlFour += '<option value="'+res.data[i].id+'">'+res.data[i].name+'('+res.data[i].organizationalStructureName+')'+'</option>'
                    }
                    $("[name = 'firstManagerId']").html(htmlOne);
                    $("[name = 'secondManagerId']").html(htmlTwo);
                    $("[name = 'thirdManagerId']").html(htmlThree);
                    $("[name = 'fourthManagerId']").html(htmlFour);
                    adminUsers = res.data;
                    form.render();
                } else {
                    layer.msg(res.data.msg);
                }
            }
        });
    }

    var storeList = [];
    // 获取所有仓库
    function getTotalStrore() {
        $.ajax({
            url: "/storeOrganizationStructure/getListNoPage",
            type: "GET",
            success: function (res) {
                if (res.code == "200") {
                    var html = '<option value="">请选择仓库</option>';
                    for (var i = 0; i <res.data.length ; i++) {
                        html += '<option value="'+res.data[i].id+'">'+res.data[i].name+'</option>'
                    }
                    $("[name = 'stationStoreId']").html(html);
                    storeList = res.data;
                    form.render();
                } else {
                    layer.msg(res.data.msg);
                }
            }
        });
    }

    getTotalStrore();
    getAdminUser();

    //通过id获取用户名
    function getAdminUserById(arr,id){
        for (var i=0;i<arr.length;i++){
            if (id == arr[i].id) {
                return arr[i].name;
            }
        }
    }

    $("[name='cenCelBtn']").click(function () {
        window.location.href="route?name=service/station/stationList";
    });
// //加载
    function checkPreId() {
        var id = $("[name='stationId']").val();
        if(id !=undefined ){
            $.ajax({
                url:"/serviceStationList/toUpdate" ,
                type:"GET" ,
                data:{"id":id},
                success:function (res) {
                    if(res.code == 200) {
                        $("[name='area']").val(res.data.area);
                        $("[name='province']").val(res.data.province);
                        $("[name='city']").val(res.data.city);
                        $("[name='county']").val(res.data.county);
                        $("[name='stationName']").val(res.data.stationName);
                        $("[name='stationType']").val(res.data.stationType);
                        $("[name='firstManagerId']").val(res.data.firstManagerId);
                        $("[name='secondManagerId']").val(res.data.secondManagerId);
                        $("[name='thirdManagerId']").val(res.data.thirdManagerId);
                        $("[name='fourthManagerId']").val(res.data.fourthManagerId);
                        $("[name='isCoreStation']").val(res.data.isCoreStation);
                        $("[name='stationCreateDate']").val(res.data.stationCreateDate);
                        $("[name='belongTreeAddress']").val(res.data.belongTreeAddress);
                        $("[name='repaireHourPrice']").val(res.data.repaireHourPrice);
                        $("[name='outMileagePrice']").val(res.data.outMileagePrice);
                        $("[name='outSubsidy']").val(res.data.outSubsidy);
                        $("[name='auxiliaryEquipmentCost']").val(res.data.auxiliaryEquipmentCost);
                        $("[name='outCarFirst']").val(res.data.outCarFirst);
                        $("[name='stationStoreId']").val(res.data.stationStoreId);
                        $("[name='outCarSecond']").val(res.data.outCarSecond);
                        form.render();
                    }
                },
                dataType:"json"
            });
        }
    }

    checkPreId();
    var id = $("[name='stationId']").val();
    // 监听提交
    form.on('submit(submitData)', function (data) {
        $("[name=sureButton]").attr("disabled","disabled");
        var params = {};
        params = data.field;
        params.firstManagerName = getAdminUserById(adminUsers, params.firstManagerId);
        params.secondManagerName = getAdminUserById(adminUsers, params.secondManagerId);
        params.thirdManagerName = getAdminUserById(adminUsers, params.thirdManagerId);
        params.fourthManagerName = getAdminUserById(adminUsers, params.fourthManagerId);
        params.stationStoreName = getAdminUserById(storeList, params.stationStoreId);
        params.id = $("[name='stationId']").val();
            $.ajax({
                url:"serviceStationList/addOrUpdate" ,
                type:"POST" ,
                data: params,
                success:function (res) {
                    if(res.code==200){
                        window.location.href="route?name=service/station/stationList";
                        layer.msg("操作成功");
                    }else {
                        $("[name=sureButton]").removeAttr("disabled");
                        $("[name=sureButton]").attr("enabled","enabled");
                        layer.msg("操作失败");
                    }
                },
                dataType:"json"
            });
            return false;
    });
});

