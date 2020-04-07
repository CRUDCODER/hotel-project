var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchGoodsStorage();
    increaseGoodsStorage();
});

function fetchGoodsStorage() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'goodsStorage',
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'storageId',align:"center", title: "入库编号"},
            {field: 'goodsName', align:"center", title: '商品名称'},
            {field: 'storageDate',align:"center",  title: '入库日期'},
            {field: 'storageCount',align:"center",  title: '入库数量'},
            // {field: 'dateUpdated',align:"center",  title: '更新时间'},
            // {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function increaseGoodsStorage() {
    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'商品入库',
            area:['550px','350px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchGoodsStorage();
            }
        })
    });
}
