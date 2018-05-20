$(function () {
    // $('#asd').attr("selected","selected");

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
function selectLinePlan() {
    $.ajax({
        type: "get",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "selectLinePlan",
            id:getUrlParam("id"),
        },
        dataType: 'json',
        success: function (json) {
            console.log("selectLinePlan Ok");
            console.log(json);
            if (json.flag == 1) {
                var data=json.data;
                console.log(data);
                $('#id').val(data.id);
                $('#travel_id').val(data.travel_id);
                $('#start_time').val(data.start_time);
                $('#plan_price').val(data.plan_price);
                $('#plan_child_price').val(data.plan_child_price);
                $('#gather_time').val(data.gather_time);
                $('#gather_place').val(data.gather_place);
                $('#dismiss_place').val(data.dismiss_place);
            }
            
            

        },
        error: function (data) {
            console.log("selectLinePlan No");
            console.log(data);
        }
    });

}
// 创建日程
function addLinePlan() {
    
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "insertLinePlan",
            jsonData:JSON.stringify({
                travelId:getUrlParam('id'),
                startTime:getValById('start_time'),
                planPrice:getValById('plan_price'),
                planChildPrice:getValById('plan_child_price'),
                gatherTime:getValById('gather_time'),
                gatherPlace:getValById('gather_place'),
                dismissPlace:getValById('dismiss_place')
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLinePlan success");
            console.log(json);
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }
        },
        error: function (data) {
            layer.msg(json.msg,{icon:1,time:1000});
            console.log("insertLinePlan failed ");
            console.log(data);
        }
    });
}

//更新
function updateLinePlan() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateLinePlan",
            jsonData:JSON.stringify({
                id:getValById('id'),
                travelId:getValById('travel_id'),
                startTime:getValById('start_time'),
                planPrice:getValById('plan_price'),
                planChildPrice:getValById('plan_child_price'),
                gatherTime:getValById('gather_time'),
                gatherPlace:getValById('gather_place'),
                dismissPlace:getValById('dismiss_place')
            })
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }
            console.log("updateLinePlan success");
            console.log(json);

        },
        error: function (data) {
            layer.msg(json.msg,{icon:1,time:1000});
            console.log("updateLinePlan failed ");
            console.log(data);
        }
    });
}