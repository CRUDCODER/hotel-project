var $;
var table;
var form;

layui.use(['form', 'table','laydate'], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchRoomReservation();
    isMemberFlag();

});

function fetchRoomReservation() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"receptions",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'receptionId',align:"center", title: "接待编号"},
            {field: 'guestName', align:"center", title: '客户姓名'},
            {field: 'guestPhone',align:"center",  title: '客户电话'},
            {field: 'guestCard',align:"center",  title: '身份证'},
            {field: 'roomNumber',align:"center",  title: '期望入住房间号'},
            {field: 'receptionDate',align:"center",  title: '接待日期'},
            {field: 'receptionRemark',align:"center",  title: '接待备注'},
            {align:"center",  title: '是否会员',templet: function (e) {
                    if (e.guestFlag==0){
                        return "<span style='color: #1aa094'>散客</span>"
                    } else if (e.guestFlag==1){
                        return "<span style='color: #CB602D'>会员</span>"
                    }
                }},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function isMemberFlag() {
    $(".data-add-btn").click(function () {
        layer.confirm('是否已有预定？', {
            btn: ['已有预定','还未预定'], //按钮
            title:"提示",
            icon: 3
        }, function(){
            hasReception();
        }, function(){
            noReception();
        });
    });
}
function hasReception() {
    $(location).attr('href','hasReception.html')
    //layer.msg('已预定', {icon: 1});
}
function noReception() {
    $(location).attr('href','noReception.html')
}
