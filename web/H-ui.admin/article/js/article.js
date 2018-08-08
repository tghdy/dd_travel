//创建线路基本信息（同时也会创建一条detail信息）

function addArticle() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/articleAdmin",
        data: {
            method: "addArticle",
            jsonData:JSON.stringify({
                category:getValById('category'),                
                title:getValById('title'),
                newsContent:ue.getContent(),
                newsView:getValById('news_view'),
                // newsPriority:getValById('news_priority'),
                state:getValById('state'),
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("success");
            alert("添加成功");
            console.log(json);
        },
        error: function (data) {
            console.log("noa");
            console.log(data);
        }
    });




}


