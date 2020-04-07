var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;



    fetchReplaceRoom();

    replaceRoom();
});

function fetchReplaceRoom() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"replaceRooms",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'replaceId',align:"center", title: "换房编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'guestName',align:"center",  title: '客户姓名'},
            {field: 'guestPhone',align:"center",  title: '客户电话'},
            {field: 'guestCard',align:"center",  title: '客户身份证'},
            {field: 'replaceDate',align:"center",  title: '换房日期'},
            {field: 'replaceMoney',align:"center",  title: '换房花费金额'},

        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function replaceRoom() {
    $(".data-add-btn").click(function () {
        $(location).attr('href','replace.html')
    });
}
