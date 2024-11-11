public class DivideAndConquer {

  public static int fibonacci(int n) {
    // TASK 1.A.a
    if (n == 0) { // Base case 0
      return 0;
    } else if (n == 1) { // Base case 1
      return 1;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  public static int search(int[] A, int v) { // V is the searched for value and A is the array
    // TASK 1.A.b
    int lowerIndex = 0; // Lower value index
    int higherIndex = A.length - 1; // Higher Value index

    while (lowerIndex <= higherIndex) {
      int middleIndex = lowerIndex + (higherIndex - lowerIndex) / 2;

      if (A[middleIndex] == v) {
        return v;
      } else if (A[middleIndex] <= v) {
        lowerIndex = middleIndex + 1;
      } else {
        higherIndex = middleIndex - 1;
      }
    }

    return -1; // If number is not found, return -1
  }

  public static void hanoi(int n, char A, char B, char C) {
    // TASK 1.A.c
    if (n == 1) {
      System.out.println("Move disk" + " from tower " + A + " to tower " + C);
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
