function initAreaInfo() {
    $.ajax({
        type: 'get',
        url: '/dd_travel_war/place',
        data: {
            method: 'selectAres',
            pid:0
        },
        async:false,
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                var data = json.data;
                var fromAreaInf = '';
                var toAreaInf = '';
                var ins = '';

                $(data).each(function (index, item) {
                    ins +=
                        '<option value="' + item.id + '">' + item.place_name + '</option>';
                });
                
                $('#fromAreas').html(ins);
                $('#toAreas').html(ins);
                
            }
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
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
        async:false,
        dataType: 'json',
        success: function (json) {
            initAreaInfo();
            $('#fromAreas>option').each(function (index, item) {
                if ($(item).val()==json.data.travel_from) {
                    $(item).attr('selected', 'selected');
                }
            });
            $('#toAreas>option').each(function (index, item) {
                if ($(item).val() == json.data.travel_to) {
                    $(item).attr('selected', 'selected');
                }
            });
            // console.log("selectLineDetail Ok");
            if (json.flag == 1) {
                var data=json.data;
                console.log(data);
                $('#travel_id').val(data.travel_id);
                $('#travel_subtitle').val(data.travel_subtitle);
                $('#travel_feature').val(data.travel_feature);
                $('#travel_tips').val(data.travel_tips);
                $('#travel_views').val(data.travel_views);
                $('#line_labels').val(data.line_labels);
                $('#travel_info').val(data.travel_info);
                $('#reserve_info').val(data.reserve_info);
                $('#warm_prompt').val(data.warm_prompt);
                $('#to_info').val(data.to_info);
                $('#travel_picture').attr("src",data.travel_picture);
                $('#travel_picture2').attr("src",data.travel_picture2);
                $('#travel_picture3').attr("src",data.travel_picture3);
                $('#travel_picture4').attr("src",data.travel_picture4);
                $('#seo_title').val(data.seo_title);
                $('#seo_key').val(data.seo_key);
                $('#seo_desc').val(data.seo_desc);
                
            }
        },
        error: function (data) {
            console.log("selectLineDetail No");
            console.log(data);
        }
    });

}
function updateLineDetail() {
    console.log({
        travelId:getValById('travel_id'),
        travelSubtitle:getValById('travel_subtitle'),
        travelFeature:getValById('travel_feature'),
        travelTips:getValById('travel_tips'),
        travelFrom:getValById('fromAreas'),
        travelTo:getValById('toAreas'),
        travelViews:getValById('travel_views'),
        // lineLabels:getValById('line_labels'),
        travelInfo:getValById('travel_info'),
        reserveInfo:getValById('reserve_info'),
        warmPrompt:getValById('warm_prompt'),
        toInfo:getValById('to_info'),
        travelPicture:getValById('travel_picture'),
        travelPicture2:getValById('travel_picture2'),
        travelPicture3:getValById('travel_picture3'),
        travelPicture4:getValById('travel_picture4'),
        schedulesPdf:getValById('schedules_pdf')
    })
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
                travelFrom:getValById('fromAreas'),
                travelTo:getValById('toAreas'),
                travelViews:getValById('travel_views'),
                // lineLabels:getValById('line_labels'),
                travelInfo:getValById('travel_info'),
                reserveInfo:getValById('reserve_info'),
                warmPrompt:getValById('warm_prompt'),
                toInfo:getValById('to_info'),
                travelPicture:getValById('travel_picture'),
                travelPicture2:getValById('travel_picture2'),
                travelPicture3:getValById('travel_picture3'),
                travelPicture4:getValById('travel_picture4'),
                schedulesPdf:getValById('schedules_pdf'),
                seoTitle:getValById('seo_title'),
                seoKey:getValById('seo_key'),
                seoDesc:getValById('seo_desc'),
                
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log(json);
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

function uploadPicture() {
    var data = new FormData($('#form1')[0]);
    $.ajax({
        url: "/dd_travel_war/fileUpload",
        type: 'POST',
        data: data,
        dataType: 'JSON',
        cache: false,
        processData: false,
        contentType: false,
        success: function (json) {
            console.log(json);
                $('#line_photos').find('img').each(function (index, item) {
                if (index < json.data.length) {
                    //回显上传完成的图片
                    $(this).attr("src", '/upload/' + json.data[index]);
                }
            })

        }
    });
}
function uploadPdf() {
    var data = new FormData($('#form2')[0]);
    $.ajax({
        url: "/dd_travel_war/fileUpload",
        type: 'POST',
        data: data,
        dataType: 'JSON',
        cache: false,
        processData: false,
        contentType: false,
        success: function (json) {
            if (json.flag==1) {
                layer.msg('上传成功!', {icon: 6, time: 1000});
            } else {
                layer.msg('上传失败!', {icon: 5, time: 1000});
                
            }

        }
    });
}