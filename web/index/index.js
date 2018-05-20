$(function() {
    console.log("asd")
    $.ajax({
        type: 'get',
        url: 'adress-tab.json',
        dataType: 'json',
        success: function (json) {
            var ins = '';
            for (var obj in json) {
                // console.log(obj);
                //遍历主要左侧栏目内容
                for (var title in json[obj]) {
                    
                    ins +=
                        '<div class="main">' +  //第一个div
                        '<div class="left" style="position: absolute;margin-top: '+64*obj+'px">' +//第二个div
                        '<i class="Hui-iconfont" style="color: #CB1425;font-size: 25px;position: absolute;margin-left: 15px;margin-top: 5px;opacity: 0.5">&#xe67a;</i>' +
                        '<a class="tt" href="#" style="font-size: 15px;margin-top: 8px;margin-left: 50px;text-decoration: none;display: block;">' +
                        title+//周边游
                        '</a>';
                    for (var emphasisItem in json[obj][title]['emphasis']) {
                        ins +=
                            '<a class="tt emphasis" href="' + json[obj][title]['emphasis'][emphasisItem]['url'] + '" class="emphasis">' +
                            json[obj][title]['emphasis'][emphasisItem]['name'] +
                            '</a>';
                    }
                    ins += '</div>';
                    ins += '<div class="right">';
                    for (var otherItem in json[obj][title]['other']) {
                        ins +=
                            '<p style="font-size: 13px;color: #000;margin-left: 25px;line-height: 50px;font-weight: bold;">' +
                            json[obj][title]['other'][otherItem]['name'] +
                            '</p>';
                        for (var itemsItem in json[obj][title]['other'][otherItem]['items']) {
                            if(itemsItem == 0) {
                                ins += 
                                    '<a href="#" class="tt"><span style="margin-left: 25px;font-size: 13px;opacity: 0.8;float: left;margin-top:-10px;font-family:SimSun;font-weight: bold;">' + 123 + '</span></a>';
                            } else {
                                ins +=
                                    '<span style="margin-left: 10px;font-size: 13px;opacity: 0.8;float: left;margin-top:-12px;">|</span>' +
                                    '<a href="' + json[obj][title]['other'][otherItem]['items'][itemsItem]['url'] + '" class="tt"><span style="margin-left: 25px;font-size: 13px;opacity: 0.8;float: left;margin-top:-10px;font-family:SimSun;font-weight: bold;">' + json[obj][title]['other'][otherItem]['items'][itemsItem]['name'] + '</span></a>';
                            }
                            
                        }
                        ins += '<br>';
                        
                    }
                    ins += '</div>';//第二个div尾巴
                    ins += '</div>';//第一个div尾巴
                }
                
                // console.log(json[obj]);
                // var ins = '';
            }
            
            $('.max').prepend(ins);
            $(".left").hover(function() {
                $(this).parent(".main").find(".right").css("display","block");
            });$(".main").mouseleave(function() {
                $(".left").parent(".main").find(".right").css("display","none");
            });
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
})

//获取首页推荐线路
function getAdbLine(type) {
    $.ajax({
        type: "get",
        url: "/dd_travel_war/adb",
        data: {
            method: "indexLineAdb",
            lineType:type
        },
        dataType: 'json',
        success: function (json) {9
            console.log("indexSixAdbListLine Ok");
            console.log(json);
            if (json.flag == 1) {
                var data=json.data;
                var ins = '';
                if (data.notasideAdb.length > 0) {
                    $(data.notasideAdb).each(function (index, item) {
                        // console.log(item.adb_id);
                        //判断是否需要边距，如果是第一个或者第四个则不需要边距隔开
                        if (index == 0 || index == 3)
                            ins += '<div class="line_nav">';
                        else
                            ins += '<div class="line_nav" style="margin-left: 6px">';
                        ins +=
                            '<img src="'+item.aside_picture+'" style="margin-top: 5px;margin-left: 5px;">' +
                            '<a href="/dd_travel_war/line-detail/line-detail.html?id='+item.travel_id+'" class="tt" style="text-decoration:none;">' +
                            '<span style="display: block;margin-top: 5px;font-size: 15px;height: 40px;">'+item.travel_name+'</span>' +
                            '</a>' +
                            '<span style="color: #888;font-size: 13px;display: block;margin-top: 20px;float: left;">最大人数:'+item.travel_count+'</span>' +
                            '<span style="color: #F86B4F;font-size: 20px;display: block;margin-top: 10px;float: right;">¥'+item.travel_price+'起</span>' +
                            '</div>';
                    });
                } else {
                    ins = '无相关信息';
                }
                $('#notAsideAdb').html(ins);
                if(data.asideAdb.aside_picture != null) {
                    $('#asideAdb').attr('src', data.asideAdb.aside_picture);
                } else {
                    $('#asideAdb').attr('alt', '无相关信息');
                }
                
            } else {
                $('#asideAdb').attr('alt', '无相关信息');
                $('#asideAdb').attr('src', '#');
                $('#notAsideAdb').html('无相关信息');
            }

        },
        error: function (data) {
            console.log("indexSixAdbListLine No");
            console.log(data);
        }
    });
    
}



//轮播图
window.onload = function bannerSlide() {
    if (!document.getElementById) return false;
    if (!document.getElementsByTagName) return false;
    if (!document.getElementById("bannerBox")) return false;
    var i = 0;
    var box = document.getElementById("bannerBox");
    var banner = box.getElementsByTagName("div");
    sli = setInterval(slide, 4000); //轮播间隔时间
    function slide() {
        if (i == banner.length - 1) {
            banner[0].style.display = "block";
            banner[0].style.opacity = "1";
            banner[i].style.opacity = "0";
            setTimeout(function () {
                banner[i].style.display = "none";
                banner[i].style.zIndex = "1";
                banner[0].style.zIndex = "5";
                i = 0;
            }, 500) //动画过渡时间，搭配CSS使用
        } else {
            banner[i + 1].style.display = "block";
            banner[i + 1].style.opacity = "1";
            banner[i].style.opacity = "0";
            setTimeout(function () {
                banner[i + 1].style.zIndex = "5";
                banner[i].style.display = "none";
                banner[i].style.zIndex = "1";
                i++;
            }, 500)
        }
    }
}

//获取轮播图片
function selectSlidePicture() {
    $.ajax({
        type: "get",
        url: "/dd_travel_war/adb",
        data: {
            method: "indexSlideAdb"
        },
        dataType: 'json',
        success: function (json) {
            console.log(json);
        },
        error: function (data) {
            console.log(data);
        }

    });
}

/**
 * @Author: TusuZer
 * @Date: 2018/4/5 下午8:25
 * @Des: tusuzer
 **/
function searchLine() {
    var value = $('#searchValue').val();
    window.location.href = '../line-list/line-list.html?key=' + value;

}

$(function () {
    getAdbLine(10);
    $('.line-menu').find('li').click(function () {
        $(this).siblings('.red-bottom').removeClass('red-bottom');
        $(this).addClass('red-bottom');
    });
    showUserInf();
})

$(function () {
    $(".tra_01,.tra_02,.tra_03,.tra_04").mouseenter(function(){
        $(".tra_0"+$(this).attr("class")[5]).children(".title").css("height","80px");
    });
    $(".tra_01,.tra_02,.tra_03,.tra_04").mouseleave(function(){
        $(".tra_0"+$(this).attr("class")[5]).children(".title").css("height","50px");
    });
})//旅游攻略动画

$(function () {
    $(".choose_d").mouseenter(function(){
        $(".choose").css("display","block");
    });
    $(".search_first").mouseleave(function(){
        $(".choose").css("display","none");
    });
    $(".choose_1,.choose_2,.choose_3,.choose_4,.choose_5").click(function(){
        var n = $(this).attr("class")[7];
        switch (n){
            case '1':$('#cho').text("全部产品");
                break;
            case '2':$('#cho').text("跟团游");
                break;
            case '3':$('#cho').text("定制游");
                break;
            case '4':$('#cho').text("签证");
                break;
            case '5':$('#cho').text("邮轮");
                break;
        }
    });
})//查询框选择