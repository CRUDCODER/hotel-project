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
        url: 'http://localhost:9090/hotel/api/special',
        cols: [[
            {field: 'specialId',align:"center", title: "特价编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'specialMoney',align:"center",  title: '优惠价格'},
            {field: 'specialDate',align:"center",  title: '优惠时间'},
            {field: 'specialRemark',align:"center",  title: '备注'},
            {align:"center",  title: '出租状态',templet: function (e) {
                    if (e.isShow==1){
                        return "<span style='color: #1aa094'>正在进行中</span>"
                    } else if (e.isShow==0){
                        return "<span style='color: #CB602D'>已结束</span>"
                    }

                }},
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
            title:'添加特价房',
            area:['550px','450px'],
            content:['increase.html','no'],
            offset:'auto',
            end:function(){
                fetchSepcialRoom();
            }
        })
    });
}

