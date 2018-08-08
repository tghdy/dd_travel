var inf1="",inf2="",inf3="",inf4="",inf5="";
function selectVisaDetail() {
    var id = getUrlParam('id');
    $.ajax({
        type:"get",
        url:"/dd_travel_war/visa",
        data:{
            id:id,
            method:"visaAllInf"
        },
        dataType:"json",
        success:function (json) {
    		console.log(json);
            $('#title').val(json.data.visa_title);
            $('#picture').attr("src",json.data.visa_pic);
            $('#deal_place').val(json.data.deal_place);
            $('#validity_period').val(json.data.validity_period);
            $('#most_stay').val(json.data.most_stay);
            $('#deal_time').val(json.data.deal_time);
            $('#interview').val(json.data.interview);
            $('#entry').val(json.data.entry);
            $('#custom_range').val(json.data.custom_range);
            $('#price').val(json.data.price);
            $('#matters').val(json.data.matters);
            $('#description_fees').val(json.data.description_fees);
            $('#warning').val(json.data.warning);
            $('#inf1').html(json.data.custom_inf1);
            $('#inf2').html(json.data.custom_inf2);
            $('#inf3').html(json.data.custom_inf3);
            $('#inf4').html(json.data.custom_inf4);
            $('#inf5').html(json.data.custom_inf5);
            inf1 = json.data.custom_inf1;
            inf2 = json.data.custom_inf2;
            inf3 = json.data.custom_inf3;
            inf4 = json.data.custom_inf4;
            inf5 = json.data.custom_inf5;
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}

function updateVisa() {
    var id = getUrlParam('id');
    var title = $("#title").val();
    var picture = $("#picture").attr('src');
    var deal_place = $("#deal_place").val();
    var validity_period = $("#validity_period").val();
    var most_stay = $("#most_stay").val();
    var deal_time = $("#deal_time").val();
    var interview = $("#interview").val();
    var entry = $("#entry").val();
    var custom_range = $("#custom_range").val();
    var price = $("#price").val();

    var a = $("#matters").val();
    var matters = a.replace(/(\r\n)|(\n)/g,'<br>');
    var b = $("#description_fees").val();
    var description_fees = b.replace(/(\r\n)|(\n)/g,'<br>');
    var c = $("#warning").val();
    var warning = c.replace(/(\r\n)|(\n)/g,'<br>');
    $.ajax({
        type:"post",
        url:"/dd_travel_war/visa",
        data:{
            id:id,
            title:title,
            picture:picture,
            deal_place:deal_place,
            validity_period:validity_period,
            most_stay:most_stay,
            deal_time:deal_time,
            interview:interview,
            entry:entry,
            custom_range:custom_range,
            price:price,
            matters:matters,
            description_fees:description_fees,
            warning:warning,
            custom_inf1:inf1,
            custom_inf2:inf2,
            custom_inf3:inf3,
            custom_inf4:inf4,
            custom_inf5:inf5,
            method:"updateVisa"

        },
        dataType:"json",
        success:function (json) {
            alert("修改成功!");
            layer_close();
    		console.log(json);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}


function insertVisa() {
    var title = $("#title").val();
    var picture = $("#picture").attr('src');
    var deal_place = $("#deal_place").val();
    var validity_period = $("#validity_period").val();
    var most_stay = $("#most_stay").val();
    var deal_time = $("#deal_time").val();
    var interview = $("#interview").val();
    var entry = $("#entry").val();
    var custom_range = $("#custom_range").val();
    var price = $("#price").val();
    var matters = $("#matters").val();
    var description_fees = $("#description_fees").val();
    var warning = $("#warning").val();
    $.ajax({
        type:"post",
        url:"/dd_travel_war/visa",
        data:{
            title:title,
            picture:picture,
            deal_place:deal_place,
            validity_period:validity_period,
            most_stay:most_stay,
            deal_time:deal_time,
            interview:interview,
            entry:entry,
            custom_range:custom_range,
            price:price,
            matters:matters,
            description_fees:description_fees,
            warning:warning,
            custom_inf1:inf1,
            custom_inf2:inf2,
            custom_inf3:inf3,
            custom_inf4:inf4,
            custom_inf5:inf5,
            method:"insertVisa"
        },
        dataType:"json",
        success:function (json) {
            alert("添加成功!");
            layer_close();
    		console.log(json);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
}

function set_inf1() {
    inf1 = ue.getContent();
}
function set_inf2() {
    inf2 = ue.getContent();
}
function set_inf3() {
    inf3 = ue.getContent();
}
function set_inf4() {
    inf4 = ue.getContent();
}
function set_inf5() {
    inf5 = ue.getContent();
}
function reset() {
    ue.execCommand('cleardoc');
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