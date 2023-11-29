//MENU BTN
document.addEventListener('DOMContentLoaded', function () {
     const menuButton1 = document.getElementById('menuButton1'),
           menuButton2 = document.getElementById('menuButton2'),
           menu1 = document.getElementById('menu1'),
           menu2 = document.getElementById('menu2');

    menuButton1.addEventListener('click', function (event) {
        event.stopPropagation();
        toggleMenu(menu1);
        hideMenu(menu2);
    });

    menuButton2.addEventListener('click', function (event) {
        event.stopPropagation();
        toggleMenu(menu2);
        hideMenu(menu1);
    });

    function toggleMenu(menu) {
        menu.style.display = (menu.style.display === 'none' || menu.style.display === '') ? 'block' : 'none';
    }

    function hideMenu(menu) {
        menu.style.display = 'none';
    }

    // Cerrar el menú al hacer clic fuera de él
    document.addEventListener('click', function () {
        hideMenu(menu1);
        hideMenu(menu2);
    });

    // Evitar que el clic en el menú cierre el menú
    menu1.addEventListener('click', function (event) {
        event.stopPropagation();
    });

    menu2.addEventListener('click', function (event) {
        event.stopPropagation();
    });
});


//CARDS
const btnLeft = document.querySelector(".btn-left"),
      btnRight = document.querySelector(".btn-right"),
      slider = document.querySelector("#slider"),
      sliderSection = document.querySelectorAll(".slider-section");


btnLeft.addEventListener("click", e => moveToLeft())
btnRight.addEventListener("click", e => moveToRight())

 setInterval(() => {
     moveToRight()
 }, 3000);


let ope = 0,
    cont = 0,
    widthImg = 100 / sliderSection.length;

function moveToRight() {
    if (cont >= sliderSection.length-1) {
        cont = 0;
        ope = 0;
        slider.style.transform = `translate(-${ope}%)`;
        slider.style.transition = "none";
        return;
    } 
    cont++;
    ope = ope + widthImg;
    slider.style.transform = `translate(-${ope}%)`;
    slider.style.transition = "all ease .7s" 
}  

function moveToLeft() {
    cont--;
    if (cont < 0 ) {
        cont = sliderSection.length-1;
        ope = widthImg * (sliderSection.length-1)
        slider.style.transform = `translate(-${ope}%)`;
        slider.style.transition = "none";
        return;
    } 
    ope = operacion - widthImg;
    slider.style.transform = `translate(-${ope}%)`;
    slider.style.transition = "all ease .7s"
} 

//funcion para carteles de error y exitoooooo
    document.addEventListener('DOMContentLoaded', function () {
        // Función para mostrar mensajes
        function mostrarMensaje(tipo, mensaje) {
            if (tipo === "exito") {
                alert("¡Éxito! " + mensaje);
            } else if (tipo === "error") {
                alert("Error: " + mensaje);
            }
        }

        // Verificar si hay mensajes en el DOM y mostrar alertas
        var mensajes = document.querySelectorAll(".mensaje"); // Ajusta el selector según tu estructura HTML
        if (mensajes.length > 0) {
            mensajes.forEach(function (elemento) {
                var tipo = elemento.getAttribute("data-tipo");
                var mensaje = elemento.textContent;
                mostrarMensaje(tipo, mensaje);
            });
        }
    });
    
// FALTA FUNCION PARA MANIPULAR LAS STARS DE LAS CARDS

