#!/usr/bin/env python3


def test_string():
    city = 'Boston'
    index = 0
    output = []
    while index < len(city):
        output.append((city[index]))
        index += 2
    print(output)


def test_dictionary(arr_one:list,arr_two:list) -> dict:
    new_dict:dict = {}
    n = len(arr_one)
    m = len(arr_two)
    if n!=m:
        print("Cannot be composed as a dictionary, ensure same element count")
    else:
        new_dict = dict(zip(arr_one, arr_two))
    return new_dict

def dictionary_task(num_students:int=10)->dict:
    student_numbers = []
    grades = []

    # Keep in mind keys do not have duplicated values

    for i in range(num_students):
        student_no = int(input("Enter the student number  "))
        grade = float(input("Enter their average grade for this semester  "))
        student_numbers.append(student_no)
        grades.append(grade)

    return dict(zip(student_numbers, grades))

# Must be immutable
def dictionary_keys():
    test_score = {
        "toosi":[1,2,3,4],
        "fitz":[21,22,23,24],
    }

    print(test_score["fitz"])
    print(len(test_score))


def set_test():
    set1 = set()
    set1.add(2)
    print(set1)
    set1.add(3)
    print(set1)
    set1.add(2)
    print(set1)

    set1.update([2,10])
    print(set1)
    print(len(set1))

def main():
    test_string()
    print(test_dictionary([1,2,3,4],[4,5,6,7]))
    num_students:int = int(input("Enter the amount of students required"))
    dictionary_new = dictionary_task(num_students)
    set_test()


if __name__ == '__main__':
    main()
