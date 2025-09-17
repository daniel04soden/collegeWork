#!/usr/bin/env python3
from datetime import datetime

# 1.


def greet(lang="English",name="Testname"):
    match lang:
        case "French":
            print(f"Salut {name}")
        case "German":
            print(f"Hallo {name}")
        case "Irish":
            print(f"dia duit {name}")
        case _:
            print(f"Hello {name}")

# 2.


def info_dump():
    current_date = datetime.now()
    name:str = input("Enter your name:  ")
    age:int = int(input("Enter your age:  "))
    count_leap_years:int = age//4
    day = current_date.day + count_leap_years
    age_in_months:int = (age-1)*12


    print(f"")
    print(f"Your name is {name}, you are {age} years old aka: {age_in_months} months and {day} days old")
    print(f"In yours years of living there were {count_leap_years} leap years")
# 3.

def get_sum(n:int=100,loop_type:str="for")->int:
    sumValue = 0
    if loop_type == "while":
        sum_list = [i for i in range(0, 100)]
        sumValue = sum(sum_list)
    elif loop_type == "for":
        j = 1
        while j<n:
            sumValue+=j
            j+=1
    else:
        print("Enter valid loop type")
    print(sumValue)
    return sumValue

#4.

def divisible(lower_bound:int) ->list:
    res = []
    if lower_bound > 0:
        print("Too big")
        return res
    else:
        for i in range(lower_bound,-(lower_bound),7):
            if (i % 5 == 0 and i < 0) or (i % 9 == 0):
                print(f"Yes {i}")
            res.append(i)
    return res
#5.


def separate_list(arr:list):
    print(f"Original List {arr} \n")
    even = []
    odd = []

    for i in range(len(arr)):
        if arr[i] % 2 == 0:
            if arr[i] % 4 == 0:
                even.append(arr[i])
        else:
            odd.append(arr[i])
    print(f"Even Numbers: {even}\n")
    print(f"Odd Numbers {odd}\n")
    odd = odd[::-1]
    print(f"Reversed Odd Numbers: {odd}\n")
    combo_list = even + odd
    print(f"Combination of odd and even: {combo_list}\n")
    sum_of = sum(combo_list).sorted()
    print(f"Sum list {sum_of}\n")
    print(f"Max in combolist {max(combo_list)}\n")
    print(f"Min in combolist {min(combo_list)}\n")

def main():
    name:str = input("Enter your name  ")
    greet("Enlgish",name)
    greet("French",name)
    greet("German",name)
    greet("Irish",name)
    info_dump()
    get_sum(10,"for")
    get_sum(10,"while")
    arr = []
    for i in range(0,100):
        arr.append(i)
    separate_list(arr)

if __name__=="__main__":
    main()
