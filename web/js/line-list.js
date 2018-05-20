var area =
    {
        浙江: {
            sonArea: ["杭州", "绍兴", "宁波"],
                days: [1, 2, 3, 4]
        },
        广东: {
            sonArea: ["潮汕", "闽南"],
                days: [1, 2, 3]
        },
        安徽: {
            sonArea: ["黄山", "泰山"],
            days: [1,2,3,4]
        },
        北京: {
            sonArea: ["北戴河","北京"],
            days: [1,2,3,4]
        }
        
        
    };


//初始化界面,此函数废弃
    function inita(area) {
        //实现方法，先完成父类的地区填充因为地区信息都是相等的，然后搜索线路信息，最后比对确认是哪个区域的线路加红显示
        //初始化目的地父级信息
    var ins = '<span class="col-sm-1 c-red" onclick="changeColor(this);">不限</span>';
    for (var p in area) {
        ins += '<span class="col-sm-1" onclick="changeColor(this);">' + p + '</span>';
    }
    $('#area').find('.inf').html(ins);
    //设置搜索框的值
    $('#search-value').val(getUrlParam('key'));
    //初始化导航栏和线路信息
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/line',
        data: {
            method: 'lineSearch',
            jsonData: JSON.stringify({
                // toName:'',
                // toPname:'',
                sort: 0,
                title: getUrlParam('key'),
                day: 0,
                lineType: -1
                //默认搜索全部信息所以传递-1，后台体现为不限制线路类型
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log(json);
            dealNavInf(json);
            //更新线路列表信息
            dealListJson(json);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });

    // updateRightAdbInf();

}


//更新右侧广告栏
function updateRightAdbInf() {
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/adb',
        data: {
            method: 'hotLineAdb'
        },
        dataType: 'json',
        success: function (json) {
            var ins = '';
            if (json.data.length == 0) {
                return;
            }
            $(json.data).each(function (index, item) {
                ins +=
                    '<li>' +
                    '<div style="position:relative;">' +
                    '<div class="cover-box" style="position: absolute; bottom: 0">满意度：91%</div>' +
                    '<a href="../line-detail/line-detail.html?id=' + item.travel_id + '"><img src="' + item.travel_picture + '" alt="那啥" style="width: 100%;height: 100%;"></a>' +
                    '</div>' +
                    '<div class="text_right_line">' +
                    '<div class="desc_title"><a href="../line-detail/line-detail.html?id=' + item.travel_id + '">' +
                    item.travel_name +
                    '</a></div>' +
                    '<div class="desc_detail">' +
                    '<span class="feelings">3429人购买</span>' +
                    '<span class="price_number f-r">¥' + item.travel_price + '</span>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            });
            // console.log(ins);
            $('.right-adb').find('ul').html(ins);

        },
        error: function (data) {
            console.log(data.msg);
        }
    });

}


//传入排序方式，综合目的地等多个条件动态更新线路信息,当按价格排序被触发时使用  
function updateLineInf(sortWay) {
    var title = getValById('search-value');
    var area = dealNavItemValue($('#area').find('.c-red').text());
    var sonArea = dealNavItemValue($('#sonArea').find('.c-red').text());
    var day = dealNavItemValue($('#days').find('.c-red').text());
    console.log(title + area + sonArea + day);
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/line',
        data: {
            method: 'lineSearch',
            jsonData: JSON.stringify({
                title: title,
                toPname: area,
                toName: sonArea,
                day: day,
                sort: sortWay
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log(json);
            // dealNavInf(json);
            dealListJson(json);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
}

//处理并显示拿到的线路数据
function dealListJson(json) {
    if (json.data.length == 0) {
        $('#line-inf-wrap').html("无线路信息");
        return;
    }
    var ins = "<ul>";
    $(json.data).each(function (index, item) {
        ins +=
            "<li>" +
            '<div class="line-pic-wrap col-sm-3 pd-0"><img src="' + item.travel_picture + '" alt="显示失败"></div>' +
            '<div class="line-mid-wrap col-sm-6 ml-15 pd-0">' +
            '<h2 class="f-16 pd-0"><a href="../line-detail/line-detail.html?id=' + item.id + '" class="c-blue">' + item.travel_name + '</a></h2>' +
            '<p class="c-aaa mt-10">线路特色：' + item.travel_feature + '</p>' +
            '<p class="mt-30">线路编号：' + item.travel_no + '</p>' +
            '</div>' +
            '<div class="line-mid-wrap f-r">' +
            '<p>¥<span class="f-16">' + item.travel_price + '</span>起</p>' +
            '<p><a href="../line-detail/line-detail.html?id='+item.id+'" class="btn btn-warning pt-10 plr-10">查看详情</a></p>' +
            '</div>' +
            '<div class="clearfix"></div>' +
            '</li>';
    })
    ins += "</ul>";

    $('#inf-num').html('<span class="f-12 f-r mt-5" id="inf-num">共找到' + $(json.data).length + "条记录</span>");
    $('#line-inf-wrap').html(ins);
    // updateLineInf();
}
function changeColor(op) {
    $(op).siblings('.c-red').removeClass('c-red');
    $(op).addClass('c-red');

    updateLineInf();
};

//初始化界面，更新线路信息和导航栏目信息，根据关键字，目的地名，线路类型初始化线路信息，在用
function initPage(key, toName, lineType) {
    console.log(key);
    console.log(toName);
    console.log(lineType);
    
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/line',
        data: {
            method: 'lineSearch',
            jsonData: JSON.stringify({
                sort: null,
                title: key,
                day: 0,
                toName: toName,
                lineType: lineType
            })
        },
        dataType: 'json',
        success: function (json) {
            //无线路信息时候不显示导航栏
            if (json.data == null || json.data.length == 0) {
                $('#inf-num').html('共找到0条信息');
                $('#line-inf-wrap').find('ul').html("无线路信息");
                $('.son-nav').hide();
                // alert('暂无线路信息');
                return;
            } else if(getUrlParam('lineType')==1){
                $('.son-nav').show();
            } 
                
            //处理拿到的线路数据
            dealListJson(json);
            
            //国内线路，展示导航栏信息
            var areaFlag = 1;//地区相同：如都是浙江
            var areaValue = json.data[0].area;

            var sonAreaFlag = 1;//区域相同：如都是杭州
            var sonAreaValue = json.data[0].son_area;

            //更新搜索条件栏
            $(json.data).each(function (index, item) {
                if (item.area != areaValue) {
                    areaFlag = 0;
                }
                if (item.son_area != sonAreaValue) {
                    areaFlag = 0;
                }

            });
            //插入地区信息
            var insAre = '<span class="col-sm-1 c-red" onclick="areaClicked(this);">不限</span>';
            for (var p in area) {
                insAre += '<span class="col-sm-1" onclick="areaClicked(this);">' + p + '</span>';
            }
            $('#area').find('.inf').html(insAre);

            //设置搜索框的值
            $('#search-value').val(key);

            var insAreaSon = '<span class="col-sm-1 c-red" onclick="sonAreaClicked(this);">不限</span>';
            var insDay = '<span class="col-sm-1 c-red" onclick="dayClicked(this);">不限</span>';

            //来自同一父地区
            if (areaFlag == 1) {

                for (var i = 0; i < area[areaValue].sonArea.length; i++) {
                    insAreaSon +=
                        '<span class="col-sm-1" onclick="sonAreaClicked(this);">' + area[areaValue].sonArea[i] + '</span>';
                }
                $('#sonArea').find('.inf').html(insAreaSon);
                for (var i = 0; i < area[areaValue].days.length; i++) {
                    insDay +=
                        '<span class="col-sm-1" onclick="dayClicked(this);">' + area[areaValue].days[i] + '</span>';
                }
                $('#days').find('.inf').html(insDay);
                var areaObj = $('#area').find('.inf').find('span:contains("' + areaValue + '")');
                // console.log('span:contains("' + areaValue + '")');
                if (areaObj != null) {
                    $(areaObj).siblings('span').removeClass('c-red');
                    $(areaObj).addClass('c-red');
                }
                //如果线路来一同一个子地区，且该子地区在该父地区能找到，则将改地区加红显示
                if (sonAreaFlag != 0) {
                    var sonAreaObj = $('#sonArea').find('.inf').find('span:contains(' + sonAreaValue + ')');
                    if (sonAreaObj != null) {
                        $(sonAreaObj).siblings('span').removeClass('c-red');
                        $(sonAreaObj).addClass('c-red');
                    }
                }

            //来自不同地区
            } else {
                $('#sonArea').find('.inf').html(insAreaSon);
                $('#days').find('.inf').html(insDay);
            }
        },
        error: function (data) {
            console.log(data.msg);
        }
    });

}

//当地区被点击，遍历area数组，更改子地区，最后根据当前条件完成线路搜索
function areaClicked(o) {
    $(o).siblings('.c-red').removeClass('c-red');
    $(o).addClass('c-red');
    //被点击的区域的值
    var clickedAreaValue = $(o).text();

    var ins = '<span class="col-sm-1 c-red" onclick="sonAreaClicked(this);">不限</span>';
    var insDay = '<span class="col-sm-1 c-red" onclick="dayClicked(this);">不限</span>';

    if (clickedAreaValue == '不限') {
        $('#sonArea').find('.inf').html(ins);
        $('#days').find('.inf').html(ins);
    } else {//未选择不限制父地区
        for (var i = 0; i < area[clickedAreaValue].sonArea.length; i++) {
            ins +=
                '<span class="col-sm-1" onclick="sonAreaClicked(this);">' + area[clickedAreaValue].sonArea[i] + '</span>';
        }
        for (var i = 0; i < area[clickedAreaValue].days.length; i++) {
            insDay +=
                '<span class="col-sm-1" onclick="dayClicked(this);">' + area[clickedAreaValue].days[i] + '</span>';
        }
        $('#sonArea').find('.inf').html(ins);
        $('#days').find('.inf').html(insDay);
    }

    //将"不限"转化为""
    clickedAreaValue = dealNavItemValue(clickedAreaValue);
    updateAllInf(clickedAreaValue, "", "", "", "");

}

//子地区被点击触发，点亮子地区，最后根据当前条件完成线路搜索
function sonAreaClicked(o) {
    $(o).siblings('.c-red').removeClass('c-red');
    $(o).addClass('c-red');
    //被点击的区域的值
    var clickedSonAreaValue = $(o).text();
    clickedSonAreaValue = dealNavItemValue(clickedSonAreaValue);
    //待完成
    var areaValue = $('#area').find('.inf').find('.c-red').text();
    areaValue = dealNavItemValue(areaValue);
    var day = $('#days').find('.inf').find('.c-red').text();
    day = dealNavItemValue(day);
    console.log(areaValue + day);
    //默认只搜索这三个条件
    updateAllInf(areaValue, clickedSonAreaValue, day, '', '');
}

//天数区域被点击，点亮当前天数，最后根据当前条件完成线路搜索
function dayClicked(o) {
    $(o).siblings('.c-red').removeClass('c-red');
    $(o).addClass('c-red');

    //被点击的区域(即天数)的值
    var clickedSonAreaValue = $(o).text();
    clickedSonAreaValue = dealNavItemValue(clickedSonAreaValue);
    //
    var areaValue = $('#area').find('.inf').find('.c-red').text();
    areaValue = dealNavItemValue(areaValue);
    var sonArea = $('#sonArea').find('.inf').find('.c-red').text();
    sonArea = dealNavItemValue(sonArea);
    //默认只搜索这三个条件
    updateAllInf(areaValue, sonArea, clickedSonAreaValue, '', '');

}


//更新线路信息，在用中
function updateAllInf(area, sonArea, day, sort, title) {
    console.log({
        title: title,
        toPname: area,
        toName: sonArea,
        day: day,
        sort: sort
    });
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/line',
        data: {
            method: 'lineSearch',
            jsonData: JSON.stringify({
                title: title,
                toPname: area,
                toName: sonArea,
                lineType:getUrlParam('lineType'),
                day: day,
                sort: sort
            })
        },
        dataType: 'json',
        success: function (json) {
            dealListJson(json);
            console.log(json);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });

}


//将不限转化为空值,在用中
function dealNavItemValue(value) {
    return value === '不限' ? '' : value;
}

//渲染红色导航栏，在用中
function redNavItem(lineType) {
    $('#nav-ul').find('li').each(function (index, item) {
        //
        if (item.id.substring(2) == lineType) {
            $(this).addClass('red-item');
        }
    });
}