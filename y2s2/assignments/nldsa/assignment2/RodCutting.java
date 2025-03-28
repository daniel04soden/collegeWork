package assignment2;

import java.util.*;

public class RodCutting {
  private final int[] prices;

  public RodCutting(int[] prices) {
    this.prices = prices;
  }

  public LinkedList<Integer> best_cuts() {
    int n = this.prices.length; // get the length of the prices list
    LinkedList<Integer> cutting = new LinkedList<>(); // Initialise result linked list - size 0
    cutting.add(1); // Size
    for (int j = 1; j < n-1; j++) {
      int q = (int) Double.NEGATIVE_INFINITY;
      for (int i = 1; i <= j; i++) {
        q = Math.max(q,prices[i-1]+cutting.get(j-i));
      }
        cutting.add(q);
    }

    return cutting;
  }

  public static void main(String[] args) {
    int[] prices = { 1, 5, 8, 9, 12, 14, 17, 19, 20, 21 };
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
