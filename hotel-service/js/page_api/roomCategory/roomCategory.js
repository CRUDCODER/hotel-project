var $;
var table;
var form;

layui.use(['form', 'table',], function () {
     $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    // // 监听搜索操作
    // form.on('submit(data-search-btn)', function (data) {
    //     var result = JSON.stringify(data.field);
    //     layer.alert(result, {
    //         title: '最终的搜索信息'
    //     });
    //
    //     //执行搜索重载
    //     table.reload('currentTableId', {
    //         page: {
    //             curr: 1
    //         }
    //         , where: {
    //             searchParams: result
    //         }
    //     }, 'data');
    //
    //     return false;
    // });


    // // 监听删除操作
    // $(".data-delete-btn").on("click", function () {
    //     var checkStatus = table.checkStatus('currentTableId')
    //         , data = checkStatus.data;
    //     layer.alert(JSON.stringify(data));
    // });
    //
    // //监听表格复选框选择
    // table.on('checkbox(currentTableFilter)', function (obj) {
    //     console.log(obj)
    // });

    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            modifyRoomCategory(data.roomCategoryId);
            //layer.alert('编辑行：<br>' + JSON.stringify(data))
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除'+data.roomCategoryName+'相关信息吗?', function (index) {
                removeRoomCategory(data.roomCategoryId)
            },function () {
                layer.msg("已取消",{icon:1});
            });
        }
    });
    getAllRoomCategory();
    increaseRoomCategory();
});

function getAllRoomCategory() {
    console.log(window.localStorage.getItem("token"))
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'/roomCategorys',
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'roomCategoryId',align:"center", title: "房间类别编号"},
            {field: 'roomCategoryName', align:"center", title: '房间类别名称'},
            {field: 'roomCategoryRemark',align:"center",  title: '房间类别备注'},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function increaseRoomCategory() {
    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'添加客房分类信息',
            area:['550px','300px'],
            content:['add.html','no'],
            offset:'auto',
            end:function(){
               getAllRoomCategory();
            }
        })
    });
}
function modifyRoomCategory(roomCategoryId) {
    $("#primarykey").val(roomCategoryId);
    layer.open({
        type:2,
        skin: 'demo-class', //样式类名
        title:'修改客房分类信息',
        area:['550px','300px'],
        content:['modify.html','no'],
        offset:'auto',
        end:function(){
            getAllRoomCategory();
        }
    })
}
function removeRoomCategory(roomCategoryId) {

    deleteRequest("roomCategory/"+roomCategoryId,res=>{
        layer.msg("删除成功",{icon:1});
        getAllRoomCategory();
    })

}
