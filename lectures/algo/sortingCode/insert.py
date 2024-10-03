A = [2,5,4,6,1,3]

for j in A:
    print(A)
    k = A[j]
    i = j-1

    while i>0 and A[i] >k:
        A[i+1] = A[i]
        i -= 1
    A[i+1] = k

