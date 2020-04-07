var form;
var date;

layui.use(['form', 'laydate'], function () {
    form = layui.form;
    date = layui.laydate;
    date.render({
        elem: '#date'
    });
    fetchgoodsCategoryForSelect();
    form.on('select', function (data) {
        if (data.elem.name == "goodsCategoryId") {
            queryByGoodsCategoryId(data.value);
        }
    });

    check();
});

function fetchgoodsCategoryForSelect() {

    getRequest("","goodsCategoryForSelect",res=>{
        $.each(res, function (index, value) {
            $("#goodsCategoryId").append("<option value='" + value.goodsCategoryId + "'>" + value.goodsCategoryName + "</option>")
            form.render('select');
        })
    })
}

function queryByGoodsCategoryId(id) {
    $("#goodsName").empty();
    $("#goodsName").append("<option value=''></option>")
    getRequest("","fetchGoodsCategoryId/"+id,res=>{
        $.each(res, function (index, value) {
            $("#goodsName").append("<option value='" + value.goodsId + "'>" + value.goodsName + "</option>")
            form.render('select');
        })
    })
}

function check() {
    form.on('submit(demo1)', function (data) {
        var value = data.field;
        if (value.storageCount <= 0) {
            layer.msg("入库数量不能小于等于0", {
                //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                icon: 5,
                offset: 100,
                shift: 6, //抖动效果
                time: 3000
            });
            return false;
        }
        if (isNaN(value.storageCount)) {
            layer.msg("只能填写数字", {
                //1:正确；2:错误；3:询问；4:锁定；5:失败；6：成功；7:警告；16：加载
                icon: 5,
                offset: 100,
                shift: 6, //抖动效果
                time: 3000
            });
            return false;
        }
        increaseGoodsStorage(value);
})

function increaseGoodsStorage(value) {
        postRequest(value,"goodsStore",res=>{
            parent.layer.msg(res.msg, {icon: 1});
            var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
            parent.layer.close(index);
        })
    }
}
