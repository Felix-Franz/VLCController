function reconnectInstances(){
    $.ajax({
        type: "POST",
        url: path + "instances/reconnect",
        data: null,
        success: function(data){
            reloadConnectorInfoTable();
            toastr.info('All players got reconnected', 'Reconnected players', {
                    timeOut: 3000,
                    closeButton: true
                });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            notifyBackendConnectionError();
        }
    });
}

function controlPOST(command){
    $.ajax({
        type: "POST",
        url: path + "control/" + command,
        data: null,
        success: function(data){
            setTimeout(function() {
                reloadConnectorInfoTable();
            }, 500);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            notifyBackendConnectionError();
        }
    });
}
function controlPlay(){
	controlPOST("play");
    $("#controlPlay").fadeOut(100, function(){
        $("#controlPause").fadeIn(100)
    });
}
function controlPause(){
	controlPOST("pause");
	$("#controlPause").fadeOut(100, function(){
            $("#controlPlay").fadeIn(100)
        });
}
function controlStop(){
	controlPOST("stop");
}
function controlBackward(){
	controlPOST("backward");
}
function controlFullscreen(){
	controlPOST("fullscreen");
}
function controlShuffle(){
	controlPOST("shuffle");
}
function controlRepeat(){
	controlPOST("repeat");
}
function controlForward(){
	controlPOST("forward");
}