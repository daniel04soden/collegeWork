#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Nov 22 17:00:02 2025

Name: Daniel Soden
Student ID: R00247215
Cohort: SDH3B

"""

"""
Imports
"""

import pandas as pd

"""
Task 1
"""

def task_1(file_name:str):
    df = pd.read_csv(file_name)

    df_male_population = df[df["Gender"] == 'Male']
    df_female_population = df[df["Gender"] == 'Female']
    print(df_male_population.shape[0])
    print(df_female_population.shape[0])

    more_females = df_female_population > df_male_population # Needed for cleansing



"""
Main Output
"""

def main():
    while True:
        choice = int(input("Enter a task to run (0 to quit):  "))
        match choice:
            case 0:
                print("Thank you for running my program!!")
                break
            case 1:
                print(f"Task 1\n{'='*40}\n")
                task_1('data/health.csv')
            case 2:
                print(f"Task 2\n{'='*40}\n")
            case 3:
                print(f"Task 3\n{'='*40}\n")
            case 4:
                print(f"Task 4\n{'='*40}\n")
            case 5:
                print(f"Task 5\n{'='*40}\n")
            case 6:
                print(f"Task 6\n{'='*40}\n")
            case 7:
                print(f"Task 7\n{'='*40}\n")
            case _:
                print("Invalid task")
if __name__ == "__main__":
    main()
