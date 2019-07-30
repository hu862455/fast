var createMember = function () {

    function changeMoney(limit) {
        $("#chargeAmount").val(limit);
    }

    function initPage() {
        // 获取所有的会员等级信息
        /*$.ajax({
            url: contentPath + "memberLevel/allMemberLevel",
            method: 'POST',
            async: false,
            cache: false,
            success: function (data) {
                var data = JSON.parse(data);
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += "<option value =" + data[i].id + " onclick=\"createMember.changeMoney('" + data[i].limit + "')\">" + data[i].levelName + "</option>"
                }
                $("#memberLevel").html(html);
            }
        });*/
        $("#memberLevel>option").each(function () {
            $(this).click(function () {
                var limit = this.attr("limit");
                $("#chargeAmount").val(limit);
            });
        });

        $("#form").steps({
            bodyTag: "fieldset",
            onStepChanging: function (event, currentIndex, newIndex) {
                // Always allow going backward even if the current step contains invalid fields!
                if (currentIndex > newIndex) {
                    return true;
                }

                // Forbid suppressing "Warning" step if the user is to young
                if (newIndex === 3 && Number($("#age").val()) < 18) {
                    return false;
                }

                var form = $(this);

                // Clean up if user went backward before
                if (currentIndex < newIndex) {
                    // To remove error styles
                    $(".body:eq(" + newIndex + ") label.error", form).remove();
                    $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
                }

                // Disable validation on fields that are disabled or hidden.
                form.validate().settings.ignore = ":disabled,:hidden";

                // Start validation; 如果错误，则阻止向前
                return form.valid();
            },
            onStepChanged: function (event, currentIndex, priorIndex) {
                // Suppress (skip) "Warning" step if the user is old enough.
                if (currentIndex === 2 && Number($("#age").val()) >= 18) {
                    $(this).steps("下一个");
                }

                // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
                if (currentIndex === 2 && priorIndex === 3) {
                    $(this).steps("上一个");
                }
            },
            onFinishing: function (event, currentIndex) {
                var form = $(this);

                // Disable validation on fields that are disabled.
                // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
                form.validate().settings.ignore = ":disabled";

                // Start validation; Prevent form submission if false
                return form.valid();
            },
            onFinished: function (event, currentIndex) {
                var form = $(this);

                // Submit form input

                var userName = $("#userName").val();
                var password = $("#password").val();
                var telephone = $("#telephone").val();
                var sex = $("#sex").val();
                var email = $("#email").val();
                var address = $("#address").val();
                var memberLevel = $("#memberLevel").val();
                var chargeAmount = $("#chargeAmount").val();

                $.ajax({
                    url: contentPath + "member/createMember",
                    data: {
                        name: userName,
                        memberLevelId: memberLevel,
                        address: address,
                        sex: sex,
                        telephone: telephone,
                        account: chargeAmount,
                        password: password
                    },
                    method: 'POST',
                    async: false,
                    cache: false,
                    success: function (data) {
                        var code = data.code;
                        if (code === 1) {
                            swal("失败", "您的会员信息创建失败！", "error");
                        } else {
                            swal("成功", "您的会员信息创建成功！", "success");
                        }
                    }

                })
            }
        }).validate({
            errorPlacement: function (error, element) {
                element.before(error);
            },
            rules: {
                confirm: {
                    equalTo: "#password"
                }
            }
        });
    }

    return {
        initPage: initPage,
        changeMoney: changeMoney
    }
}();


$().ready(function () {
    createMember.initPage();
});
