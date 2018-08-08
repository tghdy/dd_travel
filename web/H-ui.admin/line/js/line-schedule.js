$(function () {

});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
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

function selectLineSchedule() {
    $.ajax({
        type: "get",
        url: "/dd_travel_war/lineAdmin",
        async: false,
        data: {
            method: "selectLineSchedule",
            id: getUrlParam("id"),
            seq: getUrlParam("seq")
        },
        dataType: 'json',
        success: function (json) {
            console.log("selectLineSchedule Ok");
            console.log(json);
            if (json.flag == 1) {
                var data = json.data;
                $('#travel_id').val(data.travel_id);
                $('#seq').val(data.seq);
                $('#sche_detail').val(data.sche_detail);
                $('#sche_stay_level').val(data.sche_stay_level);
                $('#stay_hotel').val(data.stay_hotel);
                $('#sche_meal').val(data.sche_meal);
                $('#sche_meal2').val(data.sche_meal2);
                $('#sche_meal3').val(data.sche_meal3);
                $('#sche_views').val(data.sche_views);
                $('#sche_pic').attr('src', data.sche_pic);
                $('#sche_pic2').attr('src', data.sche_pic2);
                $('#sche_pic3').attr('src', data.sche_pic3);
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
            jsonData: JSON.stringify({
                travelId: getUrlParam("id"),
                seq: getValById("seq"),
                scheDetail: getValById("sche_detail"),
                scheStayLevel: getValById("sche_stay_level"),
                stayHotel: getValById("stay_hotel"),
                scheMeal: getValById("sche_meal"),
                scheMeal2: getValById("sche_meal2"),
                scheMeal3: getValById("sche_meal3"),
                scheViews: getValById("sche_views")
            })
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                layer.msg(json.msg, {icon: 1, time: 1000}, function () {
                    layer_close();
                })
            }
            console.log("insertLinePlan success");
            console.log(json);

        },
        error: function (data) {
            layer.msg(json.msg, {icon: 1, time: 1000});
            console.log("insertLinePlan failed ");
            console.log(data);
        }
    });

}

function addLineSchedules() {
    var tid = getUrlParam('travelId');
    //验证线路id的两种来源，一种是来源于别的详情js的travelId变量一种是url传来的，如果url没传就肯定是综合添加页面传来的
    if (tid == null) {
        tid = travelId;
    }
    if (tid == null) {
        alert('请先添加线路基本信息');
        return;
    }
    var scheduleArray = [];
    var seqs = $('.seq');
    var sche_details = $('.sche_detail');
    var sche_stay_levels = $('.sche_stay_level');
    var stay_hotels = $('.stay_hotel');
    var sche_meals = $('.sche_meal');
    var sche_meal2s = $('.sche_meal2');
    var sche_meal3s = $('.sche_meal3');
    var sche_views = $('.sche_views');
    var sche_pic = $('.sche_pic');
    var sche_pic2 = $('.sche_pic2');
    var sche_pic3 = $('.sche_pic3');

    for (var i = 0; i < seqs.length; i++) {
        scheduleArray[i] = {
            travelId: tid,
            seq: seqs[i].value,
            scheDetail: sche_details[i].value,
            scheStayLevel: sche_stay_levels[i].value,
            stayHotel: stay_hotels[i].value,
            scheMeal: sche_meals[i].value,
            scheMeal2: sche_meal2s[i].value,
            scheMeal3: sche_meal3s[i].value,
            scheViews: sche_views[i].value,
            schePic: $(sche_pic[i]).attr('src'),
            schePic2: $(sche_pic2[i]).attr('src'),
            schePic3: $(sche_pic3[i]).attr('src'),
        };
    }
    $.ajax({
        type: 'post',
        url: '/dd_travel_war/lineAdmin',
        data: {
            method: 'insertLineSchedules',
            jsonArray: JSON.stringify(scheduleArray)
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                layer.msg(json.msg, {icon: 6, time: 1000}, function () {
                    layer_close();
                });
            }
            console.log(json);
            alert("插入成功");
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
}

function updateLineSchedule() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateLineSchedule",
            jsonData: JSON.stringify({
                travelId: getUrlParam("id"),
                seq: getValById("seq"),
                scheDetail: getValById("sche_detail"),
                scheStayLevel: getValById("sche_stay_level"),
                stayHotel: getValById("stay_hotel"),
                scheMeal: getValById("sche_meal"),
                scheMeal2: getValById("sche_meal2"),
                scheMeal3: getValById("sche_meal3"),
                schePic: getValById("sche_pic"),
                schePic2: getValById("sche_pic2"),
                schePic3: getValById("sche_pic3"),
                scheViews: getValById("sche_views")
            })
        },
        dataType: 'json',
        success: function (json) {
            if (json.flag == 1) {
                layer.msg(json.msg, {icon: 1, time: 1000}, function () {
                    layer_close();
                })
            }
            console.log("updateLineSchedule success");
            console.log(json);

        },
        error: function (data) {
            layer.msg(json.msg, {icon: 1, time: 1000});
            console.log("updateLineSchedule failed ");
            console.log(data);
        }
    });

}

function generateScheduleList() {
    $('#schedules-area').html('');
    var daysNum = prompt("请输入日程的天数");
    for (var i = 0; i < daysNum; i++) {
        var ins =
            '<div class="form form-horizontal">' +
            '       <div class="row cl">' +
            '            <label class="form-label col-xs-4 col-sm-2">序号：</label>' +
            '            <div class="formControls col-xs-8 col-sm-9">' +
            '                <input type="text" class="input-text seq" value="" placeholder="" id="" name="">' +
            '            </div>' +
            '        </div>' +
            '        <!--详情-->' +
            '        <div class="row cl">' +
            '            <label class="form-label col-xs-4 col-sm-2">详情：</label>' +
            '            <div class="formControls col-xs-8 col-sm-9">' +
            '                <textarea id="" name="" cols="" rows="" class="textarea sche_detail" placeholder="说点什么...最少输入10个字符"' +
            '                          datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！"' +
            '                          onKeyUp="$.Huitextarealength(this,200)"></textarea>' +
            '                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>' +
            '            </div>' +
            '        </div>' +
            '        <div class="row cl">' +
            '            <label class="form-label col-xs-4 col-sm-2">酒店星级：</label>' +
            '            <div class="formControls col-xs-8 col-sm-9">' +
            '                <input type="text" class="input-text sche_stay_level" value="" placeholder="" id="" name="">' +
            '            </div>' +
            '        </div>' +
            '        <div class="row cl">' +
            '            <label class="form-label col-xs-4 col-sm-2">酒店名：</label>' +
            '            <div class="formControls col-xs-8 col-sm-9">' +
            '                <input type="text" class="input-text stay_hotel" value="" placeholder="" id="" name="">' +
            '            </div>' +
            '        </div>' +
            '        <div class="row cl">' +
            '             <label class="form-label col-xs-4 col-sm-2">早餐：</label>' +
            '             <div class="formControls col-xs-8 col-sm-9">' +
            '               <span class="select-box">' +
            '                   <select name="" class="select sche_meal" id="is_aside">' +
            '                       <option value="0">不包含</option>' +
            '                       <option value="1">包含</option>' +
            '                   </select>' +
            '                </span>' +
            '            </div>' +
            '        </div>' +
            '        <div class="row cl">' +
            '             <label class="form-label col-xs-4 col-sm-2">午餐：</label>' +
            '             <div class="formControls col-xs-8 col-sm-9">' +
            '               <span class="select-box">' +
            '                   <select name="" class="select sche_meal2" id="is_aside">' +
            '                       <option value="0">不包含</option>' +
            '                       <option value="1">包含</option>' +
            '                   </select>' +
            '                </span>' +
            '            </div>' +
            '        </div>' +
            '        <div class="row cl">' +
            '             <label class="form-label col-xs-4 col-sm-2">晚餐：</label>' +
            '             <div class="formControls col-xs-8 col-sm-9">' +
            '               <span class="select-box">' +
            '                   <select name="" class="select sche_meal3" id="is_aside">' +
            '                       <option value="0">不包含</option>' +
            '                       <option value="1">包含</option>' +
            '                   </select>' +
            '                </span>' +
            '            </div>' +
            '        </div>' +
            '        <div class="row cl">' +
            '            <label class="form-label col-xs-4 col-sm-2">景点：</label>' +
            '            <div class="formControls col-xs-8 col-sm-9">' +
            '                <input type="text" class="input-text sche_views" value="" placeholder="" id="" name="">' +
            '            </div>' +
            '        </div>' +
            '        <form class="row cl schedule_pic" id="">' +
            '            <label class="form-label col-xs-4 col-sm-2">行程图片：</label>' +
            '            <div class="col-sm-3">' +
            '                <img style="width: 200px;height: 200px;display: block;"' +
            '                     src="" alt="未上传图片" class="sche_pic">' +
            '                <span class="btn-upload form-group mt-20"> ' +
            '                <input type="file" name="p1" onchange="" style=""/> ' +
            '                </span>' +
            '            </div>' +
            '            <div class="col-sm-3">' +
            '                <img style="width: 200px;height: 200px;display: block;"' +
            '                     src="" alt="未上传图片" class="sche_pic2">' +
            '                <span class="btn-upload form-group mt-20"> ' +
            '                <input type="file" name="p1" onchange="" style=""/> ' +
            '                </span>' +
            '            </div>' +
            '            <div class="col-sm-3">' +
            '                <img style="width: 200px;height: 200px;display: block;"' +
            '                     src="" alt="未上传图片" class="sche_pic3">' +
            '                <span class="btn-upload form-group mt-20"> ' +
            '                <input type="file" name="p1" onchange="" style=""/> ' +
            '                </span>' +
            '            </div>' +
            '            <div style="clear: both;"></div>' +
            '            <input type="button" class="btn btn-primary radius col-sm-offset-2 pic-btn" onclick="addSchedulePic(this)" style=""' +
            '                   value="上传图片"/>' +
            '        </form>' +
            '       <div style="clear: both;"></div>' +
            '</div>';
        $('#schedules-area').append('<h2>第' + (i + 1) + '天</h2>');
        $('#schedules-area').append(ins);
    }
    $('#schedules-area').append('<div class="row cl">' +
        '<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">' +
        '<button onClick="addLineSchedules();" class="btn btn-secondary radius" type="button"> 提交' +
        '</button>' +
        '<button onClick="layer_close();" class="btn btn-default radius" type="button">' +
        '&nbsp;&nbsp;取消&nbsp;&nbsp;' +
        '</button>' +
        '</div>' +
        '</div>')
}


function addSchedulePic(o) {
    var i = $('.pic-btn').index(o);//当前上传的图片下标
    var data = new FormData($('.schedule_pic')[i]);
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
            var imgs = $(o).parent().find('img');
            var files = $(o).parent().find('input[type="file"]');
            var notNullFiles=[];

            $(files).each(function (index, item) {
                if(item.value != "" && item.value!=null) {
                    notNullFiles[notNullFiles.length] = item;
                }
                
            })
            console.log(notNullFiles);
            
            $(notNullFiles).each(function (index, item) {
                $(item).parent().prev().attr("src", '/upload/' + json.data[index]);
            })
        }
    });
}


