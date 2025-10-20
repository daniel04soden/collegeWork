# Exception Handling

## Errors and error handling

- Due to design erors or coding errors our programs may fail in
  unexpected ways during executions

- Or due to users using the program in unexpected ways

- Error is any unxecpted reuslt from a program during executions

- Unhandled errors may manifest themselves incorrect results or
  behaviour or as abnormal program termination

- Errors should be handled by the programmer

- Erros and bugs are not the same thing

- Bugs are moreso logical it\'ll work but not how you\'d like it to

- Memory errors

  - Memory leak

- FS Errors

  - Disk full

- Network errors

  - Network down

- Calculation errors

  - Division by 0 error \### Causes of errors

- Array errors index out of bounds

- Conversion errors

- More...

### Traditional error handling

- Every method returns a value flag indicating either success failure or
  some rror condition the calling method checks the return value and
  takes appropriate action

- Programmer must check return values

- Requires more code

- May get overlooked \### Motivations

- How do we handle the runtime error so the program can continue to run
  or terminate gracefully? \## Exceptions

- Mechanism that provides best of both worlds being built in and user
  defined exceptions

- Checked - comp time

- Unchecked - run time \### Checked exceptions - Compilation time

- Checked exceptions are logical exceptions inherited from the core
  class exception. They represent nonfatal program execution

- They must be handled in the code or passed to parent class handling
  (Bubbling) \### Unchecked exceptions - Run time

- Something that isn\'t fatal you don\'t have to do anything about it
  but your program will terminate with an appropriate error message

## How to handle them

- Try catch, try except

``` java
try{
statements
}catch (Exception exVar1){
handler for exception1;
}
```

- This can be done by clean up or retry process

- Print a message for the user explaining what they did wrong and what
  they now need to d

- You can use the printStackTrace method to chase the error

- The last clause is finally it will always be executed either after the
  try block code or after the catch block code

- Finally blocks can be used for operations that must happen no matter
  what such as cleanup operations like closing a file

- Throwing exceptions ie throw new IllegalArugement exceptions

- Only use exceptions that make sense, it separates error handling code
  from normal programming tasks

- Be aware exception handling usually requires more time and resources.
  ​## When to use exceptions

- To deal with unexpected error conditions

- Do not use it to deal with simple expected situations

- Exception to be preocessed by its caller?

  - Create an excpetion object and throw it

## Declaring exceptions

- Every method must state the types of checked exceptions it might
  throw.
- This is known as declaring exceptions

### Creating your own exceptions

``` java
class NewException extends execptions

contructor

getMessage
getError
getValue
etc
```

## Types of exceptions

- Checked
- Unchecked \## Unexpected exceptions
