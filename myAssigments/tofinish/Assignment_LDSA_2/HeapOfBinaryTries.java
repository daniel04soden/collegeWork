public class HeapOfBinaryTries {

  private BinaryTrie[] A;
  private int heapSize;

  private void heapify(int i) {
    int n = heapSize;
    while (true) {
      int l = (i * 2) + 1;
      int r = (i * 2) + 2;

      int smallest = i;

      if (l < n && A[l].compare(A[i])) {
        smallest = l;
      }

      if (r < n && A[r].compare(A[smallest])) {
        smallest = r;
      }

      if (smallest != i) {
				BinaryTrie temp = A[i];
				A[i] = A[smallest];
				A[smallest] = temp;
        i = smallest;

      } else {
				break;
      }
    }
  }

  public HeapOfBinaryTries(BinaryTrie[] A) {
    // TASK 3.A.b

    this.A = A;
    this.heapSize = A.length;
		for (int i = (heapSize/2)-1; i >= 0; i--) {
			heapify(i);
			}
  }

  public BinaryTrie extractMin() {
    // TASK 3.A.c
		BinaryTrie min = A[0];
		A[0] = A[heapSize-1];
		heapSize--;
		heapify(0);

		return min;
  }

  public void insert(BinaryTrie x) {
    // TASK 3.A.d
		heapSize++;
		int i = heapSize-1;
		while (i>0 && x.compare(A[i/2])) {
			A[i]=A[i/2];
			i = i/2;
		}
		A[i] = x;
  }

  public int size() {
    return heapSize;
  }
}
