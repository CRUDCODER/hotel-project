var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    /**
     * 暂时不做编辑
     */
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            modifyCoupons(data.couponsId);
        } else if (obj.event === 'delete') {
            layer.confirm('真的下架'+data.couponsName+'相关信息吗?', function (index) {
                deleteCoupons(data.couponsId)
            },function () {
                layer.msg("已取消",{icon:1});
            });
        }else if (obj.event === 'shelves'){
            shelvesCoupons(data.couponsId);
        }
    });


    fetchCoupns();

    increaseCoupons();
});

function fetchCoupns() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"coupons",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'couponsId',align:"center", title: "优惠卷编号"},
            {field: 'couponsName', align:"center", title: '优惠卷名称'},
            {field: 'couponMoney',align:"center",  title: '优惠金额'},
            {field: 'needScore',align:"center",  title: '所需积分'},
            {field: 'couponsRemark',align:"center",  title: '优惠卷备注'},
            {align:"center",  title: '优惠卷状态',templet: function (e) {
                    if (e.couponsFlag===1){
                        return '<span style="color: #1AA094">正常</span>'
                    } else {
                        return '<span style="color: #6666ff">已下架</span>'
                    }
                }},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function modifyCoupons(id) {
    $("#primarykey").val(id);
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'修改优惠卷信息',
            area:['550px','350px'],
            content:['modify.html','no'],
            offset:'auto',
            end:function(){
                fetchCoupns();
            }
        })
}
function deleteCoupons(id) {
    deleteRequest("coupons/"+id,res=>{
        layer.msg(res.msg,{icon:1});
        fetchCoupns();
    })
}
function shelvesCoupons(id) {

    deleteRequest("shelvesCoupons/"+id,res=>{
        layer.msg(res.msg,{icon:1});
        fetchCoupns();
    })
}
function increaseCoupons() {
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'添加优惠卷信息',
            area:['550px','350px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchCoupns();
            }
        })
    });
}
