
# What is Probability?

- A quantity indicating how likely a event to happen
- It is formalised by a branch of mathematics named probability theory
- There are however statistical inference techniques to estimate probabilities from empirical data.
- It branched into evaluating hypotheses given evidences and understanding the behaviour of random experiments

## Our study of probability


1. Interpreting how experiments are defined
2. Grouping possible outcomes
3. Counting


# Types

1. Classical and axiomatic probability: follows formal definitions
2. Experimental: Based on observations of real world experiments
3. Subjective probability; Personal judgements

- **Main focus**: Classical and axiomatic

# Random experiments

- We will study classical probability by using mental experiments
- The in between type is computational experiments ie simulations


## Real vs Mental experiments

| Mental Experiments                                     | Real world                                                    |
| ------------------------------------------------------ | ------------------------------------------------------------- |
| Repeated indefinetly                                   | Have intrinsic cost                                           |
| As soon as defined measure is defined                  | Measure needs to planned carefully                            |
| Unbiased                                               | Measuring Can influence and be influenced by the outcome      |
| Generalises the inner working of the process or system | Knowledge is built from memta anlaysis of several experiments |

 - A procedure that can be repeated infinitely can only lead to a fixed and known set of outcomes
- Each trial of the random experiments produces one outcome
### Examples
- Poker hand rankings
- Rolling 2 dies

- Each trial of the random experiment produces one outcome and an event is a subset of all possible outcomes

- An event with a single outcome is known as elementary 
- Generally an event is a group of outcomes

# Sample space

- For studying probability we will often need to interpret mental experiment problems and organise them into sets
- The union of all possible events is the sample space usually denoted with an omega symbol or an S

## Example

- Flipping two coins

- Outcome: Which face of the coin that lands up
- Sample space: **(HH,HT,TH,TT)**
	- Event:
		- A at least one head showing
		- Getting two tails
		- The same result on both coins
		- The coins show different results

- Since a sample space has all possible outcomes an event E has a complement E^c
- E^c is defined as all other elements not in E.

# Formal definition of probability

- Probability space is a triple (Sample space, how outcomes are grouped into events, Probability functions)
## Probability function

- A probability function maps an event to a number between 0 and 1 
- Probability of an empty event is always 0 the probability of the union of all events always 1 
- The others depend on how events are defined

	- Flipping two coins:
		- One head 3/4
		- two tails 1/4
		- Same reesult 2/4
		- Different results 2/4
## Complimentary probabilities 

- Since all probabilities add up to 1, one can find the probability of a complementary event via

$$
P(E`) = 1 - P(E)
$$

- Same face die (30/36)
- Not blue 5 red 3 blue 2 green:  7/10


# Experimental Probability

- Experimental probabilities are based on observed data
- You may not assume any prior knowledge of the process

In the case the probability of event E denoted P(e) is:

$$
\frac{times \ an \ event \ is \ denoted}  {total \ times \ experiment \ is \ performed}
$$
