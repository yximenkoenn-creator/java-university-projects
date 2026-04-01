//objekty
let user = new Object();

 user = {
    name: "John",//klic name hodnota John
    age: 30,
    "vlastni auto": true
}; //je to objekt
user.isAdmin = true;
console.log (user)
for (let key in user) {
   // console.log ("Test" + user[key])
    //console.log(key);

}
let methodUser = {
    // user.sayHi = function(){
    //     console.log(user)
    //sayHi2(){
        //logika funkce
    //}
    //}


    }


//user.isAdmin // je na kratkodobe pouzivani
//delete user //smazat age