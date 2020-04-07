var form;

var flag=true;
layui.use(['form'], function () {
    form=layui.form;
    fetchgoodsCategoryForSelect();
    check();
});
function fetchgoodsCategoryForSelect() {

    getRequest("","goodsCategoryForSelect",res=>{
        $.each(res,function (index,value) {
            $("#goodsCategoryName").append("<option value='"+value.goodsCategoryId+"'>"+value.goodsCategoryName+"</option>")
        })
        form.render("select");
    })
}

function check() {
    $("#goodsName").blur(function () {
        if ($(this).val().length!=0){

            getRequest("","checkGoodsName/"+$(this).val(),res=>{
                if (res>0){
                    layer.msg("商品重复", {
                        //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                        icon : 5,
                        offset : 100,
                        shift : 6, //抖动效果
                        time : 3000
                    });
                    flag=false;
                } else {
                    flag=true;
                }
            })
        }
    });
    form.on('submit(demo1)', function (data) {
        if (flag==false){
            layer.msg("商品重复", {
                //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                icon : 5,
                offset : 100,
                shift : 6, //抖动效果
                time : 3000
            });
            return false;
        } else {
            increaseGoods(data.field);
        }
    })
}
function increaseGoods(data) {
    postRequest(data,"goods",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })
}


