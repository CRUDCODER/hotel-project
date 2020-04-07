var $;
var form;
var step;

var data1;
var data2;
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
            title: '添加接待记录'
        }, {
            title: '办理入住手续'
        }, {
            title: '完成'
        }]
    });


    form.on('submit(formStep)', function (data) {
        data1=data.field;
        nextForm(data.field);
        step.next('#stepForm');
        return false;
    });

    form.on('submit(formStep2)', function (data) {
        data2=data.field;
        confirmReception();
        step.next('#stepForm');
        return false;
    });

    $('.pre').click(function () {
        alert("1")
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        $(location).attr('href','noReception.html')
        //step.next('#stepForm');
    });
    $('#confirm').click(function () {
        $(location).attr('href','reception.html')
        //step.next('#stepForm');
    });
    form.on('select', function (data) {
        if ( data.elem.name=="roomCategoryId"){
            queryByRoomCategoryId(data.value);
        }
    });

    fetchRoomCategoryForSelect();
})

function nextForm(data) {
    //
    // $.ajax({
    //     url:BASE_URL+"/checkCard/"+data.guestCard,
    //     type:'get',
    //     dataType:'json',
    //     success:res=>{
    //         if(res>0){
    //         layer.msg("该用户为黑名单用户,请谨慎处理!", {
    //             icon : 7,//1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告
    //             offset : 50,
    //             shift : 6,
    //             time : 4000
    //             });
    //         }
    //     }
    // })
    $("#guestName").text(data.guestName);

    getRequest("","fetchRoomByRoomId/"+data.roomId,res=>{
        $("#roomNumber2").text(res.roomNumber);
        $("#roomPrice").text(res.roomMoney)
        console.log(res);
    });
}
function confirmReception() {

    postRequest(data1,"guestReception",res=>{
        if (res.status==200){
            data2.receptionId=res.data;
            confirmLive();
        } else {
            return false;
        }
    })
}
function confirmLive() {
    var price=$("#roomPrice").text();
    var totalPrice=parseInt(price)+parseInt(data2.liveMoney);
    data2.liveMoney=totalPrice;
    postRequest(data2,"guestLive",res=>{
        if (res.status==200){
            $("#msg").addClass("layui-icon layui-icon-ok")
        } else {
            $("#msg").addClass("layui-icon layui-icon-close");
            $("#msg").css("background-color","red");
            $("#message").text("支付失败")
        }
    })
    console.log(data2)
}
function fetchRoomCategoryForSelect(){
    getRequest("","RoomCategoryForSelect",res=>{
        $.each(res,function (index,value) {
            $("#roomCategoryId").append("<option value='"+value.roomCategoryId+"'>"+value.roomCategoryName+"</option>")
            form.render('select');
        })
    })
}
function queryByRoomCategoryId(id) {
    $("#roomNumber").empty();
    $("#roomNumber").append("<option value=''></option>")
    getRequest("","fetchByRoomCategoryIdAndRoomFlag?roomCategoryId="+id+"&roomFlag=0",res=>{
        $.each(res,function (index,value) {
            $("#roomNumber").append("<option value='"+value.roomId+"'>"+value.roomNumber+"</option>")
            form.render('select');
        })
    })
}
