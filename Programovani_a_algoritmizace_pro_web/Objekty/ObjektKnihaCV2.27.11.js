
let kniha = {
    nazev: 'zitra, zitra, zitra',
    autor: 'Gabrielle Zevin',
    pocetStran: 480,
    info() {
        return `Kniha ${this.nazev} od ${this.autor} má ${this.pocetStran} stran`;
    },
    jeDlouha() {
        if (this.pocetStran > 300) {
            return true;
        } else { 
            return false;
        }
    }
}

console.log(kniha.info() + " je dlouha: " + kniha.jeDlouha());

