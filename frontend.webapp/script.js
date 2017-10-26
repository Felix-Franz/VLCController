var path = "api/";

function onInit(){
    //load table
    $('#connectorInfo').bootstrapTable({
        url: path + 'instances'
    });
    //make place for footer
    $('#contend').css('margin-top', $('#nav').css('height'));
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

onInit();