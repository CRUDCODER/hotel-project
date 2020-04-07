var $;
var table;
var form;

var goodCategoryId='';
var goodsCount='';
var count2='';
layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    fetchGoods();
    increaseGoods();
    fetchgoodsCategoryForSelect();
    seachGoods();
});

function fetchGoods() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'goods?goodsCategoryId='+goodCategoryId+"&goodsCount="+goodsCount+"&count2="+count2,
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'goodsId',align:"center", title: "商品编号"},
            {field: 'goodsName', align:"center", title: '商品名称'},
            {field: 'goodsCategoryName',align:"center",  title: '商品类别'},
            {field: 'goodsPrice',align:"center",  title: '商品价格'},
            {field: 'goodsCount',align:"center",  title: '库存数量'},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function increaseGoods() {
    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'添加商品信息',
            area:['550px','300px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchGoods();
            }
        })
    });
}


function fetchgoodsCategoryForSelect() {

    getRequest("","goodsCategoryForSelect",res=> {
        $.each(res, function (index, value) {
            $("#goodCategory").append("<option value='" + value.goodsCategoryId + "'>" + value.goodsCategoryName + "</option>")
            form.render('select');
        })
    })
}

function seachGoods(){
    form.on('submit(data-search-btn)', function (data) {
        var value=data.field;
        if(isNaN(value.goodsCount)){
               layer.msg("请填写数字",{
                   shift:6,
                   icon:2,
                   time:2000
               })
               $("#count1").val("")
               return false;
        }
        if(isNaN(value.count2)){
            layer.msg("请填写数字",{
                shift:6,
                icon:2,
                time:2000
            })
            $("#count2").val("")
            return false;
        }
        if(parseInt(value.goodsCount)>parseInt(value.count2)){
            layer.msg("填写范围有误",{
                shift:6,
                icon:2,
                time:2000
            })
            $("#count1").val("")
            $("#count2").val("")
            return false;
        }


        if(value.goodCategoryId==''){
            goodCategoryId='';
        }else{
            goodCategoryId=value.goodCategoryId;
        }

        if(value.goodsCount==''){
            goodsCount='';
        }else{
            goodsCount=value.goodsCount;
        }
        if(value.count2==''){
            count2='';
        }else{
            count2=value.count2;
        }

        fetchGoods();
    })
}
