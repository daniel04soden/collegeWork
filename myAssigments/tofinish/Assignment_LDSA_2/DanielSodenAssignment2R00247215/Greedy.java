import java.util.LinkedList;

public class Greedy {

    public static LinkedList<Activity> activitySelection(
        LinkedList<Activity> activities
    ) {
        /* The function activity selection takes in a linked list of activities and from
		  here uses a greedy form of selection to maximise the amount of activities
			while taking into account activity start and finish time

			To implement this we get the first value from the activity list, add it to our
			linked list A, set our overlap to false and loop through the activities and if
			there is no overlap we add a new activity

			params: LinkedList<Activity> activities; A linked list containing possible activities

			returns:
						LinkedList<Activity> A; Linked list of activities that fit into our greedy selection

		 */
        // TASK 1.B.a
        LinkedList<Activity> A = new LinkedList<>(); // Defining the linked list for activities
        Activity firstActivity = activities.getFirst(); // Get and add the very first values
        A.add(firstActivity);
        boolean overlap = false; // Overlapping is false as we start

        // Get i to then add it
        for (Activity newActivity : activities) { // Iterate over the linked list
            overlap = newActivity.overlap(firstActivity);

            if (!overlap) { // If there's no overlap between start and finish times
                A.add(newActivity); // Add an activity
                firstActivity = newActivity; // Reinitializing the firstActivity, as the new activity
            }
        }

        return A; // Return A after adding all this
    }

    public static LinkedList<Integer> makeChange(
        int amount,
        int[] denominations
    ) {
        /*
		 The function makeChage takes in an int amount that a person has given and
		 using greedy selection, it will use the denominations array as a cash register
		 in a way and takes out the values that make up the change from the amount given

			params: int amount; amount of money to make into change
			        int[] denominations: the available "notes or coins" we can divide this change into

			returns:
			         LinkedList<Integer> change;  Linked list containing the integers making up the amount


			*/
        // TASK 1.B.b
        int n = denominations.length; // Find the length of the denominations array

        LinkedList<Integer> change = new LinkedList<Integer>(); // Initialise the linked list as the given change

        for (int i = 0; i < n; i++) { // Loop over the types of change we have
            int currentChange = denominations[i];
            if (currentChange <= amount) { // If the change at the array is less than or equal to the amount add to list
                // and go back one
                change.add(currentChange); // Add the value at i of possible denomiations
                amount -= currentChange; // We have taken
                i--;
            }
        }

        return change;
    }

    public static void main(String[] args) {
        LinkedList<Activity> activities = new LinkedList<Activity>();
        activities.add(new Activity(1, 1, 4));
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
        System.out.println("Test one: 1234");
        makeChange(
            1234,
            new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }
        ).forEach(i -> System.out.println(i));
        System.out.println("Test two 34567");
        makeChange(
            34567,
            new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }
        ).forEach(i -> System.out.println(i));
        System.out.println("Test three 900");
        makeChange(
            900,
            new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }
        ).forEach(i -> System.out.println(i));
        System.out.println("Test four 25");
        makeChange(
            25,
            new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }
        ).forEach(i -> System.out.println(i));
    }
}
