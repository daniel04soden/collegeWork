# Basics of backtracking

1.  Make a choice
2.  Verify that choice
    1.  If choice invalid
    2.  backtrack to original un-selected choice
3.  Return solution

- In the absence of a non-deterministic machine or a certificate to
  guide the search all we can do is to sequentially traverse the
  problem\'s decision tree until a solution is found
- Essentially we try to find every possible solution and verify if it is
  correct.

# Examples of backtracking

## Hard drive allocation

``` java
package assignment2;

import java.util.Arrays;

public class HDDAllocation {
  private final int[] hdds;
  private final int[] files;
  private int[] res;

  public HDDAllocation(int[] hdds, int[] files) {
    this.hdds = hdds; // Hdds with set amount of space
    this.files = files; // Files to be put in these hard drives
    this.res = new int[files.length]; // New Result array, needed in constr for recursion
    for (int i = 0; i < files.length; i++) {
      res[i] = -1; // -1 denotes an unallocated file
    }
  }

  public boolean verify(int hddIdx) {

    int allocation = 0; // Alloc as 0 so we can sum it when hdd met
    for (int i = 0; i < res.length; i++) {
      if (res[i] == hddIdx) { // Hdd eval met
        allocation += files[i]; // add file size of that index
      }
    }
    return allocation <= hdds[hddIdx]; // Boolean if fits
  }

  public int[] generate_allocation(int fileIdx) {
    // Checking if solution reached
    if (fileIdx == files.length) { // if we've gone through it to the point that we're at the last file and its
                                   // allocated
      System.out.println(Arrays.toString(res));
      return res; // Finish and return final res ie the solution
    }
    // Making our choice
    for (int i = 0; i < hdds.length; i++) { // Loop over hard drives on each recursive call
      res[fileIdx] = i; // Making our choice
      if (verify(i)) { // If this choice is valid
        int[] ans = generate_allocation(fileIdx + 1); // recurse on with ans
        if (ans != null) { // If its not null continue
          return ans; // Return for call stack
        }
      } else {
        res[fileIdx] = -1; // Backtracking/constraints
        System.out.println("Backtrack occured for HDD " + i + " at file " + files[fileIdx]); // Displaying when
                                                                                             // backtracking needed
        System.out.println(Arrays.toString(res));
      }
    }
    return null;
  }
```

## N-Queens

``` java
static List<Integer> nQueen(int n) {

       // Initialize the board
       int[][] mat = new int[n][n];

       // If the solution exists
       if (placeQueens(0, mat)) {

           // to store the columns of the queens
           List<Integer> ans = new ArrayList<>();
           for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                   if (mat[i][j] == 1) {
                       ans.add(j + 1);
                   }
               }
           }

           return ans;
       } 
       else
           return Collections.singletonList(-1);
   }
```
