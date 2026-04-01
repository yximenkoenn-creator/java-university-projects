const uzivatel = {
    jmeno: "Jan Novák",
    vek: 28,
    mesto: "Praha",
    konicky: ["čtení", "cyklistika", "programování"]
};

function vykresliKartu(osoba) {
    const kartaDiv = document.getElementById("karta");

    let html = "<h2>" + osoba.jmeno + "</h2>";
    html += "<p>Věk: " + osoba.vek + "</p>";
    html += "<p>Město: " + osoba.mesto + "</p>";

    html += "<h3>Koníčky:</h3>";
    html += "<ul>";

    for (let i = 0; i < osoba.konicky.length; i++) {
        html += "<li>" + osoba.konicky[i] + "</li>";
    }

    html += "</ul>";

    kartaDiv.innerHTML = html;
}

vykresliKartu(uzivatel);
