public class HeapOfBinaryTries {

  private BinaryTrie[] A;
  private int heapSize;

  private void exchange(BinaryTrie b1, BinaryTrie b2) {
    BinaryTrie tempBt = b1;
    b1 = b2;
    b2 = tempBt;
  }

  
  private void heapify(int i) {
    boolean running = true;
    int n = this.heapSize;
    while (running) {
      int l = i * 2;
      int r = (i * 2) + 1;

      int smallest = i;

      if (l <= n && A[l].compare(A[i])) {
        smallest = l;
      }

      if (l <= n && A[r].compare(A[smallest])) {
        smallest = l;
      }

      if (smallest != i) {
        i = smallest;
        exchange(A[i], A[smallest]);

      } else {
        running = false;
      }
    }
  }

  public HeapOfBinaryTries(BinaryTrie[] A) {
    // TASK 3.A.b
    this.A = A;
    this.heapSize = A.length;
  }

  public BinaryTrie extractMin() {
    // TASK 3.A.c
    BinaryTrie min = A[1];
    A[1] = A[heapSize];
    heapSize--;
    heapify(1);
    return min;
  }

  public void insert(BinaryTrie x) {
    // TASK 3.A.d
    heapSize+=1;
    int i = heapSize;

    while (i > 1 && A[i-1] > x){
      A[i] = A[i-1];
      i--;
    }
    A[i] = x;
  }

  public int size() {
    return heapSize;
  }
}
