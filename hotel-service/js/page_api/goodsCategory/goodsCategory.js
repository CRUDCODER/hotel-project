var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            modifyRoomCategory(data.roomCategoryId);
            //layer.alert('编辑行：<br>' + JSON.stringify(data))
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除'+data.roomCategoryName+'相关信息吗?', function (index) {
                //removeRoomCategory(data.roomCategoryId)
            },function () {
                layer.msg("已取消",{icon:1});
            });
        }
    });
    fetchGoodsCategorys();
    increaseGoodsCategory();
});

function fetchGoodsCategorys() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'goodsCategorys',
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'goodsCategoryId',align:"center", title: "商品类别编号"},
            {field: 'goodsCategoryName', align:"center", title: '商品类别名称'},
            {field: 'goodsCategoryRemark',align:"center",  title: '商品类别备注'},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function increaseGoodsCategory() {
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'添加商品分类信息',
            area:['550px','300px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchGoodsCategorys();
            }
        })
    });
}
