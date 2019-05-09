$(function () {
    $("#grid").datagrid({
           columns:columns,
           url:'storeoper_getListByPage',
           pagination:true,
           singleSelect:true
    });
    $("#btnSearch").bind('click',function () {
        var formData=$('#searchForm').serializeJSON();
        $("#grid").datagrid('load',formData);
        
    })

});