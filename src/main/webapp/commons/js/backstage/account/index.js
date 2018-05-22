$(function(){
    var index = index || {
        //初始化表格
        tableInit: function(){
            $('#table').dataTable();
        },

        //初始化
        init: function(){
            index.tableInit();
        }
    };

    index.init();
});