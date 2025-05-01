# Daniel docs

## HDDAllocation

### Constructor for HDDAllocation

```java

  public HDDAllocation(int[] hdds, int[] files) {
    this.hdds = hdds; // Hdds with set amount of space
    this.files = files; // Files to be put in these hard drives
    this.res = new int[files.length]; // New Result array, needed in constr for recursion
    for (int i = 0; i < files.length; i++) {
      res[i] = -1; // -1 denotes an unallocated file
    }
  }

```

- The constructor above acts as the blueprint for our class containing the allocating of the hard drives
- Imagine we have x amount of hard drives and y amount of files
- We have a third variable res, another representation of the files, where they are first constructed
  with the non-existent -1 hard drive given that would never be a hard drive, and once allocated will be replaced with the real
  hard drive number.

## Verifying hard drive allocation

```java
  public boolean verify(int hddIdx) {

    int allocation = 0; // Alloc as 0 so we can sum it when hdd met
    for (int i = 0; i < res.length; i++) {
      if (res[i] == hddIdx) { // Hdd eval met
        allocation += files[i]; // add file size of that index
      }
    }
    return allocation <= hdds[hddIdx]; // Boolean if fits
  }
```

- The above java code takes in the current hard drive index which is looped over until the file we are
  currently trying to allocate
- Allocation is created as a variable to see how much is currently allocated to this hard drive
- We loop through our res list, and if we find an instance of the passed in hard drive, we add this file to allocation
- From here, once we know how much this hard drive has been allocated, then we check if this new allocation fits on the hard drive
- We return a boolean and if this boolean fails, we then backtrack (see main function)

## Generate hard drive allocation

```java

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

- The above method follows the general backtracking schema

0. Check if our solution has been reached - Lines 53 to 58

- By checking if the fileIdx we have incremented throughout recursion is at the final file length,
  then we know that all the files have been allocated on the res array. Then we can fully return the res array

1. Make a choice - Allocate a file to a hard drive - Line 60 to 61

- The choice here that is made is not always optimal, currently we just give the file idx, the hard drive on the loop
  and later we will validate if this choice is correct

2. Validate against constraints - Fitting on hard drive - Verify method - Line 62

- Using the previously explained validate method, if that method returns true, we recursively move on
  to our next fileIdx evaluation ie that file has been allocated correctly.

3. Backtrack - If needed - Line 68

- if after verification, it is found that the file does not fit on this current hard drive, the choice
  is changed back to -1 and must be reevaluated on the next iteration of the hard drive loop

- Sub outcome - if not fit at all, return null for recursive call

### Output of HDDAllocation

```zsh

[0, 0, 1, 2, 0, 1, 2]

# File 0 has size 300MB and goes on HDD0.

# File 1 has size 200MB and goes on HDD0.

# File 2 has size 300MB and goes on HDD1.

# File 3 has size 1200MB and goes on HDD2.

# File 4 has size 400MB and goes on HDD0.

# File 5 has size 700MB and goes on HDD1.

# File 6 has size 700MB and goes on HDD2.

# HDD0 space used 900MB / 1000MB.

# HDD1 space used 1000MB / 1000MB.

# HDD2 space used 1900MB / 2000MB.

```

## Rod cutting

### Constructor

- pretty self explanatory, just assigning the worth for the different prices in the constructor param

```java
public class RodCutting {
  private final int[] prices;

  public RodCutting(int[] prices) {
    this.prices = prices;
  }
```

### Setup

```java

  public LinkedList<Integer> best_cuts() {
    int n = prices.length; // Length of cuts list for reuse

    LinkedList<Integer> cutLengths = new LinkedList<>(); // Tracking best length of cuts for optimal revenue -
                                                         // constantly changing
    LinkedList<Integer> bestPrices = new LinkedList<>(); // Best prices after iterating through prices calculated at
                                                         // particular cuts
    LinkedList<Integer> res = new LinkedList<>(); // Final linkedlist of cuts made for optimal price to return

    for (int i = 0; i < n; i++) { // Fill up LinkedList with 0s for indexing looping reasons
      cutLengths.add(0);
      bestPrices.add(0);
    }
```

- In the above code, we set up the beginning of our dynamic programming algorithm
- Three linked lists are made, the result list, the list of the best prices we can hit, and the cuts made to hit those cuts
- The res list is left empty until the end, and the first two are filled with 0s as a means of future comparison

### Main code

```java
    for (int i = 1; i < n + 1; i++) { // Loop over prices array for comparison
      int maxPrice = -1; // Smallest price for comparsion - could be -infinity
      for (int j = 1; j <= i; j++) { // Looking at next price ahead, depending on value of i ie 1-2,1-8 etc
        // Current price through iteration in comparison to store best prices
        int currPrice = Math.max(maxPrice, prices[j - 1] + bestPrices.get(i - j));
        if (currPrice > maxPrice) { // if our new current price is greater than the loop max price
          maxPrice = currPrice;
          cutLengths.add(i, j); // Add the cut of j at the index i
        }
      }
      bestPrices.add(i, maxPrice);
    }
```

- From here, we loop over the original array of prices
- The max price here is set to -1 so then its the lowest possible,
- Then we loop up until that price to length is decided upon
- The idea is from no cuts, to 1 cut, to 2 cuts etc, we try each one plus the original price
- If this new combination, is actually greater than the current price, we add that index to our length
- Once a best price is decided on each loop, we add that to the price list
- Think a lot of the lists in the fact that

### Result

```sh
# Length correlate to        {1,2,3,4,5,6,7,8,9,10};
#                            {1,5,8,9,12,14,17,19,20,21};

# Best cut at each length: [0, 1, 2, 3, 2, 2, 3, 2, 2, 3, 2]
# Best price at each length: [0, 1, 5, 8, 10, 13, 16, 18, 21, 24, 26]
# The best cuts for a rod of length 10m are

# 2m selling at €5
# 2m selling at €5
# 3m selling at €8
# 3m selling at €8
# The overall price is €26
```
