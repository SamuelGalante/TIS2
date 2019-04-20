var offset = $('#menu').offset().top;
var $menu = $('#menu');
var $topo = $('#topo');
$(document).on('scroll', function () {
    if (offset <= $(document).scrollTop()) {
        $menu.addClass('fixarMenu');
        $topo.addClass('topoDois');
        
    } else {
        $menu.removeClass('fixarMenu');
        $topo.removeClass('topoDois');
    }
});