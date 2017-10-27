function reconnectInstances(){
    $.ajax({
        type: "POST",
        url: path + "instances/reconnect",
        data: null,
        success: function(data){
            reloadConnectorInfoTable();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            notifyBackendConnectionError();
        }
    });
}

function controlPOST(command){
	$.post(path + "control/" + command,
        /*{
          client: "webapp"
        }*/
        null,
        function(data,status){
			if (status == "success")
                setTimeout(function() {
			        reloadConnectorInfoTable();
                }, 500);
			else
				alert("Failed to connect to the api for executing command " + command);
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