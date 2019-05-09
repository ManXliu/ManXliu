/**
 * crud操作
 * 
 */

var method="";
$(function(){
	//$.messager.alert("提示",[{"name":"管理员组","tele":"000000","uuid":1},{"name":"总裁办","tele":"111111","uuid":2},{"name":"采购部","tele":"222222","uuid":3},{"name":"销售部","tele":"333333","uuid":4},{"name":"公关部","tele":"444444","uuid":5},{"name":"行政部","tele":"555555","uuid":6},{"name":"人事部","tele":"555555","uuid":7},{"name":"运输部","tele":"444444","uuid":8},{"name":"党办","tele":"555555","uuid":9},{"name":"工会","tele":"555555","uuid":10},{"name":"仓储部","tele":"555555","uuid":11},{"name":"客服部","tele":"555555","uuid":12},{"name":"财务部","tele":"555555","uuid":13},{"name":"运营部","tele":"555555","uuid":14}]);
	$('#grid').datagrid({    
	    url:name+'_getListByPage',    
	    columns:columns,
	    singleSelect: true,
	    pagination:true,
	    toolbar: [{
            iconCls: 'icon-add',
            text: '添加',
            handler: function () {
                method = "add";
                $("#editForm").form('clear');
                $("#editWindow").window('open');
            }
        }],
	});
 	$("#btnSerach").bind('click',function(){
		
		var formdata=$("#serachForm").serializeJSON();
		//alert(JSON.stringify(formdata));
		
		$("#grid").datagrid('load',formdata);
	
	});
	$("#btnSave").bind('click',function(){
		var validate=$("#editForm").form('validate');
		if(!validate){
			return false;
		}
		var formdata=$("#editForm").serializeJSON();
//		alert(JSON.stringify(formdata));
		$.ajax({
			url:name+'_'+method,
			data:formdata,
			dataType:'json',
			type:'post',
			success:function(data){
			//	var data = eval ("(" + data + ")");
                $.messager.alert('提示',data.message);
				if(data.success){
					$("#editWindow").window('close');
					$("#grid").datagrid('reload');
				}
			}	
			
		});	
	});
});


	function dele(uuid){
	  		$.messager.confirm("提示","确认要删除吗?",function(flag){
	 			if(flag){
	 				$.ajax({
	 					url:name+'_delete?id='+uuid,
	 					dataType:'json',
	 					success:function(data){
	 						var data = eval ("(" + data + ")");
	 					
	 						$.messager.alert('提示',data.message);
	 						if(data.success){
	 							$("#grid").datagrid('reload');
	 						}
	 					}
	 				
	 					
	 				});
	 			}
	 		}); 	
	 		
	 	}
 	function edit(uuid){
  		$("#editWindow").window("open");
		$("#editForm").form("clear");
 		$("#editForm").form('load',name+'_get?id='+uuid);
 		method="update";  
 	} 