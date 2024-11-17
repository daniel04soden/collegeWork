import java.util.LinkedList;

public class Greedy {

    public static LinkedList<Activity> activitySelection(LinkedList<Activity> activities) {
        // TASK 1.B.a
        LinkedList<Activity> A = new LinkedList<>(); // Defining the linked list for activities
        Activity firstActivity = activities.getFirst(); // Get and add the very first values
        A.add(firstActivity); 
        boolean overlap = false; // Overlapping is false as we start


        for (int i = 0; i < activities.size(); i++) { // Iterate over the linked list
        Activity newActivity = activities.get(i); // Get i to then add it
        overlap = newActivity.overlap(firstActivity);

            if (!overlap) { // If there's no overlap between start and finish times
                A.add(newActivity); // Add an activity
                firstActivity = newActivity;
            }
        }

        return A; // Return A after adding all this
    }

    public static LinkedList<Integer> makeChange(int amount, int[] denominations) {
        // TASK 1.B.b
        int n = denominations.length; // Find the length of the denominations array

        LinkedList<Integer> change = new LinkedList<Integer>(); // Initialise the linked list as the given change

        for (int i = 0; i < n; i++) { // Loop over the types of change we have
            if (denominations[i] <= amount) { // If the change at the array is less than or equal to the amount add to list and go back one
                change.add(denominations[i]);
                amount -= denominations[i];
                i--;
            } else {
                ;
            }
        }

        return change;
    }

    public static void main(String[] args) {
        LinkedList<Activity> activities = new LinkedList<Activity>();
        activities.add(new Activity(1,1, 4));
        activities.add(new Activity(2, 3, 5));
        activities.add(new Activity(3, 0, 6));
        activities.add(new Activity(4, 5, 7));
        activities.add(new Activity(5, 3, 8));
        activities.add(new Activity(6, 5, 9));
        activities.add(new Activity(7, 6, 10));
        activities.add(new Activity(8, 8, 11));
        activities.add(new Activity(9, 8, 12));
        activities.add(new Activity(10, 2, 13));
        activities.add(new Activity(11, 12, 14));
        activitySelection(activities).forEach(a -> a.print());


        // Added multiple test cases to ensure isn't hard coded
        System.out.println("Test one");
        makeChange(1234, new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }).forEach(i -> System.out.println(i));
        System.out.println("Test two");
        makeChange(34567, new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }).forEach(i -> System.out.println(i));
        System.out.println("Test three");
        makeChange(900, new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }).forEach(i -> System.out.println(i));
        System.out.println("Test four");
        makeChange(25, new int[] { 5000, 2000, 1000, 500, 200, 100, 50,20,10, 5, 2, 1 }).forEach(i -> System.out.println(i));

    }
}
