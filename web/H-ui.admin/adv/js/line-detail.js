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
                changeSonAresById($('#toAreas option:first-child'),'fromSonAreas');
                changeSonAresById($('#fromAreas option:first-child'),'toSonAreas');
                
            }
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
}


//显示(路线广告部分)
function showOneAdv() {
    var id = getUrlParam('id');
    $.ajax({
        type:"get",
        url:"/dd_travel_war/adb",
        data:{
            id:id,
            method:"showOneAdv"
        },
        dataType:"json",
        success:function (json) {
    		console.log(json.data);
            $('#travel_id').val(json.data.travel_id);
            $('#l_type').val(json.data.line_type);
            $('#aside_picture').attr("src",json.data.aside_picture);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}

//图片显示(首页轮播部分)
function selectslideDetail() {
    var id = getUrlParam('id');
    id = id-1;
    $.ajax({
        type:"get",
        url:"/dd_travel_war/adb",
        data:{
            method:"indexSlideAdb"
        },
        dataType:"json",
        success:function (json) {
    		console.log(json.data[id]);
    		$('#travel_picture').attr("src",json.data[id].travel_picture);

    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}
//提交(路线广告部分)
function submitAdb() {
    var id = getUrlParam('id');
    var travel_id=$("#travel_id").val();
    var line_type=$("#l_type").val();
    var url = $("#aside_picture")[0].src;
    $.ajax({
        type:"get",
        url:"/dd_travel_war/adb",
        data:{
            id:id,
            travel_id:travel_id,
            line_type:line_type,
            url:url,
            method:"submitAdb"
        },
        dataType:"json",
        success:function (json) {
            alert("成功");
            layer_close();
    		console.log(json);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}

//提交(首页轮播部分)
function submitpic() {
    var id = getUrlParam('id');
    var url = $("#travel_picture")[0].src;
    alert(id);
    $.ajax({
        type:"get",
        url:"/dd_travel_war/adb",
        data:{
            id:id,
            url:url,
            method:"addPic"
        },
        dataType:"json",
        success:function (json) {
            alert("成功");
            layer_close();
    		console.log(json);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}

//添加广告(路线广告部分)
function addAdb() {
    var travel_id=$("#travel_id").val();
    var line_type=$("#l_type").val();
    var is_aside=$("#is_aside").val();
    var url = $("#aside_picture")[0].src;
    $.ajax({
        type:"get",
        url:"/dd_travel_war/adb",
        data:{
            travel_id:travel_id,
            line_type:line_type,
            is_aside:is_aside,
            url:url,
            method:"addAdb"
        },
        dataType:"json",
        success:function (json) {
            alert("成功");
            layer_close();
    		console.log(json);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}


function selectLineDetail() {
    var id = getUrlParam('id');
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
         
            if (json.flag == 1) {
                var data=json.data;
                //显示当前出发地信息
                if(data.travel_from != null && data.travel_from != "") {
                    $('#oldFromPlace').val(selectPlaceById(data.travel_from).data.place_name);
                }
                if(data.travel_to != null && data.travel_to != "") {
                    $('#oldToPlace').val(selectPlaceById(data.travel_to).data.place_name);
                }
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
                $('#open_info').val(data.open_info);
                $('#to_info').val(data.to_info);
                $('#travel_picture').attr("src",data.travel_picture);
                $('#travel_picture2').attr("src",data.travel_picture2);
                $('#travel_picture3').attr("src",data.travel_picture3);
                $('#travel_picture4').attr("src",data.travel_picture4);
                $('#schedules_pdf').val(data.schedules_pdf);
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
//详情创建于线路基本信息插入时候，所以只有更新而没有插入
function updateLineDetail() {
    if(getValById('travel_id')==null || getValById('travel_id')==0) {
        alert('未添加或添加基本信息失败');
        return;
    }
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
                travelFrom:getValById('fromSonAreas'),
                travelTo:getValById('toSonAreas'),
                travelViews:getValById('travel_views'),
                // lineLabels:getValById('line_labels'),
                travelInfo:getValById('travel_info'),
                reserveInfo:getValById('reserve_info'),
                warmPrompt:getValById('warm_prompt'),
                openInfo:getValById('open_info'),
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
            if (json.flag == 1) {
                layer.msg(json.msg, {icon: 1, time: 1000});
            }
        },
        error: function (data) {
            layer.msg(json.msg,{icon:1,time:1000});
            console.log("insertLine failed ");
            console.log(data);
        }
    });

}

function uploadPicture(o) {
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
            var files = $(o).parent().find('input[type="file"]');
            var notNullFiles=[];
            //找到有文件的input标签加入notNullFiles
            $(files).each(function (index, item) {
                if(item.value != "" && item.value!=null) {
                    //当前的长度的值等价于新成员的下标，譬如长度3，发现新元素需要加入，那么其加入的位置下标就是第四个，即下标3
                    notNullFiles[notNullFiles.length] = item;
                }
            })
            $(notNullFiles).each(function (index, item) {
                $(item).parent().prev().attr("src", '/upload/' + json.data[index]);
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
                $('#schedules_pdf').val(json.data);
            } else {
                layer.msg('上传失败!', {icon: 5, time: 1000});
                
            }

        }
    });
}

function changeSonAresById(o,id) {
    $.ajax({
        type: 'post',
        url: '/dd_travel_war/place',
        data: {
            method: 'selectAres',
            pid: $(o).val()
        },
        async: false,
        dataType: 'json',
        success: function (json) {
            var ins = '';
            $(json.data).each(function (index, item) {
                ins +=
                    '<option value="' + item.id + '">' + item.place_name + '</option>';
            });
            $('#' + id).html(ins);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
    $('#'+id)
}

function selectPlaceById(id) {
    var result;
    $.ajax({
        type: 'post',
        url: '/dd_travel_war/place',
        data: {
            method: 'selectPlace',
            id: id
        },
        async:false,
        dataType: 'json',
        success: function (json) {
            result = json;
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
    return result;

}