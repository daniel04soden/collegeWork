public class QuickSort {
    private static void swapVariables(int[] A, int varOne, int varTwo){
        int tempVar = A[varOne];
        A[varOne] = A[varTwo];
        A[varTwo] = tempVar;
    }

    private static int partition(int[] A, int p, int r)
    {
        // TASK 2.B.a
        int pivotValue = A[r];

        int i = p-1;

        for (int j = 0; j <= r-1 ; j++) {
            if (A[j] < pivotValue) {
                i++;
                swapVariables(A, i, j);
            }
        }
        swapVariables(A, i+1, r);
        return i+1;
    }

    private static void quicksort(int[] A, int p, int r)
    {
        // TASK 2.B.b
        if (p<r) {
            int partitionVal = partition(A, p, r);
            quicksort(A, p, partitionVal -1);
            quicksort(A, partitionVal+1, r);
        }                
    }

    public static void quicksort(int[] A)
    {
        quicksort(A, 0, A.length-1);
    }

    private static void print(int[] A)
    {
        for (int i=0; i<A.length; i++)
        {
            System.out.print(A[i] + ((i<A.length-1)?", ":""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = new int[] {5,2,8,1,3,9,7,4,6};
        quicksort(A);
        print(A);
    }

}
