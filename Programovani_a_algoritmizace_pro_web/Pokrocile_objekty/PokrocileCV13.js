const kalkulacka = {
  cislo1: 0,
  cislo2: 0,
  vysledek: 0,
  secti: function () {
 this.vysledek = this.cislo1 + this.cislo2;
  },
  odecti: function () {
 this.vysledek = this.cislo1 - this.cislo2;
  }
};

const input1 = document.getElementById("cislo1");
const input2 = document.getElementById("cislo2");
const vysledekSpan = document.getElementById("vysledek");
const scitaniBtn = document.getElementById("scitaniBtn");
const odcitaniBtn = document.getElementById("odcitaniBtn");

function aktualizujCisla() {
    kalkulacka.cislo1= Number(input1.value);
    kalkulacka.cislo2= Number(input2.value);
}

function zobrazVysledek() {
 vysledekSpan.textContent = kalkulacka.vysledek;
}

scitaniBtn.addEventListener("click", function () {
  aktualizujCisla();
  kalkulacka.secti();
  zobrazVysledek();
});

odcitaniBtn.addEventListener("click", function () {
  aktualizujCisla();
  kalkulacka.odecti();
  zobrazVysledek();
});

