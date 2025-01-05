const person = {fname:"John", mname: "No Middle Name", lname:"Doe", ID: 12512, age:25};
//let text = "";
for (let x in person) { 
  //text = person[x];
  //console.log(x)
  console.log(x,person[x]);
} 

//console.log("Next we have array elements")

/*const myarray =[
   'Tuesday', 1, false, 0b111
]
for (x in myarray)
{
  //  //console.log(x)
    console.log(x,myarray[x])
}*/

console.log("Next we have myarray 2 elements")
var myarray2=[
   {'name':'John',
    'ID':'1A11'}, [1,2,3], true, "Hello World"
    
    ]
    
  for (x in myarray2)
   {
        console.log(myarray2[x])

   }
  
  //String///
    var mystring="string123"
    
    for (var x in mystring)
  console.log(x,mystring[x])