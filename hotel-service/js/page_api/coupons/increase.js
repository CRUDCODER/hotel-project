var form;

var flag=true;
layui.use(['form'], function () {
    form=layui.form;
    check();
});
function check() {
    $("#couponsName").blur(function () {
        if ($(this).val().length!=0){
            getRequest("","checkCouponsName/"+$(this).val(),res=>{
                if (res>0){
                    layer.msg("优惠卷重复", {
                        //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                        icon : 5,
                        offset : 100,
                        shift : 6, //抖动效果
                        time : 3000
                    });
                    // layer.tips("商品重复","#goodsName",{tips:[4,'#FF0000']})
                    flag=false;
                } else {
                    flag=true;
                }
            })
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
            increaseCoupons(data.field);
        }
    })
}
function increaseCoupons(data) {
    postRequest(data,"coupons",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })
}


