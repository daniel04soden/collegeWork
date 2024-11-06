import java.util.Arrays;

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

  public static int search(int[] A, int v) {
    // TASK 1.A.b
    int n = A.length;
    if (n == 0){
    	return 0;
    }
	else if(n == 1){
	return 1;
	}
    else{
	int[] leftHandSide = Arrays.copyOfRange(A, 0, n/2); // Left hand side copies the array A from range 0 to half
	int[] rightHandSide = Arrays.copyOfRange(A, n/2, n); // Right hand side copies array A from half to the end 
	int middleValue = A[n/2]; // Vallue right in the middle of the array
	if(middleValue <= v){
		return search(leftHandSide,v);
	}else{
		return search(rightHandSide,v);
	}
    }
  }

  public static void hanoi(int n, char A, char B, char C) {
    // TASK 1.A.c
    if (n == 0) {
      return;
    } else {
      hanoi(n - 1, A, C, B);
      System.out.println("Move item " + n + " from tower " + A + " to tower " + C);
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
