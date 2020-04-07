var form;

var flag=true;
layui.use(['form'], function () {
    form=layui.form;

    check();
});





function check() {
    form.on('submit(demo1)', function (data) {
        $.ajax({
            url:BASE_URL+"/checkCard/"+data.field.blacklistCard,
            type:'get',
            dataType:'json',
            success:res=>{
                if(res>0){
                    flag=false;
                layer.msg("黑名单已存在该客户,请勿重复设置!", {
                    icon : 7,//1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告
                    offset : 50,
                    shift : 6,
                    time : 4000
                });
                return false;
                }else{
                    flag=true;
                    increaseBlacklist(JSON.stringify(data.field));
                }
            }
        })

    })
}

function increaseBlacklist(data) {
    $.ajax({
        url:BASE_URL+"/blacklist",
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
