

var form;


layui.use(['form'], function () {
    form=layui.form;
    fetchGuestForSelect();

    queryLiveMoneyByLiveId();
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

function queryLiveMoneyByLiveId(){

}

function check() {
    form.on('submit(demo1)', function (data) {
       increaseGoods(JSON.stringify(data.field));
    })
}
function increaseGoods(data) {
    $.ajax({
        url:BASE_URL+"/guestContract",
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


