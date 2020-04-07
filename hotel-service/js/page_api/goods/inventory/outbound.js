var $;
var table;
var form;

var goodsId;
layui.use(['form', 'table',], function () {
    $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    // var url = location.search; //获取url中"?"符后的字串
    // var theRequest = new Object();
    // if(url.indexOf("?") != -1) {
    //     var str = url.substr(1);
    //     strs = str.split("&");
    //     for(var i = 0; i < strs.length; i++) {
    //         theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
    //     }
    // }
    // goodsId=theRequest.goodsId;
    var category = window.location.search;
    //截取，获取获得category的具体值
    goodsId = category.substring(category.lastIndexOf('=') + 1, category.length);

    fetchGoodsStorageByGoodsId();
    back();
});

function fetchGoodsStorageByGoodsId() {
    table.render({
        elem: '#currentTableId',
        url: BASE_URL+'goodsOutbound?goodsId='+goodsId,
        headers:{
            "authorization":window.localStorage.getItem("token")
        },
        cols: [[
            {field: 'outboundId',align:"center", title: "出库编号"},
            {field: 'goodsName', align:"center", title: '商品名称'},
            {field: 'roomNumber', align:"center", title: '房间号'},
            {field: 'outboundDate',align:"center",  title: '出库时间'},
            {field: 'outboundCount',align:"center",  title: '出库数量'},
        ]],
        limits: [5,10,15],
        limit: 10,
        page: true
    });
}
function back() {
    $(".data-add-btn").on("click", function () {
        $(location).attr('href', 'info.html');
    });
}

