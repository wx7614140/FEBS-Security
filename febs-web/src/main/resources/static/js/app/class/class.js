$(function() {
    initTreeTable();
});

function initTreeTable() {
    var setting = {
        id: 'classId',
        code: 'classId',
        url: ctx + 'class/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            className: $(".class-table-form").find("input[name='className']").val().trim()
        },
        columns: [{
                field: 'selectItem',
                checkbox: true
            },
            {
                title: '编号',
                field: 'classId',
                width: '50px'
            },
            {
                title: '名称',
                field: 'className'
            },
            {
                title: '年级',
                field: 'gradeId'
            },
            {
                title: '创建时间',
                field: 'createTime'
            }
        ]
    };

    $MB.initTreeTable('classTable', setting);
}

function search() {
    initTreeTable();
}

function refresh() {
    $(".class-table-form")[0].reset();
    search();
}

function deleteClasses() {
    var ids = $("#classTable").bootstrapTreeTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("请勾选需要删除的班级！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "确定删除选中班级？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'class/delete', { "ids": ids_arr }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportClassExcel(){
    $.post(ctx+"class/excel",$(".class-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportClassCsv(){
    $.post(ctx+"class/csv",$(".class-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}