var $;
var form;
var step;

var liveId;
var data1;
layui.use([ 'form', 'step'], function () {
    $ = layui.$,
        form = layui.form,
        step = layui.step;

    step.render({
        elem: '#stepForm',
        filter: 'stepForm',
        width: '100%', //设置容器宽度
        stepWidth: '750px',
        height: '500px',
        stepItems: [{
            title: '客户换房'
        }, {
            title: '换房详细'
        }, {
            title: '完成'
        }]
    });


    form.on('submit(formStep)', function (data) {
        liveId=data.field.liveId;
        step.next('#stepForm');
        return false;
    });

    form.on('submit(formStep2)', function (data) {
        data1=data.field;
        confirmReplace();
        step.next('#stepForm');
        return false;
    });

    $('.pre').click(function () {
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        $(location).attr('href','replace.html')
        //step.next('#stepForm');
    });
    $('#confirm').click(function () {
        $(location).attr('href','replaceRoom.html')
    });


    fetchGuestForSelect();
    fetchRoomCategoryForSelect();
    form.on('select', function (data) {
        if ( data.elem.name=="roomCategoryId"){
            queryByRoomCategoryId(data.value);
        }
    });

})

function confirmReplace() {
    data1.liveId=liveId;
    $.ajax({
        url:BASE_URL+"/replaceRoom",
        data:JSON.stringify(data1),
        type:'post',
        dataType: 'json',
        contentType:'application/json;charset=utf-8',
        success:res=>{
            if (res.data==true){
                $("#msg").addClass("layui-icon layui-icon-ok")
            } else {
                $("#msg").addClass("layui-icon layui-icon-close");
                $("#msg").css("background-color","red");
                $("#message").text("支付失败")
            }
        }
    })
}
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
        url:BASE_URL+"/fetchByRoomCategoryIdAndRoomFlag?roomCategoryId="+id+"&roomFlag=0",
        type:'get',
        success:function (data) {
            $.each(data,function (index,value) {
                $("#roomNumber").append("<option value='"+value.roomId+"'>"+value.roomNumber+"</option>")
                form.render('select');
            })
        }
    })
}
function fetchGuestForSelect() {
    $("#guest").empty();
    $("#guest").append('<option value=""></option>')
    $.ajax({
        url:BASE_URL+"/selectLiveToday",
        type:'get',
        dataType:'json',
        success:res=>{
            $.each(res,function (index,value) {
                $("#guest").append('<option value="'+value.liveId+'">姓名：'+value.guestName+',入住房间：'+value.roomNumber+',电话:'+value.guestPhone+'</option>')
            })
            form.render("select");
            console.log(res);
        }
    })
}

