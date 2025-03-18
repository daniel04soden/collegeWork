package assignment2;

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
        if (n == 0 || m == 0) return res;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               if () {

               }else{

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
