$(function() {
    var $subjectTableForm=$(".subject-table-form")
    var setting = {
        url: ctx + 'subject/list',
        method:"post",
        pageNum: 1,
        pageSize: 10,
        pagination: true,
        sortable: true,
        sortOrder: "asc",
        sidePagination: "server",
        uniqueId: "id",
        queryParams: function (params) {
            return {
                alias:'u',
                pageSize: params.limit||10,
                pageNum: params.offset / params.limit + 1,
                name: $subjectTableForm.find("input[name='name']").val().trim(),
                sortColumn: params.sort,      //排序列名
                order: params.order //排位命令（desc，asc）
                /* gradeId: $subjectTableForm.find("select[name='gradeId']").val(),
                 status: $subjectTableForm.find("select[name='status']").val()*/
            };
        },
        columns: [{
            checkbox: true
        },
            {
                title: '编号',
                field: 'id',
                width: '50px',
                sortable: true
            },
            {
                title: '名称',
                field: 'name',
                sortable: true
            },
            {
                title: '备注',
                field: 'remarks',
                sortable: true
            },
            {
                title: '年级',
                field: 'gradeId',
                sortable: true
            },
            {
                title: '创建时间',
                field: 'createDate',
                sortable: true
            },
            {
                title: '创建人',
                field: 'createBy',
                sortable: true
            },{
                title: '修改时间',
                field: 'updateDate',
                sortable: true
            },
            {
                title: '修改人',
                field: 'updateBy',
                sortable: true
            }
        ]
    };

    $MB.initTable('subjectTable', setting);
});

function search() {
    $MB.refreshTable('subjectTable');
}

function refresh() {
    $(".subject-table-form")[0].reset();
    search();
}

function deleteClasses() {
    var ids = $("#subjectTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("请勾选需要删除的课程！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "确定删除选中课程？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'subject/delete', { "ids": ids_arr }, function(r) {
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
    $.post(ctx+"subject/excel",$(".subject-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportClassCsv(){
    $.post(ctx+"subject/csv",$(".subject-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}