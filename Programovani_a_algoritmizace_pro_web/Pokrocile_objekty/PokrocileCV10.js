const film = { name: "Alenka v risi divu",
    rok:2010,
    reziser: "Tim Burton"
};
const tlacitko = document.getElementById("zobrazitBtn");
const div = document.getElementById("info");
tlacitko.addEventListener("click", function () {
    div.innerHTML = `
        <h2>${film.name}</h2>
        <p><strong>Rok:</strong> ${film.rok}</p>
        <p><strong>Režisér:</strong> ${film.reziser}</p>
    `;
});
