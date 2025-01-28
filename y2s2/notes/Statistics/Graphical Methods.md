# Introduction and Steam & Leaf Plots

## Data Description

- If we registered data from a team playing a championship there re many different way to describe such data.

- Graphical methods is the representation of data in a graphical format
- The type of graphical method we use depends on the data type 

### Categorical data

- It does not map top numeric scale and must divided into categories
- the number of units falling into each category can be counted
- A bar or pie chart can be used to represent categorical data.
- For example, consider a sample of 1,056 students and the type of material they download from the internet 

#### Bar chat

- A bar chart can be used to present this data where each category is represented by a bar and the height represents the frequency for each category
- It is a simple and effective way of representing categorical data and discrete numerical data
- The horizontal axis represent the values in the data set
- the vertical axis represents the frequency of each value 

#### Histograms

- Bar chat consisting of series of vertically drawn bars
- Used for continuous data
- Classes interval - x axis
- Vertical axis 
	- Same class widths
	- Different class widths: frequencies per unit of the different classes or frequency density
- The shape of a histogram provides the important info about the distributions of the data
- Two shapes of interest
- Asymmetric = skewed
- Symmetric - bell shaped
- These properties are important in determining what kind of method of analysis is needed for the statistical inference
##### Constructing a histogram

- GIven a grouped frequency table with different class widths to construct a histogram you must calculate the frequency per unit for each interval
1. Calculate the width of each class (upper bound - lower bound),  
2. Select the unit size (the smallest width of the classes),  
3. Calculate the number of units in each class ... (width ÷ size)  
4. Calculate the frequency density for each class ... (frequency ÷ number of  units)

#### Pie chart

- A pie chart represents each category as a proportion or percentage of the total frequency 
- A pie chart is visual representation of relative frequency 
- Sector angle = Relative frequency ( as a decimal ) x 360

### Numerical Data

-  Have numerical values
1. Stem and leaf plot
2. Histogram
3. Box-plot

#### Stem and Leaf Plot 

- This is used to visualise numerical data
- It is a useful way to represent smaller sets of data because:
1. All original values are shown
2. Illustrates the data distribution and where the data is concentrated


- It contains two column separated by a vertical line
- The left column has the stem and the right has the leaves
- When constructing it, we must:
1. Include a key to show how the stem and leaf combine 
2. List the stems and leaves in ascending order

# Skewed data 

- Left right or now normalised skew distribution essentially.
- Like leaving cert stuff.
- Left skew has top data on right and vice versa

- A left skewed or negatively skewed distribution is where most of the data is to the right of the histogram
- A right skewed distribution is where most of the data is to the left of the histogram
- We need to locate the peak and see how the data is dispersed to each of its sides.

# Five number summary

- The five number summary of a dataset consists of the following values presented in ascending order:

1. Min
2. Q1: Value under which 25% of data points are found in an ordered dataset
3. Q2:(Median) Value under which 50% of data points are found in an ordered dataset
4. Q3: Value under which 75% of data points are found in an ordered dataset
5. Maximum

## Box Plot

- A presentation of the data based on the five number summary
1. Range - Max - min
2. Median - Vertical line inside the box 
3. First quartile - Vertical line at the left side of the box
4. Third quartile -  Vertical line at the right side of the box
5. Box contains the middle 50% of the values - IE interquartile range

- The shape of a boxplot provides info about the distribution of data and allows us to identify certain statistical values
- They divide the data into section that each contain 25% of the data.

- You can quickly detect any skewness int he shape of a distribution

- An outlier is a dta point that differes significantly from other observations
- Box plots are used to detect outliers that exist in a data
- How come?
	- Wouldn't the outlier be between the max and min.

### Min and max

- The min and max in a box plot are not always the min and max found in the dataset values
- They can be functions of the interquartile range which is the length going from the first to the third quartile 
- If the min data value is higher the Q1 - 1.5 \* IQ, the whisker stops in the min data value. The max is analogous ie it is the max dta value if that value is lower than Q3+1.5 \*IQR

### Boxplot construction

1. Calculate five number summary for your data
2. Draw horizoinatal line for scale of measurement of the data
3. Draw a box above the line with the left and rihgt ends at Q1 and Q3
4. Draw a line through the box at the location of the median
5. Find the length of each whisker by evaluating the min and max
6. Finish the boxplot by drawing the outliers with circles![[Screenshot_20250128_145428.png]]![[Screenshot_20250128_145450.png]]