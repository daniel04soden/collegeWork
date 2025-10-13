# Author: Daniel Soden
# Purpose: Data analytics learning for exam this week

# Imports
import os

# Week 3 tasks


def check_nums(num: int):
    condi_one = (num % 2 != 0) and (num % 5 == 0)
    condi_two = (num < 0) and (num % 7 == 0)
    return condi_one or condi_two


def task_1_wk3(my_list: list) -> list:
    subset = []
    for i in range(len(my_list)):
        if check_nums(my_list[i]):
            subset.append(my_list[i])
        else:
            pass
    return subset

def task_2_wk3(my_list:list,new_list:list,num:int)->list:
    if num >= len(my_list):
        return new_list
    if check_nums(my_list[num]):
        new_list.append(my_list[num])

    return task_2_wk3(my_list,new_list,num+1)

def task_3_wk3(file_name:str)->list:
    my_list = []
    with open(file_name,"r") as file:
        lines = file.readlines()
    for line in lines:
        for chars in line:
            if chars.isdigit():
                my_list.append(int(chars))
            else:
                pass
    return my_list

def task_4_wk3(dir:str)->dict:
    res = {}
    files = os.listdir(dir)

    for i in range(len(files)):
        count = 0
        with open(f"{dir}/{files[i]}","r") as my_file:
            for lines in my_file:
                lines = lines.split()
                count += len(lines)
            res.update({f"{files[i]}":count})
    return res

def task_5_wk3(n:int):
    if n == 0:
        return 0
    if n == 1 or n == 2:
        return 1
    else:
        return task_5_wk3(n-1) + task_5_wk3(n-2)

def task_6_wk3(n)->dict:
    my_dict = {}
    for i in range(n):
        my_dict.update({i+1:task_5_wk3(i)})
    return my_dict

def task_7_wk3(dict_one:dict,dict_two:dict)->dict:
    res = {}
    res.update(dict_one)
    res.update(dict_two)
    res_unique = set()
    for vals in res.values():
        res_unique.add(vals)
    print(res_unique)
    return res

class Entity_One:
    def __init__(self,menu:dict):
        self.menu:dict = menu
    def __str__(self):
        return f"{self.menu}"

class Entity_Two:
    def __init__(self,order:list,menu:dict):
        self.order:list = order
        self.menu:list = menu
    def __str__(self):
        return f"{self.order}"
    def prepBill(self)->float:
        total = 0.0
        for i in range(len(self.order)):
            total += self.menu[order[i]]
        return total
            
# Main out

def main():
    test_l = [5, 10, 15, -7, -14, 20, -21, 35, 40, -49, 55, 63, 23, -8, 19, -82]
    print(task_1_wk3(test_l))
    sub_list = []
    print(task_2_wk3(test_l,sub_list,0))
    print(task_3_wk3("AnimalFarm.txt"))
    print(task_4_wk3("data"))
    for i in range(0,9):
        print(task_5_wk3(i))
    # print(task_6_wk3(40))
    inventory_a = {
    'apple': 50,
    'banana': 120,
    'orange': 75,
    'grape': 200,
    'kiwi': 30,
    'melon': 15,
    'mango': 100
    }
    inventory_b = {
    'banana': 80,       
    'strawberry': 90,      
    'grapefruit': 25,      
    'pineapple': 60,    
    'orange': 100       
    }
    print(task_7_wk3(inventory_a,inventory_b))

if __name__ == "__main__":
    main()
