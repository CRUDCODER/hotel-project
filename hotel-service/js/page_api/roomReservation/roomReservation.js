var $;
var table;
var form;

var memberNumber='';
var memberPhone='';
var reservationFlag='';
layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
        laydate=layui.laydate;
    fetchRoomReservation();
    seachRoomReservation();
});

function fetchRoomReservation() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"roomReservations?memberNumber="+memberNumber+"&memberPhone="+memberPhone+"&reservationFlag="+reservationFlag,
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'reservationId',align:"center", title: "预定编号"},
            {field: 'memberNumber', align:"center", title: '会员卡号'},
            {field: 'memberName',align:"center",  title: '会员姓名'},
            {field: 'memberPhone',align:"center",  title: '会员电话'},
            {field: 'roomNumber',align:"center",  title: '预定房间'},
            {field: 'payMoney',align:"center",  title: '付款金额'},
            {field: 'reservationDate',align:"center",  title: '预定日期'},
            {field: 'payDate',align:"center",  title: '付款日期'},
            {field: 'reservationNumber',align:"center", title: "预定单号"},
            {field: 'reservationRemark',align:"center", title: "预定备注"},
            {align:"center",  title: '是否入住',templet: function (e) {
                    if (e.reservationFlag==0){
                        return "<span style='color: #1aa094'>未入住</span>"
                    } else if (e.reservationFlag==1){
                        return "<span style='color: #CB602D'>已入住</span>"
                    }else if(e.reservationFlag==2){
                        return "<span style='color: #334B5C'>已取消</span>"
                    }
                }},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function seachRoomReservation(){
    form.on('submit(data-search-btn)', function (data) {
        var value=data.field;
        console.log(value)
        if(value.memberNumber==''){
            memberNumber='';
        }else{
            memberNumber=value.memberNumber;
        }

        if(value.memberPhone==''){
            memberPhone='';
        }else{
            memberPhone=value.memberPhone;
        }

        if(value.reservationFlag==''){
            reservationFlag='';
        }else{
            reservationFlag=value.reservationFlag
        }

        fetchRoomReservation();
    })
}
