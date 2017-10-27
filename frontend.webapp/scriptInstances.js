function onInitInstances(){
    //load table
    $('#connectorInfo').bootstrapTable({
        url: path + 'instances'
    });
}

function stateFormatter(value){
    if (value=="undefined")
        return "not connected"
    return value;
}
function volumeFormatter(value){
    if (value==-1){
        return "-"
    }
    return value;
}
function responseTimeFormatter(value){
    if (value==-1)
        return "&infin;"
    return value;
}

function reloadConnectorInfoTable(){
    $('#connectorInfo').bootstrapTable('refresh');
}

onInitInstances();