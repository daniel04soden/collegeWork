function myFunction(x, y) {
  function anotherfunction (a) {
return a*a;
  }
    console.log("The first argument is",arguments[0])
    console.log("The 2nd argument is",arguments[1])//
  if (y === undefined) {
   y = anotherfunction(3);
  //y=1;
  }
  return x*y;
  
}
//function function2()
//{}
console.log(myFunction(4)); ///
//console.log(z)
//w=function2();
//console.log(w)