$(function() {
    var $classTableForm=$(".class-table-form")
    var setting = {
        url: ctx + 'class/list',
        pageNum: 1,
        pageSize: 10,
        pagination: true,
        sortable: true,
        sortOrder: "asc",
        sidePagination: "server",
        uniqueId: "classId",
        queryParams: function (params) {
            return {
                pageSize: params.limit||10,
                pageNum: params.offset / params.limit + 1,
                className: $classTableForm.find("input[name='className']").val().trim(),
                oderCloumn: params.sort,      //排序列名
                asc: params.order=='asc' //排位命令（desc，asc）
                /* gradeId: $classTableForm.find("select[name='gradeId']").val(),
                 status: $classTableForm.find("select[name='status']").val()*/
            };
        },
        columns: [{
            checkbox: true
        },
            {
                title: '编号',
                field: 'classId',
                width: '50px',
                sortable: true
            },
            {
                title: '名称',
                field: 'className',
                sortable: true
            },
            {
                title: '年级',
                field: 'gradeId',
                sortable: true
            },
            {
                title: '创建时间',
                field: 'createTime',
                sortable: true
            }
        ]
    };

    $MB.initTable('classTable', setting);
});

function search() {
    $MB.refreshTable('classTable');
}

function refresh() {
    $(".class-table-form")[0].reset();
    search();
}

function deleteClasses() {
    var ids = $("#classTable").bootstrapTable("getSelections");
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