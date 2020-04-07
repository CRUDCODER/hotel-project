var form;

var liveMoney;
layui.use(['form'], function () {
    form=layui.form;
    fetchGuestForSelect();

    form.on('select', function (data) {
      if(data.elem.name==='liveId'){
            queryLiveMoneyByLiveId(data.value);
      }
    });


    check();
});
function fetchGuestForSelect() {
    $("#guest").empty();
    $("#guest").append('<option value=""></option>')
    $.ajax({
        url:BASE_URL+"/selectLiveAll",
        type:'get',
        dataType:'json',
        success:res=>{
            $.each(res,function (index,value) {
                $("#guest").append('<option value="'+value.liveId+'">姓名：'+value.guestName+',入住房间：'+value.roomNumber+',电话:'+value.guestPhone+'</option>')
            })
            form.render("select");
        }
    })
}

function queryLiveMoneyByLiveId(id){
    $.ajax({
        url:BASE_URL+"/queryLiveMoneyByLiveId/"+id,
        type:'get',
        dataType:'json',
        success:res=>{
            $("#liveMoney").val(res)
            liveMoney=res;
        }
    });
}

function check() {
    form.on('submit(demo1)', function (data) {
        if(parseInt( data.field.refundMoney)>parseInt( liveMoney)){
            layer.msg("返回金额不能大于押金", {
                icon : 7,//1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告
                offset : 100,
                shift : 6,
                time : 4000
                });
             return false;
        }else{

        }
       increaseLeave(JSON.stringify(data.field));
    })
}
function increaseLeave(data) {
    $.ajax({
        url:BASE_URL+"/guestLeave",
        data:data,
        type:"post",
        dataType:"json",
        contentType:"application/json;charset=utf-8",
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
        }
    })
}
