// pages/myCoupons/myCoupons.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    coupons:[0,1,2]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(app.globalData.userInfo==null){
      wx.showModal({
        title: '未登录',
        content: '检测到您未登录',
        confirmText: "立即登录",
        cancelText: "暂不登录",
        success: function (res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '/pages/login/login',
            })
            
          } else {
            wx.switchTab({
              url: '/pages/minePage/minePage',
            })
          }
        }
      });
    }
    this.fetchCoupons();
  },

  fetchCoupons:function(){
     wx.request({
      url: 'http://192.168.0.109:9090/hotel/api/memberExchanges?page=1&limit=10&memberId='+app.globalData.userInfo.memberId,
      method: 'GET',
      dataType: 'json',
      success: res=>{
        this.setData({
          coupons:res.data.data
        });
      },
    });
    // var data2={data:{"title":123,"text":"aaa"},data2:{"title":456,"text":"bbb"}}

    // this.setData({
    //   coupons:data2
    // })
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