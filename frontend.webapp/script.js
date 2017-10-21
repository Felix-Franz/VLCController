var path = "api/";

function init(){

}

function controlPOST(command){
	$.post(path + "control/" + command,
        /*{
          client: "webapp"
        }*/
        null,
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


init();