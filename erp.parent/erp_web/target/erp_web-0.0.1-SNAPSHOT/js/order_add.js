var existEditIndex=-1;
$(function () {
    $('#ordersgrid').datagrid({
        singleSelect:true,
        columns:[[
            {field:'goodsuuid',title:'商品编号',width:100,editor:{type:'numberbox',options:{disabled:true}}},
            {field:'goodsname',title:'商品名称',width:100,editor:{type:'combobox',options:{url:'goods_getList',valueField:'name',textField:'name',
            onSelect:function (value) {
                var priceEdt=$("#ordersgrid").datagrid("getEditor",{index:existEditIndex,field:'price'});
                if(Request['type']*1==1) {
                    $(priceEdt.target).val(value.inprice);
                }
                if (Request['type']*1==2){
                    $(priceEdt.target).val(value.outprice);
                }
                var goodsuuidEdt=$("#ordersgrid").datagrid("getEditor",{index:existEditIndex,field:'goodsuuid'});
                $(goodsuuidEdt.target).val(value.uuid);
                cal();
                sum();
            }

            }}},
            {field:'price',title:'商品价格',width:100,editor:{type:'numberbox',options:{precision:2,disabled:true}}},
            {field:'num',title:'商品数量',width:100,editor:{type:'numberbox'}},
            {field:'money',title:'金额',width:100,editor:{type:'numberbox',options:{precision:2,}}},
            {field:'-',title:'操作',width:100,formatter:function (value,row,index) {
                    return "<a href='javascript:void(0);' onclick='deleteRow("+index+")'>删除</a>"
                }}
        ]],
        toolbar:[{
            iconCls:'icon-add',
            text:'增加',
            handler:function () {
                $("#ordersgrid").datagrid('appendRow',{num:0,money:0});
                $('#ordersgrid').datagrid('endEdit',existEditIndex);
                existEditIndex=$('#ordersgrid').datagrid('getRows').length-1;
                $('#ordersgrid').datagrid('beginEdit',existEditIndex);
                bindGridEvent();
                cal();
                sum();

            }},{
            iconCls:'icon-save',
            text:'提交',
            handler:function () {
                $("#ordersgrid").datagrid('endEdit',existEditIndex);
                var formData=$("#orderForm").serializeJSON();
                formData['json']=JSON.stringify($('#ordersgrid').datagrid('getRows'));
                $.ajax({
                    data:formData,
                    url:'orders_add?t.type='+Request['type'],
                    dataType:'json',
                    type:'post',
                    success:function(rtn){
                        $.messager.alert('提示',rtn.message,'info',function(){
                            if(rtn.success){
                                //清空供应商
                                $('#suppliers').combogrid('clear');
                                //清空表格
                                $('#ordersgrid').datagrid('loadData',{total:0, rows:[]});

                                $("#addOrdersDlg").dialog("close");

                                $('#grid').datagrid('reload');
                            }
                        });
                    }



                });
            }

        }],
        onClickRow:function (rowIndex,rowData) {
            $('#ordersgrid').datagrid('endEdit', existEditIndex);
            existEditIndex = rowIndex;
            $('#ordersgrid').datagrid('beginEdit', existEditIndex);
            bindGridEvent();
            cal();
            sum();


    }

    });
    $('#suppliers').combogrid({
        panelWidth:600,

        idField:'uuid',
        textField:'name',
        url:'supplier_getList?t1.type=1',
        columns:[[
            {field:'uuid',title:'编号',width:100},
            {field:'name',title:'名称',width:100},
            {field:'address',title:'联系地址',width:100},
            {field:'contact',title:'联系人',width:100},
            {field:'tele',title:'联系电话',width:100},
            {field:'email',title:'邮件地址',width:100}]]
    });

});
function cal() {
    var numEdt=$("#ordersgrid").datagrid('getEditor',{index:existEditIndex,field:'num'});
    var num=$(numEdt.target).val();
    var priceEdt=$("#ordersgrid").datagrid('getEditor',{index:existEditIndex,field:'price'});
    var price=$(priceEdt.target).val();
    var money=(num*price).toFixed(2);
    var moneyEdt=$('#ordersgrid').datagrid('getEditor',{index:existEditIndex,field:'money'});
    $(moneyEdt.target).val(money);
    //关键 设置money的值
    $("#ordersgrid").datagrid('getRows')[existEditIndex].money=money;

}
function bindGridEvent() {
    var priceEdt=$("#ordersgrid").datagrid('getEditor',{index:existEditIndex,field:'price'});
    $(priceEdt.target).bind('keyup',function () {
        cal();
        sum();
    });
    var numEdt=$("#ordersgrid").datagrid('getEditor',{index:existEditIndex,field:'num'});
    $(numEdt.target).bind('keyup',function () {
       cal();
       sum();
    });
}
function deleteRow(index) {
    $("#ordersgrid").datagrid('endEdit',existEditIndex);
    $("#ordersgrid").datagrid('deleteRow',index);
    var data=$("#ordersgrid").datagrid('getData');
    $("#ordersgrid").datagrid('loadData',data);
    sum();
}
function sum() {
    var rows=$("#ordersgrid").datagrid('getRows');
    var total=0;
    for (var i=0;i<rows.length;i++){
        total+=parseFloat(rows[i].money);
    }
    $('#sum').html(total.toFixed(2));

}
function save() {
    var formdata=$('#orderForm').serialize();
    alert(formdata);
}
