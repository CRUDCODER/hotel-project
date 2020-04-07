// pages/bookHotel/bookHotel.js

var roomPrice;
var hotelName;
var roomName;
var startDate;
var endDate;
var memberName;
var memberPhone;
var memberCard;
var roomNumber;
const app=getApp()
Page({

     /**
      * 页面的初始数据
      */
     data: {
          isDiscount: false,
          roomPrice,
          hotelName,
          roomName,
          startDate,
          endDate,
          discount: '不选择优惠',
          memberName:'',
          memberPhone:'',
          memberCard:'',
          roomNumber:'',
          goods:'',
          remark:'',
          data: [],
          couponsName:'不选择优惠'
     },

     /**
      * 生命周期函数--监听页面加载
      */
     onLoad: function (options) {
       this.fetchCoupons();
       console.log(options)
          roomPrice = options.price;
          hotelName = options.hotelName;
          //roomName = options.roomName;
          startDate = options.startDate;
          endDate = options.endDate;
       roomNumber = options.roomNumber;
       memberName = app.globalData.userInfo.memberName;
       memberPhone = app.globalData.userInfo.memberPhone;
       memberCard = app.globalData.userInfo.memberCard;
          this.setData({
               roomPrice: roomPrice,
               hotelName: hotelName,
               //roomName: roomName,
               startDate: startDate,
               endDate: endDate,
            memberName: memberName,
            memberPhone: memberPhone,
            memberCard: memberCard
          });
       this.fetchRoomGoods();
     },
    fetchRoomGoods:function(){
      wx.request({
        url: 'http://192.168.0.109:9090/hotel/api/GoodsByRoomNumber?roomNumber='+roomNumber,
        method:'get',
        dataType:'json',
        success:res=>{
          this.setData({
            goods:res.data.msg
          });
          console.log(res.data.msg)
        }
      })
    },
  payMoney:function(){
    var data = { "memberId": app.globalData.userInfo.memberId, "roomNumber": roomNumber, "payMoney": this.data.roomPrice,"reservationDate":startDate,"reservationRemark":this.data.remark,"couponsName":this.data.couponsName};
    wx.request({
      url: 'http://192.168.0.109:9090/hotel/api/roomReservation',
      dataType:'json',
      method:"post",
      data:data,
      success:res=>{
        if(res.data.data==true){
          wx.showToast({
            title: '预定成功',
          });
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/homePage/homePage',

            })
          }, 800) 
        }else{
          wx.showToast({
            title: '预定成功',
          });
        }
      }
    })
  },
  reservationRemark:function(e){
    this.setData({
      remark:e.detail.value
    });
  }, 
  fetchCoupons:function(){
    wx.request({
     url: 'http://192.168.0.109:9090/hotel/api/memberExchanges?page=1&limit=10&exchangeFlag=1&memberId='+app.globalData.userInfo.memberId,
     method: 'GET',
     dataType: 'json',
     success: res=>{
      for(var i=0;i<res.data.data.length;i++){
        this.data.data[i]=res.data.data[i].couponsName
      }
     },
   });
 },
  open: function(){
      this.setData({
        roomPrice:roomPrice
      });
      var that=this;
      wx.showActionSheet({
          itemList:that.data.data,
          success: function(res) {
              if (!res.cancel) {
                that.setData({
                  couponsName:that.data.data[res.tapIndex]
                });
                that.queryByCouponsName();
              }
          }
      });
  },
  queryByCouponsName:function(){
      wx.request({
        url:'http://192.168.0.109:9090/hotel/api/couponsByCouponsName/'+this.data.couponsName,
        dataType:'json',
        method:'get',
        success:res=>{
          var nowMoney=parseInt(this.data.roomPrice)-parseInt(res.data.couponMoney);
          this.setData({
            roomPrice:nowMoney
          });
        }
      });
  },
  closeDialog: function () {
    this.setData({
        istrue: false
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