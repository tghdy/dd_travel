$(function () {
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});
});
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}
//根据id获取值
function getValById(idValue) {
    var res = '#' + idValue;
    var tagName = $(res)[0].tagName;
    // console.log(idValue+':::'+$(res).val());
    if (tagName == 'SELECT')
        return $(res).find('option:selected').val();
    if (tagName == 'IMG')
        return $(res).attr('src');
    return $(res).val();
}

// 选中options中跟value相同的项
function matchOptionAndValue(options, value) {
    $(options).each(function (index, item) {
        if($(item).val() == value) {
            $(item).attr("selected","selected");
        }
    });
}

function showUserInf() {
    var ins = '';
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/user',
        data: {
            method: 'userInf',
        },
        dataType: 'json',
        success: function (json) {
            var data = json.data;
            if (data == null) {
                return;
            }
            if (data.userAccount == null) {
                ins += '<span style="color: #c9c9c9">欢迎您:' + data.mobilePhone + '</span>';
            } else {
                ins += '<span style="color: #c9c9c9">欢迎您:' + data.userName + '</span>';
            }
            ins += '<span><a href="../order-list/dingDanSearch.html" style="margin-left: 10px;">会员中心</a></span>';
            ins += '<span style="position: relative;font-size: 13px;opacity: 0.3;margin:0 5px;">|</span>';
            ins += '<span><a href="javascript:;" onclick="exitUser();">退出</a></span>';
            $('#login-inf').html(ins);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
}

function exitUser() {
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/user',
        data: {
            method: 'exit',
            
        },
        dataType: 'json',
        success: function (json) {
            $('#login-inf').html(
                '<a href="/dd_travel_war/user/login.html" class="tt" style="font-size: 12px;position: relative;text-decoration: none;">登录</a>' +
                '<span style="position: relative;font-size: 13px;opacity: 0.3; margin: 0 5px;">|</span>' +
                '<a href="/dd_travel_war/user/register.html" class="tt" style="font-size: 12px;;position: relative;text-decoration: none;">免费注册</a>')
            // alert(json.msg);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
    
}
//获取某月的第一天
function getCurrentMonthFirst(m) {
    var now = new Date();
    var date = new Date(now.getFullYear(), m, 1);
    // date.setDate(1);
    return date;
}

<!--获取某月最后一天-->
function getCurrentMonthLast(m) {
    var now = new Date();
    // var currentMonth=date.getMonth();
    var nextMonth = ++m;
    var nextMonthFirstDay = new Date(now.getFullYear(), nextMonth, 1);
    var oneDay = 1000 * 60 * 60 * 24;
    return new Date(nextMonthFirstDay - oneDay);
}