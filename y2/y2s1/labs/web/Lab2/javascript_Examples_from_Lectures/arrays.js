var myarray =[
    'Tuesday', 1, false, 0b111 //7
]
var myarray2=[
{'name':'John',
'ID':'1A11'}, [1,2,3], true, "Hello World"

]
console.log(myarray)
console.log(myarray.length)
//console.log(myarray2[3])
//console.log(myarray2[0].ID)
console.log(myarray[3])
console.log(myarray[myarray.length-1]) ///
//myarray[4]="new entry";
myarray.splice(2,1,'adding2') //
//myarray.splice(2,1,'adding2')
console.log(myarray)
//console.log(myarray.pop())
myarray.push('Monday')
console.log(myarray)
myarray.push('Sunday')
console.log(myarray)
myarray.pop()
console.log(myarray)

