public class MergeSort {

  private static int[] merge(int[] A1, int[] A2) {
    // TASK 2.A.a

    int n = A1.length + A2.length; // The new arrays length is the two arrays sum
    int i = 0; // Index for A1
    int j = 0; // Index for A2
    int[] A3 = new int[n]; // New array with the capacity of n

    for (int k = 0; k < n; k++) { // K is the iterative index
      boolean lengthCheck = i < A1.length && j < A2.length;

      if (lengthCheck) {
              if (i <= j) { // If the indicator of the first array is less than the indicator for the second
                // array
                A3[k] = A1[i]; // The new array at the index of k becomes A1 at the index i
                i++; // Increase indicator
              } else { // Otherwise assign K to the value at array 2
              A3[k] = A2[j];
              j++;
              }
            }
    }
    return A3;
  }

  public static int[] mergesort(int[] A) {
    // TASK 2.A.b
    int n = A.length;

    if (n <= 1) {
      return A;
    } else {
      int middle = (n / 2);
      int[] ALeft = new int[middle];
      int[] ARight = new int[n - middle];

      for (int i = 0; i < middle; i++) {
        ALeft[i] = A[i];
      }

      for (int i = middle; i < n; i++) {
        ARight[i - middle] = A[i];
      }

      int[] sortA1 = mergesort(ALeft);
      int[] sortA2 = mergesort(ARight);
      return merge(sortA1, sortA2);
    }
  }

  private static void print(int[] A) {
    for (int i = 0; i < A.length; i++) {
      System.out.print(A[i] + ((i < A.length - 1) ? ", " : ""));
    }
    System.out.println();
  }

  public static void main(String[] args) {
    print(merge(new int[] { 1, 3, 5, 7, 9 }, new int[] { 2, 4, 6, 8 }));
    print(mergesort(new int[] { 5, 2, 8, 1, 3, 9, 7, 4, 6 }));
  }

}
