var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchRoomGoods();
    increaseRoomGoods();
});
function fetchRoomGoods() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'roomGoods',
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'roomGoodsId',align:"center", title: "配置编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'goodsName',align:"center",  title: '商品名称'},
            {field: 'goodsCount',align:"center",  title: '配置数量'},
            {field: 'dateUpdated',align:"center",  title: '更新时间'},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function increaseRoomGoods() {
    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'配置房间商品',
            area:['550px','460px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchRoomGoods();
            }
        })
    });
}
