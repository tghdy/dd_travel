$(function () {
    searOrder();
    
    $('#states').change(function () {
        console.log($(this).val());
        console.log($('#types').find('option:selected').val());
        searOrder($(this).val(),$('#types').find('option:selected').val());
    });
    $('#types').change(function () {
        console.log($(this).val());
        console.log($('#states').find('option:selected').val());
        searOrder($('#states').find('option:selected').val(),$(this).val());
    });

});

function searOrder(orderState, lineType) {
    if (orderState == null) {
        orderState = -1;
    }
    if (lineType == null) {
        lineType = -1;
    }
    $.ajax({
        type: "get",
        url: "/dd_travel_war/order",
        data: {
            method: "orderList",
            orderState:orderState,
            lineType:lineType
        },
        dataType: 'json',
        success: function (json) {
            if(json.flag==0) {
                alert(json.msg);
            }
            console.log("orderList Ok");
            console.log(json);
            var data = json.data;
            var ins = '';
            $(data).each(function (intex, item) {
                var state = item.order_state;
                if(state==1) {
                    state = '待确认';
                }
                if(state==2) {
                    state = '待付款';
                }
                if(state==3) {
                    state = '进行中';
                }
                if(state==4) {
                    state = '已完成';
                }
                if(state==9) {
                    state = '已取消';
                }

                ins +=
                    '<li>' +
                    '<div class="order-detail">' +
                    '<table class="w-970">' +
                    '<tbody>' +
                    '<tr>' +
                    '<td colspan="5" class="bg-w">订单号:' +
                    '<span class="fc-r f-w">' + item.order_no + '</span>' +
                    '</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<td width="50%">' +
                    '<a target="_blank" href="baidu.com">' + item.travel_name + '</a>' +
                    '<p>下单日期：&nbsp&nbsp<span class="time">' + item.order_time + '</span></p>' +
                    '<p>出游日期：&nbsp&nbsp<span class="time">' + 'nonono' + '</span></p>' +
                    '</td>' +
                    '<td class="pay" width="20%">' +
                    '<p><span class="pricelab">总金额：</span><span class="price">' + item.order_price + '</span>元</p>' +
                    '</td>' +
                    '<td class="status" width="15%">'+state+'</td>' +
                    '<td class="operate" width="15%"><div class="button"><a target="_blank" href="">订单详情</a></div></td>' +
                    '</tr>' +
                    '</tbody>' +
                    '</table>' +
                    '</div>' +
                    '</li>';
            });
            $('.order-list').html(ins);

        },
        error: function (data) {
            console.log("orderList No");
            console.log(data);
        }
    });    
}


function toSearchPage() {
    var key = $('#key').val();
    var lineType = $('#lineType').find('option:selected').val();
    // console.log("lineT");
    // console.log(lineType);
    window.location.href = '../line-list/line-list.html?key=' + key + '&lineType=' + lineType;
    
}

function initUserInf() {
    $('.user-area').siblings().addClass('hide');
    $('.user-area').removeClass('hide');
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/user',
        data: {
            method: 'userInf'
        },
        dataType: 'json',
        success: function (json) {
            console.log(json);
            if (json.flag != 1) {
                alert(json.msg);
                return;
            }
            var data = json.data;
            $('#user_name').val(data.userName);
            $('#user_email').val(data.email);
            $('#user_phone').val(data.mobilePhone);
            $('input[name="sex"][value="' + data.sex + '"]').attr('checked','checked');
            
        },
        error: function (data) {
            alert(data.msg);
        }
    });
}

function updateUserInf() {
    console.log({
        mobilePhone: getValById('user_phone'),
        email: getValById('user_email'),
        userName: getValById('user_name'),
        sex: $('input[name="sex"]:checked').val()
    });
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/user',
        data: {
            method: 'updateUserInf',
            jsonData:JSON.stringify({
                mobilePhone:getValById('user_phone'),
                email:getValById('user_email'),
                userName:getValById('user_name'),
                sex:$('input[name="sex"]:checked').val()
            })
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                alert(json.msg);
            }
            console.log(json);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
    
}

function initOrders() {
    $('.order-area').siblings().addClass('hide');
    $('.order-area').removeClass('hide');
    
}

function toSearchLinePage() {
    var lineType = $('#lineType').find('option:selected').val();
    window.location.href = '../line-list/line-list.html?key=' + $('#key').val + '&lineType=' + lineType;
    
}