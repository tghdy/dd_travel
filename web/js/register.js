function register() {
    var account = $('#account').val();
    var password = $('#password').val();
    var passwordRep = $('#passwordRep').val();
    var name = $('#name').val();
    var phone = $('#phone').val();
    account = 'a123';
    password = 'asdasd';
    passwordRep = password;
    name = 'tusuzer';
    phone = '1888888888';

    if (passwordRep != password) {
        alert('两次输入不一致');
        return;
    }
    if (account == '' || password == '' || phone == '' || name == '') {
        alert('请填写完整信息');
        return;
    }

    $.ajax({
        url: "/dd_travel_war/TravelUser/register",
        method: "post",
        data: JSON.stringify({
            userAccount: account,
            password: password,
            userName: name,
            mobilePhone: phone
        }),
        dataType: 'json',
        success: function (json) {
            console.log(json);
            if (json.status == 1) {
                alert('注册成功')
            }

        },
        error: function (data) {
            console.log(data);
        }
    });

}

$(function () {
    register();
})