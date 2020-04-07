// pages/searchHotel/searchHotel.js

var mHotelList = [];

function HotelBean() {
     var image;
     var name;
     var score;
     var service;
     var address;
     var distance;
     var price;
     var test;
     var startDate;
     var endDate;
     var roomNumber;
}

Page({

     /**
      * 页面的初始数据
      */
     data: {
          location: '',
          hotelArray: [],
          loadenable: true,
          shownavindex: 1,
          priceL2H: true,
          page:1,
          startDate:'',
          endDate:''
     },

     /**
      * 生命周期函数--监听页面加载
      */
     onLoad: function (options) {
       //console.log(startDate+"---"+endDate)
          this.fetchRoomInfo();
     },

    fetchRoomInfo:function(){
      var that = this;
       wx.request({
        url: 'http://localhost:9090/hotel/api/selectTodayIsSpecial',
        method: 'GET',
        dataType: 'json',
        success: function (res) {
          console.log(res.data)
          // if( res.data.length==0){
          //   that.setData({ 
          //     loadenable:false
          //   })
          // }
          for (var i = 0; i < res.data.length; i++) {
            var hotelBean = new HotelBean();
            var date=new Date();
            hotelBean.startDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
            hotelBean.endDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1);
            
            hotelBean.image = '../../res/images/ic_hotel_image.png';
            hotelBean.name = res.data[i].roomNumber + ',' + res.data[i].categoryName;
            hotelBean.roomNumber = res.data[i].roomNumber;
            hotelBean.score = 4.5;
            hotelBean.service = '停车场/温泉/餐饮';
            hotelBean.address = res.data[i].roomFloor + '楼,' + res.data[i].roomArea + '㎡';
            hotelBean.price = res.data[i].roomMoney;
            mHotelList.push(hotelBean);
            that.setData({
              loadenable:false
            })
          }
          that.setData({
            hotelArray: mHotelList
          });
        },
      })
    },

     filterMenuTap: function (e) {
          var index = e.currentTarget.dataset.index;
          this.setData({
               shownavindex: index
          });
          if (index == 2) {
               var priceL2H = !this.data.priceL2H;
               this.setData({
                    priceL2H: priceL2H
               });
          } else {
               this.setData({
                    priceL2H: true
               });
          }
     },

     filterTap: function () {
          wx.navigateTo({
               url: '../hotelFilter/hotelFilter',
          })
     },

     locationTap: function () {
          wx.navigateTo({
               url: '../select_city/select_city'
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
       this.data.page=this.data.page+1;
       console.log(this.data.page)
          // var that = this;
          // wx: setTimeout(function () {
          //      mHotelList = [];
          //      for (var i = 0; i < 10; i++) {
          //           var hotelBean = new HotelBean();
          //           hotelBean.image = '../../res/images/ic_hotel_image.png';
          //           hotelBean.name = '深圳市优软科技大酒店';
          //           hotelBean.score = 4.5;
          //           hotelBean.service = '停车场/温泉/餐饮';
          //           hotelBean.address = '深大地铁站';
            
          //           hotelBean.price = 299;

          //           mHotelList.push(hotelBean);
          //      }

          //      that.setData({
          //           hotelArray: mHotelList
          //      });
          //      wx.showToast({
          //           title: '刷新成功',
          //           duration: 1500
          //      })
          //      wx.stopPullDownRefresh();
          // }, 2000);
     },

     /**
      * 页面上拉触底事件的处理函数
      */
     onReachBottom: function () {
       this.data.page = this.data.page + 1;
       console.log(this.data.page)
          var that = this;
          wx: setTimeout(function () {
              //  for (var i = 0; i < 10; i++) {
              //       var hotelBean = new HotelBean();
              //       hotelBean.image = '../../res/images/ic_hotel_image.png';
              //       hotelBean.name = '深圳市优软科技大酒店';
              //       hotelBean.score = 4.5;
              //       hotelBean.service = '停车场/温泉/餐饮';
              //       hotelBean.address = '深大地铁站';
              //       hotelBean.distance = '3.5';
              //       hotelBean.price = 299;

              //       mHotelList.push(hotelBean);
              //  }

              //  that.setData({
              //       hotelArray: mHotelList
              //  });
              that.fetchRoomInfo();
               
          }, 2000);

     },

     /**
      * 用户点击右上角分享
      */
     onShareAppMessage: function () {

     }
})