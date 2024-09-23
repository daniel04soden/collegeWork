day= new Date().getDay()
console.log(day)
day = 0 || day == 6 ? console.log("Weekend") : console.log("week day")
