var form;

var flag=true;
layui.use(['form'], function () {
    form=layui.form;
    fetchRoomCategoryForSelect();
    check();
});


function fetchRoomCategoryForSelect() {
    getRequest("","RoomCategoryForSelect",res=>{
        $.each(res,function (index,value) {
            $("#roomCategory").append("<option value='"+value.roomCategoryId+"'>"+value.roomCategoryName+"</option>")
        })
        form.render("select");
    })
}
function check() {
    $("#roomNumber").blur(function () {
        // if ($(this).val().length==0){
        //     layer.tips("房间号不能为空","#roomNumber",{tips:[4,'#FF0000']})
        // }
        if (isNaN($(this).val())){
            layer.tips("只能填写数字","#roomNumber",{tips:[4,'#FF0000']})
        }
        else if ($(this).val().length!=0) {
            getRequest("","checkRoomNumber/"+$(this).val(),res=>{
                if (res>0){
                    flag=false;
                    layer.msg("房间号重复",{icon:2})
                }else {
                    flag=true;
                }
            })
        }
    });

    form.on('submit(demo1)', function (data) {
        if (flag==false){
            layer.tips("房间号重复","#roomNumber",{tips:[4,'#FF0000']})
            return false;
        } else if (isNaN(data.field.roomNumber)){
            layer.tips("只能填写数字","#roomNumber",{tips:[4,'#FF0000']})
            return false;
        }else  if (data.field.roomCategoryId==""){
            layer.tips("请选择类别","#roomCategory",{tips:[4,'#FF0000']})
            return false;
        }
        else  if (isNaN(data.field.roomFloor)){
            layer.tips("只能填写数字","#roomFloor",{tips:[4,'#FF0000']})
            return false;
        }
        else  if (isNaN(data.field.roomArea)){
            layer.tips("只能填写数字","#roomArea",{tips:[4,'#FF0000']})
            return false;
        }
        else  if (isNaN(data.field.roomMoney)){
            layer.tips("只能填写数字","#roomMoney",{tips:[4,'#FF0000']})
            return false;
        }else {
            increaseRoom(data.field);
        }
    });
}
function increaseRoom(data) {
    postRequest(data,"room",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })
}
