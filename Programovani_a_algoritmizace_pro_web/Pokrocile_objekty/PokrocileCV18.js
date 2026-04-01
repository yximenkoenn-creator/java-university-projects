const produkty = [
   { id: 1, nazev: "Jablko" },
   { id: 2, nazev: "Banán" },
  { id: 4, nazev: "Maso" }
];

const kosik = [];

const produktyList = document.getElementById("produktyList");
const kosikList = document.getElementById("kosikList");

function vykresliProdukty() {
  produktyList.innerHTML = "";

  // vytvořte <li> pro každý produkt
  produkty.forEach(produkt => {
    const li = document.createElement("li");
    li.textContent = produkt.nazev + " ";
    // přidejte do něj tlačítko "Přidat do košíku"
    const pridatBtn = document.createElement("button");
    pridatBtn.textContent = "Přidat do košíku";
    pridatBtn.addEventListener("click", function() {
      pridatDoKosiku(produkt);
    });
    li.appendChild(pridatBtn);
    produktyList.appendChild(li);
  });
}

function vykresliKosik() {
  kosikList.innerHTML = "";
  kosik.forEach(produkt => {
    const li = document.createElement("li");
    li.textContent = produkt.nazev;
    kosikList.appendChild(li);
  });
}

function pridatDoKosiku(produkt) {
  kosik.push(produkt);
  vykresliKosik();
}
vykresliProdukty();
