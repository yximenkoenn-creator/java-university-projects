const pocitadlo = {
  hodnota: 0,
  pridej: function () {
     this.hodnota++;
  },
  uber: function () {
     this.hodnota--;
  },
  vynuluj: function () {
     this.hodnota = 0;
  }
};
const zobrazeni = document.getElementById("hodnota");
const btnPridat = document.getElementById("pridej");
const btnUber = document.getElementById("uber");
const btnVynuluj = document.getElementById("vynuluj");

function zobrazHodnotu() {
    zobrazeni.textContent = pocitadlo.hodnota;
}

btnPridat.addEventListener("click", function () {
    pocitadlo.pridej();
    zobrazHodnotu();
});

btnUber.addEventListener("click", function () {
    pocitadlo.uber();
    zobrazHodnotu();
});

btnVynuluj.addEventListener("click", function () {
    pocitadlo.vynuluj();
    zobrazHodnotu();
});
zobrazHodnotu();