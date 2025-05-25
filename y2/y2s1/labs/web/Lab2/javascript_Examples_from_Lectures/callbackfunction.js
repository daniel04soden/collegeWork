const message = function() {  
    console.log("This message is shown after 3 seconds");
}
 
setTimeout(message, 3000); // here message is a callback function, which is called inside setTimeout.
// here it means the function message is called after something is happend

