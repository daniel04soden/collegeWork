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
        // TODO: Task 2
        throw new RuntimeException("Not yet implemented!");
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