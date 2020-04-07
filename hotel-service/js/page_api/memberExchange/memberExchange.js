var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;



    fetchMemberExchange();


});

function fetchMemberExchange() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"memberExchanges",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'exchangeId',align:"center", title: "兑换编号"},
            {field: 'memberNumber', align:"center", title: '会员编号'},
            {field: 'memberName',align:"center",  title: '会员姓名'},
            {field: 'couponsName',align:"center",  title: '优惠卷名称'},
            {field: 'exchangeDate',align:"center",  title: '兑换时间'},
            {field: 'consumeScore',align:"center",  title: '花费积分'},
            {align:"center",  title: '是否使用',templet: function (e) {
                    if (e.exchangeFlag===1){
                        return '<span style="color: #1AA094">未使用</span>'
                    } else {
                        return '<span style="color: #6666ff">已使用</span>'
                    }
                }},

        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

