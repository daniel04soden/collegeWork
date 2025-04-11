# Author: Daniel Soden
# I declare all of this work as my own

# Importing our dataset
baby_data <- read.csv("project_data_2025.csv")
# Defining a sample vector
x <- rnorm(100)


# Question 1: Hypothesis baby weight test:

# Calculating variables 
q1_mean <- mean(baby_data$bwt) # Actual mean
print(q1_mean) 
q1_sd <- sd(baby_data$bwt) # Actual standard deviation
print(q1_sd)
Ho <- q1_mean > 3000 # Null hypothesis
Ha <- q1_mean == 3000 # Alternate hypothesis

# Test output:
t.test(x,mu = q1_mean,alternative = 'greater')

# Plot for demonstration:

# creating a sequence of values 
# between -15 to 15 with a difference of 0.1

out = dnorm(baby_data$bwt, q1_mean, q1_sd)
png(file="normDistroq1.png")
plot(baby_data$bwt, out)
dev.off()

