function validarcontrasenia() {

    // Dos inputs al obtener valor
    var password1 = document.getElementById('password_admin').value;
    var password2 = document.getElementById('password_admin_rep').value;

    // Boton a desabilitar
    var desabilitar = document.getElementById('envio');

    if (password1 !== password2) {
        document.getElementById('alertacontrasenia').innerHTML = "Debe coincidir con las contraseñas";
        desabilitar.disabled = true;
    } else {
        document.getElementById('alertacontrasenia').innerHTML = "";
        desabilitar.disabled = false;
    }
}

function validarActividad(texto, campo, alerta, longitud) {
    var desabilitar = document.getElementById("envio");
    
    if (texto.value.length < longitud) {
        document.getElementById(alerta).innerHTML = campo + " debe tener mas de " + longitud + " caracteres";
        desabilitar.disabled = true;
    } else {
        document.getElementById(alerta).innerHTML = "";
        desabilitar.disabled = false;
    }
}

function validarFacilitador(texto, campos_alerta, alerta_nombre, longitud) {
    var desabilitar = document.getElementById("envio");
    
    if (texto.value.length < longitud) {
        document.getElementById(alerta_nombre).innerHTML = campos_alerta + " debe tener mas de " + longitud + " caracteres.";
        desabilitar.disabled = true;
    } else {
        document.getElementById(alerta_nombre).innerHTML = "";
        desabilitar.disabled = false;
    }
}

function validarCategoria(texto, campos_alerta, alerta_descripEdit, longitud) {
    var desabilitar = document.getElementById("envio");
    
    if (texto.value.length < longitud) {
        document.getElementById(alerta_descripEdit).innerHTML = campos_alerta + " debe tener mas de " + longitud + " caracteres.";
        desabilitar.disabled = true;
    } else {
        document.getElementById(alerta_descripEdit).innerHTML = "";
        desabilitar.disabled = false;
    }
}
    
function validarCorreo() {
    var correo = document.getElementById('correo');
    var desabilitar = document.getElementById('envio');

    var expresionRegular = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;

    if (correo.value.match(expresionRegular)){
        document.getElementById("alertaCorreo").innerHTML = "";
        desabilitar.disabled = false;
    } else {
        document.getElementById("alertaCorreo").innerHTML = "La dirección de email " + correo.value + " es incorrecta.";
        desabilitar.disabled = true;
    }
}

function validarAdministrador(texto, campos_alerta, alerta_descripEdit, longitud) {
    var desabilitar = document.getElementById("envio");
    
    if (texto.value.length < longitud) {
        document.getElementById(alerta_descripEdit).innerHTML = campos_alerta + " debe tener mas de " + longitud + " caracteres.";
        desabilitar.disabled = true;
    } else {
        document.getElementById(alerta_descripEdit).innerHTML = "";
        desabilitar.disabled = false;
    }
}
    