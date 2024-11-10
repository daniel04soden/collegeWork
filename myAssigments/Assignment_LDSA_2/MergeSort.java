public class MergeSort {

  private static int[] merge(int[] A1, int[] A2) {
    // TASK 2.A.a
<<<<<<< HEAD

    return A1;
=======
    int n = A1.length + A2.length; // The new arrays length is the two arrays sum
    int i = 0; // Index for A1
    int j = 0; // Index for A2
    int[] A3 = new int[n]; // New array with the capacity of n

    for (int k = 0; k < n; k++) {
      if (A1[i] <= A2[j]) {
        A3[k] = A1[i];
        i++;
      } else {
        A3[k] = A2[j];
        j++;
      }

    }
    return A3;
>>>>>>> fb10922 (added partial merge sort code, nearly there)
  }

  public static int[] mergesort(int[] A) {
    // TASK 2.A.b
    // if (A.length <= 1) {
    // return A;
    // }
    //
    throw new RuntimeException("bruh");

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
