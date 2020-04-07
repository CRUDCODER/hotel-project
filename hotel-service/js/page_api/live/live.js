var $;
var table;
var form;


var guestName='';

var guestPhone='';

var liveFlag='';

var guestFlag='';

layui.use(['form', 'table','laydate'], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchLives();
    seachLive();

});

function fetchLives() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"lives?guestName="+guestName+"&guestPhone="+guestPhone+"&liveFlag="+liveFlag+"&guestFlag="+guestFlag,
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'liveId',align:"center", title: "入住编号"},
            {field: 'guestName', align:"center", title: '客户姓名'},
            {field: 'guestPhone',align:"center",  title: '客户电话'},
            {field: 'guestCard',align:"center",  title: '身份证'},
            {field: 'roomNumber',align:"center",  title: '入住房间'},
            {field: 'liveMoney',align:"center",  title: '缴纳金额'},
            {field: 'liveDate',align:"center",  title: '入住日期'},
            {field: 'endDate',align:"center",  title: '退房日期'},
            {field: 'liveRemark',align:"center",  title: '入住备注'},
            {title:'入住状态',align:"center",templet:function (e) {
                    if (e.liveFlag===1){
                        return '<span style="color: #1aa094">正在居住中</span>'
                    } else if(e.liveFlag===0){
                        return '<span style="color: #6666ff">已退房</span>'
                    }else if(e.liveFlag===2){
                        return '<span style="color: #E21918">已超时</span>'
                    }
                }}
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function seachLive(){
    form.on('submit(data-search-btn)', function (data) {
            var value=data.field;
            if(value.guestName==''){
                guestName='';
            }else{
                guestName=value.guestName;
            }
            if(value.guestPhone==''){
                guestPhone='';
            }else{
                guestPhone=value.guestPhone;
            }
            if(value.liveFlag==''){
                liveFlag='';
            }else{
                liveFlag=value.liveFlag;
            }
            if(value.guestFlag==''){
                guestFlag='';
            }else{
                guestFlag=value.guestFlag;
            }

            fetchLives();

    })
}
