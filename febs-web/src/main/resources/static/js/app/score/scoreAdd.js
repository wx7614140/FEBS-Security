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
    $("#stuId").select2({
        language: "zh-CN",
        inputMessage: "请选择学生",
        ajax: {
            url: "user/list",
                dataType : 'json',
            delay: 250,
            data: function(params){
                if(!params.page){
                    params.page=1;
                }
                return {
                    username : params.term, // 搜索框内输入的内容，传递到Java后端的parameter为username
                    pageNum : params.page,// 第几页，分页哦
                    pageSize : 5// 每页显示多少行
                };
            },
            // 分页
            processResults : function(data, params) {
                var dataRows=[]
                for(var i=0;i<data.rows.length;i++){
                   var dataObj={};
                    dataObj.text=data.rows[i].username;
                    dataObj.id=data.rows[i].userId;
                    dataRows.push(dataObj);
                }
                params.page = params.page || 1;
                return {
                    results : dataRows,// 后台返回的数据集
                    pagination : {
                        more : (params.page*5) < data.total// 总页数为10，那么1-9页的时候都可以下拉刷新
                    }
                };
            },
            cache : false
        },
        escapeMarkup : function(markup) {
            return markup;
        }
    });
    $("#subId").select2({
        language: "zh-CN",
        inputMessage: "请选择课程",
        ajax: {
            url: "subject/list",
            dataType : 'json',
            delay: 250,
            data: function(params){
                return {
                    name : params.term, // 搜索框内输入的内容，传递到Java后端的parameter为username
                    pageNum : params.page,// 第几页，分页哦
                    pageSize : 5// 每页显示多少行
                };
            },
            // 分页
            processResults : function(data, params) {
                var dataRows=[]
                for(var i=0;i<data.rows.length;i++){
                    var dataObj={};
                    dataObj.text=data.rows[i].name;
                    dataObj.id=data.rows[i].id;
                    dataRows.push(dataObj);
                }
                params.page = params.page || 1;
                return {
                    results : dataRows,// 后台返回的数据集
                    pagination : {
                        more : (params.page*5) < data.total// 总页数为10，那么1-9页的时候都可以下拉刷新
                    }
                };
            },
            cache : false
        },
        escapeMarkup : function(markup) {
            return markup;
        }
    });
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
