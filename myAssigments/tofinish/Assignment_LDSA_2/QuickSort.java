public class QuickSort {

  // Extra defined function for swapping vars
  private static void swapArrayVariables(int[] myArray, int v1, int v2) {
    int tempVar = myArray[v1];
    myArray[v1] = myArray[v2];
    myArray[v2] = tempVar;
  }

  private static int partition(int[] A, int p, int r) {
    int x = A[p];
    int i = p - 1;
    int j = r + 1;

    boolean running = true;

    while (running){
      do {
        j--;
      }while(!(A[j]<= x));
      do{
        i++;
      }while(!(A[i]>=x));
      if(i<j){
        swapArrayVariables(A,i,j);
      }else{
        running = false;
      }
    }
    return j;
  }

  private static void quicksort(int[] A, int p, int r) {
    // TASK 2.B.b
    if (p < r) {
      int q = partition(A, p, r);
      quicksort(A, p, q );
      quicksort(A, q + 1, r);
    }
  }

  public static void quicksort(int[] A) {
    quicksort(A, 0, A.length - 1);
  }

  private static void print(int[] A) {
    for (int i = 0; i < A.length; i++) {
      System.out.print(A[i] + ((i < A.length - 1) ? ", " : ""));
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] A = new int[] { 5, 2, 8, 1, 3, 9, 7, 4, 6 };
    quicksort(A);
    print(A);
  }

}
