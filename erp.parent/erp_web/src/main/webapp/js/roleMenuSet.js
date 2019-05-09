$(function () {
   $('#grid').datagrid({
       url:'role_getList',
       singleSelect:true,
       columns:[[
           {field:'uuid',title:'角色编号',width:100},
           {field:'name',title:'角色名称',width:100}
       ]],
       onClickRow:function (rowIndex,rowdata) {
           $('#tree').tree({
               url:'role_readRoleMenus?id='+rowdata.uuid,
               checkbox:true,
               animate:true
           });
       }


   });
   $("#btnSave").bind('click',function () {
      var nodes=$('#tree').tree('getChecked');
      var checkStr=new Array();
      $.each(nodes,function (i,node) {
         checkStr.push(node.id);
      });
      checkStr=checkStr.join(',');
      var formdata={};
      formdata.id=$("#grid").datagrid('getSelected').uuid;
      formdata.checkStr=checkStr;
      $.ajax({
          url:'role_updateRoleMenus',
          data:formdata,
          dataType:'json',
          type:'post',
          success:function (rtn) {
              $.messager.alert("提示",rtn.message,'info');
          }




      });
   });

});