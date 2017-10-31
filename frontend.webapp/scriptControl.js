function onInitControl(){
	//build slider
	$('#controlVolumeSlider').slider({
		formatter: function(value) {
			return 'volume: ' + value;
		}
	}).on('change', function(slideEvt){
	    console.log(slideEvt.value.newValue);
		$.ajax({
			type: "POST",
			url: path + "control/volume",
			contentType: 'text/plain',
			data: slideEvt.value.newValue + "",
			success: function(data){
				$("#controlVolumeNumber").html(slideEvt.value.newValue);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				notifyBackendConnectionError();
			}
		});
	});
}
onInitControl();

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
    controlPOST('stop');
    $("#controlPause").fadeOut(100, function(){
        $("#controlPlay").fadeIn(100)
    });
}