# What is a design pattern

- A standard solution to a common problem
- Technique for making code more flexibly by making it meet certain criteria
- Design or implementation structure that achievess a particular purpose
- A shorthand for descirbing certain aspects of program organisation
- Recurring aspects of design
## Patterns

- An abstraction from a concrete form which keeps recurring in specific non arbitrary contexts 
- A recurring solution to a common problem in a given context and system of forces 
- Should be as general as possible
- Contain a solution that has been proven effective in solving such problems

## Rules
- First learn the rules and the physical requirements
- Then the principles


# Defining a pattern
## Attributes of a pattern
1. Name: Meaningful name
2. Problem: Statement of the problem
3. Context: Patterns application
4. Forces: relevant forces and constraints
5. Solution: Static relationships and dynamic rules on how to realise the desired outcome
6. Consequences: Implications of using the solution
7. Examples: One or more sample applications of the pattern


## Why patterns
- Solves problems (cap)
- Capture domain expertise
- Document design decisions and rationale
- Convey expert insight


# Abstract Occurence Pattern

## Context

- Often you find a set of related objects
- Members of such set share common info
	- But also differ in different important ways

## Problems

- What is the best way to show the sets of occurrences in a class diagram?
## Forces
- You want to represent the members of each set of occurrences without duplicating the common information

- Composition favourable over inheritance