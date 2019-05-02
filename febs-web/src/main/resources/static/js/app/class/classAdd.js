var validator;
var $classAddForm = $("#class-add-form");

$(function () {
    validateRule();
    $("#class-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $classAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "class/add", $classAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "class/update", $classAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#class-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#class-add-button").attr("name", "save");
    $("#class-add-modal-title").html('新增班级');
    validator.resetForm();
    $MB.closeAndRestModal("class-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $classAddForm.validate({
        rules: {
            className: {
                required: true,
                minlength: 2,
                maxlength: 10,
                remote: {
                    url: "class/checkClassName",
                    type: "get",
                    dataType: "json",
                    data: {
                        className: function () {
                            return $("input[name='className']").val().trim();
                        },
                        oldClassName: function () {
                            return $("input[name='oldClassName']").val().trim();
                        }/*,
                        gradeId: function () {
                            return $("select[name='gradeId']").val();
                        }*/
                    }
                }
            }
        },
        messages: {
            className: {
                required: icon + "请输入班级名称",
                minlength: icon + "班级名称长度2到10个字符",
                remote: icon + "该班级名称已经存在"
            }
        }
    });
}
