var form

var flag=true;
layui.use(['form'], function () {
    form=layui.form;
    check();
})

function check() {
    $("#goodsCategoryName").blur(function () {
        if ($(this).val().length==0){
            layer.tips("请输入类别名称", "#goodsCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }else {

            getRequest("","checkGoodsCategoryName/"+$(this).val(),res=>{
                if (res>0){
                    layer.msg("类别名称已存在",{icon:5,shift:6})
                    flag=false;
                } else {
                    flag=true
                }
            })
        }
    });
    $("#goodsCategoryRemark").blur(function () {
        if ($(this).val().length==0){
            layer.tips("请输入类别备注", "#goodsCategoryRemark", {
                tips : [ 4, '#FF0000' ]
            })
        }
    })
    $("#tijiao").on('click',function () {
        if ($("#goodsCategoryName").val().length==0){
            layer.tips("请输入类别名称", "#goodsCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }
        else  if (flag==false){
            layer.tips("类别名称已存在", "#goodsCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }
        else if ($("#goodsCategoryRemark").val().length==0){
            layer.tips("请输入类别备注", "#goodsCategoryRemark", {
                tips : [ 4, '#FF0000' ]
            })
        }
        else {
            saveInfo($("#goodsCategoryName").val(),$("#goodsCategoryRemark").val());
        }
    })
}
function saveInfo(goodsCategoryName,goodsCategoryRemark) {
    var data={"goodsCategoryName":goodsCategoryName,"goodsCategoryRemark":goodsCategoryRemark};
    postRequest(data,"goodsCategory",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })
}
