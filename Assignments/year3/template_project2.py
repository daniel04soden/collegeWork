#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Nov  2 12:57:46 2025


Student Name: Daniel Soden

Student ID: R00247215

Cohort/Group/Course: SDH3B

"""

import pandas as pd
import matplotlib.pyplot as plt

# Please note that you need to implement the project using functions; avoid script-based programming as it attracts negative marks.

# Do not use any library or any syntax that was not covered in the lecture or lab

def clean_for_task_1(df:pd.DataFrame)->pd.DataFrame:
    # print(df["Item Purchased"].unique())

    """
    Notice all possible unique outputs as follows:
    ['Gloves' 'T-shirt' 'Jewelry' 'Blouse' 'Sweater' 'Sunglasses' 'Shorts'
    'Jeans' 'Hat' 'Handbag' 'Sandals' 'Pants' 'Sneakers' 'Shoes' 'Belt'
    'Socks' 'Scarf' 'Boots' 'Jacket' 'Skirt' 'Backpack' 'Dress' 'Shirt'
    'Hoodie' 'coat' 'Coat']
    - As we can see, Coat is duplicated with a non-capital 'coat', this will only require a simple replace:
    """

    # print(df["Shipping Type"].unique())

    """
    No issues in output here, data is already clean
    ['Express' 'Next Day Air' 'Store Pickup' 'Free Shipping' 'Standard'
    '2-Day Shipping']
    """


    # print(df["Item Purchased"].unique())
    """
    Notice new output:
    ['Gloves' 'T-shirt' 'Jewelry' 'Blouse' 'Sweater' 'Sunglasses' 'Shorts'
    'Jeans' 'Hat' 'Handbag' 'Sandals' 'Pants' 'Sneakers' 'Shoes' 'Belt'
    'Socks' 'Scarf' 'Boots' 'Jacket' 'Skirt' 'Backpack' 'Dress' 'Shirt'
    'Hoodie' 'Coat'] - As we can see all are unique items
    """

    df["Item Purchased"] = df["Item Purchased"].str.replace("coat","Coat")
    return df


def task_1(file_name:str):
    df = pd.read_csv(file_name,delimiter=",")
    df = clean_for_task_1(df)

    grouped_data = df.groupby(["Item Purchased","Shipping Type"]).size()
    ordered_data  = grouped_data.reset_index(name="Count").sort_values(ascending=False,by="Count")

    for items in ordered_data["Item Purchased"].unique():
        item_info = ordered_data[ordered_data["Item Purchased"] == items]
        item_sum = item_info["Count"].sum()
        print(f"\n {items}: ")
        for _,rows in item_info.iterrows():
            percentage = (rows["Count"] / item_sum)*100
            output = f"{rows["Shipping Type"]}\t" + f"Count:{rows["Count"]}" + f"\t {percentage:,.2f}"
            print(output)

def task_2_calc(prevPurchSeries,purchAmountSeries)->pd.Series:
    """
    To approximate the total purchase value for each customer, we define 
    a new feature called ’Total Purchased USD’, calculated as Purchase Amount (USD) ×Previous Purchases
    """
    return prevPurchSeries*purchAmountSeries


def task_2(file_name: str):
    df = pd.read_csv(file_name)
    bins = list(range(0, 6001, 500))

    df["Total Purchased USD"] = task_2_calc(
        df["Previous Purchases"], df["Purchase Amount (USD)"]
    )

    plt.hist(df["Total Purchased USD"], bins=bins, color="skyblue", edgecolor="black")

    male_df = df[df["Gender"] == "Male"]["Total Purchased USD"].copy()
    female_df = df[df["Gender"] == "Female"]["Total Purchased USD"].copy()

    plt.figure(figsize=(12, 6))

    plt.hist(
        [
            male_df,
            female_df,
        ],
        bins=bins,
        label=["Male", "Female"],
        stacked=False,
        edgecolor="black",
    )

    plt.xlabel("Total Purchased USD Segments")
    plt.ylabel("Frequency (Count of Customers)")
    plt.title("Total Purchased USD Distribution by Gender (Stacked Histogram)")
    plt.legend()

    plt.savefig("test.png") # Saving as image for now as my environment doesn't allow 'show'

def main():
    task_1("shopping.csv")
    task_2("shopping.csv")

if __name__ == "__main__":
    main()

