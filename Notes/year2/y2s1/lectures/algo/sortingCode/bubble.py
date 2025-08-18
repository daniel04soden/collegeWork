import random

# Generate a massive array of random numbers
data = [random.randint(0, 1000000) for _ in range(10000000)]

def bubble_sort(arr):
    swap_count = 0 
    compare_count = 0 

    # Outer loop to iterate through the list n times
    for n in range(len(arr) - 1, 0, -1):
        print(data)

        # Inner loop to compare adjacent elements
        for i in range(n):
            compare_count +=1
            if arr[i] > arr[i + 1]:
                swap_count +=1

                # Swap elements if they are in the wrong order
                swapped = True
                arr[i], arr[i + 1] = arr[i + 1], arr[i]
    print(f"Swap count {swap_count}")
    print(f"Compare count {compare_count}")
    return arr

def main():
    new_arr = bubble_sort(data)
    print(new_arr)

main()
