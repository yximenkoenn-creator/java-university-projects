const osoby = [];

const form = document.getElementById("osobaForm");
const jmenoInput = document.getElementById("jmeno");
const vekInput = document.getElementById("vek");
const seznam = document.getElementById("seznamOsob");

function vykresliOsoby() {
  seznam.innerHTML = "";
  osoby.forEach(osoba => {
    const li = document.createElement("li");
    li.textContent = `${osoba.jmeno} ( ${osoba.vek})`
    seznam.appendChild(li);

  });
}
form.addEventListener("submit", function (event) {
  event.preventDefault(); // zabrání obnovení stránky

  // 1) Vytvořte objekt osoba z hodnot formuláře
  const osoba = {
    jmeno: jmenoInput.value,
    vek: vekInput.value
  };
  // 2) Přidejte do pole 
  osoby.push(osoba);
  // 3) Vykreslete seznam znovu
  vykresliOsoby();
  // 4) Případně vyčistěte vstupní pole
  jmenoInput.value = "";
  vekInput.value = "";
});