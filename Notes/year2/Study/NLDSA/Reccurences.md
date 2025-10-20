# Analysing performance of recursive algorithms

- Quantifying asymptotic performance of recursive algorithms usually
  involves solving recurrence equation.
- Solution proven correct through induction
- Recursion trees useful visualisation
- **Tail recursion**: a recursive function in which the recursive call
  is the final statement executed by the function
- We can transform tail recursion into iterative and evaluate
  performance from there.
- \![*Pasted image 20250513192417.png*]{.spurious-link
  target="Pasted image 20250513192417.png"}
- If there are many recursion calls like in our order traversals, other
  techniques must be used to evaluate performance of the algorithm

## Performance depends on

1.  Depth of call stack
2.  How many recursive calls
3.  Remaining runtime (any other inner loops)
4.  Can the base case be solved more efficiently

## Divide and Conquer

### Divide and conquer structure is as follows:

``` py
if p small to solve now:
    return solve
else:
    divide into smaller sub problems:
        p1..pa = divide(p)
    conquer sub problems:
        r1..ra = mapa(p1..pa)
    combine the solutions of the subproblems:
        return reduce(r1..ra)
```

- Base cases assume our instance is small enough implying it can be
  solved in constant time

- If not, we break our problem into smaller sub problems

- Each problem is solved on its own

- Work is done and we return so for next recursive call \### Recurrence

- Divide and conquer runtime is usually :
  $$ T(n) = aT(n/b) + f(n)
   $$

- Runtime determined by:

  - Recursive calls
  - What fraction are the sub-problems divided into
  - Driving function f(n) that describes the runtime of each instance

## Master theorem

\![*Pasted image 20250513193316.png*]{.spurious-link
target="Pasted image 20250513193316.png"}

- Solution shows how performance is determined by the two execution
  paths of the divide and conquer schema, the base case and execution of
  the core function
- Basis for the master theorem, provides sultions for important problems
  by comparing driving function with the number of base cases and
  determining which of the two dominates overall performance
