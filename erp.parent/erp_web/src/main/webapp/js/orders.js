$(function () {
    var url='orders_getListByPage';
    var inoutTitle='';
    var btnText="";
    if(Request['oper']=='orders'){
        if(Request['type']*1 == 1){
            url +="?t1.type=1";
            document.title="采购订单查询";
        }
        if(Request['type']*1 == 2){
            url +="?t1.type=2";
            document.title="销售订单查询";
        }
    }
    if(Request['oper']=='doCheck'){
        url+='?t1.type=1&t1.state=0';
        document.title="采购订单审核";
    }
    if(Request['oper']=='doStart'){
        url+='?t1.type=1&t1.state=1';
        document.title="采购订单确认";

    }
    if (Request['oper']=='doInStore'){
        url+='?t1.type=1&t1.state=2';
        inoutTitle='采购入库';
        document.title='采购订单入库';
    }
    if (Request['oper']=='myOrders'){
        if(Request['type']*1 == 1){
            url = "orders_myListByPage?t1.type=1";
            document.title="我的采购订单";
            btnText = "采购申请";
            //显示供应商
            $('#orderssupplier').html('供应商');
        }
        if(Request['type']*1 == 2){
            url = "orders_myListByPage?t1.type=2&t1.state=0";
            document.title="我的销售订单";
            btnText = "销售订单录入";
            //显示客户
            $('#orderssupplier').html('客户');
        }
    }
    if (Request['oper']=='doOutStore'){
        url+='?t1.type=2&t1.state=0';
        inoutTitle="出库";
        document.title="销售订单出库"
    }
    $('#grid').datagrid({
        url: url,
        columns : getColumns(),
        singleSelect: true,
        pagination: true,
        fitcolumns:true,
        onDblClickRow:function (rowIndex,rowData) {
           $("#uuid").html(rowData.uuid);
           $("#supplier").html(rowData.supplierName);
           $("#creater").html(rowData.createrName);
           $("#starter").html(rowData.starterName);
           $("#ender").html(rowData.enderName);
           $("#checker").html(rowData.checkerName);
           $("#createtime").html(new Date(rowData.createtime).Format('yyyy-MM-dd'));
           $("#checktime").html(new Date(rowData.checktime).Format('yyyy-MM-dd'));
           $("#endtime").html(new Date(rowData.endtime).Format('yyyy-MM-dd'));
           $("#starttime").html(new Date(rowData.starttime).Format('yyyy-MM-dd'));
           $("#state").html(getState(rowData.state));
           //打开窗口
           $("#orderWindow").dialog('open');
           //datagrid 中的loadData 方法传入orderDetails数据
           $("#itemgrid").datagrid("loadData",rowData.orderDetails);
        }

    });
   $('#itemgrid').datagrid({
        columns:[[
            {field:'uuid',title:'编号',width:100},
            {field:'goodsuuid',title:'商品编号',width:100},
            {field:'goodsname',title:'商品名称',width:100},
            {field:'price',title:'商品价格',width:100},
            {field:'num',title:'商品数量',width:100},
            {field:'money',title:'金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getDetailState}
        ]],
        fitcolumns:true,
        singleSelect:true
   });
    if(Request['oper']=='doCheck'){
        $("#orderWindow").dialog({
            toolbar:[{
                text:'审核',
                iconCls:'icon-search',
                handler:function(){
                    doCheck();
                }
            }]

        });
    }
    if (Request['oper']=='doStart'){
        $("#orderWindow").dialog({
            toolbar:[{
                text:'确认',
                iconCls:'icon-search',
                handler:function(){
                    doStart();
                }
            }]

        });

    }
    if(Request['oper']=='doInStore'||Request['oper']=='doOutStore'){
        $('#itemgrid').datagrid({
            onDblClickRow:function (rowIndex,rowData) {
                $("#goodsname").html(rowData.goodsname);
                $("#goodsuuid").html(rowData.goodsuuid);
                $("#goodsnum").html(rowData.num);
                //订单编号
                $("#itemuuid").val(rowData.uuid);
                $("#itemDlg").dialog('open');

            }
        });
    }

    if(Request['oper'] == 'myOrders') {
        $('#grid').datagrid({
            toolbar: [{
                text: btnText,
                iconCls: 'icon-add',
                handler: function () {
                    $('#addOrdersDlg').dialog('open');
                }
            }]

        });
    }
    $("#itemDlg").dialog({
        title:inoutTitle,
        height:200,
        width:300,
        modal:true,
        closed:true,
        buttons:[{
            text:inoutTitle,
            iconCls:'icon-save',
            handler:function () {
                doInOutStore();
            }
        }]


    });
    $("#addOrdersDlg").dialog({
        title:'增加订单',
        height:400,
        width:650,
        modal:true,
        closed:true
    });
});
function getDetailState(value) {
    if (Request['type']*1==1){
        switch (value*1){
            case 0: return "未入库";
            case 1: return "已入库";
            default: return  "";
        }
    }
    if (Request['type']*1==2){
        switch (value*1){
            case 0: return "未出库";
            case 1: return "已出库";
            default: return  "";
        }
    }
}
function getState(value) {
    if (Request['type']*1==1) {
        switch (value * 1) {
            case 0:
                return "未审核";
            case 1:
                return "已审核";
            case 2:
                return "已确认";
            case 3:
                return "已入库";
            default:
                return "";
        }
    }
    if (Request['type']*1==2){
        switch (value*1){
            case 0:
                return "未出库";
            case 1:
                return "已出库";
            default:
                return "";
        }
    }
}
function doCheck() {
    $.messager.confirm("确认","确认要审核吗?",function (yes) {
        if (yes){
            $.ajax({
                url:'orders_doCheck?id='+$("#uuid").html(),
                type:'post',
                dataType:'json',
                success:function (rtn) {
                    $.messager.alert('提示',rtn.message,'info',function (){
                        if (rtn.success){
                            $("#orderWindow").dialog('close');
                            $("#grid").datagrid('reload');
                        }
                    });

                }

            });
        }
    });
}
function doStart() {
    $.messager.confirm("确认","确认要确认订单吗?",function (yes) {
        if (yes){
            $.ajax({
                url:'orders_doStart?id='+$("#uuid").html(),
                type:'post',
                dataType:'json',
                success:function (rtn) {
                    $.messager.alert('提示',rtn.message,'info',function (){
                        if (rtn.success){
                            $("#orderWindow").dialog('close');
                            $("#grid").datagrid('reload');
                        }
                    });

                }

            });
        }
    });
}
function doInOutStore() {
    var messgae="";
    var url="";
    if (Request['oper']=='doInStore'){
        messgae="确认要入库吗?";
        url='orderdetail_doInStore';
    }
    if (Request['oper']=='doOutStore'){
        messgae='确认要出库吗?';
        url='orderdetail_doOutStore';
    }
    var formData=$("#itemForm").serializeJSON();
    if (formData.storeuuid==''){
        $.messager.alert("提示",'请选择要入库的仓库 ','info');
        return;
    }
    $.messager.confirm('确认',messgae,function (yes) {
       if (yes){
           $.ajax({
               url:url,
               data:formData,
               type:'post',
               dataType:'json',
               success:function (rtn) {
                   $.messager.alert('提示',rtn.message,'info',function () {
                       if (rtn.success){
                           $('#itemDlg').dialog('close');
                           $('#itemgrid').datagrid('getSelected').state='1';
                           var data=$('#itemgrid').datagrid('getData');
                           $("#itemgrid").datagrid("loadData",data);
                           var allIn=true;
                           $.each(data.rows,function (i,row) {
                              if (row.state*1==0){
                                  allIn=false;
                                  return false;
                              }
                           });
                           if (allIn==true){
                               $('#orderWindow').dialog('close');
                               $('#grid').datagrid('reload');
                           }
                       }
                   })
               }


           });
       }
    });

}
function getColumns() {
    if (Request['type']*1==1){
        return [[
                    {field:'uuid',title:'编号',width:100},
                    {field:'createtime',title:'生成日期',width:100,formatter: function(value,row,index){
                            return new Date(value).Format('yyyy-MM-dd');}},
                    {field:'checktime',title:'审核日期',width:100,formatter: function(value,row,index){
                            return new Date(value).Format('yyyy-MM-dd');}},
                    {field:'starttime',title:'确认日期',width:100,formatter: function(value,row,index){
                            return new Date(value).Format('yyyy-MM-dd');}},
                    {field:'endtime',title:'入库日期',width:100,formatter: function(value,row,index){
                            return new Date(value).Format('yyyy-MM-dd');}},
                    {field:'createrName',title:'下单员',width:100},
                    {field:'checkerName',title:'审核员',width:100},
                    {field:'starterName',title:'采购员',width:100},
                    {field:'enderName',title:'库管员',width:100},
                    {field:'supplierName',title:'供应商',width:100},
                    {field:'totalmoney',title:'合计金额',width:100},
                    {field:'state',title:'采购状态',width:100,formatter:getState},
                    {field:'waybillsn',title:'运单号',width:100}]];
    }
    if (Request['type']*1==2){
        return    [[{field:'uuid',title:'编号',width:100},

            {field:'endtime',title:'出库日期',width:100,formatter: function(value,row,index){
                    return new Date(value).Format('yyyy-MM-dd');}},
            {field:'createrName',title:'下单员',width:100},
            {field:'enderName',title:'库管员',width:100},
            {field:'supplierName',title:'客户',width:100},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getState},
            {field:'waybillsn',title:'运单号',width:100}]];

    }
}