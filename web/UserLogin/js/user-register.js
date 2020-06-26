$(document).ready(function () {
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));
    $('#register-form').bootstrapValidator({
        message: "This value is not valid",
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 6,
                        message: '用户名长度必须在3到6之间'
                    },
                    regexp: {
                        regexp: /[\u4e00-\u9fa5]/,
                        message: '用户名只能为汉字'
                    },
                }
            },
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
            age: {
                validators: {
                    notEmpty: {
                        message: '年龄不能为空'
                    },
                    between: {
                        min: 18,
                        max: 120,
                        message: '年龄必须在18到120之间'
                    }
                },
            },
            telephone: {
                validators: {
                    notEmpty: {
                        message: '电话不能为空'
                    },
                    regexp: {
                        regexp: /^1[3456789]\d{9}$/,
                        message: '电话号码格式错误'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    }
                }
            },
            captcha: {
                validators: {
                    callback: {
                        message: '验证失败',
                        callback: function (value, validator) {
                            var items = $('#captchaOperation').html().split(' '),
                                sum = parseInt(items[0]) + parseInt(items[2]);
                            return value == sum
                        }
                    }
                }
            }
        }
    });

    $('#register').click(function () {
        $('#register-form').bootstrapValidator('validate');
        if ($('#register-form').data('bootstrapValidator').isValid()) {
            $.ajax({
                type: 'post',
                dataType: 'json',
                url: 'http://localhost:8080/register',
                data: $('#register-form').serialize(),
                async: false,
                success: function (data) {
                    if (data.name === '200') {
                        alert(data.password);
                        window.location.href = 'http://localhost:8080/index.html';
                    } else {
                        alert(data.password);
                        window.location.href = "http://localhost:8080/UserLogin/register.html"
                    }
                },
                error: function () {
                    alert("服务器错误")
                }
            })
        } else {
            alert("请检查表单未填项及表单数据是否有误")
        }
    })
});


