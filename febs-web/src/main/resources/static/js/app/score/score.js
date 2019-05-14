$(function() {
    var $scoreTableForm=$(".score-table-form")
    var setting = {
        url: ctx + 'score/list',
        method:"get",
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
                username: $scoreTableForm.find("input[name='username']").val().trim(),
                sortColumn: params.sort,      //排序列名
                order: params.order //排位命令（desc，asc）
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
                title: '学生',
                field: 'username',
                sortable: true
            },
            {
                title: '课程',
                field: 'subname',
                sortable: true
            },

            {   title: '分数',
                field: 'score',
                sortable: true
            },
            {
                title: '备注',
                field: 'remarks',
                sortable: true
            },
            {
                title: '创建时间',
                field: 'createDate',
                sortable: true
            },
            {
                title: '创建人',
                field: 'creator',
                sortable: true
            },{
                title: '修改时间',
                field: 'updateDate',
                sortable: true
            },
            {
                title: '修改人',
                field: 'updator',
                sortable: true
            }
        ]
    };

    $MB.initTable('scoreTable', setting);
});

function search() {
    $MB.refreshTable('scoreTable');
}

function refresh() {
    $(".score-table-form")[0].reset();
    search();
}

function deleteScores() {
    var ids = $("#scoreTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("请勾选需要删除的分数！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "确定删除选中分数？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'score/delete', { "ids": ids_arr }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportScoreExcel(){
    $.post(ctx+"score/excel",$(".score-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportScoreCsv(){
    $.post(ctx+"score/csv",$(".score-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}