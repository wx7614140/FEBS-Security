var validator;
var $deptAddForm = $("#dept-add-form");

$(function () {
    validateRule();
    createDeptTree();

    $("#dept-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getDept();
        validator = $deptAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "dept/add", $deptAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "dept/update", $deptAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#dept-add .btn-close").click(function () {
        closeModal();
    });
    $('#deptTree').on("activate_node.jstree",function(){
        $("input[name='deptName']").removeData("previousValue");
        $("input[name='deptName']")[0].focus();
    })
});

function closeModal() {
    $("#dept-add-button").attr("name", "save");
    $("#dept-add-modal-title").html('新增部门');
    validator.resetForm();
    $MB.closeAndRestModal("dept-add");
    $MB.refreshJsTree("deptTree", createDeptTree());
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $deptAddForm.validate({
        rules: {
            deptName: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "dept/checkDeptName",
                    type: "get",
                    dataType: "json",
                    cache :false,
                    data: {
                        deptName: function () {
                            return $("input[name='deptName']").val().trim();
                        },
                        oldDeptName: function () {
                            return $("input[name='oldDeptName']").val().trim();
                        },
                        parentId: function () {
                            getDept();
                            return $("input[name='parentId']").val().trim();
                        }
                    }
                }
            }
        },
        messages: {
            deptName: {
                required: icon + "请输入部门名称",
                minlength: icon + "部门名称长度3到10个字符",
                remote: icon + "该部门名称已经存在"
            }
        }
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getDept() {
    var ref = $('#deptTree').jstree(true);
    var deptIds = ref.get_checked();
    $("[name='parentId']").val(deptIds[0]);
}