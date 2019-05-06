function updateClass() {
    var selected = $("#classTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的班级！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个班级！');
        return;
    }
    var classId = selected[0].id;
    $.post(ctx + "class/getClass", {"classId": classId}, function (r) {
        if (r.code === 0) {
            var $form = $('#class-add');
            var $classTree = $('#classTree');
            $form.modal();
            var clazz = r.msg;
            $("#class-add-modal-title").html('修改班级');
            $form.find("input[name='className']").val(clazz.className);
            $form.find("input[name='oldClassName']").val(clazz.className);
            $form.find("input[name='classId']").val(clazz.classId);
            $classTree.jstree('disable_node', clazz.classId);
            $("#class-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}