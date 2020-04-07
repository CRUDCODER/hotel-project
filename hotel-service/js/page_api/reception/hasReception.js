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
        //nextForm(data.field);
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
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        $(location).attr('href','hasReception.html')
        //step.next('#stepForm');
    });
    $('#confirm').click(function () {
        $(location).attr('href','hasReception.html')
        //$(location).attr('href','reception.html')
        //step.next('#stepForm');
    });
    form.on('select', function (data) {
        if ( data.elem.name=="memberId"){
            queryByMemberId(data.value);
        }
    });

    fetchMemberForSelect();
})

// function nextForm(data) {
//     $("#guestName").text(data.guestName);
//     $.ajax({
//         url:BASE_URL+"/fetchRoomByRoomId/"+data.roomId,
//         type:'get',
//         success:function (res) {
//             $("#roomNumber2").text(res.roomNumber);
//             $("#roomPrice").text(res.roomMoney)
//             console.log(res);
//         }
//     })
// }
function confirmReception() {

    postRequest(data1,"memberReception",res=>{
        if (res.status==500){
            layer.msg('支付失败', {
                icon: 2
                ,time:3000,
                shift: 6
            });
            return false;
        }else {
            data2.receptionId=data.data;
            confirmLive();
        }
    })
}
function confirmLive() {

    postRequest(data2,"memberLive",res=>{
        if (res.data==true){
            $("#msg").addClass("layui-icon layui-icon-ok")
        }else {
            $("#msg").addClass("layui-icon layui-icon-close");
            $("#msg").css("background-color","red");
            $("#message").text("支付失败")
        }
    })
    //
    // $.ajax({
    //     url:BASE_URL+"/memberLive",
    //     type:'post',
    //     contentType:'application/json;charset=utf-8',
    //     dataType:"json",
    //     data:JSON.stringify(data2),
    //     success:function (data) {
    //         if (data.data==true){
    //             $("#msg").addClass("layui-icon layui-icon-ok")
    //         }else {
    //             $("#msg").addClass("layui-icon layui-icon-close");
    //             $("#msg").css("background-color","red");
    //             $("#message").text("支付失败")
    //         }
    //     }
    // })
    console.log(data2)
}
function fetchMemberForSelect(){

    getRequest("","fetchMemberForSelect",res=>{
        $.each(res,function (index,value) {
            $("#memberId").append("<option value='"+value.memberId+"'>"+value.memberName+"</option>")
            form.render('select');
        })
    })
}
function queryByMemberId(id) {
    $("#reservationId").empty();
    $("#reservationId").append("<option value=''></option>")
    getRequest("","fetchByMemberId/"+id,res=>{
        $.each(res,function (index,value) {
            $("#reservationId").append("<option value='"+value.reservationId+"'>"+value.reservationNumber+"</option>")
            form.render('select');
        })
    })
}
