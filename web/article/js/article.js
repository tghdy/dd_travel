$(document).ready(function(){
    var id = GetQueryString("id");
    $.ajax({
        type:"get",
        url:"/dd_travel_war/articleAdmin",
        data:{
            id:id,
            method:"selectArticle"
        },
        dataType:"json",
        success:function (json) {
    		console.log(json.data);
    		$(".title").text(json.data.title);
            $("#author").text("作者："+json.data.news_author);
            $("#create_time").text("发表于："+json.data.create_time);
            $("#view").text("浏览： "+json.data.news_view+"次");
            $(".article").html(json.data.news_content);
    	},
        error:function(data){
            console.log(data.msg);
        }
    });
});

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}