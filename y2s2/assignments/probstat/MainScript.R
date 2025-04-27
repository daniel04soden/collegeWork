# Author: Daniel Soden
# I declare all of this work as my own

# Library

library(BSDA)

# Importing our dataset

baby_data <- read.csv("project_data_2025.csv")
summary(baby_data$bwt)
summary(baby_data$age)

# Question 1: Hypothesis test for mean birth weight

# Step 1

q1_mean <- mean(baby_data$bwt) # Mean body weight of sample calculated - for comparison
population_mean_hypothesized <- 3000 # Provided sample mean from 30

population_sd_known <- 500 # Provided standard deviation

n <- length(baby_data$bwt) # Sample size


# - Formula for z-test statistic of mean
# step 2
z_test_stat <- (q1_mean-population_mean_hypothesized)/
  (population_sd_known/sqrt(n))

# Calculating p score for right one tailed test:

# step 3

critical_value <- qnorm(1-0.05)
print(critical_value)

# Step 4

ans <- z_test_stat < critical_value

# Output for demonstrations

print(paste("Sample Mean:", population_mean_hypothesized))
print(paste("Calculated Mean:", q1_mean))
print(paste("Sample SD:", population_sd_known))
print(paste("Sample Size:", n))
print(paste(" Size:", n))
print(paste("Z-test statistic:", z_test_stat))
if
print(paste("Rejection:",ans))

png(file="histogram_q1.png") # Png file created on run for report
hist(baby_data$bwt, main="Histogram of Birth Weights", xlab="Birth Weight (grams)", ylab="Frequency", col="lightblue")
abline(v=3000, col="red", lty=2) 
dev.off()

# Question 2: Confidence interval for mean birth weight

ci <- 1.96 # 95% confidence interval
res_add <- population_mean_hypothesized + (ci*(population_sd_known/sqrt(n))) 
res_subtract <- population_mean_hypothesized - (ci*(population_sd_known/sqrt(n))) 

print("Confidence Interval:")
print(paste("95% chance that the population weight lies in(", res_add,",",res_subtract,")")) # nolint: line_length_linter.

# Question 3: Box plot for correlation between baby weight and smoking 



png(filename = "boxplot_q2.png") # Save boxplot for demonstration
boxplot(bwt ~ smoke, data = baby_data,
        main = "Effect of smoking on baby weight",
        xlab = "Smoking Mothers", 
        ylab = "Baby Weight")
dev.off()
