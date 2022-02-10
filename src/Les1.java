import java.text.MessageFormat;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Les1 {
   public static String filePath = "./src/sample-01.txt";
   public static void main(String args[]) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File(filePath));
       int aantal = scanner.nextInt();
       int targetSum = scanner.nextInt();

       int[] nums = new int[aantal];
       int i = 0;

       while (scanner.hasNext()) {
           if (scanner.hasNextInt()) {
               nums[i] = scanner.nextInt();
               i++;
           }
       }
      System.out.println(MessageFormat.format(
              "total unique pairs found for sum {0}: {1}"
              ,targetSum , pairsTotalNum(nums, targetSum)));
   }

    public static int pairsTotalNum(int[] nums, int total) {
      System.out.println(MessageFormat.format("original array: {0}",
              Arrays.toString(nums)));
      Arrays.sort(nums);

      int lowI = nums.length - 1;
      int highNum = nums[0];
      int lowNum = nums[lowI];
      int totalPairs = 0;

      return totalPairs;

    }

}

