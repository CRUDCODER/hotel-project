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
        url: BASE_URL+"/checkouts",
        cols: [[
            {field: 'checkoutId',align:"center", title: "结算编号"},
            {field: 'checkoutGuest',align:"center",  title: '结算顾客'},
            {field: 'checkoutPhone',align:"center",  title: '顾客电话'},
            {field: 'checkoutDate',align:"center",  title: '结算日期'},
            {field: 'checkoutMoney',align:"center",  title: '结算金额'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

