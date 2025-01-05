let original = 10;
function updateOriginal(originalVal) {
    for (let index = 0; index < 200; index++) {
        originalVal += 10; 
        console.log(originalVal)        
    }

console.log(originalVal);
}
updateOriginal(original); 
console.log(original);
