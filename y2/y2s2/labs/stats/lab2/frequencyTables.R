# Part 1 


scores<-c(0, 1, 0, 9, 10, 2, 5, 5, 4, 3, 7, 6, 8, 9, 2, 3, 0, 5, 6, 5, 7, 8, 7, 4, 3, 8, 6, 6, 5, 6, 1, 9, 7, 4, 3, 5, 2)
table(scores)


# Part 2 Graphical Methods

x <- c(49.2,49.6,49.9,50.3,50.4,50.6, 50.6 ,50.7,50.8,51.1,51.1,51.3,51.3,51.4,51.4,51.6,51.6,51.7,51.8,52.0,52.1,52.2 	,52.3 	,52.4 	,52.7 	,52.8 	,53.2 	,53.4)

stem(x)

# Measures of centrality

mean(x)
median(x) # Median best measure of centrality given its an array
mode(x)

# Measures of Variability

range(x) # Range is best measure of variability
sd(x)
IQR(x)

# Q2

xy <- c(2,3,7,7,8,9,10,11,11,13,13,13,13,13,14,14,14,15,15,15,17,17,17,18,19,19,20,20,21,21,21,22,23,25,25,26,27,28,31,47)

table(xy)

