# Module Introduction & Java basic syntax

``` java
// Simple hello world program
public class myMain {
    public static void main(String[] args){
        System.out.println("Hello, world!")
    }
}

```

## Background: Java Programs execution

- Most computers with Java know things such as for loops,booleans, data
  types, if statements etc
- However it doesn\'t know things such as logarithms so we need to
  import certain libraries

``` java
// Importing files (sample not 100% accurate)

import Java.Math
    Math.log()

```

### Type system

- Python is a dynamically typed language

  ``` python

  x=12
  print(x)

  ```

- Java is a statically typed language

  ``` java
  int x = 12;
  boolean c = true;
  ArrayList<Integer> e = new ArrayList<~>();
  e.add(1);

  System.out.println(x);

  ```

### Operators:

- Python: and, or, \>,\< ,\>= etc
- Java: &&, \|\|, \>,\<,\>- etc

### Functions in Java

- In programming, modularity refers to the separation of the
  functionality of a program into independent interchangeable pieces,
  each of them fully repsonsible of a concrete task

  - Advantages of Modularity:
    - As size and complexity grows, tasks may become difficult but the
      decomposition of a problem into multiple small and independent
      pieces allows to better model the problem domain
