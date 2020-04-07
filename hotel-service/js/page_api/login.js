layui.use(['form'], function () {
    var form = layui.form,
        layer = layui.layer;

    // 登录过期的时候，跳出ifram框架
    if (top.location != self.location) top.location = self.location;
    // 粒子线条背景
    $(document).ready(function(){
        $('.layui-container').particleground({
            dotColor:'#5cbdaa',
            lineColor:'#5cbdaa'
        });
    });

    // 进行登录操作
    form.on('submit(login)', function (data) {
        data = data.field;
        if (data.username == '') {
            layer.msg('用户名不能为空');
            return false;
        }
        if (data.password == '') {
            layer.msg('密码不能为空');
            return false;
        }
        var data={"userAccount":data.username,"userPassword":data.password}
        $.ajax({
            url:BASE_URL+"/login",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json;charset=utf-8",
            success:function (data) {
                layer.msg(data.msg, {icon: 1});
                window.localStorage.setItem("token",data.data.token)
                setInterval(function () {
                    $(this).attr('location',"../index.html")
                },800)
            },
            error:function (msg) {
                console.log("发送出错,具体:"+msg)
            }
        })
        return false;
    });
});
