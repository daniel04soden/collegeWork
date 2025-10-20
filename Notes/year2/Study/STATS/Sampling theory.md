# Sampling theory - inro

- **Parameter**: feature of a population that we are interested in

- **Mean**: average of a population

- **Proportion**: P ie the section of a population that apply to what we
  are surveying

- **Sample Mean**: estimated average of a sample (eg average weight of a
  sample mean)

- **Sample Proportion**: Provides an estimate of p

- **Point estimate**: A single value used to approximate the population
  parameter

- Subject to Sampling error

- Interval estimate is a type of estimation that uses an interval of
  values, based on sampling info to estimate the poulation parameter

- The value of the sample statistic can be modelled as a random variable
  (ie numerical quantity whose value depends on the random outcome of an
  experiment)

- The probability distribution of the sample stat is known as the
  sampling distribution

- When several samples are taken from the sample population and the mean
  is calculated, the sampling distribution of the mean is formed

- Central limit theory states the mean of sufficiently many random
  variables which are independent and identically distrubted converges
  to a normal distrubtion with the same mean and similar standard
  deviation

# Confidence intervals of the mean

- For the Confidence interval of the mean we use the population and
  sample mean

- The CLT states that the sampling distrubtion of the mean x is normally
  distrubted with mean u and standard deviation sdxsqrt(n)

- Approximately two sd form the mean u should capture 95% of the sample
  mean

- A distance of 1.96 x sd/sqrt(n) = 1.96 x SE(x)

- The interval x+- 1.96 sd sqrt(n) is the 95% ci of the population mean
  u

- From here we get our z score, calculated by: z = x-u/sd which gives us
  P\[Z\>= z\]

- Confidence level is a measure of the level of certainty that the true
  population parameter lies wihtin the calculated Confidence interval

# Confidence interval of the Proportion

- For ci of the Proportion we use population Proportion p, sample
  Proportion

- Assuming a large sample size, the sampling distribution of a mean is
  approximately normal and the se of the mean is sd/sqrt(n)

- To estimate the poulation Proportion p webuild on the Confidence
  intervals for a mean and then modle characteristics we look at in the
  population as a bernouli variable

- Bernouli variable with proportion p has expected value p and sd
  sqrt(p\*(1-p))

- SE of the proportion is sqrt(p^(1-p^)/n)
