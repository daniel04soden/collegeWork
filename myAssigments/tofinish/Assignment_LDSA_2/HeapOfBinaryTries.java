package assignment2;

public class HeapOfBinaryTries {
    private BinaryTrie[] A;
    private int heapSize;

    private void heapify(int i)

    {
        boolean running = true;
        while (running){
            int l;
            int r;
            int smallest = i;

            if (l <= heapSize && A[l] < A[i]){
                smallest = l;
            }else if (r <= heapSize && A[r] < A[smallest]){
                smallest = r;
            }else if (smallest != i){
                // swapping A at i wit A at smallest 
                BinaryTrie temp = A[i]; 
                A[smallest] = A[i];
                A[i] = temp;
                i = smallest;

            }else{
                running = false;
            }

        }
        
    }

    public HeapOfBinaryTries(BinaryTrie[] A)
    {
        // TASK 3.A.b
        throw new RuntimeException("Not yet implemented!");
    }

    public BinaryTrie extractMin()
    {
        // TASK 3.A.c
    }

    public void insert(BinaryTrie x) {
        // TASK 3.A.d
        BinaryTrie parent = new BinaryTrie(x, x);
        heapSize[A] = heapSize[A] + 1;
        i = heapSize[A];
        while (i>1 && A[parent[i]] > x){
            A[i] = A[parent(i)];
            i = parent(i);
        }
        A[i] = x;
    }

    public int size()
    {
        return heapSize;
    }
}
