package assignment2;

import java.util.LinkedList;

public class RodCutting {
    private final int[] prices;

    public RodCutting(int[] prices)
    {
        this.prices = prices;
    }

    public LinkedList<Integer> best_cuts()
    {
        int n = prices.length; // Lenght of cuts list for reuse

        LinkedList<Integer> cutLengths = new LinkedList<>(); // Tracking best length of cuts for optimal revenue - constantly changing
        LinkedList<Integer> bestPrices = new LinkedList<>(); // Best prices after iterating through prices calculated at particular cuts
        LinkedList<Integer> res = new LinkedList<>(); // Final linkedlist of cuts made for optimal price to return

       for (int i = 0; i < n; i++) { // Fill up linkedlists with 0s for indexing looping reasons
           cutLengths.add(0);
           bestPrices.add(0);
       }
       for (int i = 1; i < n+1; i++) { // Loop over prices array for comparison 
           int max_price = -1; // Smallest price for comparsion - could be -infinity
          for (int j = 1; j <=i; j++) { // Looking at next price ahead, depending on value of i ie 1-2,1-8 etc
              int curr_price = Math.max(max_price,prices[j-1]+bestPrices.get(i-j));  // Current price through iteration in comparison to store best prices

              if (curr_price>max_price) { // if our new current price is greater than the loop max price
                  max_price = curr_price; 
                  cutLengths.add(i,j); // Add the cut of j at the index i
              }
          }
          bestPrices.add(i,max_price);
       }

        int finalLength = n;
        while (finalLength > 0){
            int cut = cutLengths.get(finalLength);
            finalLength -= cut;
            res.add(cut);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] prices = {  1, 5, 8, 9, 12, 14, 17, 19, 20, 21 };
        LinkedList<Integer> cuts = new RodCutting(prices).best_cuts();
        System.out.println("The best cuts for a rod of length " + prices.length + "m are");
        int total_price=0;
        for (Integer cut : cuts)
        {
            System.out.println(" - " + cut + "m selling at €"+prices[cut-1]);
            total_price += prices[cut-1];
        }
        System.out.println("The overall price is €"+total_price+".");
    }
}
