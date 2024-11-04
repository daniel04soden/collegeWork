public class DivideAndConquer {

    public static int fibonacci(int n) {
        // TASK 1.A.a
				if (n == 0) { // Base case 0
				return 0;
				} else if(n == 1 ){ // Base case 1 
				return 1;
				}else{
				return fibonacci(n-1) + fibonacci(n-2);
			}
    }

    public static int search(int[] A, int v)
    {
        // TASK 1.A.b
				int res = 0;
				int n = A.length;
				
				if (n == 0 ){
				return 0;
		}else{
					if (A[n/2] <= v) {
                        
					} else {
				
					}
		}


		return res;
    }

    public static void hanoi(int n, char A, char B, char C)
    {
        // TASK 1.A.c
        if (n == 0) {
            ;
        } else {
            
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            System.out.println(fibonacci(i));
        }
        System.out.println();
        for (int i=0; i<10; i++) {
            System.out.println(search(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, i));
        }
        System.out.println();
        hanoi(4, 'A', 'B', 'C');
    }
}
