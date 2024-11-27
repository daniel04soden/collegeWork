public class QuickSort {

  // Extra defined function for swapping vars
  private static void swapArrayVariables(int[] myArray, int v1, int v2) { // Method added to just swap the values in the array
    int tempVar = myArray[v1];
    myArray[v1] = myArray[v2];
    myArray[v2] = tempVar;
  }

  private static int partition(int[] A, int p, int r) {
		/*
		 Partition the array into two ends through a pivot value
		 
		 params:
				int[] A: array to be partitioned
				int p: starting index of sub array
				int r: ending index of sub array

			returns:
						int j; the index of the end value + 1;
		  */
    int x = A[p]; // Assign x as the pivot at the start of the array
    int i = p - 1; // Index from the left hand side to the pivot
    int j = r + 1; // Index from the right hand side of the pivot

    while (true){
      do {
        j--;// decrement right hand side index until A[j] is greater than x
      }while(!(A[j]<= x));
      do{
        i++;
      }while(!(A[i]>=x)); // decrement left hand side index until A[i] is less than x
      if(i<j){ // if i is less than j in the original array then swap them
        swapArrayVariables(A,i,j);
      }else{
				break; //otherwise break out of the while loop and give back j
      }
    }
    return j;
  }

  private static void quicksort(int[] A, int p, int r) {
		/*
		 The method quicksort takes in an array, the end and start indexes
		 chooses a partition and from there it will recursively call itself twice 
		 until the array is sorted

		 params:
					int[] A: array given to be sorted
					int p: starting index;
					int r: ending index
			returns:
						None;
		*/
    // TASK 2.B.b
    if (p < r) { // if p is less than r then 
      int q = partition(A, p, r); // create the partitioned end value of q
      quicksort(A, p, q ); // Recursively call this funciton with the values p and q as start and end points ie up to partition
      quicksort(A, q + 1, r); // Call recursion on array from partition+1 to end
    }
  }

  public static void quicksort(int[] A) {
    quicksort(A, 0, A.length - 1); // Quicksort sub method with predefined parameters
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
