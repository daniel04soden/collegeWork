def og_insert(arr:list) -> list:
    for j in arr:
        k = arr[j]
        i = j-1

        while i>0 and arr[i] >k:
            arr[i+1] = arr[i]
            i -= 1
        arr[i+1] = k
    return arr



