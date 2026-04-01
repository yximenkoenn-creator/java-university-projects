const produkty = [
  { nazev: "Notebook", kategorie: "elektronika" },
  { nazev: "Alenka v risi divu ", kategorie: "knihy" },
  { nazev: "saty", kategorie: "obleceni" }
];
const select = document.getElementById("kategorieSelect");
const seznam = document.getElementById("seznamProduktu");

function vykresliProdukty(poleProduktu) {
  seznam.innerHTML = "";

  poleProduktu.forEach(function (produkt) {
    const li = document.createElement("li");
    li.textContent = `${produkt.nazev} (${produkt.kategorie})`;
    seznam.appendChild(li);
  });
}

function vypisElektroniky() {
  const elektronika = produkty.filter(function (produkt) {
    return produkt.kategorie === "elektronika";
  });
  vykresliProdukty(elektronika);
}

function vypisKnihy() {
  const knihy = produkty.filter(function (produkt) {
    return produkt.kategorie === "knihy";
  });
  vykresliProdukty(knihy);
}

function vypisObleceni() {
  const obleceni = produkty.filter(function (produkt) {
    return produkt.kategorie === "obleceni";
  });
  vykresliProdukty(obleceni);
}

if (select) {
  select.addEventListener("change", function () {
    const vybranaKategorie = select.value;
    switch (vybranaKategorie) {
      case "elektronika":
        vypisElektroniky();
        break;
      case "knihy":
        vypisKnihy();
        break;
      case "obleceni":
        vypisObleceni();
        break;
      default:
        vykresliProdukty(produkty);
        break;
    }
  });
}

vykresliProdukty(produkty);