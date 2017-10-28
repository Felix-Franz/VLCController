var path = "api/";

// function called every x seconds
window.setInterval(function(){
  reloadConnectorInfoTable();
}, 5000);


function notifyBackendConnectionError(){
    toastr.error('Could not connect to backend!<br />Try to restart the server!', 'Connection failed', {
        timeOut: 5000,
        closeButton: true,
        onclick: function(){
            window.open('https://github.com/Felix-Franz/VLCController/wiki');
        }
    });
}