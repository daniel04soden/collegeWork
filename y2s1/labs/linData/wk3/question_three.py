def search(A:list,x:int):
    final_index = "nil"
    for i in range(len(A)):
        if A[i] == x:
            final_index = int(A[i])
        else:
            pass

    return final_index


def main():
    list_1 = [2,3,4,5,6,7,8,6,5,4,3]
    print(search(list_1,4))

    list_2 = [4,5,6,7,8,5,4,29]
    print(search(list_2,12))

    list_3 = [1,4,5,6,8,8,7,5,4,3]
    print(search(list_3,12))

    list_4 = [4,4,4,4,2,2,1]
    print(search(list_4,4))


if __name__ == "__main__":
    main()
