const btnLeft = document.querySelector(".btn-left"),
      btnRight = document.querySelector(".btn-right"),
      slider = document.querySelector("#slider"),
      sliderSection = document.querySelectorAll(".slider-section");


btnLeft.addEventListener("click", e => moveToLeft());
btnRight.addEventListener("click", e => moveToRight());

 setInterval(() => {
     moveToRight();
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
    slider.style.transition = "all ease .7s" ;
}  

function moveToLeft() {
    cont--;
    if (cont < 0 ) {
        cont = sliderSection.length-1;
        ope = widthImg * (sliderSection.length-1);
        slider.style.transform = `translate(-${ope}%)`;
        slider.style.transition = "none";
        return;
    } 
    ope = ope - widthImg;
    slider.style.transform = `translate(-${ope}%)`;
    slider.style.transition = "all ease .7s";
} 