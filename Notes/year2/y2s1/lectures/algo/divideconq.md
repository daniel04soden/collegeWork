# Call Stack:

``` java

void func1{
    return;

}

void func2{
    value1 = func1()

   return value1
}

void func3{
    value2 = func2()

   return value2
}


```

- LIFO Stack data structure

## Local variables

- Local variables must also be remembered for after any nested procedure
  returns

  - Therefore, they are maintained and accessed on the call stack as
    well
    - When a local variable is declared
      - Push initial value
      - LocalVariablePointer = StackPointer
    - When a local variable is accessed
      - Memory(localvariablepointer)

      <!-- -->

      - When a local variable loses scope

## Parameters and return values

- Parameters in most languages are passed by value, meaning they become
  new local variables in the scope of the procedure and are therefore
  also maintained on the call stack.
- Return values on the other hand are not

``` java
int procedure(int i){
    return i+1;
}

void caller() {
    int j=0;
    j = procedure(j);

}

```
