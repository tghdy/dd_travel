$(function () {
    
});

function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return decodeURI(r[2]); return null;
}

function getValById(idValue) {
    var res = '#' + idValue;
    var tagName = $(res)[0].tagName;
    if (tagName == 'SELECT')
        return $(res).find('option:selected').val();
    if (tagName == 'IMG')
        return $(res).attr('src');
    return $(res).val();
}

function selectLineSchedule() {
    $.ajax({
        type: "get",
        url: "/dd_travel_war/lineAdmin",
        async: false,
        data: {
            method: "selectLineSchedule",
            id:getUrlParam("id"),
            seq:getUrlParam("seq")
        },
        dataType: 'json',
        success: function (json) {
            console.log("selectLineSchedule Ok");
            console.log(json);
            if (json.flag == 1) {
                var data=json.data;
                $('#travel_id').val(data.travel_id);
                $('#seq').val(data.seq);
                $('#sche_detail').val(data.sche_detail);
                $('#sche_stay_level').val(data.sche_stay_level);
                $('#stay_hotel').val(data.stay_hotel);
                $('#sche_meal').val(data.sche_meal);
                $('#sche_meal2').val(data.sche_meal2);
                $('#sche_meal3').val(data.sche_meal3);
                $('#sche_views').val(data.sche_views);
            }
        },
        error: function (data) {
            console.log("selectLineSchedule No");
            console.log(data);
        }
    });

}
function addLineSchedule() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "insertLineSchedule",
            jsonData:JSON.stringify({
                travelId:getUrlParam("id"),
                seq:getValById("seq"),
                scheDetail:getValById("sche_detail"),
                scheStayLevel:getValById("sche_stay_level"),
                stayHotel:getValById("stay_hotel"),
                scheMeal:getValById("sche_meal"),
                scheMeal2:getValById("sche_meal2"),
                scheMeal3:getValById("sche_meal3"),
                scheViews:getValById("sche_views")
            })
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }
            console.log("insertLinePlan success");
            console.log(json);

        },
        error: function (data) {
            layer.msg(json.msg,{icon:1,time:1000});
            console.log("insertLinePlan failed ");
            console.log(data);
        }
    });

}
function updateLineSchedule() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateLineSchedule",
            jsonData:JSON.stringify({
                travelId:getUrlParam("id"),
                seq:getValById("seq"),
                scheDetail:getValById("sche_detail"),
                scheStayLevel:getValById("sche_stay_level"),
                stayHotel:getValById("stay_hotel"),
                scheMeal:getValById("sche_meal"),
                scheMeal2:getValById("sche_meal2"),
                scheMeal3:getValById("sche_meal3"),
                scheViews:getValById("sche_views")
            })
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }
            console.log("updattLineSchedule success");
            console.log(json);

        },
        error: function (data) {
            layer.msg(json.msg,{icon:1,time:1000});
            console.log("updattLineSchedule failed ");
            console.log(data);
        }
    });

}