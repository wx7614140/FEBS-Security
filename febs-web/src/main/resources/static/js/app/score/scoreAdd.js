var validator;
var $scoreAddForm = $("#score-add-form");

$(function () {
    validateRule();
    $("#score-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $scoreAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "score/add", $scoreAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "score/update", $scoreAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#score-add .btn-close").click(function () {
        closeModal();
    });
    $.get(ctx +"user/studentlist", {},function(data){
            var options;
            for(var index=0;index<data.rows.length;index++){
                options+="<option value='"+data.rows[index].userId+"'" ;
                if($("#stuId").val()==data.rows[index].userId){
                    options+=" selected ";
                }
                options+=">"+data.rows[index].username+"</option>";
            }
            $("#stuId").append(options);
        }
    );
    $.get(ctx +"subject/alllist", {},function(data){
            console.log(data);
            var options;
            for(var index=0;index<data.rows.length;index++){
                options+="<option value='"+data.rows[index].id+"'" ;
                if($("#sudId").val()==data.rows[index].id){
                    options+=" selected ";
                }
                options+=">";
                if(data.rows[index].gradeId==1){
                    options+="一年级";
                }
                if(data.rows[index].gradeId==2){
                    options+="二年级";
                }
                if(data.rows[index].gradeId==3){
                    options+="三年级";
                }
                if(data.rows[index].gradeId==4){
                    options+="四年级";
                }
                if(data.rows[index].gradeId==5){
                    options+="五年级";
                }
                if(data.rows[index].gradeId==6){
                    options+="六年级";
                }
                if(data.rows[index].gradeId==7){
                    options+="初一";
                }
                if(data.rows[index].gradeId==8){
                    options+="初二";
                }
                if(data.rows[index].gradeId==9){
                    options+="初三";
                }
                if(data.rows[index].gradeId==10){
                    options+="高一";
                }
                if(data.rows[index].gradeId==11){
                    options+="高二";
                }
                if(data.rows[index].gradeId==11){
                    options+="高三";
                }

                options+=data.rows[index].name+"</option>";
            }
            $("#subId").append(options);
        }
    );
});

function closeModal() {
    $("#score-add-button").attr("name", "save");
    $("#score-add-modal-title").html('新增分数');
    validator.resetForm();
    $MB.closeAndRestModal("score-add");
}
function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $scoreAddForm.validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 10,
                remote: {
                    url: "score/checkScore",
                    type: "post",
                    dataType: "json",
                    data: {
                        name: function () {
                            return $("input[name='stuId']").val().trim();
                        },
                        oldName: function () {
                            return $("input[name='subId']").val().trim();
                        }
                    }
                }
            }
        },
        messages: {
            name: {
                required: icon + "请输入分数名称",
                minlength: icon + "分数名称长度2到10个字符",
                remote: icon + "该分数名称已经存在"
            }
        }
    });
}
