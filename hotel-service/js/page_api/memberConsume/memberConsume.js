var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;



    fetchMemberConsume();


});

function fetchMemberConsume() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"memberConsumes",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'consumeId',align:"center", title: "消费编号"},
            {field: 'memberNumber', align:"center", title: '会员编号'},
            {field: 'memberName',align:"center",  title: '会员姓名'},
            {field: 'consumeCategory',align:"center",  title: '消费类型'},
            {field: 'consumeMoney',align:"center",  title: '消费金额'},
            {field: 'consumeDate',align:"center",  title: '消费时间'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

