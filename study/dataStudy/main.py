# Author: Daniel Soden
# Purpose: Data analytics learning for exam this week

# Imports

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


# Main out


def main():
    test_l = [5, 10, 15, -7, -14, 20, -21, 35, 40, -49, 55, 63, 23, -8, 19, -82]
    print(task_1_wk3(test_l))


if __name__ == "__main__":
    main()
