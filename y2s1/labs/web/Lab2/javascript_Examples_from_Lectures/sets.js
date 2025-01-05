// Create a Set
//const letters = new Set(["a","b","c",1,0b111])
const letters = new Set(["a","b","c","1",1,0b11,{fname:"firstname",lname:"last name"}]);

// List all Elements
letters.add("d")
letters.add("e")
letters.add("e")
letters.delete("a")

letters.forEach (function(value) {
  console.log(value)
}) 

/*for (x of letters.values())
{
    console.log(x)
}*/