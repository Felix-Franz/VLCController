function reconnectInstances(){
    $.post(path + "instances/reconnect", null, function(data,status){
        if (status == "success")
            reloadConnectorInfoTable(); //ToDo wheather it will be ran to early like with commands
        else
            alert("Failed to connect to the api for executing command " + command);
        });
}

function reloadConnectorInfoTable(){
    $('#connectorInfo').bootstrapTable('refresh');
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