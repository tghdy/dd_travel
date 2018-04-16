$(function () {
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});
    // lineList();
    // insertLine();
    // insertLinePlan();
    // insertLineSchedule();
    // updateTravelLineState();
    // updateTravelLine();
    // updateLinePlan();
    // updateLineSchedule();
})
function lineList() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "lineList"
        },
        dataType: 'json',
        success: function (json) {
            console.log("lineListOk");
            console.log(json);
    
        },
        error: function (data) {
            console.log("lineListNo");
            console.log(data);
        }
    });
    
}

function showLineBase(travelId) {
    var url = './line-base-show.html?id=' + travelId;
    layer_show("基本信息", url, '800', '500');
}
function showLineDetail(travelId) {
    var url = './line-detail-show.html?id=' + travelId;
    layer_show("基本信息", url, '800', '500');
}
function showLinePlans(travelId) {
    var url = './line-plan-list.html?id=' + travelId;
    layer_show("基本信息", url, '1000', '500');
}
function showLineSchedules(travelId) {
    var url = './line-schedule-list.html?id=' + travelId;
    layer_show("基本信息", url, '1000', '500');
}


function insertLine() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "insertLine",
            jsonData:JSON.stringify({
                travelNo:"12345678",
                travelName:"线路无名",
                trafficType:0,
                travelCount:999,
                travelOrderType:0,
                lineType:10,
                travelDay:5,
                travelPrice:188,
                travelChildPrice:94,
                state:1
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLine success");
            console.log(json);

        },
        error: function (data) {
            console.log("insertLine failed ");
            console.log(data);
        }
    });
}

function insertLinePlan() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "insertLinePlan",
            jsonData:JSON.stringify({
                travelId:"666",
                seq:1,
                startTime:"shijian",
                planPrice:888,
                planChildPrice:444,
                gatherTime:"gatherTime",
                gatherPlace:5,
                dismissPlace:5
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLinePlan success");
            console.log(json);

        },
        error: function (data) {
            console.log("insertLinePlan failed ");
            console.log(data);
        }
    });
}

function insertLineSchedule() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "insertLineSchedule",
            jsonData:JSON.stringify({
                travelId:"666",
                seq:1,
                scheDetail:"scheDetail",
                scheStayLevel:5,
                stayHotel:"阿里巴巴大酒店",
                scheMeal:0,
                scheMeal2:2,
                scheMeal3:3,
                scheViews:"景点组合串"
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("insertLineSchedule success");
            console.log(json);

        },
        error: function (data) {
            console.log("insertLineSchedule failed ");
            console.log(data);
        }
    });
}

function updateTravelLineState() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateTravelLineState",
            id: 1,
            state: 0
        },
        dataType: 'json',
        success: function (json) {
            console.log("updateTravelLineState success");
            console.log(json);

        },
        error: function (data) {
            console.log("updateTravelLineState failed ");
            console.log(data);
        }
    });
}
function updateTravelLine() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateTravelLine",
            jsonData:JSON.stringify({
                id:"1",
                travelNo:"12345678",
                travelName:"线路无名啊o",
                trafficType:2,
                travelCount:1000,
                travelOrderType:1,
                lineType:9,
                travelDay:10,
                travelPrice:187,
                travelChildPrice:99,
                state:1
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("updateTravelLine success");
            console.log(json);

        },
        error: function (data) {
            console.log("updateTraveLine failed ");
            console.log(data);
        }
    });
}
function updateLinePlan() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateLinePlan",
            jsonData:JSON.stringify({
                travelId:"666",
                seq:2,
                startTime:"shijian",
                planPrice:777,
                planChildPrice:333,
                gatherTime:"gatherTimeMEME",
                gatherPlace:3,
                dismissPlace:4
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("updateLinePlan success");
            console.log(json);

        },
        error: function (data) {
            console.log("updateLinePlan failed ");
            console.log(data);
        }
    });
}

function updateLineSchedule() {
    $.ajax({
        type: "post",
        url: "/dd_travel_war/lineAdmin",
        data: {
            method: "updateLineSchedule",
            jsonData:JSON.stringify({
                travelId:"666",
                seq:2,
                scheDetail:"scheDetailnow",
                scheStayLevel:5,
                stayHotel:"阿里舅舅大酒店",
                scheMeal:0,
                scheMeal2:2,
                scheMeal3:3,
                scheViews:"景点组合串2"
            })
        },
        dataType: 'json',
        success: function (json) {
            console.log("updateLineSchedule success");
            console.log(json);

        },
        error: function (data) {
            console.log("updateLineSchedule failed");
            console.log(data);
        }
    });
}
