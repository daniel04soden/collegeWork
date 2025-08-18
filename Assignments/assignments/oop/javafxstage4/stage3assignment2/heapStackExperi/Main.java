import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

  public static void heapExperiment(String fileString) {
    long start = System.nanoTime();
    try (FileWriter write = new FileWriter(fileString, false)) {
      write.write("ObjectsMade,MemoryLeft,TimeToAdd\n");
      ArrayList<long[]> storage = new ArrayList<>();
      int objectsMade = 0;
      long logSlow = start; // start tracking for viewing slowdown
      while (true) {
        // general loop

        long[] largeObject = new long[1000000]; // Large object to be added
        storage.add(largeObject);
        objectsMade++;

        long memoryLeft = Runtime.getRuntime().freeMemory();
        long end = System.nanoTime();
        long timeToAdd = (end - start) / 1000000;

        write.write(objectsMade + "," + memoryLeft + "," + timeToAdd + "\n");
        if ((end - logSlow) / 1000000 >= 1000) {
          System.out.println("Objects Made: " + objectsMade + ", Memory Left: " + memoryLeft / (1024 * 1024)
              + " MB, Time from start: " + timeToAdd + " ms");
          logSlow = end;
        }
      }
    } catch (OutOfMemoryError e) {
      long endTime = (System.nanoTime() - start) / 1000000;
      System.out.println("OutOfMemoryError occurred after " + endTime + " ms");
    } catch (IOException e) {
      System.err.println("IOException occurred: " + e.getMessage());
    }
  }

  public static void stackExperiment(String fileName) {
    long start = System.nanoTime();
    try (FileWriter write = new FileWriter(fileName, false)) {
      write.write("Depth,TimeFromStart,MemoryLeft,ErrorType\n");
      try {
        recursiveMethod(0, write, start); 
      } catch (StackOverflowError e) {
        long end = System.nanoTime();
        long timeTaken = (end - start) / 1000000;
        System.out.println("StackOverflowError occurred after " + timeTaken + " ms");
      }
    } catch (IOException e) {
      System.err.println("IOException occurred: " + e.getMessage());
    }
  }

  // Recursion casues stackoverflow - call stack
  private static void recursiveMethod(int depth, FileWriter write, long startTime) throws IOException {
    long[] largeArray = new long[1000];
    long now = System.nanoTime();
    long timeFromStart = (now - startTime) / 1000000;
    long memoryLeft = Runtime.getRuntime().freeMemory(); 
    write.write(depth + "," + timeFromStart + "," + memoryLeft + "\n"); 
    System.out.println("Depth: " + depth + ", Time from start: " + timeFromStart + " ms, Memory Left: "
        + memoryLeft / (1024 * 1024) + " MB");

    recursiveMethod(depth + 1, write, startTime);
  }

  public static void test(int decision) {
    switch (decision) {
      case 1:
        heapExperiment("heapExperimentData.csv");
        break;
      case 2:
        stackExperiment("stackExperimentData.csv");
        break;
      default:
        System.out.println("Error invalid input for experiment");
        break;
    }
  }

  public static void main(String[] args) {
    test(2);
  }
}