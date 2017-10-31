function onInitInstances(){
    //load table
    $('#connectorInfo').bootstrapTable({
        url: path + 'instances',
        onLoadError: function(){
            toastr.error('Could not load connector info table from backend!<br />Try to restart the server!', 'Connection failed', {
                timeOut: 30000,
                closeButton: true,
                onclick: function(){
                    window.open('https://github.com/Felix-Franz/VLCController/wiki');
                }
            });
        }
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