# Regression
- **Regression**: Obtaining a mathematical equation which describes the relationship between two variables
- The equation can be used to compare two sets of data or for making predictions based on given info 
- **Independent variables** x in the context of regression are variables whose outcomes are not influenced by anything in the experiment
- **Dependent variables** y in the context of regression are variables whose outcome is influenced by any independent variable.
- Regression analysis is used for prediction ie it allows us to predict and unobserved value of one variable given the value of another.
- We will only consider the simplest type of regression where there are only 2 variables (ie one independent variable x and one dependent variable y)
- The is simple linear regression
# Scatter plots
- Scatter plots are 2d plots showing the x and y value for each observation, from it we can quickly determine whether there is  a relationship and if it is approximately linear.
## Types of scatter plot relationships
- Positive linear - As x increases, y increases in a straight line
- Negative linear - A x increases, y decreases in a straight line
- Positive curvilinear - Positive Curved relationship between x and y
- Negative curvilinear - Negative Curved relationship between x and y


## Least squares regression lines
- Slope and intercept is gotten from this formula:
	- ![[Pasted image 20250521131637.png]]
	- These are both provided in the log table but essentially the slope gives us our average rate of change (negative for decreasing, positive for increasing)
	- And a^ gives us the y value when x is 0
### Regression prediction
- **Interpolation**: Predicting y values for x-values that are between the observed x values in the original data set
- **Extrapolation**: predicting y values for x values that are beyond the observed x values in the original data set.
- **Correlation** r is a numerical value between -1 and 1 which shows the strength of the linear relationship between two variables
- When r is very close to 1, the scatter plot will closely follow an increasing line. 
- When r is smaller but still greater than 0 the scatter plot still shows a positive relationship, but is more 
  dispersed around the coordinates.
- The coefficient of determination R^2 is the proportion of the variation in the dependent variable that is predictable from the independent variable
- It is used as a measure of how well the simple linear regression fits the data
- In the case of the simple linear regression, the coefficient of determination R^2 is the square of the correlation between x and y 
- That is as the correlation is significantly different than 0, R^2 is close to 1. Otherwise, if the correlation is weak, R^2 is close to 0
