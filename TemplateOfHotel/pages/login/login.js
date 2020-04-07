const app=getApp()
Page({
  data:{
    number:0,
    password:''
  },
  login:function(param){
    wx.request({
      url: 'http://192.168.0.109:9090/hotel/api/loginMember',
      dataType:"json",
      method:"post",
      data: { "memberNumber": this.data.number, "memberPassword": this.data.password},
      success:function(res){
        var that=this;
        if (res.data.status==200){

          app.globalData.userInfo=res.data.data;
          console.log(app.globalData.userInfo)
            wx.showToast({
              title: '登陆成功',
            });
           setTimeout(function () {
            wx.switchTab({
              url: '/pages/homePage/homePage',

            })
           }, 800)
         }else{
          wx.showModal({
            title: '出现错误',
            content: '卡号或密码错误',
            confirmText: "再试一次",
            cancelText: "暂不登录",
            success: function (res) {
              if (res.confirm) {
                console.log('再试一次')
              } else {
                wx.switchTab({
                  url: '/pages/homePage/homePage',
                })
              }
            }
          });
         }
      }
    })
   // console.log(this.data.number + " " + this.data.password)
  },
  register:function(){
    wx.navigateTo({
      url: '/pages/register/register'
    })
  },
  memberNumber:function(e){
    this.setData({
      number:e.detail.value
    });
  },
  memberPassword:function(e){
    this.setData({
      password: e.detail.value
    });
  }
})
