var path = "api/control/";


function controlPOST(command){
	$.post(path + command,
        {
          client: "webapp"
        },
        function(data,status){
			if (status != "success")
				alert("Failed to connect to the api for executing command " + command);
        });
}

function controlPlay(){
	controlPOST("play");
}
function controlPause(){
	controlPOST("pause");
}
function controlStop(){
	controlPOST("stop");
}
function controlBackward(){
	controlPOST("backward");
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