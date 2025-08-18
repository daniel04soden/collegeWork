x1 <- c(82,45,53,91,70,10) # Creating an array in R

x1

x2 <- c(87,60,51,95,80,12)

totalx <- x1 + x2  # Adding the same length value array i think

totalx # Write variable to print

y1 <- 5 : 13 # 5 to 13 in increments in 1
y1

y2 <- 6.6 : 12.6 # 6.6 to 12.6 in increments in 1
y2

x1[2] # Returns 45

x1[-4] # Returns all other methods bar at index 4.. what use is this?


x1[1:5] # Returns first k=5 elements of the vector

x1[(length(x1)-2):length(x1)] # last 3 elements of vector x1

x1[c(1,3,5)] # Returns 1st 3rd and 5th elements of vector x1


x1[-c(2,4)] # returns everything but the 2nd and 4th elements of vector x1 

x1[x1>70]
