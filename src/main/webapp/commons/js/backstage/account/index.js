$(function(){
    var index = index || {
        //初始化表格
        tableInit: function(){
            $('#table').bootstrapTable({
                url               : '/api/1.0/user/page',
                method         : 'GET',
                dataType       : 'json',
                sidePagination : 'server',
                idField           : 'id',
                uniqueId         : 'id',
                striped           : true,
                cached           : false,
                pagination       : true,
                pageNumber    : 1,
                pageSize         : 20,
                pageList          : [20, 50, 100, 200],
                sortable          : false,
                search            : false,
                showFooter     : true,
                columns: [{
                    field   : 'id',
                    visible : false
                },{
                    field    : 'name',
                    title     : '用户名',
                    align    : 'center'
                },{
                    field     : 'nickname',
                    title     : '用户昵称',
                    align    : 'center'
                }]
            });
        },

        //初始化
        init: function(){
            index.tableInit();
        }
    };

    index.init();
});