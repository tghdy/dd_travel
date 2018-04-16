$(function () {
    // $('#asd').attr("selected","selected");
    // alert(location.href);
    selectLineDetail();
});

function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return decodeURI(r[2]); return null;
}

function selectLineDetail() {
    var id = getUrlParam("id");
    $.ajax({
        type: "get",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "selectLineDetail",
            id:id
        },
        dataType: 'json',
        success: function (json) {
            console.log("JSON:::::");
            console.log(json);
            // console.log("selectLineDetail Ok");
            if (json.flag == 1) {
                var data=json.data;
                $('#travel_id').val(data.travel_id);
                $('#travel_subtitle').val(data.travel_subtitle);
                $('#travel_feature').val(data.travel_feature);
                $('#travel_tips').val(data.travel_tips);
                $('#travel_from').val(data.travel_from);
                $('#travel_to').val(data.travel_to);
                $('#travel_views').val(data.travel_views);
                $('#line_labels').val(data.line_labels);
                $('#travel_info').val(data.travel_info);
                $('#travel_picture').attr("src",data.travel_picture);
            }
        },
        error: function (data) {
            console.log("selectLineDetail No");
            console.log(data);
        }
    });

}
function updateLineDetail() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateLineDetail",
            jsonData:JSON.stringify({
                travelId:getValById('travel_id'),
                travelSubtitle:getValById('travel_subtitle'),
                travelFeature:getValById('travel_feature'),
                travelTips:getValById('travel_tips'),
                travelFrom:getValById('travel_from'),
                travelTo:getValById('travel_to'),
                travelViews:getValById('travel_views'),
                lineLabels:getValById('line_labels'),
                travelInfo:getValById('travel_info'),
                travelPicture:getValById('travel_picture'),
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLine success");
            if (json.flag == 1) {
                layer.msg(json.msg,{icon:1,time:1000},function () {
                    layer_close();
                })
            }
        },
        error: function (data) {
            layer.msg(json.msg,{icon:1,time:1000});
            console.log("insertLine failed ");
            console.log(data);
        }
    });

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

function matchOptionAndValue(options, value) {
    $(options).each(function (index, item) {
        if($(item).val() == value) {
            // console.log($(item).text()+$(item).val()+":"+value);
            // console.log($(item).val() == value);
            $(item).attr("selected","selected");
        }
    });
}