var path = "api/";
//model for settings
var settings = {
    startupTime: 1000,
    updateTime: 30000
}
//model for variables
var model = {
    control: {
        volumeSlider: null
    },
    instance: {
        shown: false,
        name: null,
        volumeSlider: null
    }
};

function onInitMain(){
    onInitControl();
    onInitDesign();
    onInitInstances();
    // function will called after x seconds
    window.setTimeout(function (){
        updateAll();
    }, settings.starupTime);
    // function called every x seconds
    window.setInterval(function(){
        updateAll();
    }, settings.updateTime);
}

function updateAll(){
    reloadConnectorInfoTable();
    updateControlPlayPause();
    updateControlVolume();
    updateInstanceVolume();
}

function notifyBackendConnectionError(detailMessage){
    var message = 'Could not connect to backend!<br />Try to restart the server!';
    if (detailMessage != undefined){
        message += "<br />Detail: " + detailMessage
    }
    console.error(message.replace(/<br \/>/g, "\n"));
    toastr.error(message, 'Connection failed', {
        timeOut: 5000,
        closeButton: true,
        onclick: function(){
            window.open('https://github.com/Felix-Franz/VLCController/wiki');
        }
    });
}

onInitMain();