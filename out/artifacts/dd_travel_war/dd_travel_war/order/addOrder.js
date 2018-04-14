var seq;
var travelId;
var travelNo;
var childPrice;
var adultPrice;

$(function () {
    initOrderPage();
});

function submit() {
    insertOrder();
}

function initOrderPage() {
    //显示用户的值
    $('#adult').val(getUrlParam('adult'));
    $('#child').val(getUrlParam('child'));
    
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/dd_travel_war/order',
        data: {
            method: 'orderPageInit',
            id: 1,
            seq: 1
        },
        dataType: 'json',
        success: function (json) {
            // console.log("success");
            var data = json.data;
            console.log(data);
            
            //初始化线路相关的几个值
            seq = data.linePlan.travel_id;
            travelId = data.linePlan.travel_id;
            travelNo = data.lineInf.travel_no;
            childPrice = data.linePlan.plan_child_price;
            adultPrice = data.linePlan.plan_price;
            //更新面板
            updatePricePanel();

            
            $('#travel_name').text(data.lineInf.travel_name);
            $('#fromName').text(data.lineInf.fromName);
            $('#go_time').text(data.linePlan.start_time);
            $('#back_time').text(data.linePlan.start_time);
            $('#gather_time').text(data.linePlan.gather_time);
            $('#gather_place').text(data.linePlan.gather_place);
            $('#dismiss_place').text(data.linePlan.dismiss_place);
            
            
        },
        error: function (data) {
            console.log("failed");
            console.log(data)
        }

    });
}

//更新价格面板
function updatePricePanel() {
    //初始化面板
    var childNum = getValById('child');
    var adultNum = getValById('adult');
    
    var total = adultNum * adultPrice +  childNum * childPrice;

    var ins = '';
    ins +=
        '<span>' + adultNum + '成人×¥' + adultPrice + '</span>' +
        '<br>' +
        '<span>' + childNum + '儿童×¥' + childPrice + '</span>';
    // 插入面板
    $('#price_inf').html(ins);
    // 插入总价
    $('#total').text('¥'+total);
}

function insertOrder() {
    
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/dd_travel_war/order',
        data: {
            method: 'insertOrder',
            travelId: travelId,
            seq: seq,
            adult: getValById('adult'),
            child: getValById('child')
        },
        dataType: 'json',
        success: function (json) {
            console.log("insert order success");
            console.log(json);
            if(json.flag == 1) {
                insertOrderDetail(json.data);
            } else {
                // layer.msg('生成订单失败!', {icon: 5, time: 1000});
                console.log('生成订单失败');
            }
            

        },
        error: function (data) {
            console.log("insert order failed");
            console.log(data);
            
        }

    });
    
}

function insertOrderDetail(id) {
    console.log({
        orderId: id,
        adultNum: getValById('adult'),
        childNum: getValById('child'),
        orderRemarks: getValById('remarks'),
        orderUser: getValById('name'),
        orderMobile: getValById('phone'),
        orderEmail: getValById('email'),
        orderIdcard: getValById('idcard')
    });
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/dd_travel_war/order',
        data: {
            method: 'insertOrderDetail',
            jsonData:JSON.stringify({
                orderId: id,
                adultNum:getValById('adult'),
                childNum:getValById('child'),
                orderRemarks:getValById('remarks'),
                orderUser:getValById('name'),
                orderMobile:getValById('phone'),
                orderEmail:getValById('email'),
                orderIdcard:getValById('idcard')
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insert order detail success");

        },
        error: function (data) {
            console.log("insert order detail failed");
            console.log(data)
        }
    });
}



function changeNum(type, way) {
    var value = 0;
    
    if (type == 'child') {
        value = parseInt($('#child').val());
        if (way == '+') {
            $('#child').val(value+1);
        } else {
            if (value == 0) {
                return;
            }
            $('#child').val(value-1);
        }
    } else {
        value = parseInt($('#adult').val());
        if (way == '+') {
            $('#adult').val(value+1);
        } else {
            if (value == 0) {
                return;
            }
            $('#adult').val(value-1);
        }
    }
    updatePricePanel();
    
}

