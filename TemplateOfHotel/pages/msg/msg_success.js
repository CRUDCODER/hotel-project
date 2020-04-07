Page({

    data:{
        memberNumber:''
    },

    onLoad: function (options) {
        console.log(options)
        this.setData({
            memberNumber:options.memberNumber
        })
    },

    returnLogin:function(){
        wx.navigateTo({
            url: '/pages/login/login'
        })
    }
});