var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'storage') {
            searchStorage(data.goodsId)
            //layer.alert('编辑行：<br>' + JSON.stringify(data))
        } else if (obj.event === 'outbound') {
            searchOutbound(+data.goodsId)
        }
    });
    fetchGoods();
});
function fetchGoods() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'goods',
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'goodsId',align:"center", title: "商品编号"},
            {field: 'goodsName', align:"center", title: '商品名称'},
            {field: 'goodsCategoryName',align:"center",  title: '商品类别'},
            {field: 'goodsCount',align:"center",  title: '库存数量'},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function searchStorage(goodsId) {
    $(location).attr('href', 'storage.html?goodsId='+goodsId);
}

function searchOutbound(goodsId) {
    $(location).attr('href', 'outbound.html?goodsId='+goodsId);
}
