function onInitControl(){
	//build slider
	model.control.volumeSlider = $('#controlVolumeSlider').slider({
		formatter: function(value) {
			return 'volume: ' + value;
		}
	}).on('change', function(slideEvt){
		$.ajax({
			type: "POST",
			url: path + "control/volume",
			contentType: 'text/plain',
			data: slideEvt.value.newValue + "",
			success: function(data){
				$("#controlVolumeNumber").html(slideEvt.value.newValue);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				notifyBackendConnectionError("Cloud not update volume!");
			}
		});
	});
}

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
            notifyBackendConnectionError("Could not reconnect instances!");
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
            notifyBackendConnectionError("Could not run command " + command + "!");
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

function updateControlPlayPause(){
	$.ajax({
        type: "GET",
        url: path + "control/state",
        data: null,
        success: function (data){
            if (data == "playing"){
                var hide = "#controlPlay";
                var show = "#controlPause";
            }else{
                var hide = "#controlPause";
                var show = "#controlPlay";
            }
            $(hide).fadeOut(100, function(){
                $(show).fadeIn(100)
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            notifyBackendConnectionError("Could not load master state!");
        }
    });
}

function updateControlVolume(){
    $.ajax({
            type: "GET",
            url: path + "control/volume",
            data: null,
            success: function (data){
                $("#controlVolumeNumber").html(data);
	            model.control.volumeSlider.slider('setValue', data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                notifyBackendConnectionError("Could not load master volume!");
            }
        });
}