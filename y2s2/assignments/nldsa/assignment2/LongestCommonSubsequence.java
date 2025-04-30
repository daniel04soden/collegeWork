package assignment2;

import java.util.*;

/**
 * LongestCommonSubsequence
 */
public class LongestCommonSubsequence {
  private final String X;
  private final String Y;

  public LongestCommonSubsequence(String X, String Y) {
    this.X = X;
    this.Y = Y;
  }

  /**
   * @param matrix
   *               Takes in a matrix and prints it out for logging purposes
   */
  private void printMatrix(int[][] matrix) {
    System.out.println("Current Matrix \n");
    for (int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }
    System.out.println(); // Printing out
  }

  /**
   * @param matrix - Matrix with the corresponding common subsequence values
   *               filled
   * @param row    - corner row to start from
   * @param column - corner column to start from
   * 
   * @return Longest common subsequence
   */
  public String reconstruct(int[][] matrix, int row, int column) {
    String res = ""; // String to start from
    while ((row > 0) && (column > 0)) { // Loop until we hit our zeros, indicating no commmon substring present
      if (X.charAt(row - 1) == Y.charAt(column - 1)) { // If the character at their respective rows and columns are
                                                       // common
        res += X.charAt(row - 1); // Set new string as character above row
        row--; // Move up once
        column--; // Move left
      } else if (matrix[row - 1][column] >= matrix[row][column - 1]) { // If value above current matrix pos is
                                                                       // greater/equal to left of current matrix pos
        row--; // Move up
      } else {
        column--; // Move left
      }
    }
    return new StringBuilder(res).reverse().toString(); // Reconstructed string - can be done without stringbuilder,
                                                        // just easier for this
  }

  public String compare() {
    int m = X.length(); // getting these lengths for convenieces
    System.out.println("Length of " + this.X + " = " + m);
    int n = Y.length();
    System.out.println("Length of " + this.Y + " = " + n);
    System.out.println("keep in mind reason rows and columns bigger n+1 and m+1");
    int trackMatrix[][] = new int[m + 1][n + 1]; // Init matrix to keep track of longest subsequence

    // Fill first column with 0s
    for (int i = 0; i < m; i++) {
      trackMatrix[i][0] = 0;
    }

    // Fill first row with 0s
    for (int j = 0; j < n; j++) {
      trackMatrix[0][j] = 0;
    }

    for (int k = 1; k <= m; k++) { // Looping over columns
      for (int p = 1; p <= n; p++) { // looping over rows
        if (X.charAt(k - 1) == Y.charAt(p - 1)) { // Check for common characters at row to column between X and y
                                                  // X is evaluated via the rows and Y the columns
          trackMatrix[k][p] = 1 + trackMatrix[k - 1][p - 1]; // If so set the current matrix pos to 1+length at X and Y
        } else {
          trackMatrix[k][p] = Math.max(trackMatrix[k][p - 1], trackMatrix[k - 1][p]);
          // Otherwise set current matrix pos to larger between two strings (row is X,
          // column is Y)
        }

        /*
         * Final Matrix for reference
         * - B D C A B A
         * -[0, 0, 0, 0, 0, 0, 0]
         * A[0, 0, 0, 0, 1, 1, 1]
         * B[0, 1, 1, 1, 1, 2, 2]
         * C[0, 1, 1, 2, 2, 2, 2]
         * B[0, 1, 1, 2, 2, 3, 3]
         * D[0, 1, 2, 2, 2, 3, 3]
         * A[0, 1, 2, 2, 3, 3, 4]
         * B[0, 1, 2, 2, 3, 4, 4]
         *
         * X = "ABCBDAB";
         * Y = "BDCABA";
         */
      }
    }
    printMatrix(trackMatrix); // Printing current status of matrix - Helps see the progress of it

    return reconstruct(trackMatrix, m, n);
  }

  public static void main(String[] args) {
    String X = "ABCBDAB";
    String Y = "BDCABA";
    String Z = new LongestCommonSubsequence(X, Y).compare();
    System.out.println("The longest common subsequence of '" + X + "' and '" + Y + "' is '" + Z + "'.");
    String A = "HELLOWORLD";
    String B = "WORLDHELLO";
    String C = "DANIELSODEN";
    String D = "SODENDANIEL";
    String O = new LongestCommonSubsequence(A, B).compare();
    String P = new LongestCommonSubsequence(C, D).compare();
    System.out.println("The longest common subsequence of '" + A + "' and '" + B + "' is '" + O + "'.");
    System.out.println("The longest common subsequence of '" + C + "' and '" + D + "' is '" + P + "'.");
  }
}
