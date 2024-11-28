public class DivideAndConquer {

  public static int fibonacci(int n) {
		/*
		 This function follows the logic of the fibonacci sequence
		 in which we have the two base bases of 0 and 1 which return
		 those specific values for the n input
		 And otherwise it will return the sum of the recursive call
		 of the two previous values

		params: int n; n is nth fibonacci number
		 * */
    // TASK 1.A.a
    if (n == 0) { // Base case 0
      return 0;
    } else if (n == 1) { // Base case 1
      return 1;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2); // Recursively run the function for the first and second previous value of n
    }
  }

  public static int search(int[] A, int v) { // V is the searched for value and A is the array
		/*
			Search also known as binary search is a searching algorithm 
			designed around the idea that it is assumed we are operating
			on an already sorted array. We start at the middle index and 
			from there if the value is less than the middle it goes left
			and if not it goes right, this then repeats until we have found 
			the value.

			params: int[] A; Integer of arrays A in which we are searching for a value
						: int v; Integer of which we are looking for.

			returns:
							if value found:
									returns v
							else:
									returns -1
		  */
    // TASK 1.A.b
    int lowerIndex = 0; // Lower value index
    int higherIndex = A.length - 1; // Higher Value index

    while (lowerIndex <= higherIndex) { // While the lower pointing index is less than or equal to the higher index,loop
      int middleIndex = lowerIndex + (higherIndex - lowerIndex) / 2; // Middle index reassigned each loop

      if (A[middleIndex] == v) { // If the search value is the middle index
        return v; // Return the search value
      } else if (A[middleIndex] < v) { // If the value at the middle index is less than v
        lowerIndex = middleIndex + 1; // Move the middle index forward
      } else {
        higherIndex = middleIndex - 1; // Otherwise move the higher index back
      }
    }

    return -1; // If number is not found, return -1
  }

  public static void hanoi(int n, char A, char B, char C) { 
		/*
			This method works upon the concept of the tower of hanoi
			where we must move the tower of disks with its number represented
			by n. The respective towers are represented by the letters A,B and C 

			params: n; Integer  representing the number of disks on a tower
						: A; Character representing the left most tower
						: B; Character representing the middle tower
						: C; Character representing the right most tower

			returns:
							none; just prints out steps for hanoi tower movement
		 */
    // TASK 1.A.c
    if (n == 1) {
      System.out.println("Move disk" + " from tower " + A + " to tower " + C); // Base case and print output once n is 1
    } else {
      hanoi(n - 1, A, C, B);
      hanoi(1, A, B, C);
      hanoi(n - 1, B, A, C);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(fibonacci(i));
    }
    System.out.println();
    for (int i = 0; i < 10; i++) {
      System.out.println(search(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, i));
    }
    System.out.println();
    hanoi(4, 'A', 'B', 'C');
  }
}
