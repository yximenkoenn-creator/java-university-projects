const otazky = [
  { text: "Kolik je 2 + 2?", odpoved: "4" },
  { text: "co znamena <li> v js?", odpoved: "list item" },
  { text: "co znamena <div> v js?", odpoved: "oddil" },
  { text: "co znamena addEventListener? ", odpoved: "metoda, která umožňuje připojit tzv. posluchače událostí (event listeners) k HTML elementu, aby se na něj mohly reagovat uživatelské akce (kliknutí, stisknutí klávesy, pohyb myší) nebo jiné události (načtení stránky)" }
];

let aktualniIndex = 0;

const otazkaText = document.getElementById("otazkaText");
const odpovedInput = document.getElementById("odpovedInput");
const zodpovedetBtn = document.getElementById("zodpovedetBtn");
const vysledekP = document.getElementById("vysledek");

function zobrazOtazku() {
  otazkaText.textContent = otazky[aktualniIndex].text;
  vysledekP.textContent = "";
  odpovedInput.value = "";
}

zodpovedetBtn.addEventListener("click", function () {
  // přečtěte odpověď z inputu
  const odpovedUzivatele = odpovedInput.value.trim().toLowerCase();
  const spravnaOdpoved = otazky[aktualniIndex].odpoved.trim().toLowerCase();

  if (odpovedUzivatele === spravnaOdpoved) {
    vysledekP.textContent = "Správně!";
    vysledekP.style.color = "green";
    aktualniIndex++;
  } else {
    vysledekP.textContent = `Špatně! Správná odpověď je: ${otazky[aktualniIndex].odpoved}`;
    vysledekP.style.color = "red";
    aktualniIndex++;
  }
  // vypište do vysledekP, zda je odpověď správná
  setTimeout(zobrazOtazku, 3000);
  if (aktualniIndex >= otazky.length) {
    aktualniIndex = 0;
  }

});
zobrazOtazku();
