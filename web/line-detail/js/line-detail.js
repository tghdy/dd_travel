$(function () {
    initLineDetail();
    
})
$(function () {
    $(function () {
        $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});
    });
    
    $(".area").mouseenter(function () {
        $(".test1").css({"display": "block"});
    });
    $(".test1").mouseleave(function () {
        $(".test1").css({"display": "none"});
    });
    $(".show_1").mouseenter(function () {
        $(".test2").css({"display": "block"});
    });
    $(".test2").mouseleave(function () {
        $(".test2").css({"display": "none"});
    });
    $(".download").mouseenter(function () {
        $(".download a").css({"color": "white"});
        $(".download i").css({"color": "white"});
        $(".download").css({"background": "#f86b4f"});
    });
    $(".download").mouseleave(function () {
        $(".download a").css({"color": "#f86b4f"});
        $(".download i").css({"color": "#f86b4f"});
        $(".download").css({"background": "white"});
    });
    $(".button_1").mouseenter(function () {
        $(".button_1").css({"background": "#f65b3c"});
    });
    $(".button_1").mouseleave(function () {
        $(".button_1").css({"background": "#f86b4f"});
    });
    $(".button_2").mouseenter(function () {
        $(".button_2").css({"background": "#f86b4f", "color": "white"});
    });
    $(".button_2").mouseleave(function () {
        $(".button_2").css({"background": "white", "color": "#f86b4f"});
    });
    var b = document.getElementById("eqq").offsetTop;
    $(window).scroll(function () {

        var a = document.getElementById("eqq").offsetTop;
        // console.log("c:::");
        var c = $(".bm_left").offset().left;
        // console.log(c);
        var d = $(".bm_right").offset().left;
        if (a < $(window).scrollTop()) {
            $("#eqq").css({"position": "fixed", "top": "0", "left": d});
            $("#eqqq").css({"position": "fixed", "top": "0", "left": c});
            // console.log("asdasd")
            // $("#eqqq").css({"border": "2px solid #f86b4f", "top": "0"});
        }
        if (b > $(window).scrollTop()) {
            $("#eqq").css({"position": "relative", "left": "0"});
            $("#eqqq").css({"position": "relative", "left": "0"});
        }
    });
    $("#a_1").click(function () {
        var a = document.getElementById("b_1").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
    $("#a_2").click(function () {
        var a = document.getElementById("b_2").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
    $("#a_3").click(function () {
        var a = document.getElementById("b_3").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
    $("#a_4").click(function () {
        var a = document.getElementById("b_4").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
    $("#a_5").click(function () {
        var a = document.getElementById("b_5").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
    $("#a_6").click(function () {
        var a = document.getElementById("b_6").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
    $("#a_7").click(function () {
        var a = document.getElementById("b_7").offsetTop;
        $('html,body').animate({scrollTop: a - 54}, 'slow');
    });
});

//初始化详情界面
function initLineDetail(type) {
    $.ajax({
        type: "get",
        url: "/dd_travel_war/line",
        data: {
            method: "lineAllInf",
            id: getUrlParam('id')
        },
        dataType: 'json',
        success: function (json) {
            // console.log("lineDetail Ok");
            console.log(json);
            
            if (json.flag == 1) {
                var data = json.data;
                var schedules = data.schedules;
                var plans = data.plans;
                var relatedLine = data.relatedLine;


                //渲染红色导航栏
                redNavItem(data.line_type);
                //处理详情信息
                dealDetail(data);
                //处理班次内容
                dealPlans(plans);
                //生成日历
                createCalendar(plans);
                //处理日程信息
                dealSchedules(schedules);
                //处理关联线路信息
                dealRelatedLine(relatedLine);
            }

        },
        error: function (data) {
            // console.log("lineDetail No");
            console.log(data);
        }
    });
}


//插入详情数据
function dealDetail(data) {
    // console.log("asdasd")
    // console.log("qweqwe")
    // ???
    $('#travel_name').text(data.travel_name);
    $('#travel_price').text(data.travel_price);
    $('#travel_count').text(data.travel_count);
    $('#travel_views').text(data.travel_views);
    $('#travel_day').text('行程天数：' + data.travel_day);
    $('#travel_no').text('线路编号：' + data.travel_no);
    $('#fromName').text('出发城市：' + data.fromName);
    // 0=步行，1=汽车，2=火车，3=飞机，4=轮船，9=其他
    var trafficCn = '';
    switch (data.traffic_type) {
        case 0:
            trafficCn = '步行';
            break;
        case 1:
            trafficCn = '汽车';
            break;
        case 2:
            trafficCn = '火车';
            break;
        case 3:
            trafficCn = '飞机';
            break;
        case 4:
            trafficCn = '轮船';
            break;
        case 9:
            trafficCn = '其他';
            break;
    }
    //交通信息
    $('#traffic_type').text('往返交通：' + trafficCn);

    // 团费信息
    $('#travel_info').html(data.travel_info);
    // 提示信息
    $('#travel_tips').html(data.travel_tips);
    // 线路特色
    $('#travel_feature').text(data.travel_feature);
    //图片开始
    $('#p0').html('<img src="'+data.travel_picture+'"/>');
    $('#p1').html('<img src="'+data.travel_picture1+'"/>');
    $('#p2').html('<img src="'+data.travel_picture2+'"/>');
    $('#p3').html('<img src="'+data.travel_picture3+'"/>');
    
    $('.p0').html('<img src="'+data.travel_picture+'"/>');
    $('.p1').html('<img src="'+data.travel_picture1+'"/>');
    $('.p2').html('<img src="'+data.travel_picture2+'"/>');
    $('.p3').html('<img src="'+data.travel_picture3+'"/>');


    var navInf = '';
    if (data.toName != null) {
        navInf +=
            '<a href='+data.toName+'"/linde-list/line-list.html?key=">' +
            data.toName+'旅游' +
            '</a>';
    }
    navInf +=
        '<span style="margin-left: 10px;margin-right: 10px;">>></span>' +
        '<a href="/line-detailN/line-detail.html?id='+ data.id +'">' + data.travel_name + '</a>';
    
    $('#nav-inf').append(navInf);
    // console.log(data);

}

//插入班次数据
function dealPlans(plans) {
    // ????
    // console.log(plans)
    var ins = '';
    $(plans).each(function (index, item) {
        // console.log(item);
        ins += '<option value="' + item.seq + '">' +
            item.start_time +
            '&nbsp;&nbsp;&nbsp;成人价¥' + item.plan_price + '起' +
            '儿童价¥' + item.plan_child_price + '起' +
            '</option>';
        $('#line_plans').html(ins);
    });
}

//生成日历
function createCalendar(plans) {
    // console.log(plans);
    var planArray = new Array(100);
    $.each(plans, function (index, item) {
        planArray[index] = parseInt(item.start_time.substring(item.start_time.length-2,item.start_time.length));
    });

    var lastDate = getCurrentMonthLast(new Date().getMonth());
    var firstDate = new Date();
    firstDate.setDate(1);
    

    console.log(firstDate);
    


    var dayNum = lastDate.getDate();//当前月天数
    var firstDayWeekIndex = firstDate.getDay();//月第一天的星期下标 0对应星期日1到6则对应正常周一到周六

    var rowNum = Math.ceil((firstDayWeekIndex + dayNum) / 7);
    
    var ins = '' +
        '<tr>' +
        '<th><span class="datax" style="color:#f86b4f;">日</span></th>' +
        '<th><span class="datax">一</span></th>' +
        '<th><span class="datax">二</span></th>' +
        '<th><span class="datax">二</span></th>' +
        '<th><span class="datax">四</span></th>' +
        '<th><span class="datax">五</span></th>' +
        '<th><span class="datax" style="color:#f86b4f;">六</span></th>' +
        '</tr>';
    
    //准备插入表格
    var flag = 0;//开始标记
    var nowIndex = 1;//当前待插入号数

    // var planArryString = ['01, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 22];
    console.log(planArray);
    for (var i = 0; i <= rowNum; i++) {
        ins +=
            '<tr>';
        
        for (var j = 0; j < 7; j++) {
            if ((j >= firstDayWeekIndex || flag == 1) && nowIndex <= dayNum) {
                if (flag == 0) {
                    flag = 1;
                }

                if (planArray.indexOf(nowIndex) !== -1) {
                    //有计划
                    ins +=
                        '<td bgcolor="#fefedd">' +
                        (nowIndex++) + '<br>' + '<i>¥29</i>' +
                        '</td>';
                    continue;
                }
                //无计划
                ins +=
                    '<td>' +
                    (nowIndex++) +
                    '</td>';
                continue;
            }
            //空白格
            ins +=
                '<td>' +
                '&nbsp;' +
                '</td>';
        }

        ins +=
            '<tr>';

    }

    $('#calendar').find('tbody').html(ins);



}

//插入日程数据
function dealSchedules(schedules) {
    var scheduleTableHtml = '';
    var scheduleDetailHtml = '';
    var zili = '<td>自理</td>';
    var baohan = '<td class="t_col">包含</td>';
    $(schedules).each(function (i, item) {
        // 日程表格
        item.sche_meal = item.sche_meal == 0 ? zili : baohan;
        item.sche_meal2 = item.sche_meal2 == 0 ? zili : baohan;
        item.sche_meal3 = item.sche_meal3 == 0 ? zili : baohan;
        scheduleTableHtml +=
            '<tr>' +
            '<td class="t_col">第'+item.seq+'天</td>' +
            '<td>'+item.stay_hotel+'</td>' +
            item.sche_meal+
            item.sche_meal2+
            item.sche_meal3+
            '</tr>';
        scheduleDetailHtml +=
            '<div class="day">' +
            '<div class="day_head">' +
            '<div class="day_s">第' + item.seq + '天</div>' +
            '<p style="font-size: 15px;">'+ item.sche_views +'</p>' +
            '</div>' +
            '<div class="day_body">' +
            '<div class="day_miaoshu">' +
            item.sche_detail.replace(/\n/g,'<br>') +
            '</div>' +
            '<div class="day_body_col">' +
            '<p style="font-weight: 600;">酒店信息：</p>' +
            '<p>'+item.stay_hotel+'</p>' +
            '<p style="font-weight: 600;margin-left: 20px;">参考星级：</p>' +
            '<p style="color: red;">'+item.sche_stay_level+'</p>' +
            '</div>' +
            '<div class="day_body_col">' +
            '<p style="font-weight: 600;">用餐信息</p>' +
            '<span>早餐：'+item.sche_meal+'</span>' +
            '<span>早餐：'+item.sche_meal2+'</span>' +
            '<span>早餐：'+item.sche_meal3+'</span>' +
            '</div>' +
            '</div>' +
            '</div>';
        
        
    });
    //插入表格信息
    $('#schedule-table').html(scheduleTableHtml);
    //插入详细信息
    $('#schedules-detail').html(scheduleDetailHtml);
}

//处理关联线路信息
function dealRelatedLine(relatedLine) {
    var ins = '';
    $(relatedLine).each(function (index, item) {
        ins +=
            '<div class="bm_right_pic1">' +
            '<div class="bm_right_pic1_top">' +
            '<a href="/dd_travel_war/line-detail/line-detail.html?id='+item.id+'">' +
            '<img src="img/5.jpg" height="100%" width="100%"/>' +
            '<p style="color: black;font-size: 10px;">' +
            item.travel_name +
            '</p>' +
            '</a>' +
            '</div>' +
            '<div class="bm_right_pic1_bot">' +
            '<p>¥'+item.travel_price+'起</p>' +
            '</div>' +
            '</div>';
    })
    $('#eqq').append(ins);
}

//查看大图的js
function showBigPic(o) {
    var id = $(o).attr('id');
    var obj=$('.' + id);
    obj.parent().find('.on').removeClass('on');
    obj.addClass('on');
}

//位置跳转函数
function toIdPos(idValue) {
    // console.log("asdasd");
    // console.log($("#" + idValue).offset().top);    
    $('html, body').animate({
        scrollTop: $("#"+idValue).offset().top-60
    }, 500);
    
}

function toReservePage() {
    var adultNum = $('#adultNum').val();
    var childNum = $('#childNum').val();
    var url = '../order/addOrder.html?adult=' + adultNum + '&child=' + childNum;
    window.location.href = url;
    
}