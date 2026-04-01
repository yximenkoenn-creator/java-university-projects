const cislaInput = document.getElementById("cislaInput");
const spocitejBtn = document.getElementById("spocitejBtn");
const minSpan = document.getElementById("min");
const maxSpan = document.getElementById("max");
const prumerSpan = document.getElementById("prumer");

function prevedNaPoleCisel(text) {
  const cisla = text.split(",").map(s => Number(s.trim()));
  return cisla;
}

function spocitejMinimum(pole) {
  return Math.min(...pole);
}

function spocitejMaximum(pole) {
  return Math.max(...pole);
}

function spocitejPrumer(pole) {
  const soucet = pole.reduce((a, b) => a + b, 0);
  return soucet / pole.length;
}

spocitejBtn.addEventListener("click", function () {
  const text = cislaInput.value;
  const pole = prevedNaPoleCisel(text);

  const minimum = spocitejMinimum(pole);
  const maximum = spocitejMaximum(pole);
  const prumer = spocitejPrumer(pole);

  minSpan.textContent = minimum;
  maxSpan.textContent = maximum;
  prumerSpan.textContent = prumer;
});
