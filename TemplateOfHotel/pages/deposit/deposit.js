// pages/deposit/deposit.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isAgree:true,
    depositMoney:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  money:function(e){
    this.setData({
      depositMoney: e.detail.value
    });
    console.log(this.data.depositMoney)
  },
  payMoney:function(){
    var data = { "depositMoney": this.data.depositMoney, "memberId": app.globalData.userInfo.memberId}
    wx.request({
      url: 'http://192.168.0.109:9090/hotel/api/memberDeposit',
      method:'post',
      dataType: "json",
      data:data,
      success:res=>{
        if(res.data.data==true){
          this.fetchMemberInfo();
          wx.showToast({
            title: '充值成功',
          });
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/minePage/minePage',
            })
          }, 800) 
        }
      }
    })
  },

  fetchMemberInfo:function(){
    wx.request({
      url: 'http://192.168.0.109:9090/hotel/api/member/' + app.globalData.userInfo.memberId,
      method:'get',
      dataType:'json',
      success:res=>{
        app.globalData.userInfo = res.data;
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