
var title=[];
$(function(){
    fetchStatisticalReservation();
});

function fetchStatisticalReservation(){
    $.ajax({
        url:BASE_URL+"/statisticalReservation",
        type:'get',
        dataType:'json',
        success:res=>{
            $.each(res,function(index,value){
                title[index]=value.name
            });

            fetchChart(res);
        }
    });
}

function fetchChart(data){
    console.log(title)
    var echartsRecords = echarts.init(document.getElementById('recept'));
    var optionRecords = {
        title: {
            text: '预定客户实际入住情况',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: title
        },
        series: [
            {
                name: '人数',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data:data,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    echartsRecords.setOption(optionRecords);
}
