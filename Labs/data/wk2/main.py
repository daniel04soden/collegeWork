#!/usr/bin/env python3
from datetime import datetime

# 1.

def greet(lang="English"):
    match lang:
        case "French":
            print("Salut")
        case "German":
            print("Hallo")
        case "Irish":
            print("dia duit")
        case _:
            print("Hello")

#2.

def info_dump():
    current_date = datetime.now()
    name:str = input("Enter your name:  ")
    age:int = int(input("Enter your age:  "))
    count_leap_years:int = age//4
    day = current_date.day + count_leap_years
    age_in_months:int = (age-1)*12


    print(f"Your name is {name}")
    print(f"You are is {age} years old aka: {age_in_months} months and {day} days old")
    print(f"In yours years of living there were {count_leap_years} leap years")
# 3.

def get_sum(n:int=100,loop_type:str="for")->int:
    sum = 0
    if loop_type == "while":
        for i in range(0,n):
            sum+=i
    elif loop_type == "for":
        j = 0
        while j<n:
            sum+=j
            j+=1
    else:
        print("Enter valid loop type")
    print(sum)
    return sum

#4.

def divisible(lower_bound:int) ->list:
    res = []
    if lower_bound > 0:
        print("Too big")
    else:
        for i in range(lower_bound,-(lower_bound),7):
            if (i % 5 == 0 and i < 0) or (i % 9 == 0):
                print(f"Yes {i}")
            res.append(i)
    return res
#5.

def separate_list(arr:list):
    print(arr)
    even = []
    odd = []

    for i in range(n:=len(arr)):
        if arr[i] % 2 == 0:
            if (arr[i]%4 == 0):
                even.append(arr[i])
        else:
                odd.append(arr[i])
    print(even)
    odd = odd[::-1]
    print(odd)
    combo_list = even + odd
    print(combo_list)
    sum_of = sum(combo_list)
    res = (sum_of,max(combo_list),min(combo_list))
    print(res)

def main():
    greet()
    greet("French")
    greet("German")
    greet("Irish")
    info_dump()
    get_sum(10,"for")
    get_sum(10,"while")
    arr = []
    for i in range(0,100):
        arr.append(i)
    separate_list(arr)

if __name__=="__main__":
    main()
