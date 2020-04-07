var $;
var table;
var form;

layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;



    fetchContract();

    increaseContract();
});

function fetchContract() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"contracts",
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'contractId',align:"center", title: "续住编号"},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'guestName',align:"center",  title: '客户姓名'},
            {field: 'guestPhone',align:"center",  title: '客户电话'},
            {field: 'guestCard',align:"center",  title: '客户身份证'},
            {field: 'contractDate',align:"center",  title: '续住日期'},
            {field: 'contractMoney',align:"center",  title: '续住金额'},
            {field: 'contractRemark',align:"center",  title: '续住备注'},

        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function increaseContract() {
    $(".data-add-btn").click(function () {
            layer.open({
                type:2,
                skin: 'demo-class', //样式类名
                title:'客户续住',
                area:['550px','420px'],
                content:['increase.html','no'],
                offset:'auto',
                end:function(){
                    fetchContract();
                }
            })
    });
}
