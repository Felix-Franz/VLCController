var path = "api/";

function onInit(){
    //load table
    $('#connectorInfo').bootstrapTable({
        url: path + 'instances'
    });
    //make place for footer
    $('#contend').css('margin-bottom', $('#control').css('height'));

    $('#collapseControl').on('show.bs.collapse', function () {
        $('.panel-heading').animate({
            backgroundColor: "#515151"
        }, 500, function (){
            $('#contend').animate({'margin-bottom': $('#control').css('height')}, 500);
        });
    })

    $('#collapseControl').on('hide.bs.collapse', function () {
        $('.panel-heading').animate({
            backgroundColor: "#00B4FF"
        }, 500, function (){
            $('#contend').animate({'margin-bottom': $('#control').css('height')}, 500);
        });
    })
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

onInit();