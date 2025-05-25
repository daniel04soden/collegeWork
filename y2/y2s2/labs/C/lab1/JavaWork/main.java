import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class main {
  public static void main(String[] args) {
    int[] arr = new int[1000];
    for (int i = 0; i < arr.length; i++) {
      Random myRand = new Random();
      int x = myRand.nextInt(100);
      arr[i] = x;
      System.out.println(arr[i]);
    }

    Set<Integer> uniqueValues = new HashSet<>();

    for (int num : arr) {
      uniqueValues.add(num);
    }

    int[] uniqueArr = uniqueValues.stream().mapToInt(Integer::intValue).toArray();
    List<E> listofVals = Arrays.asList(uniqueArr)

    for (int num : uniqueArr) {
      System.out.print(num + " ,");

    }

  }
}
