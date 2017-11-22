function loadHost(){
    $("#connectToServerConent").html('<center><div class="loader"></div><p>loading</p></center>');
    $.ajax({
        type: "GET",
        url: path + "server/host/",
        data: null,
        success: function(data){
            buildHost(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            notifyBackendConnectionError("Could not get host address!")
        }
    });
}

function buildHost(host){
	var content = "";
	for (var i=0; i<host.length; i++){
		content+=
		'<div class = "col-sm-6 col-md-4">'
			+ '<a href = "' + host[i].host + '" class="thumbnail" target="_blank">'
				+ '<img src = "' + host[i].qr + '" alt="' + host[i].host + '" title="Host address ' + host[i].host + '" />'
				+ '<p class="caption" style="text-align: center;">' + host[i].host +'</p>'
			+ '</a>'
		+ '</div>';
	}
	$("#connectToServerConent").html(content);
}