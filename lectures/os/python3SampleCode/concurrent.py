import threading

import time



def print_numbers():
    for i in range(0,6):
        print(f"Printing number{i}")
        time.sleep(1)

def print_letters():
    for letters in "Geeks":
        print(f"Printing letters in {letters}")
        time.sleep(1)

thread1 = threading.Thread(target=print_numbers)
thread2= threading.Thread(target=print_letters)

# Start the threads
thread1.start()
thread2.start()
 
# The main thread waits for both threads to finish
thread1.join()
thread2.join()

print()
print()
print()
print("Both threads finished")
