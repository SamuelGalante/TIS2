$(document).ready(function(){
    importarHead();
    importarHeader()
    $('#verProduto').hide();
})

function importarHead(){
    var conteudo = `
    <script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
    <link href="bootstrap/css/bootstrap-grid.css" rel="stylesheet" type="text/css">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="bootstrap/css/style.css" rel="stylesheet" type="text/css">
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
    `;

    $("head").append(conteudo);
}

function importarHeader(){
    var conteudo = `
    <div class="container-fluid">
        <div class="row align-items-center" id="cabecalho">
            <div class="col- ml-4">
                <img src="img/logo.png" alt="" style="max-height: 13vh;">
            </div>
            <div class="col- ml-auto mr-5">
                <h5 onclick="openPage('cardapio.html')" id="areaUsuario"> ÁREA DO USUÁRIO </h5>
            </div>
        </div>
    </div>
    `;

    $("header").append(conteudo);
}

function openPage(page) {
  window.open(page, '_self');
}

$('.clickTable').on('click', function(){
  $('#verProduto').show();
})

function modalAdd(modalId) {
  $("#formulario")[0].reset();
  $(modalId).modal('show');
}

function pegarImagem(){
  var img = $('#imgProd').attr('value');
  document.getElementById('codImg').value = img;
  alert(img);
}

function FactoryXMLHttpRequest() {

  if (window.XMLHttpRequest) {
    return new XMLHttpRequest();// Opera 8.0+, Firefox, Chrome, Safari
  }
  else if (window.XDomainRequest) {
    return new XDomainRequest(); // Antigo Safari
  }
  else if (window.ActiveXObject) {
    var msxmls = new Array(// Internet Explorer
      'Msxml2.XMLHTTP',
      'Microsoft.XMLHTTP',
      'Msxml3.XMLHTTP',
      'Msxml2.XMLHTTP.7.0',
      'Msxml2.XMLHTTP.6.0',
      'Msxml2.XMLHTTP.5.0',
      'Msxml2.XMLHTTP.4.0',
      'Msxml2.XMLHTTP.3.0');
    for (var i = 0; i < msxmls.length; i++) {
      try {
        return new ActiveXObject(msxmls[i]);
      } catch (e) {
      }
    }
  } else throw new Error("Could not instantiate XMLHttpRequest");
}
