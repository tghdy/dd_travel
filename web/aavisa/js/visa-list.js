$(document).ready(function(){
    $.ajax({
        type:"get",
        url:"/dd_travel_war/visa",
        data:{
            method:"getAllInf",
            inf:""
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
            var data = json.data;
            var len = data.length;
            var ins = '';
            for(var i=0;i<len;i++){
                if(i>=5){
                    break;
                }
                ins +=
                    '<li>'+
                    '<img src="'+data[i].visa_pic+'" alt="" width="">'+
                    '<div class="visa-item-cnt">'+
                    '<h2 class="shua">'+
                    '<a href="/dd_travel_war/aavisa-detail/index.html?id='+data[i].id+'" target="_blank">'+
                    data[i].visa_title+'</a></h2><div class="f-l">'+
                    '<span>受理时间：'+data[i].deal_time+'</span>'+
                    '<span>有效日期：'+data[i].validity_period+'</span>'+
                    '<span>办理地点：'+data[i].deal_place+'</span>'+
                    '<span>签 证 费： 不包含</span>'+
                    '<span>入境次数：'+data[i].entry+'</span>'+'</div></div></li>'
            }
            $("#search_d").html(ins);
            console.log(ins);
        },
        error:function(data){
            console.log(data.msg);
        }
    });

    $(".choose_item_0").click(function(){
        var sea=$(this).text();
        chooseDetail(sea);
    });

    //用户登陆状态
    showUserInf_visa();
});

function GitAllDetail() {
    var inf = $("#input").val();
    $.ajax({
        type:"get",
        url:"/dd_travel_war/visa",
        data:{
            method:"getAllInf",
            inf:inf
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
            var data = json.data;
    		var len = data.length;
            var ins = '';
    		for(var i=0;i<len;i++){
                ins +=
                    '<li>'+
                    '<img src="'+data[i].visa_pic+'" alt="" width="">'+
                    '<div class="visa-item-cnt">'+
                    '<h2 class="shua">'+
                    '<a href="/dd_travel_war/aavisa-detail/index.html?id='+data[i].id+'" target="_blank">'+
                    data[i].visa_title+'</a></h2><div class="f-l">'+
                    '<span>受理时间：'+data[i].deal_time+'</span>'+
                    '<span>有效日期：'+data[i].validity_period+'</span>'+
                    '<span>办理地点：'+data[i].deal_place+'</span>'+
                    '<span>签 证 费： 不包含</span>'+
                    '<span>入境次数：'+data[i].entry+'</span>'+'</div></div></li>'
            }
            $("#search_d").html(ins);
            console.log(ins);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}

function chooseDetail(sea) {
    $.ajax({
        type:"get",
        url:"/dd_travel_war/visa",
        data:{
            method:"getAllInf",
            inf:sea
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
            var data = json.data;
            var len = data.length;
            var ins = '';
            for(var i=0;i<len;i++){
                ins +=
                    '<li>'+
                    '<img src="'+data[i].visa_pic+'" alt="" width="">'+
                    '<div class="visa-item-cnt">'+
                    '<h2 class="shua">'+
                    '<a href="/dd_travel_war/aavisa-detail/index.html?id='+data[i].id+'" target="_blank">'+
                    data[i].visa_title+'</a></h2><div class="f-l">'+
                    '<span>受理时间：'+data[i].deal_time+'</span>'+
                    '<span>有效日期：'+data[i].validity_period+'</span>'+
                    '<span>办理地点：'+data[i].deal_place+'</span>'+
                    '<span>签 证 费： 不包含</span>'+
                    '<span>入境次数：'+data[i].entry+'</span>'+'</div></div></li>'
            }
            $("#search_d").html(ins);
            console.log(ins);
        },
        error:function(data){
            console.log(data.msg);
        }
    });
}