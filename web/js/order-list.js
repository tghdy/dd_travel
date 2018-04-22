$(function () {
    //显示用户信息
    showUserInf();
    
    //初始化订单信息
    searOrder();

    $('#states').change(function () {
        console.log($(this).val());
        console.log($('#types').find('option:selected').val());
        searOrder($(this).val(), $('#types').find('option:selected').val());
    });
    $('#types').change(function () {
        console.log($(this).val());
        console.log($('#states').find('option:selected').val());
        searOrder($('#states').find('option:selected').val(), $(this).val());
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
            orderState: orderState,
            lineType: lineType
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 0) {
                alert(json.msg);
            }
            console.log("orderList Ok");
            console.log(json);
            var data = json.data;
            var ins = '';
            $(data).each(function (intex, item) {
                var state = item.order_state;
                if (state == 0) {
                    state = '待确认';
                }
                if (state == 1) {
                    state = '待付款';
                }
                if (state == 2) {
                    state = '进行中';
                }
                if (state == 3) {
                    state = '已完成';
                }
                if (state == 9) {
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
                    '<p>出游日期：&nbsp&nbsp<span class="time">' + item.start_time + '</span></p>' +
                    '</td>' +
                    '<td class="pay" width="20%">' +
                    '<p><span class="pricelab">总金额：</span><span class="price">' + item.order_price + '</span>元</p>' +
                    '</td>' +
                    '<td class="status" width="15%">' + state + '</td>' +
                    '<td class="operate" width="15%"><div class="button"><a target="_blank" onclick="watchOrderDetail(' + item.id + ',' + item.plan_seq + ');"' +
                    ' href="javascript:;">订单详情</a></div></td>' +
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
            $('input[name="sex"][value="' + data.sex + '"]').attr('checked', 'checked');

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
            jsonData: JSON.stringify({
                mobilePhone: getValById('user_phone'),
                email: getValById('user_email'),
                userName: getValById('user_name'),
                sex: $('input[name="sex"]:checked').val()
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

function watchOrderDetail(id, seq) {
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/order',
        data: {
            method: 'orderDetail',
            id: id,
            seq: seq
        },
        dataType: 'json',
        success: function (json) {
            var data = json.data;
            console.log(data);
            var ins =
                '<div>' +
                '<div style="margin: 10px 10px;border: 1px solid #dcdcdc;background:#f3f3f3;padding: 5px 10px;"><span style="color: #666;" id="travel_name">' +
                data.travel_name +
                '</span></div>' +
                '<ul style="margin: 0 20px;" id="detailUl">' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">订单号：</p><span id="order_id">' +
                data.order_no +
                '</span>' +
                '</li>' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">下单时间：</p><span id="order_time">' +
                data.order_time +
                '</span>' +
                '</li>' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">出行日期：</p><span id="start_time">' +
                data.start_time +
                '</span>' +
                '</li>' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">总金额：</p><span id="price">' +
                data.order_price +
                '</span>' +
                '</li>' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">联系人：</p><span id="order_user">' +
                data.order_user +
                '</span>' +
                '</li>' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">电话：</p><span id="order_mobile">' +
                data.order_mobile +
                '</span>' +
                '</li>' +
                '<li>' +
                '<p style="width: 100px;text-align: right;">邮箱：</p><span id="order_email">' +
                data.order_email +
                '</span>' +
                '</li>' +
                '</ul>' +
                '</div>';
            layer.open({
                title: false,
                // area:['800px','500px'],
                content: ins
            });
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
    // layer.open({
    //     title: false,
    //     // area:['800px','500px'],
    //     content: '<div>' +
    //     '    <div style="margin: 10px 10px;border: 1px solid #dcdcdc;background:#f3f3f3;padding: 5px 10px;"><span style="color: #666;">【印象云南】昆明大理丽江双飞6日游飞扬独立成团，云南游口碑线路，酒店餐标全升级，让您玩的开心放心。</span></div>' +
    //     '    <ul style="margin: 0 20px;" id="detailUl">' +
    //     '        <li>' +
    //     '            <p style="width: 100px;text-align: right;">订单号：</p><span>123123123</span>' +
    //     '            ' +
    //     '        </li>' +
    //     '        <li>' +
    //     '            <p style="width: 100px;text-align: right;">下单时间：</p><span>2018-04-19</span>' +
    //     '        </li>' +
    //     '        <li>' +
    //     '            <p style="width: 100px;text-align: right;">出行日期：</p><span>2018-04-22</span>' +
    //     '        </li>' +
    //     '        <li>' +
    //     '            <p style="width: 100px;text-align: right;">总金额：</p><span>2134元</span>' +
    //     '        </li>' +
    //     '        <li>' +
    //     '            <p style="width: 100px;text-align: right;">联系人：</p><span>2134元</span>' +
    //     '        </li>' +
    //     '        <li>' +
    //     '            <p style="width: 100px;text-align: right;">电话：</p><span>10086</span>' +
    //     '        </li>' +
    //     '    </ul>    ' +
    //     '</div>'
}