var fs = require('fs')

datajson = require('./data.json')

console.log(datajson.name)
console.log(datajson.age)
console.log(datajson.car)


fs.readFile("./data.json","utf-8", (err,data)=>{

    if   (data)
    {
         var data_json =JSON.parse(data)
         console.log("\nSuccess")
        
    }
    else
    {
          console.log('Error')
    }
})