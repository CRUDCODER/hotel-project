const BASE_URL="http://localhost:9090/hotel/api/v1/";
$.ajaxSetup({
    timeout:3000,
    complete:function (data) {
        if (data.statusText=='timeout'){
            layer.msg("连接服务器超时 ⊙﹏⊙∥",{icon:2})
        }
        if (data.statusText=='success'){
            var res=data.responseJSON;
                if (res.status==500){
                    layer.msg(res.msg, {icon: 2});
                }
                if (res.status==500 && res.msg=='身份验证失败!'){
                    setTimeout(function () {
                        $(this).attr('location',"../login-2.html")
                    },800)
                }
        }
        if (data.statusText=='error'){
            var res = data.responseJSON;
            console.log(res)
            switch (data.status) {
                case 403:{
                    layer.msg(res.msg,{icon: 2});
                    break;
                }
                case 404: {
                    layer.msg("没有找到请求路径,请确认之后再重试!", {icon: 2, shift: 6, offset: 60});
                    break;
                }
                case 405: {
                    layer.msg(res.message, {icon: 2})
                    break;
                }
                case 500: {
                    layer.msg(res.message, {icon: 2})
                    break;
                }
                default: {
                    console.log("error message: " + res.message)
                    layer.msg("出现未知错误", {icon: 2})
                }
            }
        }
    },
    // error:function (error) {
    //     if (error.statusText=='timeout'){
    //         layer.msg("连接超时服务器超时",{icon:2})
    //     }else {
    //         var res = error.responseJSON;
    //         switch (error.status) {
    //             case 404: {
    //                 layer.msg("没有找到请求路径,请确认之后再重试!", {icon: 2, shift: 6, offset: 60});
    //                 break;
    //             }
    //             case 405: {
    //                 layer.msg(res.message, {icon: 2})
    //                 break;
    //             }
    //             case 500: {
    //                 layer.msg(res.message, {icon: 2})
    //                 break;
    //             }
    //             default: {
    //                 console.log("error message: " + res.message)
    //                 layer.msg("出现未知错误", {icon: 2})
    //             }
    //         }
    //     }
    // }
})

var loginRequest=(param,url,returnValue)=>{
    $.ajax({
        url:BASE_URL+url,
        type:"post",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify(param),
        success:returnValue,
    })
}

var getRequest = (param,url,returnValue)=>{
    $.ajax({
        url:BASE_URL+url,
        type: "get",
        dataType: "json",
        headers: {
            "authorization":window.localStorage.getItem("token")
        },
        data:param,
        timeout: 5*1000,
        success: returnValue
    })
};

var postRequest=(param,url,returnValue)=>{
    $.ajax({
        url:BASE_URL+url,
        type:"post",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify(param),
        headers: {
            "authorization":window.localStorage.getItem("token")
        },
        success:returnValue
    })
}
var deleteRequest=(url,returnValue)=>{
    $.ajax({
        url:BASE_URL+url,
        type:"delete",
        dataType:"json",
        headers: {
            "authorization":window.localStorage.getItem("token")
        },
        success:returnValue
    })
}
