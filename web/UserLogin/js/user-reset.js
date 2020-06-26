$(document).ready(function () {
    $('#reset-form').bootstrapValidator({
        message: "This value is not valid",
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱地址不能为空'
                    },
                    emailAddress: {
                        message: '邮箱格式错误'
                    }
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
        }
    });

    $('#resetPassword').click(function () {
        $('#reset-form').bootstrapValidator('validate');
        if ($('#reset-form').data('bootstrapValidator').isValid()) {
            $.ajax({
                type: 'post',
                dataType: 'json',
                url: 'http://localhost:8080/reset',
                data: $('#reset-form').serialize(),
                async: 'false',
                success: function (data) {
                    if (data.code == "200") {
                        alert(data.messages)
                        window.location.href = 'http://localhost:8080/'
                    } else {
                        alert(data.messages)
                    }
                },
                error: function () {
                    alert("服务器出错")
                }
            })
        } else {
            alert("请检查表单未填项及表单数据是否有误");
        }
    });
});

function changeNumber(obj) {
    obj.src = "http://localhost:8080/verifyNumber?" + Math.random();
}