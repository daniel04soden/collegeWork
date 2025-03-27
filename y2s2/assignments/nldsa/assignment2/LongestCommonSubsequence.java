package assignment2;

import java.util.Map;

public class LongestCommonSubsequence {

    private final String X;
    private final String Y;

    public LongestCommonSubsequence(String X, String Y) {
        this.X = X;
        this.Y = Y;
    }

    public String compare() {
        String res = "";
        int m = X.length();
        int n = Y.length();
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
          matrix[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            matrix[0][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.X.equals(this.Y)) {
                    matrix[i][j] = matrix[i][j]+1;
                }else{
                    matrix[i][j] = Math.max(matrix[i][j+1],matrix[i+1][j]);
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        String Z = new LongestCommonSubsequence(X, Y).compare();
        System.out.println(
            "The longest common subsequence of '" +
            X +
            "' and '" +
            Y +
            "' is '" +
            Z +
            "'."
        );
    }
}
