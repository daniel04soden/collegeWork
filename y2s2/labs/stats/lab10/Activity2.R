library(plotrix)

Ex2 <- read.table(file = "FamilyEnergyCost.txt", header = TRUE, sep = "\t")

# Sample mean
x <- mean(Ex2$Energy_Cost)

# Observations
n <- nrow(Ex2) # Sample size less than 30, not normally distriubted
y <- 200

ho <- y == 200 
ha <- y != 200 

devia <- sd(Ex2$Energy_Cost)
errorroror <- std.error(x)




# hist(Energy_Cost, breaks=5, prob=TRUE, xlab="Energy Cost ($)", main="Histogram of Energy Cost") 
# curve(dnorm(x, mean=m, sd=stdev), col="darkblue", lwd=2, add=TRUE, yaxt="n")

ab <- qqnorm(Energy_Cost)
ba <- qqline(Energy_Cost)

t.test(Energy_Cost, mu = 200, conf.level = 0.95)
