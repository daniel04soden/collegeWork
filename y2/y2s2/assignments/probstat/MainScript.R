# Author: Daniel Soden
# I declare all of this work as my own

library("BSDA")


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

print(paste("Rejection:",ans))

png(file="figure1-1.png") # Png file created on run for report
hist(baby_data$bwt, main="Histogram of Birth Weights", xlab="Birth Weight (grams)", ylab="Frequency", col="lightblue")
abline(v=3000, col="red", lty=2) 
dev.off()

# Question 2: Confidence interval for mean birth weight

ci <- 1.96 # 95% confidence interval
res_add <- population_mean_hypothesized + (ci*(population_sd_known/sqrt(n))) 
res_subtract <- population_mean_hypothesized - (ci*(population_sd_known/sqrt(n))) 

print("Confidence Interval:")
print(paste("95% chance that the population weight lies in(", res_add,",",res_subtract,")")) 

# Question 3: Box plot for correlation between baby weight and smoking 


print("Baby weight for non mother smokers")
non_smoking_mothers_bwt <- baby_data[baby_data$smoke == 0,]$bwt

# variables for demonstration purposes

summary(non_smoking_mothers_bwt)
IQR(non_smoking_mothers_bwt)
range(non_smoking_mothers_bwt)
print(IQR(non_smoking_mothers_bwt))
cv_non_smoking_mothers <- (sd(non_smoking_mothers_bwt)/mean(non_smoking_mothers_bwt))*100
print(paste("Coefficient of variation for non-smoking mothers",cv_non_smoking_mothers))
print(non_smoking_mothers_bwt)

png(file="figure3-1.png") # Png file created on run for report
hist(non_smoking_mothers_bwt, main="Histogram of Birth Weights of non-smoking mothers"
     , xlab="Birth Weight (grams)", ylab="Frequency", col="lightblue")
abline(v=3000, col="red", lty=2) 
dev.off()

print("Baby weight for mother smokers")
smoking_mothers_bwt<- baby_data[baby_data$smoke == 1,]$bwt
print(smoking_mothers_bwt)

# variables for demonstration purposes
summary(smoking_mothers_bwt)
IQR(smoking_mothers_bwt)
range(smoking_mothers_bwt)
cv_smoking_mothers <- (sd(smoking_mothers_bwt)/mean(smoking_mothers_bwt))*100
print(paste("Coefficient of variation for smoking mothers",cv_smoking_mothers))

png(file="figure3-2.png") # Png file created on run for report
hist(smoking_mothers_bwt, main="Histogram of Birth Weights of smoking mothers"
     , xlab="Birth Weight (grams)", ylab="Frequency", col="lightblue")
abline(v=3000, col="red", lty=2) 
dev.off()

png(filename = "figure3-3.png") # Save box plot for demonstration
boxplot(bwt ~ smoke, data = baby_data,
        main = "Effect of smoking on baby weight",
        xlab = "Smoking Mothers", 
        ylab = "Baby Weight")
dev.off()

# two sample z test

mean_non_smoke <- mean(non_smoking_mothers_bwt)
mean_smoke <- mean(smoking_mothers_bwt)
sd_non_smoke <- sd(non_smoking_mothers_bwt)
sd_smoke <- sd(smoking_mothers_bwt)
n <- length(smoking_mothers_bwt)
print(n)
m <- length(non_smoking_mothers_bwt)
print(m)

 z_test_three <- (mean_non_smoke - mean_smoke) / sqrt((sd_non_smoke^2 / m) + (sd_smoke^2 / n))
print(z_test_three)

p_val_three <- 2 * pnorm(q = z_test_three, lower.tail = FALSE)
print(p_val_three)

res_q_3 <- p_val_three < 0.05
print(res_q_3)
png("Figure-3-3.png")
qqnorm(smoking_mothers_bwt, main="Smoking present distribution",sub="Fig 3.3")
qqline(smoking_mothers_bwt)
dev.off()
png("Figure-3-4.png")
qqnorm(non_smoking_mothers_bwt, main="Smoking present distribution",sub="Fig 3.4")
qqline(non_hypertension_mothers_bwt)
dev.off()

# CI for difference in means

# Pooled difference

pool_sd_smoke <- sqrt(((n - 1) * sd_smoke^2 + (m - 1) * sd_non_smoke^2) / (n + m - 2))

degree_free_smoke <- n + m - 2
print(degree_free_smoke)

margin <- qt(0.975, df = degree_free_smoke) * sqrt(pool_sd_smoke^2 / n + pool_sd_smoke^2 / m)

# Confidence interval
low_smoke_ci <- (mean_smoke - mean_non_smoke) - margin
high_smoke_ci <- (mean_smoke - mean_non_smoke) + margin

print(low_smoke_ci)
print(high_smoke_ci)

# Question 4 - Two Sample t test

png(filename = "figure4-1.png") # Save box plot for demonstration
boxplot(bwt ~ ht, data = baby_data,
        main = "Effect of hypertension on baby weight",
        xlab = "Hypertension Mothers", 
        ylab = "Baby Weight")
dev.off()

non_hypertension_mothers_bwt <- baby_data[baby_data$ht == 0,]$bwt

hypertension_mothers_bwt<- baby_data[baby_data$ht == 1,]$bwt

mean_hyp <- mean(hypertension_mothers_bwt)
sd_hyp <- sd(hypertension_mothers_bwt)
mean_non_hyp <- mean(non_hypertension_mothers_bwt)
sd_non_hyp <- sd(non_hypertension_mothers_bwt)
x <- length(hypertension_mothers_bwt)
y <- length(non_hypertension_mothers_bwt)
pool_sd_hyp <- sqrt(((x - 1) * sd_hyp^2 + (y - 1) * sd_non_hyp^2) / (x + y - 2))
t_q4 <- (mean_non_hyp - mean_hyp) / (pool_sd_hyp * sqrt(1 / y + 1 / x))
degrees_freedom <- x + y - 2

p_value_four <- 2 * pt(-abs(t_q4), degrees_freedom)
print(p_value_four)
print(paste(p_value_four < 0.05))

t_test_result <- t.test(hypertension_mothers_bwt, non_hypertension_mothers_bwt, var.equal = TRUE)

print(t_test_result)

# Confidence interval for hypertension

margin_hyp <- qt(0.975, df = degrees_freedom) * sqrt(pool_sd_hyp^2 / n + pool_sd_hyp^2 / m)

# Confidence interval
low_hyp_ci <- (mean_hyp - mean_non_hyp) - margin_hyp
high_hyp_ci <- (mean_hyp - mean_non_hyp) + margin_hyp

print(low_hyp_ci)
print(high_hyp_ci)

# distribution graph q4

png("Figure-4-2.png")
qqnorm(hypertension_mothers_bwt, main="Hypertension present distribution",sub="Fig 4.2")
qqline(hypertension_mothers_bwt)
dev.off()

png("Figure-4-3.png")
qqnorm(non_hypertension_mothers_bwt, main="Non-hypertension present distribution",sub="Fig 4.3")
qqline(non_hypertension_mothers_bwt)
dev.off()

# Additional data for hypertension
IQR(hypertension_mothers_bwt)
IQR(non_hypertension_mothers_bwt)
range(hypertension_mothers_bwt)
range(non_hypertension_mothers_bwt)

