$(function () {
    // $('#asd').attr("selected","selected");
    selectLineBase();
    // layer.msg('添加成功!',{icon:1});
    
});

function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return decodeURI(r[2]);
    return null;
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
//创建线路基本信息（同时也会创建一条detail信息）
function insertLineBase() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/dd_travel_war/lineAdmin",
        data: {
            method: "insertLine",
            jsonData:JSON.stringify({
                travelNo:getValById("tNo"),
                travelName:getValById("tName"),
                trafficType:getValById("tTraffic"),
                travelCount:getValById("tCount"),
                travelOrderType:getValById("tOrderType"),
                lineType:getValById("tType"),
                travelDay:getValById("tDay"),
                travelPrice:getValById("tPrice"),
                travelChildPrice:getValById("tChildPrice"),
                state:getValById("state")
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLine success");
            console.log(json);
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            } else {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }

        },
        error: function (data) {
            console.log("insertLine failed ");
            console.log(data);
            layer.msg('错误!',{icon:1,time:1000});
        }
    });

}

//更新基本信息
function updateLineBase() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/dd_travel_war/lineAdmin",
        data: {
            method: "updateTravelLine",
            jsonData:JSON.stringify({
                id:getValById('tId'),
                travelNo:getValById("tNo"),
                travelName:getValById("tName"),
                trafficType:getValById("tTraffic"),
                travelCount:getValById("tCount"),
                travelOrderType:getValById("tOrderType"),
                lineType:getValById("tType"),
                travelDay:getValById("tDay"),
                travelPrice:getValById("tPrice"),
                travelChildPrice:getValById("tChildPrice"),
                state:getValById("state")
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLine success");
            console.log(json);
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            } else {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }

        },
        error: function (data) {
            console.log("insertLine failed ");
            console.log(data);
            layer.msg('错误!',{icon:1,time:1000});
        }
    });

}

function selectLineBase() {
    var id = getUrlParam("id");
    $.ajax({
        type: "get",
        url: "http://localhost:8080/dd_travel_war/lineAdmin",
        data: {
            method: "selectTravelLine",
            id:id
        },
        dataType: 'json',
        success: function (json) {
            console.log("selectTravelLine Ok");
            if (json.flag == 1) {
                var data=json.data;
                //tTraffic ok
                //tOrderType ok
                //state ok
                //tType ok
                $('#tId').val(data.id);
                $('#tChildPrice').val(data.travel_child_price);
                $('#tPrice').val(data.travel_price);
                $('#tCount').val(data.travel_count);
                $('#tDay').val(data.travel_day);
                $('#tName').val(data.travel_name);
                $('#tNo').val(data.travel_no);
                var typeOptions = $('#tType').find('option');
                matchOptionAndValue(typeOptions, data.line_type);
                var stateOptions = $('#state').find('option');
                matchOptionAndValue(stateOptions, data.state);
                var orderTypeOptions = $('#tOrderType').find('option');
                matchOptionAndValue(orderTypeOptions, data.travel_order_type);
                var trafficOptions = $('#tTraffic').find('option');
                matchOptionAndValue(trafficOptions, data.traffic_type);
            }

        },
        error: function (data) {
            console.log("selectTravelLine No");
            console.log(data);
        }
    });

}
function matchOptionAndValue(options, value) {
    $(options).each(function (index, item) {
        if($(item).val() == value) {
            // console.log($(item).text()+$(item).val()+":"+value);
            // console.log($(item).val() == value);
            $(item).attr("selected","selected");
        }
    });
}