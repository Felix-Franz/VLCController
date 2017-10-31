var path = "api/";

// function called every x seconds
window.setInterval(function(){
  reloadConnectorInfoTable();
}, 30000);


function notifyBackendConnectionError(detailMessage){
    var message = 'Could not connect to backend!<br />Try to restart the server!';
    if (detailMessage != undefined){
        message += "<br /> Detail: " + detailMessage
    }
    toastr.error(message, 'Connection failed', {
        timeOut: 5000,
        closeButton: true,
        onclick: function(){
            window.open('https://github.com/Felix-Franz/VLCController/wiki');
        }
    });
}
