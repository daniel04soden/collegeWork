def sum_arrray(A:list,n:int): # n would be the value that never changes, the range/length for the list
    sum = 0
    for i in range(0,n): # i would be the variant in this situation (for i = 0, i <= n ,i++/i--)
        sum += A[i]

    return sum


def main():
    test_list = [2,3,4,6,78,5,3]
    my_n = len(test_list)
    print(sum_arrray(test_list,my_n))

if __name__ == "__main__":
    main()

