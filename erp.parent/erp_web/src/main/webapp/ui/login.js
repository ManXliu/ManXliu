/**
 * 
 */
function login(){
		var data=$("#loginform").serializeJSON();
		$.ajax({
			url:'login_checkUser',
			data:data,
			datatype:'json',
			success:function(value){
				var value= eval("(" + value + ")");
				if(value.success){
					location.href='index.html';
				}else{
					$.messager.alert('提示',value.message,'info');
				}
			}
		});
	
}