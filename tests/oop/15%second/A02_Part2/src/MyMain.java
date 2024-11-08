
/**
 * Our main class for the A02_Part2 assignment<br>
 * .
 */
import java.util.Scanner;

public class MyMain {

  // --------------------------------------------------
  // testMovie
  // --------------------------------------------------
  /**
   * This function tests the Movie class.<br>
   */
  public static void testMovie() {
    // 1. We test the Movie class
    Movie m1 = new Drama("Oppenheimer", 10);

    String s = m1.getTitle();
    System.out.println(s);

    int i = m1.revenue();
    System.out.println(i);

    m1.setTitle("Batman");
    i = m1.revenue();
    System.out.println(i);

    Movie m2 = new PeriodDrama("Pride and Prejudice", 10, 20);

    s = m2.getTitle();
    System.out.println(s);

    i = m2.revenue();
    System.out.println(i);

    m2.setTitle("Batman");
    i = m2.revenue();
    System.out.println(i);
  }

  // --------------------------------------------------
  // testDrama
  // --------------------------------------------------
  /**
   * This function tests the Drama class.<br>
   */
  public static void testDrama() {
    // 1. We test the Drama class
    Drama m1 = new Drama("Oppenheimer", 10);

    String s = m1.getTitle();
    System.out.println(s);

    int i = m1.getNumFamousActors();
    System.out.println(i);

    i = m1.revenue();
    System.out.println(i);

    m1.setTitle("Batman");
    i = m1.revenue();
    System.out.println(i);

    m1.setNumFamousActors(100);
    i = m1.revenue();
    System.out.println(i);

    Drama m2 = new PeriodDrama("Pride and Prejudice", 10, 20);

    s = m2.getTitle();
    System.out.println(s);

    i = m2.getNumFamousActors();
    System.out.println(i);

    i = m2.revenue();
    System.out.println(i);

    m2.setTitle("Batman");
    i = m2.revenue();
    System.out.println(i);

    m2.setNumFamousActors(100);
    i = m2.revenue();
    System.out.println(i);
  }

  // --------------------------------------------------
  // testPeriodDrama
  // --------------------------------------------------
  /**
   * This function tests the PeriodDrama class.<br>
   */
  public static void testPeriodDrama() {
    PeriodDrama m1 = new PeriodDrama("Pride and Prejudice", 10, 20);

    String s = m1.getTitle();
    System.out.println(s);

    int i = m1.getNumFamousActors();
    System.out.println(i);

    i = m1.getNumIndoorSets();
    System.out.println(i);

    i = m1.revenue();
    System.out.println(i);

    i = m1.revenue(true);
    System.out.println(i);

    i = m1.revenue(false);
    System.out.println(i);

    m1.setTitle("Batman");

    i = m1.revenue();
    System.out.println(i);

    i = m1.revenue(true);
    System.out.println(i);

    i = m1.revenue(false);
    System.out.println(i);

    m1.setNumFamousActors(100);

    i = m1.revenue();
    System.out.println(i);

    i = m1.revenue(true);
    System.out.println(i);

    i = m1.revenue(false);
    System.out.println(i);

    m1.setNumIndoorSets(2000);

    i = m1.revenue();
    System.out.println(i);

    i = m1.revenue(true);
    System.out.println(i);

    i = m1.revenue(false);
    System.out.println(i);
  }

  // --------------------------------------------------
  // main
  // --------------------------------------------------
  /**
   * This is the main entry point for the Java application.<br>
   */
  public static void main(String[] args) {
    // 1. We parse any input arguments
    Scanner scanning = new Scanner(System.in);
    System.out.println("Would you like to test: \n1)Movie \n2)Drama \n3)PeriodDrama");
    int option = scanning.nextInt();
    scanning.close();

    // 2. We call to the method we want to try
    switch (option) {
      case 1:
        testMovie();
        break;
      case 2:
        testDrama();
        break;
      case 3:
        testPeriodDrama();
        break;
    }
  }

}
