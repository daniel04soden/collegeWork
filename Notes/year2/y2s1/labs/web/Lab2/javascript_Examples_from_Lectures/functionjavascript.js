let x = myFunction(4, 3); 
console.log("The product is",x)
console.log("The product is",myFunction(4,5))
//console.log(y)  

function myFunction(a, b) {
  var y=10;
  //console.log(y)  
    return a * b;   
           // Function returns the product of a and b
}

//function showMessage() {
//let message = "Hello, I’m JavaScript!"; // local variable
 //alert( message );
//}

//showMessage(); // Hello, I’m JavaScript!

//alert( message ); // Error! The variable is local to the function

let userName = 'John';

function showMessage() {
  let userName = "Bob"; 
   //userName = "Bob";

let message = 'Hello, ' + userName; 
console.log((message));
}
console.log((userName)); //
showMessage(); 
//y=showMessage;  
//console.log(y)
console.log(userName); //
