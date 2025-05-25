const fs = require('fs');

var data1={
    name:'bobby',
    age:12
}

const content = 'hi guys my name is ' + Object.values(data1)[0]

fs.writeFile('./dataWrite.txt', content, err => {
  if (err) {
    console.error(err+'There was a write error')
    return
  }
  //file written successfully
})