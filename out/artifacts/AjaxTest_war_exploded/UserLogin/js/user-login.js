var verfity = "";
window.onload = function () {
    $.ajax({
        url: 'https://www.mxnzp.com/api/verifycode/code?len=6&type=0&app_id=sngmqkqxuln6jkik&app_secret=RVV0aDBJbnpIa2k3REdVQmNuaWI2dz09',//地址
        dataType: 'json',
        type: 'GET',
        success: function (data) {
            $('#img').attr("src", data.data.verifyCodeImgUrl)
            verfity = data.data.verifyCode;
        }
    })
};

function changeVerification() {
    console.log(verfity)
    $.ajax({
        url: 'https://www.mxnzp.com/api/verifycode/code?len=6&type=0&app_id=sngmqkqxuln6jkik&app_secret=RVV0aDBJbnpIa2k3REdVQmNuaWI2dz09',//地址
        dataType: 'json',
        type: 'GET',
        success: function (data) {
            $('#img').attr("src", data.data.verifyCodeImgUrl);
            verfity = data.data.verifyCode;
        },
    })
}


$(document).ready(function () {
    $('#login-form').bootstrapValidator({
        message: "This value is not valid",
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名校验失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 6,
                        message: '用户名长度必须在3到6之间'
                    },
                    regexp: {
                        regexp: /[\u4e00-\u9fa5]/,
                        message: '用户名只能为汉字'
                    },
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: "密码不能为空"
                    },
                    stringLength: {
                        min: 8,
                        max: 40,
                        message: '密码长度需在8到40之间'
                    },
                    regexp: {
                        regexp: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$/,
                        message: '密码必须包含大写字母小写字母和数字'
                    }
                }
            },
            yanzhengma: {
                validators: {
                    callback: {
                        message: '验证码错误',
                        callback: function (value, validator) {
                            return value.toLowerCase() === verfity.toLowerCase();
                        }
                    }
                }
            }
        }
    });
    $('#signIn').click(function () {
        $('#login-form').bootstrapValidator('validate');
        if ($('#login-form').data('bootstrapValidator').isValid()) {
            $.ajax({
                type: 'post',
                dataType: 'json',
                url: 'http://localhost:8080/login',
                data: $('#login-form').serialize(),
                async: 'false',
                success: function (data) {
                    if (data.code == "200") {
                        alert(data.messages)
                    } else {
                        alert(data.messages)
                    }
                },
                error: function () {
                    alert("服务器出错")
                }
            })
        } else {
            alert("请检查表单未填项及表单数据是否有误")
        }
    })
});

