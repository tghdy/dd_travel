$(function () {

    InitVisaDetail();

});

$(document).ready(function(){
    var b = document.getElementById("navbar").offsetTop;
    $(window).scroll(function () {
        var a = document.getElementById("navbar").offsetTop;
        var c=$(".navbar").offset().left;
        if (a < $(window).scrollTop()) {
            $("#navbar").css({"position":"fixed","top":"0","left":c,"width":872});
        }
        if (b > $(window).scrollTop()){
            $("#navbar").css({"position":"relative","left":"0"});
        }


        var k_1=$(".detail_1").offset().top-75;
        var k_2=$(".detail_2").offset().top-100;
        $(".nav_1").click(function(){
            window.scrollTo(0,k_1);
        });
        $(".nav_2").click(function(){
            window.scrollTo(0,k_2);
        });


        var m_1=$(".visa_detail_center_main").offset().top-70;
        var m_2=$(".detail_2").offset().top-150;
        if ($(window).scrollTop()<m_1){
            $(".navbar a").css({"border-bottom":"0","color":"#666666"});
        }
        if ($(window).scrollTop()>m_1&&$(window).scrollTop()<m_2) {
            $(".navbar a").css({"border-bottom":"0","color":"#666666"});
            $(".nav_1 a").css({"border-bottom":"3px solid #f86b4f","color":"#f86b4f"});
        }
        if ($(window).scrollTop()>m_2){
            $(".navbar a").css({"border-bottom":"0","color":"#666666"});
            $(".nav_2 a").css({"border-bottom":"3px solid #f86b4f","color":"#f86b4f"});
        }
    });


    $(".peo_1").click(function(){
        $(".visa_detail_ziliao .peo_de").css("display","none");
        $(".peo_detail_1").css("display","block");
        $(".people_type a").css({"border-bottom":"0","color":"#666666"})
        $(".peo_1").css({"border-bottom":"2px solid #f86b4f","color":"#f86b4f"})
    });
    $(".peo_2").click(function(){
        $(".visa_detail_ziliao .peo_de").css("display","none");
        $(".peo_detail_2").css("display","block");
        $(".people_type a").css({"border-bottom":"0","color":"#666666"})
        $(".peo_2").css({"border-bottom":"2px solid #f86b4f","color":"#f86b4f"})
    });
    $(".peo_3").click(function(){
        $(".visa_detail_ziliao .peo_de").css("display","none");
        $(".peo_detail_3").css("display","block");
        $(".people_type a").css({"border-bottom":"0","color":"#666666"})
        $(".peo_3").css({"border-bottom":"2px solid #f86b4f","color":"#f86b4f"})
    });
    $(".peo_4").click(function(){
        $(".visa_detail_ziliao .peo_de").css("display","none");
        $(".peo_detail_4").css("display","block");
        $(".people_type a").css({"border-bottom":"0","color":"#666666"})
        $(".peo_4").css({"border-bottom":"2px solid #f86b4f","color":"#f86b4f"})
    });
    $(".peo_5").click(function(){
        $(".visa_detail_ziliao .peo_de").css("display","none");
        $(".peo_detail_5").css("display","block");
        $(".people_type a").css({"border-bottom":"0","color":"#666666"})
        $(".peo_5").css({"border-bottom":"2px solid #f86b4f","color":"#f86b4f"})
    });
});

function InitVisaDetail() {
    $.ajax({
        type:"get",
        url:"/dd_travel_war/visa",
        data:{
            method:"visaAllInf",
            id: getUrlParam('id')
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
            var data = json.data;
            $('#visa_title').text(data.visa_title);
            $('#deal_place').text('办理地点：'+data.deal_place);
            $('#validity_period').text('有 效 期：'+data.validity_period);
            $('#most_stay').text('最多停留：'+data.most_stay);
            $('#deal_time').text('受理时间：'+data.deal_time);
            $('#interview').text('面试：'+data.interview);
            $('#entry').text('入境次数：'+data.entry);
            $('#custom_range').text('收客范围：'+data.custom_range);
            $('#price').text('$'+data.price);
            $('#custom_inf1').html(data.custom_inf1);
            $('#custom_inf2').html(data.custom_inf2);
            $('#custom_inf3').html(data.custom_inf3);
            $('#custom_inf4').html(data.custom_inf4);
            $('#custom_inf5').html(data.custom_inf5);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}
