
# Frequency Tables
- There are both frequency tables and grouped frequency tables
## Frequency tables
- Frequency tables are mainly used for discrete data 
- They list individual data values
### Example 
![[Pasted image 20250517163839.png]]
- As we see above, it simply counts the number of times, there has been 0 goals, coming out to 2, and there were 5 instances of 2 goals.
- When looking at the at least 3 goals this includes 3,4 and 5 which is how it adds up to 7.
### Cumulative frequency on an individual level

- Cumulative frequency is generally done within ranges, it is achieved by adding up the frequencies as you go along.
- The final cumulative frequency is equal to the total number of data values
- Cumulative frequencies are used to determine the number of observations that lie above a particular value in a data set
- Find specific values in a data set (eg median, mean, first and third quartile then IQR)
- Draw ogives (cumulative frequency graphs):
	- ![[Pasted image 20250517170706.png]]
- ![[Pasted image 20250517165142.png]]

### Relative frequency
- The relative is the frequency divided by the total frequency, this data is stored as either fractions, decimals or percentages. 
- Above the frequency could be calculated by dividing each frequency by 32 so the relative frequency of having 0 bugs is 2/32
- ![[Pasted image 20250517165432.png]]

## Grouped Frequency

- Again, grouped frequency is very similar to standard frequency except it can be used on continous data as well as discrete. On top of this data is organised into intervals such ranges like 0-5, 25-30 etc.
- This way of recording frequencies allows for a much larger range of data to be recorded in a much more realistic viewpoint. Instead of recording each score or each number of bugs, we can record a range of bugs 0-5,10-15.
- ![[Pasted image 20250517172047.png]]
- A grouped frequency table gives classes in which the values wil lfall and the number of vlaue sin that class
- The classes CANNOT overlap and there shouldn't be any gaps between them ie 100-105, then cant be 105-110, has to be 106-110
- A straightforward way to begin working with a grouped frequency table is to sort the table values in ascending order therefore, its a lot easier to pick out the data in the ranges.

- A grouped frequency table should have at least 5 classes, but no more than 15. 

- Class width is sometimes simple, in stretches of 5s,10s etc however it is actually given by :
$$
	- Range/Length(classes) = largest-smallest / Length(classes)
$$
- The number of classes and width are essentially arbritrary, the only rules in grouping data are that the classes are mutually exclusive and all-inclusive
- ![[Pasted image 20250517172425.png]]


# Graphical Methods


- **Graphical methods:** The presentation of data in a graphical format
- The type of graphical method we use depends on the data type (whether categorical or numerical)
- **Categorical data** : does not mapt to a numeric scale therefore must be divded into categories
- Categorical data can be represented using bar charts and pie charts
- Bar charts are used to represent data where each category is represetned by a bar whereas pie charts have a circle where the percentage of data taken up in a particular category is represented by a slice of data in a pie
- **Numerical data**: data which has numerical values.
	- Numerical data is represented by:
		1. Stem and leaf plots
		2. Histograms
		3. Box plots
- Steam and leaf example:
	- ![[Pasted image 20250517172941.png]]
- Histogram example:
	- ![[Pasted image 20250517173000.png]]
- Boxplot example
- ![[Pasted image 20250517173351.png]]
## Stem and leaf plot
- Stem and leaf plots are used to visualise numeric data 
- In simple terms, we just have the first digit in a 2 or 3 digit number on the left side and the leaf on the right hand side showing a 1 or 2 digit instance of the leading number eg,
- 1| 2,4,5 - would mean 12,14,15
- It is a useful way to represent smaller sets of data as 
	1. All original vallues are shown
	2. It illustrats the data distribution and where the data is concentrated
- Example:
	- ![[Pasted image 20250517173654.png]]
- There are left and right skewed stem and leaft plots, the easiest way to determine them is honestly, looking at it in an opposite point of view. If there is more data on the left its right skewed and if theres more data on the right its left skewed
- The data set above can be considered left skewed.

![[Pasted image 20250517173943.png]]
- This data set is right skewed

## Bar charts
- The X axis shows the categories in each data set and the y axis shows the frequency/number of each category:
- ![[Pasted image 20250517174221.png]]

- A histogram is a bar chart that consists of a series of *vertically* drawn bars. They are used for continuous data and has the same idea except the x axis more accurately represents ranges in that we can see below the ranges expressed as their start and end points
![[Pasted image 20250517175204.png]]
- The shape of a histogram provides important info about the distribution of the data
- The two shapes of interest are symmetric (bell shaped also known as normalised) and asymmetric (skewed).
- These properties are important in terms of determining what method of analysis is required for the statistical inference

- Left and right skewed:
	- Left skewed is where most of the data is to the right of the histogram
	- Right skewed is where most of the data is to the left of the histogram
	![[Pasted image 20250517175424.png]]

### How to construct a histogram?

- Given a grouped frequency table, with different class widths, to construct a histogram you must first calculate the frequency per unit for each interval:
1. Calculate the width of each class (upper bound - lower bound)
2. Select the unit size (the smallest width of the classes)
3. Calculate the number of units in each class (width/size)
4. Calculate the frequency density for each class (frequency/number of units)
- After you have calculated the frequency per units, construct the histogram using:
	- The classes for horizontal axis
	- The frequency per unit for the vertical axis
- Example:
	- ![[Pasted image 20250517180753.png]]
## Box plots:

- When constructing box plots we have to consider the five number summary:
1. Minimum
2. Q1 (First quartile )-  value under which 25% of the data points are found in an ordered dataset
3. Q2 (median) - value under which 50% of the data points are found in an ordered dataset
4. Q3 (third qurtile) - value under which 75% of data points are found in an ordered dataset
5. Maximum

- A **boxplot** is a graphical representation  of data absed on this fice number summary
- Range,median,Q1,Q3, IQR
- It's shape provides info about the distribution of the data. It allows us to identify certain statistical values (eg median min etc.)

- It divides the data into section that each contain 25% of the data.
![[Pasted image 20250517181149.png]]
- You can also see how data is skewed in box plots 
- An **outlier** is a data point that differs **significantly** from other observations 
- Boxplots are mainly used to detect outliers that exists in a dataset


- Main structure of a box plot:
![[Pasted image 20250517181412.png]]
- The min and max are not always the min and max in the dataset values
- They be a function of the IQR, which is the length going from the first to third quartile
- If the min data value is higher than Q1-1.5 the IQR, the whisker stops in the min value. The max is analogous (it is the max data value if that value is lower than Q3+1.5xIQR)

- Boxplot construction:
1. Calculate the five number summary
2. Draw a horizontal line representing the scale of measurement for the data
3. Draw a box above the line wiht the left and right ends at Q1 and Q3
4. Draw a line through the box at the location of the median
5. Find the length of each whisker by evaluating the min and max
6. Finish it by drawing the outliers with circles

- Example:
![[Pasted image 20250517181758.png]]
# Numerical methods
## Methods of centrality
- The value of the center, or middle of a data set is determinted through these three measures of centrality
	- Mean. - aka average is the sum of all values in a data set divded by the number of values in that dataset.
	- Median - The middle value in an ordered data set - Half the values are smaller than the median and half the values are larger than or equal to the median. To find the median we arrange the numbers in increasing order and the median will be around the n/2th position
	- Mode - value occurs most frequently in data set - bi modal, multi modual - no mode. These are when there's two modes, many modes, or no modes. No modes is quite common where there's no similar data
## Methods of variation
- The methods of variation describe how spread out the data is from its center:
	- Range - Difference between the max and min values in a data set - quick calculation however can be misleading due to extreme values ie outliers
	- Standard deviation - The set of sample values is a measure of variation of values around the mean. small sd means we have a dataset very close to the mean. A large sd means the data is far from the mean. The formula for standard deviation is as follows:
![[Pasted image 20250517184101.png]]
	![[Pasted image 20250517184138.png]]
- Interquartile range:
	- A measure of variability commonly used for skewed data
	- It is calculated by Q3-Q1
	- To do this we:
		1. Sort the data values
		2. Find the median
		3. Place brackets around the numbers above and below the median
		4. Q1 is the median in the lower half and Q3 is the median in the upper half
		5. Finally compute Q3-Q1 and bobs your uncle. 


### Coefficient of variation
- The coefficient of variation is used to compare the relative variation between tow or more data sets ie the data set with the larger CV has the greatest spread of data
- It can be very useful when two or more sets of data that are measured in different unit.

- Formula:
	- ![[Pasted image 20250517184342.png]]
### Coefficient of skewness
- The Coeffecient of skewness measures the skewness of a distribution:
- ![[Pasted image 20250517185128.png]]
- The direction of skewness if given by the following:
	- CS < 0 - the distribution is left skewed
	- CS > 0 - the distribution is right skewed
	- CS < 0 - No skewness - evenly