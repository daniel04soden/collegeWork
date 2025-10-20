# Why is testing needed?

- Software we build is
  - Very complex
  - Too big
- We work in teams
  - Someone may break our code
- I cannot predict any conditions on running software
- Especially when humans are involved
- This is why QA exists

## Levels of testing

### Unit testing

``` java
double division(double x, double y){
        double res;
        if ((x == 0 || y == 0) || (x == 1.00000001 || y == 0.0000000001)){
            throw runtimerr; // Edge cases of 0 division, overflow/underflow
        }else{
            return x/y;
        }
    }

main(){
double d = divison(2.0,0);
if (d == {desiredRes}) return true;
}
```

- Unit test
  - As we will see
    - Normal values
    - Abnormal values
    - Edge values \### Integration testing
- Do different units work together?

### Continuous integration

- Automated tests runs every time we push new code
- Applies mainly to unit and integration testing
- Very important during the dev process

## Extreme programming

- test written before the code itself \### Test suites

- All code needs to be tested to ensure the code is working as expected

JUnit

- Framework for writing tests
- Uses reflector
- Helps the programmer define and execute test and test suites
- formalise requirements and clarify architecture
- Write and debug code
- Integrate code and always be read to release a working version

1.  Terminology

    - Unit test single class
    - Test case - test of a single function

2.  Assert methods

    - Within a test
      - Call the method being tested and get the actual result

# Is Junit overkill for a little class?

- If it isnt tested it doesnt work

- You are not likely to have many classes trivial in a real program
