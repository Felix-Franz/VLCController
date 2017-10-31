function onInitInstances(){
    //load table
    $('#connectorInfo').bootstrapTable({
        url: path + 'instances',
        onLoadError: function(){
            notifyBackendConnectionError("Could not load connector info table!")
        }
    });
}

function buttonsFormatter(value){
    return '<button onclick="$(\'#instanceDetail\').modal();" type="button" class="btn btn-primary" title="change ' + value + '"><i class="fa fa-pencil" ></i></button>';
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