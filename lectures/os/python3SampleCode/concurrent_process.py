import multiprocessing
import time

def square(x):
    return x * x

if __name__ == "__main__":
    numbers = [1,2,3,4,5,6,7,8,9,10]

    with multiprocessing.Pool() as pool:
        results = pool.map(square,numbers)

        print(results)
        
