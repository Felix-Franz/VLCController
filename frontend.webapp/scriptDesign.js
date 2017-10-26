function onInit(){
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

onInit();