function onInitDesign(){
    //update menu onclick
    $(".nav li").on("click", function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });

    //make place for footer
    $('#content').css('margin-top', $('#nav').css('height'));
    //make place for footer
    $('#content').css('margin-bottom', $('#control').css('height'));

    //attach control bar color change
    $('#collapseControl').on('show.bs.collapse', function () {
        $('.panel-heading').animate({
            backgroundColor: "#515151"
        }, 500, function (){
            $('#content').animate({'margin-bottom': $('#control').css('height')}, 500);
        });
    });
    $('#collapseControl').on('hide.bs.collapse', function () {
        $('.panel-heading').animate({
            backgroundColor: "#00B4FF"
        }, 500, function (){
            $('#content').animate({'margin-bottom': $('#control').css('height')}, 500);
        });
    });
    //
    $("#navbar").on('click', function(){
        $("#navbar").collapse('hide');
    })
}

function openPage(pageId){
	$(".content").removeClass("active");
	$("#" + pageId).addClass("active");
}

onInitDesign();