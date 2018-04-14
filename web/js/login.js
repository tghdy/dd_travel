function login() {
    // var account = $('#account').val();
    // var password = $('#password').val();
    // var sex = $('#sex').val();
    // var name = $('#name').val();
    // var phone = $('#phone').val();
    var account = 'tusuzer';
    var password = 'mmmm';
    var sex = 1;
    var name = "wjsw";
    var phone = '12312';


    if (account === '' || password === '') {
        alert('账号和密码不能为空');
        return;
    }

    $.ajax({
        type: "post",
        url: "/dd_travel_war/user",
        data: {
            method: "register",
            jsonData:JSON.stringify({
                userAccount: account,
                password: password,
                sex:sex,
                userName:name,
                mobilePhone:phone
            })
        },
        dataType: 'json',
        success: function (json) {
            // alert('success');
            console.log(json);
            // if (json.status == 1) {
            //     window.location.href="line-list.html"
            // else {
            //     alert(json.msg);
            // }

        },
        error: function (data) {
            // alert('error');
            console.log(data);
        }
    });

}
