package com.mycompany.heapStackExperiment;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.PriorityQueue;
import java.util.Stack;

public class App {
  private static Stack<Long> s = new Stack<>();
  private static PriorityQueue<Long> heap = new PriorityQueue<>();

  public static void stackExperimentLoop() {
    Long incr = 1L;
    while (true) {
      s.push(incr);
      incr += incr;
    }
  }

  public static void heapExperimentLoop() {
    Long incr = 1L;
    while (true) {
      heap.add(incr);
      incr += incr;
    }
  }

  public static void test(int decision) {
    switch (decision) {
      case 1:
        long startTime = System.nanoTime();
        try {
          stackExperimentLoop();
        } catch (StackOverflowError | OutOfMemoryError e) {
          long endTime = System.nanoTime();
          long duration = (endTime - startTime);
          duration /= 1000000000;
          System.out.println("Seconds to execute " + duration);
          System.out.println("Stack size (N): " + s.size());
          MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
          MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

          System.out.println("Java Heap Memory Usage: ");
          System.out.println("Used: " + heapMemoryUsage.getUsed());
          System.out.println("Max: " + heapMemoryUsage.getMax());
        }
        break;
      case 2:
        startTime = System.nanoTime();
        try {
          heapExperimentLoop();
        } catch (OutOfMemoryError e) {
          long endTime = System.nanoTime();
          long duration = (endTime - startTime);
          duration /= 1000000000;
          System.out.println("Seconds to execute " + duration);
          System.out.println("heap size (N): " + heap.size());
          MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
          MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

          System.out.println("Java Heap Memory Usage: ");
          System.out.println("Used: " + heapMemoryUsage.getUsed());
          System.out.println("Max: " + heapMemoryUsage.getMax());
        }
        break;
      default:
        break;
    }
  }

  public static void main(String[] args) throws OutOfMemoryError, StackOverflowError {
    System.out.println("Stack Test");
    for (int i = 0; i < 20; i++) {
      System.out.print("-");
    }
    System.out.println();
    test(1);
    System.out.println("Heap Test");
    for (int i = 0; i < 20; i++) {
      System.out.print("-");
    }
    System.out.println();
    test(2);
  }
}