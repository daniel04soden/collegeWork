- We can model real-world situations with probability distributions,
  including the binomial, poisson and normal distributions
- It is a list of all the possible outcomes of a random variable
- Along with their corresponding probabilities
- Random variable is a mapping between events and numbers \##
  Complimentary probabilities
- Since all probabilities add up to one, we can find the probability of
  a complementary event through:
- P(event not occuring) = 1 - P(event occuring)

## Experimental probability

- Experimental probabilities are assigned based only on observed data,
  you may not assume any prior knowledge of the process! P(E) = times
  event observed/times experiment performed
- If Someone flipped a coin 1000 times, and registered how it landed,
  headds oversved 510 times, the probability of a coin landing with
  heads 510 times in 1000 trials is 510/1000, not exactly 50% but very
  close, close enough to draw the 50% conclusion

# Binomial Distributions

- Many experiments have bianry outcomes ie buying/not buying,
  failing/not failing etc

- Each trial of an experiment with binary outcomes can be called a
  bernoulli trial.

- Let p be the rporbbaility that an event occurs and hence 1-p becomes
  the probability that it doesn\'t occur resulting in the a probability
  of :
  $$ P(X=x), 1 = p, 0 = 1-p
   $$

- This is known as the bernoulli distribution and lets us introduce bias
  towards one of the outcomes via p. 

- The probability funtion for the bernouli distribution is as follow

$$P(X=x) = p ^ x (1-p) ^1-x$$
\## Binomial co-efficient:

- If we know the probability p and the number of trials n, we can figure
  out the probability of one outcome easily
- But how do we count how many of these outcomes are there. The number
  multiplying these probabilities is the binomial coefficient
- The kth position of the ngth levels tells us how many ways there are
  to combine k elements within n
- We denote the combination of k items among n as n choose k where it is
  esennitaly just n factorial divided by k factorial by n-k factorial
  but this is usually done easily in the calculator

## Important binomial formula

- The most important calculation for binomial distributions are when you
  have a fixed n and p and the porbability of a binomial random variable
  being equal to is given by:

$$P(x=k) = nCk * p^k * (1-p)^n-k$$

- P - Probability of success
- 1-P - Probability of failure
- k - number of successes
- n - Number of trials \### Examples:

Question 1: Suppose a salesperson calls 7 numbers every hour.\
The probability they close the deal is 10%. Calculate the\
probability for - No sales: - 𝑃\[𝑆 = 0\] = 𝐶7 0.10 (1 − 0.1)7 =0
0.4782969 - No sales is just equals 0 - 3 sales is the same except k =
3 - 7 sales is the same except k=7 - Less than 2 - just 0 sales + 1
sale - 5 or more sales - From n down to 5, add the P\[X=7,6,5\] - At
least 3 of the 7 - add P\[X=3..7\]

## Expected value of the mean

- When we know our probability of each value x we can get the expected
  value of the random variable x with:

- Mean = Sum of n 1-n, xi\*p(xi)

- Where xi represents each possible outcome, p(xi) is their
  corresponding probability and n is the number of outcomes \## Standard
  deviation

- Measure of variation with the same scale as the mean:

  - \![*Pasted image 20250520170325.png*]{.spurious-link
    target="Pasted image 20250520170325.png"}
  - Where x1 is each possible outcome p(xi) their corresponding
    probability and n is the number. \## Mean and sd of a binomial
    random variable

\![*Pasted image 20250520170458.png*]{.spurious-link
target="Pasted image 20250520170458.png"} \# Poisson Distribution \##
Poisson random variable - A poisson random variable is a binomial
distribution for an arbitrarily large number of trials - Given an
average rate of events within some time frame, you can estimate how
likely any number of events can happen within that same time

## Poisson distribution function

- Probability function for the poisson distribution is written as
  follows:
  $$ P[X=x] = m^x e^-m \div x!
   $$

## Cumulative Poisson distribution function

- Probability that r or more occurrence happen during the interval of a
  Poisson variable X is:
  $$ P[X>=x] = m^x e^-m \div x!
   $$

- We can use a table directly to find this value (or complimentary of
  that value if we are looking for P\[x\<r\]) \# Normal Distribution

- Normal distribution is represented in a symmetric and concentrated
  around the center like a bell shape.

- The standard Z score is calculated as follows: \$\$ :

\$\$

- The standard score translates any normal distribution to the standard,
  that is P\[X\>=x\] in the og curve has the same area as P\[X\>=z\]
