$(function () {
    // getAllUser();
})

function getAllUser() {
    $.getJSON('http://localhost:8080/dd_travel_war/userAdmin',{
        method:'allUser'
    },function (json) {
        console.log(json);
        if(json.flag === 1) {
            $(json.data).each(function (i, item) {
                console.log(item);  
                if (item.type == 0) {
                    var typeHtml = '<td class="text-c c-333">普通用户</td>';
                } else {
                    var typeHtml = '<td class="text-c c-primary">管理员</td>';
                }
                if (item.state == 0) {
                    var stateHtml = '<td class="td-status"><span class="label label-danger radius">已停用</span></td>';
                    var stateManageHtml = '<a style="text-decoration:none" onClick="member_start(this,'+item.id+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>';
                } else {
                    var stateHtml='<td class="td-status"><span class="label label-success radius">正常</span></td>'
                    var stateManageHtml = '<a style="text-decoration:none" onClick="member_stop(this,'+item.id+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>';
                    
                }
                var ins = '';
                ins +=
                    '<tr class="text-c">' +
                    // 左边框
                    '<td><input type="checkbox" value="1" name=""></td>' +
                    //id
                    '<td>'+item.id+'</td>' +
                    //账号
                    '<td><u style="cursor:pointer" class="text-primary" onclick="member_show(\'张三\',\'member-show.html\',\'10001\',\'360\',\'400\')">'
                    +item.user_account+
                    '</u></td>' +
                    //姓名
                    '<td>'+item.user_name+'</td>' +
                    '<td>'+item.mobile_phone+'</td>'+
                    '<td>'+item.email+'</td>' +
                    typeHtml +
                    stateHtml+
                    '<td class="td-manage">' +
                    stateManageHtml+
                    '<a title="编辑" href="javascript:;" onclick="member_edit(\'编辑\',\'member-add.html\',\'"+item.id+"\',\'\',\'510\')" class="ml-5" style="text-decoration:none">' +
                    '<i class="Hui-iconfont">&#xe6df;</i></a>' +
                    '</td>' +
                    '</tr>';
                $('#user-list').append(ins);

            });
        }
    });
}