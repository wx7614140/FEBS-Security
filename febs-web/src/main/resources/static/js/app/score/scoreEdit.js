function updateScore() {
    var selected = $("#scoreTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的分数！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个分数！');
        return;
    }
    var scoreId = selected[0].id;
    $.post(ctx + "score/getScore", {"id": scoreId}, function (r) {
        if (r.code === 0) {
            var $form = $('#score-add');
            $form.modal();
            var score = r.msg;
            $("#score-add-modal-title").html('修改分数');
            $form.find("input[name='score']").val(score.name);
            $form.find("input[name='id']").val(score.id);
            $form.find("textarea[name='remarks']").val(score.remarks);
            $("#score-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}