function ajax(url,value,id,key) {
    if(value!=null) {
        $.ajax({
            url: url + value,
            dataType: 'json',
            success: function (rtn) {
                $("#" + id).html(rtn[key]);
            }
        });
    }
    return "<span id='"+id+"'></span>";

}
function getState1(value,row,index) {
    var getRows=$("#grid").datagrid('getRows');
    var type=null;
    if (getRows!=null) {
        type = getRows[index]['type'];
    }
    if (value==0){
        if(type==2){
            return "未入库"
        }
        return "未审核";
    }
    if (value==1){
        if (type=2){
            return "已入库";
        }
        return "已审核";
    }
    if (value==2){
        return "已确认";
    }
    if (value==3){
        return "已入库";
    }
}
function getState(value) {
    switch (value*1){
        case 1:return "未审核";
        case 2:return "已审核";
        case 3:return "已确认";
        case 4:return "已入库";
        default: return "";
    }
}