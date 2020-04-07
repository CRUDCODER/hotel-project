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

    fetchSepcialRoom();
    increaseSepcialRoom();
});


function fetchSepcialRoom() {
    table.render({
        elem: '#currentTableId',
        url: 'http://localhost:9090/hotel/api/blacklists',
        cols: [[
            {field: 'blacklistId',align:"center", title: "黑名单编号"},
            {field: 'blacklistName', align:"center", title: '客户名称'},
            {field: 'blacklistCard',align:"center",  title: '客户身份证'},
            {field: 'blacklistDate',align:"center",  title: '创建时间'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function increaseSepcialRoom() {
    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type:2,
            skin: 'demo-class', //样式类名
            title:'添加黑名单',
            area:['550px','300px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchSepcialRoom();
            }
        })
    });
}

