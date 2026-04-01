const znamky = {
  matematika: 1,
  ceskyJazyk: 2,
  anglictina: 1,
  fyzika: 3
};

let sum = 0;
let pocetPredmetu = Object.keys(znamky).length;
let prumerZnamek = 0;
for (let i = 0; i < pocetPredmetu; i++) {
  sum += Object.values(znamky)[i];
}
prumerZnamek = sum / pocetPredmetu;
console.log("prumerna znamka: " + prumerZnamek.toFixed(2));