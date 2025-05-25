const lodash1 = require("lodash"); //lodash is third party module and need to be installed before integrating in the program
const prompt_sync = require("prompt-sync")
let running = true;
while (running) {
let randomNum = lodash1.random(1,10); //use npm install lodash

const prompt = require("prompt-sync")({ sigint: true });
let guess = prompt("Guess a number:  ");

if (guess === randomNum) {
    console.log("Success!");
    
} else {
   console.log("L L L L L L") 
}

const prompt2 = require("prompt-sync")({ sigint: true });
let decision = prompt2("Would you like to play again? ")
if (decision === "y" || decision === "Y") {
   console.log("Okay lets goooo") 
} else if (decision === "n" || decision === "N" ) {
   running = false 
}else{
    console.log("We're just gonna keep going so")
}

}