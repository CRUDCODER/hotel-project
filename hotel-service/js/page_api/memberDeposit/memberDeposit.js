var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchMemberDeposit();

});

function fetchMemberDeposit() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"memberDeposits",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'depositId',align:"center", title: "充值编号"},
            {field: 'memberNumber', align:"center", title: '会员卡号'},
            {field: 'depositMoney',align:"center",  title: '充值金额'},
            {field: 'depositDate',align:"center",  title: '充值时间'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
