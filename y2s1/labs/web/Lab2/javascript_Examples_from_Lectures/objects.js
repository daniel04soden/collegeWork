var car = {
    make: 'Ford',
    model: 'Mustang',
    year: '2005'
  };
car;
//var car2=car;
var car2=JSON.parse(JSON.stringify(car));
//console.log(car2.make)
car2.year='2010';
//car3.year='2000'
console.log("car 2 year", car2.year)//2010?
console.log("car 1 year", car.year)// 2005?
//console.log("car 3 year", car3.year)