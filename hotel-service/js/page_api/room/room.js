var $;
var table;
var form;

var roomCategoryId="";
var price1=0;
var price2=0;
var roomFlag="";
layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            modifyRoom(data.roomCategoryId);
            //layer.alert('编辑行：<br>' + JSON.stringify(data))
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除'+data.roomCategoryName+'相关信息吗?', function (index) {
                removeRoom(data.roomCategoryId)
            },function () {
                layer.msg("已取消",{icon:1});
            });
        }
    });
    fetchRoom();
    increaseRoom();
    fetchRoomCategoryForSelect();
    searchRoom();
});
function searchRoom() {
    form.on('submit(data-search-btn)', function (data) {
            roomCategoryId=data.field.roomCategoryId;
            price1=data.field.price1;
            price2=data.field.price2;
            if (price1==""){
                price1=0;
            }
            if (price2==""){
                price2=0;
            }
            roomFlag=data.field.roomFlag;
            fetchRoom();
    })
}
function fetchRoomCategoryForSelect() {
    getRequest("","RoomCategoryForSelect",res=>{
        $.each(res,function (index,value) {
            $("#roomCategory").append("<option value='"+value.roomCategoryId+"'>"+value.roomCategoryName+"</option>")
        })
        form.render("select");
    })
}
function fetchRoom() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'rooms?roomCategoryId='+roomCategoryId+"&roomMoney="+price1+"&price2="+price2+"&roomFlag="+roomFlag,
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'roomId',align:"center", title: "房间编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'categoryName',align:"center",  title: '房间类别'},
            {field: 'roomArea',align:"center",  title: '房间面积'},
            {field: 'roomFloor',align:"center",  title: '房间楼层'},
            {field: 'roomMoney',align:"center",  title: '房间价格'},
            {align:"center",  title: '出租状态',templet: function (e) {
                    if (e.roomFlag==0){
                        return "<span style='color: #1aa094'>未出租</span>"
                    } else if (e.roomFlag==1){
                        return "<span style='color: #CB602D'>已出租</span>"
                    }
                    else if (e.roomFlag==2){
                        return "<span style='color: #FFC66D'>已预定</span>"
                    }
                }},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function increaseRoom() {
    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'添加客户信息',
            area:['550px','450px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchRoom();
            }
        })
    });
}

function modifyRoom() {

}

function removeRoom() {

}
