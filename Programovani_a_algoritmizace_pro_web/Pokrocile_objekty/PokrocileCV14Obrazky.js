const obrazky = [
  "https://i.pinimg.com/1200x/6f/bd/8d/6fbd8d6014a7b73bcb6979e9ceb1a785.jpg",
  "https://i.pinimg.com/1200x/ae/d0/c7/aed0c741d14c176e4c525cb729993c2b.jpg",
  "https://i.pinimg.com/736x/e2/39/de/e239de7f17134baccd543c02ae95d86a.jpg"
];

let aktualniIndex = 0;

const img = document.getElementById("foto");
const predchoziBtn = document.getElementById("predchozi");
const dalsiBtn = document.getElementById("dalsi");

function zobrazObrazek() {
  img.src = obrazky[aktualniIndex];
}

// Nastavte první obrázek
zobrazObrazek();

predchoziBtn.addEventListener("click", function () {
    aktualniIndex--;
    if(aktualniIndex <0){
        aktualniIndex = obrazky.length -1;
    }
  zobrazObrazek();
});

dalsiBtn.addEventListener("click", function () {
    aktualniIndex++;
    if(aktualniIndex>= obrazky.length){
        aktualniIndex = 0; 
    }
  zobrazObrazek();
  
});
