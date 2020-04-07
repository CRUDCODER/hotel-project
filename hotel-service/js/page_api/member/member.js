var $;
var table;
var form;

var memberNumber='';
var memberPhone='';
var memberMoney=0;
var money=0;
layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    fetchMember();
    seachMember();

});

function fetchMember() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+"members?memberNumber="+memberNumber+"&memberPhone="+memberPhone+"&memberMoney="+memberMoney+"&money2="+money,
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'memberId',align:"center", title: "会员编号"},
            {field: 'memberNumber', align:"center", title: '会员卡号'},
            {field: 'memberPassword',align:"center",  title: '会员密码'},
            {field: 'memberName',align:"center",  title: '会员姓名'},
            {field: 'memberPhone',align:"center",  title: '会员电话'},
            {field: 'memberMoney',align:"center",  title: '会员余额'},
            {field: 'memberScore',align:"center",  title: '会员积分'},
            {field: 'memberCard',align:"center",  title: '会员身份证号'},
            {field: 'memberMail',align:"center",  title: '会员邮箱'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}

function seachMember(){
    form.on('submit(data-search-btn)', function (data) {
        var value=data.field;
        if(isNaN(value.memberMoney)){
            layer.msg("请填写数字",{
                shift:6,
                icon:2,
                time:2000
            });
            $("#memberMoney").val("");
            return false;
        }else if(isNaN(value.money)){
            layer.msg("请填写数字",{
                shift:6,
                icon:2,
                time:2000
            });
            $("#money").val("");
            return false;
        }else if(parseInt( value.memberMoney)>parseInt( value.money)){
            layer.msg("填写范围有误",{
                shift:6,
                icon:2,
                time:2000
            });
            $("#memberMoney").val("");
            $("#money").val("");
            return false;
        }
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

        if(value.memberMoney==''){
            memberMoney=0;
        }else{
            memberMoney=value.memberMoney;
        }

        if(value.money==''){
            money=0;
        }else{
            money=value.money
        }
        fetchMember();
    })
}
