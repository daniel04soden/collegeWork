const fruits = new Map([
  ["apples", 500],
  ["bananas", 300],
  ["oranges", 200]
]);
fruits.set("Mangos",1000)
fruits.set("graps",500)
console.log(fruits)
console.log(fruits.get("Mangos"))
console.log(fruits.size)//
fruits.delete("apples");
console.log(fruits.has("graps"));
console.log(fruits.has("apples"));
//x=fruits.entries()
//console.log(x)