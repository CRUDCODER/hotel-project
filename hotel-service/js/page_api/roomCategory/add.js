var form

var flag=true;
layui.use(['form'], function () {
    form=layui.form;
    check();
})

function check() {
    $("#roomCategoryName").blur(function () {
        if ($(this).val().length==0){
            layer.tips("请输入类别名称", "#roomCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }else {
            $.ajax({
                url:BASE_URL+"/checkRoomCategoryName/"+$(this).val(),
                type:"get",
                headers:{
                    "authorization":window.localStorage.getItem("token")
                },
                success:function (data) {
                    if (data.data==false){
                        layer.tips("类别名称已存在", "#roomCategoryName", {
                            tips : [ 4, '#FF0000' ]
                        })
                        flag=false;
                    }
                }
            })
        }
    });
    $("#roomCategoryRemark").blur(function () {
        if ($(this).val().length==0){
            layer.tips("请输入类别备注", "#roomCategoryRemark", {
                tips : [ 4, '#FF0000' ]
            })
        }
    })
    $("#tijiao").on('click',function () {
        if ($("#roomCategoryName").val().length==0){
            layer.tips("请输入类别名称", "#roomCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }
        else  if (flag==false){
            layer.tips("类别名称已存在", "#roomCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }
        else if ($("#roomCategoryRemark").val().length==0){
            layer.tips("请输入类别备注", "#roomCategoryRemark", {
                tips : [ 4, '#FF0000' ]
            })
        }
        else {
            saveInfo($("#roomCategoryName").val(),$("#roomCategoryRemark").val());
        }
    })
}

function saveInfo(roomCategoryName,roomCategoryRemark) {
    var data={"roomCategoryName":roomCategoryName,"roomCategoryRemark":roomCategoryRemark};
    $.ajax({
        url:BASE_URL+"/roomCategory",
        type:"post",
        dataType:"json",
        data:JSON.stringify(data),
        headers:{
          "authorization":window.localStorage.getItem("token")
        },
        contentType:'application/json;charset=utf-8',
        success:function (data) {
            if (data.data==true){
                parent.layer.msg(data.msg, {icon: 1});
                var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
                parent.layer.close(index);
            }else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }
    })
}
