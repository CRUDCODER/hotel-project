var form;
var date;

layui.use(['form','laydate'], function () {
    form=layui.form;
    date=layui.laydate;
    date.render({
        elem:'#date'
    });
    fetchRoomCategoryForSelect();
    fetchgoodsCategoryForSelect();
    form.on('select', function (data) {
        if ( data.elem.name=="roomCategoryId"){
            queryByRoomCategoryId(data.value);
        }
        if (data.elem.name=="goodsCategoryId") {
            queryByGoodsCategoryId(data.value);
        }
    });
    check();
});
function fetchRoomCategoryForSelect(){
    getRequest("","RoomCategoryForSelect",res=>{
        $.each(res,function (index,value) {
            $("#roomCategoryId").append("<option value='"+value.roomCategoryId+"'>"+value.roomCategoryName+"</option>")
            form.render('select');
        })
    })
}

function fetchgoodsCategoryForSelect() {

    getRequest("","goodsCategoryForSelect",res=>{
        $.each(res,function (index,value) {
            $("#goodsCategoryId").append("<option value='"+value.goodsCategoryId+"'>"+value.goodsCategoryName+"</option>")
            form.render('select');
        })
    })
}
function queryByRoomCategoryId(id) {
    $("#roomNumber").empty();
    $("#roomNumber").append("<option value=''></option>")
    getRequest("","fetchByRoomCategoryId/"+id,res=>{
        $.each(res,function (index,value) {
            $("#roomNumber").append("<option value='"+value.roomId+"'>"+value.roomNumber+"</option>")
            form.render('select');
        })
    })
}
function queryByGoodsCategoryId(id) {
    $("#goodsName").empty();
    $("#goodsName").append("<option value=''></option>")
    getRequest("","fetchGoodsCategoryId/"+id,res=>{
        $.each(res,function (index,value) {
            $("#goodsName").append("<option value='"+value.goodsId+"'>"+value.goodsName+"</option>")
            form.render('select');
        })
    })
}

function check() {
    form.on('submit(demo1)', function (data) {
        increaseRoomGoods(data.field);
    })
}

function increaseRoomGoods(data) {
    postRequest(data,"roomGoods",res=>{
        parent.layer.msg(res.msg, {icon: 1});
        var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
        parent.layer.close(index);
    })
}
