# Substitution Method

## Avoiding Pitfalls

- It is very important to be careful with the constant factors in O notation
- It may be tempting to reason the solution to T(n) = 2T(n/2) + -O-(n) is T(n) = O(n).
- However this is fundamentally wrong as the constant factors hidden in the O notation must be consistent throughout the argument
- Two notations asking for trouble, what can we ignore?? - Refer to different constant factors
- Writing it out with explicit factors with c and c\` as before we can see why it O(n) is wrong as we cannot choose a consistent constatn c to show T(n) <= cn
- If in doubt write out all constants


## Tweaking the inductive hypothesis
$$
T(n) = 2T(n/2) + -O-(1)
$$

- By applying the same reasoning we get T(n) <= 2c(n/2) + -O-(1) 
- Therefore !<= cn
- This did not work however we can make the inductive hypothesis stronger
- Instead of using T(n) = cn for O(n), we subtract lower order terms and work with T(n) = cn -d instead.
- Then we argue that
- T(n) <= cn - d