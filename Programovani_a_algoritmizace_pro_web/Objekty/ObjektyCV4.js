let studenti = [
    {
    jmeno: "Marek",
    vek: 24,
    obor: "informatika"
    },
{ 
jmeno:"Olesya",
vek:19,
obor:"medicina"
},
{jmeno: "Sasha",
vek:19,
obor: "informatika"
}
];
function students(studenti){
    studenti.forEach(student=>{
        console.log(student.jmeno);
    });
}
function vicNez21(studenti){
    return studenti.filter(student=>student.vek>21)
  console.log(vicNez21(studenti));

};
function search(studenti,searchJmeno){
    return studenti.find(student=>student.jmeno===searchJmeno);
}
console.log("seznam studentu: ");
students(studenti);
console.log("Kdo ma vek 21+ :  ");
console.log(vicNez21(studenti));
console.log("search student: ")
console.log(search(studenti,"Sasha"));
