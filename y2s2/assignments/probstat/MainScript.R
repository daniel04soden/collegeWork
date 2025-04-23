# Author: Daniel Soden
# I declare all of this work as my own

# Importing our dataset

baby_data <- read.csv("project_data_2025.csv")

# Question 1: Hypothesis test for mean birth weight

q1_mean <- mean(baby_data$bwt) # Mean body weight of sample 
q1_sd_sample <- sd(baby_data$bwt) # Standard deviation of sample
n <- length(baby_data$bwt) # Sample size

png(file="histogram_q1.png") # Png file created on run for report
hist(baby_data$bwt, main="Histogram of Birth Weights", xlab="Birth Weight (grams)", ylab="Frequency", col="lightblue")
abline(v=3000, col="red", lty=2) 
dev.off()

population_mean_hypothesized <- 3000 # Provided sample mean from 30
population_sd_known <- 500 # Provided standard deviation

z_score <- (q1_mean - population_mean_hypothesized) / (population_sd_known / sqrt(n)) # - Formula for test statistic of mean
p_value <- pnorm(z_statistic, lower.tail = FALSE)

print(paste("Sample Mean:", q1_mean))
print(paste("Sample SD:", q1_sd_sample))
print(paste("Sample Size:", n))
print(paste("Z-score:", z_statistic))
print(paste("P-value:", p_value))

# Question 2: Confidence interval for mean birth weight

z <- 1.96 # 95% confidence interval
resAdd <- population_mean_hypothesized + (z*(population_sd_known/sqrt(n))) 
resSubtract <- population_mean_hypothesized - (z*(population_sd_known/sqrt(n))) 

print(paste("95% chance that the population weight lies in(", resAdd,",",resSubtract,")"))