var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;


    fetchLeaves();

    increaseLeave();
});

function fetchLeaves() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"leaves",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'leaveId',align:"center", title: "离店编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'guestName',align:"center",  title: '客户姓名'},
            {field: 'guestPhone',align:"center",  title: '客户电话'},
            {field: 'guestCard',align:"center",  title: '客户身份证'},
            {field: 'leaveDate',align:"center",  title: '离店日期'},
            {field: 'refundMoney',align:"center",  title: '退还押金'},
            {field: 'leaveRemark',align:"center",  title: '离店备注'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function increaseLeave() {
    $(".data-add-btn").click(function () {
            layer.open({
                type:2,
                skin: 'demo-class', //样式类名
                title:'客户离店',
                area:['550px','420px'],
                content:['increase.html','no'],
                offset:'auto',
                end:function(){
                    fetchLeaves();
                }
            })
    });
}
