# What is it ?

- Statistical inference combines the concepts of sampling summarisation statistics and probability distributions
- All to determine estimates of a population parameter of interest
- Range of values confident in
- Test claims made about a population parameter

- Rather than just estimating parameter we need to judge the evidence supporting claims
- A hypothesis is a claim made about a population parameter whose truth has yet to be proven or disproven

- Examples:
	- Smoking causes cancer
	- Vaccines prevent diseases 
	- Government is overall approved by the population

- When stat methods aare used to test a hypothesis the result is always a statement of probability
- Statistical methods will never prove or disprove a hypothesis with certainty but it may be possible to attach a very high probability to your conclusion
- A hypothesis test then compares two mutally exlcusivne hypotheses to determine which has more statistical evidence
- The null hypothesis claims that an effect being studied does not exist/has no statistical support
- The alternative hypothsis claims that na effect being studies does exist/has statistical support

## Hypothesis test steps
1. Given a claim we state the null and alternative hypothesis and express them in a symbolic form
- The null hypothesis is the claim to be tested - crime rate has falle
- The alternative is usually the opposite - crime rate has NOT fallen
- The burden of proof is on Ha and Ho and is rejected only if the sample data provides evidence against it and in favour of Ha
$$
p = 0.35
$$
$$
Ho: p < 0.35
$$

$$
HA: p >= 0.35
$$
2. Calculate test statistic
- The test statistic is computed from the sample data and is used to decide if you 
- Fail to reject Ho
- Reject Ho for Ha
- The general formula is 
$$
sample Mean - mean/ s*sqrt(n)
$$
- For a claim about the proportion the formula is 
$$
p^ - p/ sqrt(p^*(1-p^)**n)
$$
3. Given a significance level, identify the critical values
- Closely realted to the significance level alpha which is the probability of rejecting Ho when it is true
- Choosing the significance level alpha depends on various factors such as the context of the research
- For example in medical research where the consequences of a false positive could be harmful a low significance level is preferred
- The critical region is the region that leads to rejecting Ho
- Critical value is any value that separates the critical region from the fail to reject Ho region
- If the null and alternative hypothesis contain an = and !=, respectively, then we use a two tailed test and the significance level alpha is divided equally between the two tails of the rejected Ho regions (tails)
- We use a two tailed test and depending on the significance level alpha may fail to reject Ho or reject Ho
- We use a right tailed test depending on the significance level alpha which mail fail to reject Ho or reject Ho
4. Compare test statistic to critical values and state the conclusion of the test
- Once you hjave the value of the test stat, it is compared to the critical values
- We can then say whether the evidence support Ha or roeject Ho

# Main Steps
1. Given a claim state:
	1. Null hypothesis
	2. Alternative hypothesis
2. Calculate the test statistic
3. Given a significance level alpha, identify the critical values
4. Compare test statistic to critical values, and state the conclusion of the hypothesis test