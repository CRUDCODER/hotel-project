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
        url: BASE_URL+"/payFees",
        cols: [[
            {field: 'payFeeId',align:"center", title: "交费编号"},
            {field: 'payFeeCategory', align:"center", title: '交费类型'},
            {field: 'payFeeName',align:"center",  title: '交费顾客'},
            {field: 'payFeePhone',align:"center",  title: '顾客电话'},
            {field: 'payFeeMoney',align:"center",  title: '交费金额'},
            {field: 'payFeeDate',align:"center",  title: '交费时间'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

