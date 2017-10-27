function loadHost(){
	$.get(path + "server/host/",
        /*{
          client: "webapp"
        }*/
        null,
        function(data,status){
			if (status == "success")
                buildHost(data)
			else
				alert("Failed to connect load host addresses!");
        });
}

function buildHost(host){
	var content = "";
	for (var i=0; i<host.length; i++){
		content+=
		'<div class = "col-sm-6 col-md-4">'
			+ '<a href = "' + host[i].host + '" class = "thumbnail">'
				+ '<img src = "' + host[i].qr + '" alt="' + host[i].host + '" title="Host address ' + host[i].host + '" />'
				+ '<p class="caption" style="text-align: center;">' + host[i].host +'</p>'
			+ '</a>'
		+ '</div>';
	}
	$("#connectToServerConent").html(content);
}