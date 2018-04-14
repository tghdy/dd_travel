var tPrice;

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

function sendOrder() {
    var parm = {
        travelId: getUrlParam('id'),
        userId: 1,
        orderPrice: tPrice,
        adultNum: getUrlParam('adult'),
        childNum: getUrlParam('child'),
        orderRemarks: $('#request').val(),
        //come
        orderUser: $('#orderUser').val(),
        orderMobile: $('#orderMobile').val(),
        orderEmail: $('#orderEmail').val(),
        orderIdcard: $('#orderIdcard').val()
    };
    // parm = {
    //     travelId: 1,
    //     userId: 1,
    //     orderPrice: tPrice,
    //     adultNum: getUrlParam('adult'),
    //     childNum: getUrlParam('child'),
    //     orderRemarks: '备注信息',
    //     //come
    //     orderUser: 2,
    //     orderMobile: '18888643840',
    //     orderEmail: '123@qq.com',
    //     orderIdcard: '500234'
    // };
    console.log(parm);
    var a=123;
    
    $.ajax({
        url: "http://localhost:8080/TravelOrder/insertOrder",
        method: "post",
        data: JSON.stringify(parm),
        dataType: 'json',
        success: function (json) {
            console.log(json);
            location.href = '../order-list/order-list.html?uid=1';
        },
        error: function (data) {
            console.log(data);
        }       
        
    });
}


$(function () {
    var adult = getUrlParam("adult");
    var child = getUrlParam("child");
    var aP = getUrlParam('adultPrice');
    var cP = getUrlParam('childPrice');
    if (adult > 0) {
        $('#adult-area').append(adult + '成人&nbsp;&nbsp;*&nbsp;&nbsp;¥' + aP + '<br>');
    }
    if (child > 0) {
        $('#child-area').append(child + '儿童&nbsp;&nbsp;*&nbsp;&nbsp;¥' + cP);
    }
    tPrice = (adult * aP + child * cP);
    $('#tPrice').append('总价：' + tPrice);
    $('#adultNum').attr('value', adult);
    $('#childNum').attr('value', child);

});

