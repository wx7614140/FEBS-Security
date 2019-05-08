function updateSubject() {
    var selected = $("#subjectTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的课程！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个课程！');
        return;
    }
    var subjectId = selected[0].id;
    $.post(ctx + "subject/getSubject", {"id": subjectId}, function (r) {
        if (r.code === 0) {
            var $form = $('#subject-add');
            $form.modal();
            var subject = r.msg;
            $("#subject-add-modal-title").html('修改课程');
            $form.find("input[name='name']").val(subject.name);
            $form.find("input[name='oldName']").val(subject.name);
            $form.find("input[name='id']").val(subject.id);
            $form.find("textarea[name='remarks']").val(subject.remarks);
            $("#subject-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}