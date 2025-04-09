Ex1=read.table(file = "FatContent.txt", header = TRUE, sep = "\t")
attach(Ex1)
n <- nrow(Ex1)

xbar <- mean(Ex1$Total.Fat)
std_dev <- 2.6
std_err = (std_dev/sqrt(n))

print(std_err)

hist(Total.Fat, breaks=10, prob=TRUE, xlab="Total Fat (g)") 
curve(dnorm(x, mean=xbar, sd=std_dev), col="darkblue", lwd=2, add=TRUE, yaxt="n")

print(qqnorm(Total.Fat))
print(qqline(Total.Fat))

std_errrrr <- (x - xbar) / std_dev

mu <- 15 # Set mean value equal to the population mean given in the null hypothesis
z <- (xbar-mu)/ std_errrrr # Calculate the z-statistic
Pvalue <- 2*pnorm(-abs(z)) # Calculate the p-value for a two-sided test

me <- qnorm(.975)*std_errrrr

print(xbar - me)
print(xbar + me)

