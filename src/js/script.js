// menu
// var offset = $('#menu').offset().top;
// var $menu = $('#menu');
// var $topo = $('#topo');
// $(document).on('scroll', function () {
//     if (offset <= $(document).scrollTop()) {
//         $menu.addClass('fixarMenu');
//         $topo.addClass('topoDois');

//     } else {
//         $menu.removeClass('fixarMenu');
//         $topo.removeClass('topoDois');
//     }
// });

// slider
var swiper = new Swiper('.swiper-comida', {
    slidesPerView: 3,
    // quantos slides estao visiveis
    slidesPerGroup: 3,
    // numero de slides q vao ser passados em grupo
    spaceBetween: 15,
    // espacos entre os slides
    loop: true,
    navigation: {
        prevEl: '.swiper-button-comida-next',
        nextEl: '.swiper-button-comida-next',
    },
});

var swiper = new Swiper('.swiper-bebida', {
    slidesPerView: 3,
    // quantos slides estao visiveis
    slidesPerGroup: 3,
    // numero de slides q vao ser passados em grupo
    spaceBetween: 15,
    // espacos entre os slides
    loop: true,
    navigation: {
        nextEl: '.swiper-button-bebida-next',
        prevEl: '.swiper-button-bebida-prev',
    },
});

var swiper = new Swiper('.swiper-sobremesa', {
    slidesPerView: 3,
    // quantos slides estao visiveis
    slidesPerGroup: 3,
    // numero de slides q vao ser passados em grupo
    spaceBetween: 15,
    // espacos entre os slides
    loop: true,
    navigation: {
        nextEl: '.swiper-button-sobremesa-next',
        prevEl: '.swiper-button-sobremesa-prev',
    },
});