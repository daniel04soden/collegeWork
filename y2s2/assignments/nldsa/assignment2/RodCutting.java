package assignment2;

import java.util.Arrays;
import java.util.LinkedList;

public class RodCutting {
  private final int[] prices;

  public RodCutting(int[] prices) {
    this.prices = prices;
  }

  public LinkedList<Integer> best_cuts() {
    System.out.println(Arrays.toString(prices));
    int n = prices.length; // Length of cuts list for reuse

    LinkedList<Integer> cutLengths = new LinkedList<>(); // Lengths of the cuts made at each 4 = 2, 10 = 2 etc
    LinkedList<Integer> bestPrices = new LinkedList<>(); // Best price at each length 10, 26, 1 is 1, 4 is 10 (2x5)
                                                         // particular cuts
    LinkedList<Integer> res = new LinkedList<>(); // Final linkedlist of cuts made for optimal price to return

    for (int i = 0; i < n; i++) { // Fill up LinkedList with 0s for indexing looping reasons
      cutLengths.add(0);
      bestPrices.add(0);
    }

    for (int i = 1; i < n + 1; i++) { // Loop over prices array for comparison

      int maxPrice = -1; // Smallest price for comparsion - could be -infinity
      for (int j = 1; j <= i; j++) { // Looking at next price ahead, depending on value of i ie 1-2,1-8 etc
        // Current price through iteration in comparison to store best prices

        System.out.println("-----------");
        System.out.println("max price: " + maxPrice);
        System.out.println("Price list price " + prices[j - 1]);
        System.out.println("Best prices price " + prices[j - 1]);
        System.out.println("comparison price " + prices[j - 1] + bestPrices.get(i - j));

        int currPrice = Math.max(maxPrice, prices[j - 1] + bestPrices.get(i - j)); // Cut going to make vs cuts already
                                                                                   // made
        System.out.println("current price " + currPrice);
        if (currPrice > maxPrice) { // if our new current price is greater than the loop max price
          maxPrice = currPrice;
          cutLengths.add(i, j); // Add the cut of j at the index i
        }
      }
      bestPrices.add(i, maxPrice);
    }

    int finalLength = n; // Final length is the size of the prices array - until prices array 0, keep
                         // going
    System.out.println("Deciding on cuts lengths");

    while (finalLength > 0) {
      int cut = cutLengths.get(finalLength);
      System.out.println(cut);
      finalLength -= cut;
      res.add(cut);
    }

    System.out.println("Lengths to achieve price");
    System.out.println(cutLengths);
    System.out.println("Price achieved at specific cut length");
    System.out.println(bestPrices);
    return res;
  }

  public static void main(String[] args) {
    int[] prices = { 1, 5, 8, 9, 12, 14, 17, 19, 20, 21 };
    // int[] length = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    LinkedList<Integer> cuts = new RodCutting(prices).best_cuts();
    System.out.println("The best cuts for a rod of length " + prices.length + "m are");
    int total_price = 0;
    for (Integer cut : cuts) {
      System.out.println(" - " + cut + "m selling at €" + prices[cut - 1]);
      total_price += prices[cut - 1];
    }
    System.out.println("The overall price is €" + total_price + ".");
  }
}
