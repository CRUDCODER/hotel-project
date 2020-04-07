var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchGoodsOutbound();

});

function fetchGoodsOutbound() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'goodsOutbound',
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'outboundId',align:"center", title: "出库编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'goodsName',align:"center",  title: '商品名称'},
            {field: 'outboundDate',align:"center",  title: '出库时间'},
            {field: 'outboundCount',align:"center",  title: '出库数量'},
            // {field: 'dateUpdated',align:"center",  title: '更新时间'},
            // {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
