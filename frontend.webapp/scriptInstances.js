function onInitInstances(){
    //load table
    $('#connectorInfo').bootstrapTable({
        url: path + 'instances',
        onLoadError: function(){
            notifyBackendConnectionError("Could not load connector info table!")
        }
    });
}

function buttonsFormatter(value, row){
    console.log(row);
    console.log(row.state != undefined)
    var settingsButton = '<button onclick="" type="button" class="btn btn-primary" title="edit ' + row.name + '"><i class="fa fa-pencil" ></i></button>';
    var controlButton = ( row.state != "undefined" ? '<button onclick="$(\'#instanceDetail\').modal();" type="button" class="btn btn-primary" title="control ' + row.name + '"><i class="fa fa-gamepad" ></i></button>' : '');
    return '<div class="btn-group">' + settingsButton + controlButton + '</div>'
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