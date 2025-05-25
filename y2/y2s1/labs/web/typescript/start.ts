let message:string = "Hello world";
message.toLowerCase;

function sayHi(name: string){
  console.log("Hi there " + name.toUpperCase());
};

function guessNumber(myNum:number) {
 if (myNum == 12) {
  return true
 } else {
 return false 
 } 
}


function main(){
  console.log(message);  
  sayHi("Daniel")
  let guess1:boolean = guessNumber(12);
  console.log(guess1);

  let guess2:boolean = guessNumber(11);
  console.log(guess2);

};


main();
