const ukoly = [];

const todoList = {

    pridatUkol(text) {
        if (text.trim() === '') return;
        ukoly.push({
            text: text,
            hotovo: false
        });
    },

    oznacitHotovo(index) {
        if (index >= 0 && index < ukoly.length) {
            ukoly[index].hotovo = !ukoly[index].hotovo;
        }
    }
};

document.addEventListener('DOMContentLoaded', () => {
    const tlacitko = document.getElementById('pridat');
    const input = document.getElementById('novyUkol');

    tlacitko.addEventListener('click', () => {
        // @ts-ignore
        todoList.pridatUkol(input.value);
        console.log(ukoly);
        // @ts-ignore
        input.value = ''; 
    });
});
