var message = "Hello world";
message.toLowerCase;
function sayHi(name) {
    console.log("Hi there " + name.toUpperCase());
}
;
function guessNumber(myNum) {
    if (myNum == 12) {
        return true;
    }
    else {
        return false;
    }
}
function main() {
    console.log(message);
    sayHi("Daniel");
    var guess1 = guessNumber(12);
    console.log(guess1);
    var guess2 = guessNumber(11);
    console.log(guess2);
}
;
main();
