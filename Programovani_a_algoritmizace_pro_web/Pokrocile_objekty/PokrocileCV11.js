const knihy = [
    { nazev: "Alenka v říši divů", autor: "Lewis Carroll" },
    { nazev: "Atomové návyky", autor: "James Clear" },
    { nazev: "Complete Guide to Anatomy for Artists & Illustrators", autor: "Gottfried Bammes" }
];

const tlacitko = document.getElementById("nacistKnihy");
const seznam = document.getElementById("seznamKnih");

function vykresliKnihy(poleKnih) {
    seznam.innerHTML = "";
    poleKnih.forEach(function(kniha) {
        const polozka = document.createElement("li");
        polozka.textContent = `${kniha.nazev} — ${kniha.autor}`;
        seznam.appendChild(polozka);
    });
}
tlacitko.addEventListener("click", function () {
    vykresliKnihy(knihy);
});
