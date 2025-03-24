# JavaDocs
- Java doc is the automatic documentation creation tool for Java 
- Used for genrating jva code docs in html from source code which requires docs in a predefined format 
## Generate a javadoc
- Eclipse/Intellij
	- On the top menu select tools generate javadoc
	- Need to have comments for each method and class
	- HTML tags can also be used given its just html markup 
- @depracted - good tag to say a method may not be relevant anymore
- @version - which version is it referring to
	
## Is it useful?
- If done properly
- bad:
	- What the code does
- Good:
	- Why does the codes it

## Why is it not there?

- Expensive time consuming
- Written by people who can't write or who don't know the material
- Assumes knowledge readers don't have
- Outdated
## Documentation outside of the code

- What software does?
- How it does it?
- Descriptions of methods and classes.
- When we don't have access to the library code
- Cloud tools
- Before we use any software
	- To check if it fits our purpose

## Documentation inside of the code
- For us and other devs to help understand the code 
	- Direct documentation = comments
	- Indirect documentation = names of variables, methods
	
# Database integration

- Java Database connector - JDBC 
- Driver manager: Loads db drivers and manages connections between the application and the driver
1. Connection
2. Statement
3. Result set

- For try catch block for any other issues, we use the finally keyword as well

## Better try catch blocks
- try
	- catch
	- finally
		- try
		- catch