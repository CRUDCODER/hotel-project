var form;

var flag=true;
layui.use(['form'], function () {
    form=layui.form;
    fetchRoomCategoryForSelect();
    form.on('select', function (data) {
        if ( data.elem.name=="roomCategoryId"){
            queryByRoomCategoryId(data.value);
        }
    });

    check();
});
function fetchRoomCategoryForSelect(){
    $.ajax({
        url:BASE_URL+"/RoomCategoryForSelect",
        type:'get',
        async:true,
        success:function (data) {
            $.each(data,function (index,value) {
                $("#roomCategoryId").append("<option value='"+value.roomCategoryId+"'>"+value.roomCategoryName+"</option>")
                form.render('select');
            })
        }
    })
}


function queryByRoomCategoryId(id) {
    $("#roomNumber").empty();
    $("#roomNumber").append("<option value=''></option>")
    $.ajax({
        url:BASE_URL+"/fetchByRoomCategoryId/"+id,
        type:'get',
        success:function (data) {
            $.each(data,function (index,value) {
                $("#roomNumber").append("<option value='"+value.roomId+"'>"+value.roomNumber+"</option>")
                form.render('select');
            })
        }
    })
}

function check() {
    form.on('submit(demo1)', function (data) {
        $.ajax({
            url:BASE_URL+"/checkIsIn/"+data.field.roomId,
            type:'get',
            dataType:'json',
            success:res=>{
                if(res>0){
                    flag=false;
                layer.msg("当天同一间房只能设置一次", {
                    icon : 7,//1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告
                    offset : 100,
                    shift : 6,
                    time : 4000
                });
                return false;
                }else{
                    flag=true;
                    increaseSpecialRoom(JSON.stringify(data.field));
                }
            }
        })

    })
}

function increaseSpecialRoom(data) {
    $.ajax({
        url:BASE_URL+"/special",
        type:'post',
        dataType:"json",
        data:data,
        contentType:'application/json;charset=utf-8',
        success:function (data) {
            if (data.data==true){
                parent.layer.msg(data.msg, {icon: 1});
                var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
                parent.layer.close(index);
            }else {
                parent.layer.msg(data.msg, {icon: 2});
                var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
                parent.layer.close(index);
            }
        },
        error:function (msg) {
            parent.layer.msg(msg.responseJSON.message, {icon: 2});
            var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
            parent.layer.close(index);
        }
    })
}
