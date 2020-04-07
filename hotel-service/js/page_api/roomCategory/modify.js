var flag=true;
var form;
var roomCategoryId;
var roomCategoryName="";
layui.use(['form'], function () {
    form=layui.form;
    roomCategoryId=parent.$("#primarykey").val();
    check();
    getRoomCategoryByRoomCategoryId(roomCategoryId);
})
function check() {
    $("#roomCategoryName").blur(function () {
        if ($(this).val().length==0){
            layer.tips("请输入类别名称", "#roomCategoryName", {
                tips : [ 4, '#FF0000' ]
            })
        }else if ($(this).val()!=roomCategoryName) {
            getRequest("","checkRoomCategoryName/"+$(this).val(),res=>{
                if (res.data.data==false){
                    flag=false;
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
function getRoomCategoryByRoomCategoryId(roomCategoryId) {
    getRequest("","roomCategory/"+roomCategoryId,res=>{
        roomCategoryName=res.roomCategoryName;
        $("#roomCategoryName").val(res.roomCategoryName);
        $("#roomCategoryRemark").val(res.roomCategoryRemark);
    })
}
function saveInfo(roomCategoryName,roomCategoryRemark) {
    var data={"roomCategoryName":roomCategoryName,"roomCategoryRemark":roomCategoryRemark,"roomCategoryId":roomCategoryId};
    postRequest(data,"modifyRoomCategory",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })

}
