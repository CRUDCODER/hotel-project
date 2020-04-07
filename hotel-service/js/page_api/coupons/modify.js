var form;

var flag=true;
var couponsId;
var oldName;
layui.use(['form'], function () {
    couponsId=parent.$("#primarykey").val();
    form=layui.form;
    check();
    fetchByCouponsId();
});
function fetchByCouponsId() {

    getRequest("","coupons/"+couponsId,res=>{
        oldName=res.couponsName;
        $("#couponsName").val(res.couponsName);
        $("#couponsMoney").val(res.couponMoney);
        $("#needScore").val(res.needScore);
        $("#couponsRemark").val(res.couponsRemark);
    })
}
function check() {
    $("#couponsName").blur(function () {
        if ($(this).val()!=oldName){
            getRequest("","checkCouponsName/"+$(this).val(),res=>{
                if (data>0){
                    layer.msg("优惠卷重复", {
                        //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                        icon : 5,
                        offset : 100,
                        shift : 6, //抖动效果
                        time : 3000
                    });
                    // layer.tips("商品重复","#goodsName",{tips:[4,'#FF0000']})
                    flag=false;
                }else {
                    flag=true;
                }
            })
        }else {
            flag=true;
        }
    });
    form.on('submit(demo1)', function (data) {
        if (flag==false){
            layer.msg("优惠卷重复", {
                //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                icon : 5,
                offset : 100,
                shift : 6, //抖动效果
                time : 3000
            });
            return false;
        } else {
            data.field.couponsId=couponsId;
            modifyCoupons(data.field);
        }
    })
}
function modifyCoupons(data) {
    postRequest(data,"modifyCoupons",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })
}


