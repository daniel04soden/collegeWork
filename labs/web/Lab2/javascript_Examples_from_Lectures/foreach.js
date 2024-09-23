const numbers = [45,4,9,16,25];
console.log(numbers.forEach(myFunction));

function myFunction(value)
 {
     console.log(value);
     console.log("Hello World")
    } 
  const number2 = [65, 44, 12, 4];
    number2.forEach(myFunction)
    
    function myFunction(item, index, arr) {
      arr[index] = item * 10;
      console.log("The item at index",index,"is",item)
      console.log("When",item,"is multiplied by 10 it becomes",arr[index])
    }