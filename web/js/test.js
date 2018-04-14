var array = [
    {周边游:[{name:"厦门"}]},
    {国内游:"asd"},
    {国际游:"asd"},
];

$(function () {
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});
 
})
function testLogin() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/user",
        data: {
            method: "login",
            account: 'qwe',
            password: 'qwe'
        },
        dataType: 'json',
        success: function (json) {
            console.log("success");
            console.log(json);
     
        },
        error: function (data) {
            console.log("failed ");
            console.log(data);
        }
    });
    
}

function testLineList() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/line",
        data: {
            method: "search",
            jsonData:JSON.stringify({
                name:"宁波",
                sort:0,
                toId:1,
                day:0
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("success");
            console.log(json);

        },
        error: function (data) {
            console.log("failed ");
            console.log(data);
        }
    });
}

function testLineDetail() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/line",
        data: {
            method: "lineDetail",
            id:1
        },
        dataType: 'json',
        success: function (json) {
            console.log("success");
            console.log(json);

        },
        error: function (data) {
            console.log("failed ");
            console.log(data);
        }
    });
}

function testUserList() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/userAdmin",
        data: {
            method: "userList",
            jsonData:JSON.stringify({
                name:"红红火火",
                email:"123",
                phone:"18888643840",
                state:1,
                startIndex:1
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("success");
            console.log(json);

        },
        error: function (data) {
            console.log("failed ");
            console.log(data);
        }
    });
}

function testOrderList() {
    
    $.ajax({
        type: "post",
        url: "/dd_travel_war/order",
        data: {
            method: "orderList",
            // orderState:1,
            // lineType:1
        },
        dataType: 'json',
        success: function (json) {
            console.log(json);

        },
        error: function (data) {
            console.log(data);
        }
    });
    
}