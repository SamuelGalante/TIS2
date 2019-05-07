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

// function alerta() {
//     alert("Pedido realizado com sucesso !");
// }

$(document).ready(function(){
    $('#btnSubmit').click(function(){
        $('#myAlert').show('fade');
    }) 
});

