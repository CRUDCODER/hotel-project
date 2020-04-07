// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

      memberName:'',
      memberPhone:'',
      memberPassword:'',
      memberCard:'',
      memberMail:'',
      memberImg: ''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  name:function(e){
    this.setData({
     memberName:e.detail.value
    });
  },
  phone:function(e){
    this.setData({
      memberPhone:e.detail.value
    })
  },
  card:function(e){
    this.setData({
      memberCard:e.detail.value
    })
  },
  mail:function(e){
    this.setData({
      memberMail:e.detail.value
    });
  },
  password:function(e){
    this.setData({
      memberPassword:e.detail.value
    });
  },

  register:function(){
    //等待发送请求...
    console.log(this.data)
    var that=this;
      wx.request({
      url: 'http://192.168.0.109:9090/hotel/api/member',
      data: JSON.stringify(that.data),
      header: {'content-type':'application/json'},
      method: 'post',
      dataType: 'json',
      success: (result)=>{
        if(result.data.status==200){
          wx.navigateTo({
            url: '/pages/msg/msg_success?memberNumber='+result.data.data
        })
        }else{
          wx.navigateTo({
            url: '/pages/msg/msg_fail'
        })
        }
      },
    });

  },
  chooseImage: function (e) {
    var that = this;
    wx.chooseImage({
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
          console.log(res.tempFilePaths[0])
            // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
            that.setData({
              memberImg: res.tempFilePaths[0]
            });
        }
    })
},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})