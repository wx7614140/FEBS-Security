var validator;
var $subjectAddForm = $("#subject-add-form");

$(function () {
    validateRule();
    $("#subject-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $subjectAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "subject/add", $subjectAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "subject/update", $subjectAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#subject-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#subject-add-button").attr("name", "save");
    $("#subject-add-modal-title").html('新增课程');
    validator.resetForm();
    $MB.closeAndRestModal("subject-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $subjectAddForm.validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 10,
                remote: {
                    url: "subject/checkSubjectName",
                    type: "post",
                    dataType: "json",
                    data: {
                        name: function () {
                            return $("input[name='name']").val().trim();
                        },
                        oldName: function () {
                            return $("input[name='oldName']").val().trim();
                        }/*,
                        gradeId: function () {
                            return $("select[name='gradeId']").val();
                        }*/
                    }
                }
            }
        },
        messages: {
            name: {
                required: icon + "请输入课程名称",
                minlength: icon + "课程名称长度2到10个字符",
                remote: icon + "该课程名称已经存在"
            }
        }
    });
}
