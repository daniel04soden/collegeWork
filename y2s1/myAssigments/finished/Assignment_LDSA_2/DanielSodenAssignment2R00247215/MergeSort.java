public class MergeSort {

  private static int[] merge(int[] A1, int[] A2) {
			/*
			 This method takes in two seperate already sorted arrays 
			 and merges them together

			 params: 
						int[] A1; First half of the array to be merged
						int[] A2; Second half of the array to be merged
				
			returns:
						int[] A3; Merged array
			 * */


    // TASK 2.A.a

    int n = A1.length + A2.length; // The new arrays length is the two arrays sum
    int i = 0, j = 0; // Index for A2
    int[] A3 = new int[n]; // New array with the capacity of n

    for (int k = 0; k < n; k++) { // the value k loops over length of the two arrays combined
			// done for readability purpose
      boolean leftLenCheck = i < A1.length; // Checks for respective indexes to their length
      boolean rightLenCheck = j < A2.length;
      boolean lengthCheck = leftLenCheck && rightLenCheck; // Overall check

      if (lengthCheck) {
        if (A1[i] <= A2[j]) { 
          A3[k] = A1[i]; // Current A1 values is added to the list
          i++; // i is incremented
        } else { // Opposite for A2
          A3[k] = A2[j]; 
          j++;
        }
      } else {
        if (leftLenCheck) { 
          A3[k] = A1[i]; // Add A1 value to A3
          i++;
        } else if (rightLenCheck) {
          A3[k] = A2[j]; // Add A2 value to A3
          j++;
        }
      }
    }
    return A3;
  }

  public static int[] mergesort(int[] A) {
		/*
		 This method takes in an array A and splits it into two different arrays,
		 sorts each array and from there merges these arrays.

		 params: int[] A; given unsorted Array

		 returns: merge(sortA1,sortA2); merged sorted arrays
		 * */
    // TASK 2.A.b
    int n = A.length; // Length of A is n

    if (n <= 1) { // if length is greater than or equal to 1
      return A; // just spit back out A
    } else {
      int middle = (n / 2); // otherwise we find the middle, and split up the left and right arrays
      int[] ALeft = new int[middle];
      int[] ARight = new int[n - middle];

      for (int i = 0; i < middle; i++) {
        ALeft[i] = A[i]; // Adding values from 0 to the middle
      }

      for (int i = middle; i < n; i++) {
        ARight[i - middle] = A[i]; // Adding values from the middle to the top
      }

      int[] sortA1 = mergesort(ALeft); // Recursively call both arrays
      int[] sortA2 = mergesort(ARight);
      return merge(sortA1, sortA2); // Merge each array
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
