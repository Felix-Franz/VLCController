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
    //ToDo Settings: var settingsButton = '<button onclick="" type="button" class="btn btn-primary" title="edit ' + row.name + '"><i class="fa fa-pencil" ></i></button>';
    var settingsButton = ''; //remove
    var controlButton = ( row.state != "undefined" ? '<button onclick="loadInstanceControl(\''+ row.name + '\');" type="button" class="btn btn-primary" title="control ' + row.name + '"><i class="fa fa-gamepad" ></i></button>' : '');
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

function getInstance(name, onLoad){
    $.ajax({
        type: "GET",
        url: path + "instances/single/" + name,
        data: null,
        success: function (data){onLoad(data)},
        error: function(XMLHttpRequest, textStatus, errorThrown){
            notifyBackendConnectionError("Could not run load " + name + "!");
        }
    });
}
function loadInstanceControl(name){
    getInstance(name, function (data){
        $("#instanceControlHeader").html("Control " + data.name);
        var title = "<p><label>title: </label><text> " + data.title + "</text></p>";
        var state = "<p><label>state: </label><text> " + data.state + "</text></p>";
        var responseTime = "<p><label>response time: </label><text> " + data.responseTime + "</text></p>";
        var controls = '<div style="text-align: center;">'
            + '     <button onclick="instanceControlPOST(\'' + data.name + '\', \'backward\');" type="button" class="btn btn-primary btn-lg controlbtn controlBackward" title="plays previouse title"><i class="fa fa-step-backward fa-lg"></i></button>'
            + '     <button onclick="instanceControlPlay(\'' + data.name + '\')" type="button" id="instanceControlPlay" class="btn btn-primary btn-lg controlbtn" title="plays a title on this media player" ' + (data.state == "playing" ? 'style="display: none;"' : '') + '><i class="fa fa-play fa-lg"></i></button>'
            + '     <button onclick="instanceControlPause(\'' + data.name + '\')" type="button" id="instanceControlPause" class="btn btn-primary btn-lg controlbtn" title="pauses this media player" ' + (data.state != "playing" ? 'style="display: none;"' : '') + '><i class="fa fa-pause fa-lg"></i></button>'
            + '     <button onclick="instanceControlStop(\'' + data.name + '\');" type="button" class="btn btn-primary btn-lg controlbtn" title="stops this media player"><i class="fa fa-stop fa-lg"></i></button>'
            + '     <button onclick="instanceControlPOST(\'' + data.name + '\', \'forward\');" type="button" class="btn btn-primary btn-lg controlbtn controlForward" title="plays next title"><i class="fa fa-step-forward fa-lg"></i></button>'
            + '     <br />'
            + '     <button onclick="instanceControlPOST(\'' + data.name + '\', \'reset\');" type="button" class="btn btn-primary btn-lg controlbtn" title="restart current title"><i class="fa fa-arrow-left" aria-hidden="true"></i></button>'
            + '     <button onclick="instanceControlPOST(\'' + data.name + '\', \'shuffle\');" type="button" class="btn btn-primary btn-lg controlbtn" title="toggles shuffle on this media player"><i class="fa fa-random" aria-hidden="true"></i></button>'
            + '     <button onclick="instanceControlPOST(\'' + data.name + '\', \'fullscreen\');" type="button" class="btn btn-primary btn-lg controlbtn" title="toggles fullscreen on this media player"><i class="fa fa-expand" aria-hidden="true"></i></button>'
            + '     <button onclick="instanceControlPOST(\'' + data.name + '\', \'repeat\');" type="button" class="btn btn-primary btn-lg controlbtn" title="toggles repeat on this media player"><i class="fa fa-repeat" aria-hidden="true"></i></button>'
            + '     <button onclick="$(\'#instanceControlVolume\').slideToggle();" type="button" class="btn btn-primary btn-lg controlbtn" title="toggles volume menu"><i class="fa fa-volume-down" aria-hidden="true"></i></button>'
            + '     <br />'
            + '     <div id="instanceControlVolume" class="controlbtn" style="display: none;">'
            + '         <span style="margin-right: 1em;">Volume:  </span>'
            + '         <input id="instanceControlVolumeSlider" data-slider-id=\'instanceControlVolumeSlider\' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="100" style="width: 13em;"/>'
            + '         <span id="instanceControlVolumeNumber" style="margin-left: 10px;">100</span>'
            + '     </div>'
            + '</div>';
        $("#instanceControlBody").html(title + state + responseTime + controls);
        //build slider
        	model.instance.volumeSlider =$('#instanceControlVolumeSlider').slider({
        		formatter: function(value) {
        			return 'volume: ' + value;
        		}
        	}).on('change', function(slideEvt){
        		$.ajax({
        			type: "POST",
        			url: path + "instances/single/" + data.name + "/volume",
        			contentType: 'text/plain',
        			data: slideEvt.value.newValue + "",
        			success: function(data){
        				$("#instanceControlVolumeNumber").html(slideEvt.value.newValue);
        			},
        			error: function(XMLHttpRequest, textStatus, errorThrown){
        				notifyBackendConnectionError("Cloud not update instance volume!");
        			}
        		});
        	});
        model.instance.name = data.name;
        model.instance.shown = true;
        updateInstancePlayPause();
        updateInstanceVolume();
        $('#instanceControl').modal();
    });
}
function instanceControlPOST(name, command){
    $.ajax({
        type: "POST",
        url: path + "instances/single/" + name + "/" + command,
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
function instanceControlPlay(name){
	instanceControlPOST(name, "play");
    $("#instanceControlPlay").fadeOut(100, function(){
        $("#instanceControlPause").fadeIn(100)
    });
}
function instanceControlPause(name){
	instanceControlPOST(name, "pause");
	$("#instanceControlPause").fadeOut(100, function(){
        $("#instanceControlPlay").fadeIn(100)
    });
}
function instanceControlStop(name){
    instanceControlPOST(name, 'stop');
    $("#instanceControlPause").fadeOut(100, function(){
        $("#instanceControlPlay").fadeIn(100)
    });
}

function updateInstancePlayPause(){
    if (model.instance.shown){
        $.ajax({
            type: "GET",
            url: path + "instances/single/" + model.instance.name + "/state",
            data: null,
            success: function (data){
                if (data == "playing"){
                    var hide = "#instanceControlPlay";
                    var show = "#instanceControlPause";
                }else{
                    var hide = "#instanceControlPause";
                    var show = "#instanceControlPlay";
                }
                $(hide).fadeOut(100, function(){
                    $(show).fadeIn(100)
                });
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                notifyBackendConnectionError("Could not run load state of " + model.instance.name + "!");
            }
        });
    }
}

function updateInstanceVolume(){
    if (model.instance.shown){
        $.ajax({
                type: "GET",
                url: path + "instances/single/" + model.instance.name + "/volume",
                data: null,
                success: function (data){
                    $("#instanceControlVolumeNumber").html(data);
                    model.instance.volumeSlider.slider('setValue', data);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    notifyBackendConnectionError("Could not load volume of " + model.instance.name + "!");
                }
            });
    }
}