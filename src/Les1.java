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

    public static int pairsTotalNum(int[] nums, int targetSum) {
      System.out.println(MessageFormat.format("original array: {0}"
              , Arrays.toString(nums)));
      Arrays.sort(nums);
        System.out.println(MessageFormat.format("sorted array: {0}"
                , Arrays.toString(nums)));

      int lowI = 0;
      int highI = nums.length - 1;
      int highNum = nums[highI];
      int lowNum = nums[lowI];
      int totalPairs = 0;

      // TODO Sanity arroy of 2, 1, equal numbers
      while (lowNum < highNum) {

          // check for doubles and skip
          if(lowI!=0 && lowNum == nums[lowI - 1]) {
              System.out.println("low double");
              lowI++;
              lowNum = nums[lowI];
               continue;
          }
          if(highI != nums.length -1 && highNum == nums[highI + 1]) {
              System.out.println("high double");
              highI--;
              highNum = nums[highI];
              continue;
          }

          int sum = nums[lowI] + nums[highI];
          System.out.println(MessageFormat.format("{0} + {1} = {2}",
                  nums[lowI], nums[highI], sum));
        if(sum == targetSum) {
          totalPairs++;
          lowI++;
          highI = nums.length -1;
          System.out.println(MessageFormat.format("{0} + {1} = {2}"
                  ,lowNum, highNum, sum));
        } else if (sum < targetSum) {
            System.out.println("sum lower than target");
            highI--;
        } else {
            lowI++;
            highI = nums.length -1;
        }
          System.out.println(MessageFormat.format("old low: {0} old high {1}"
                  ,lowNum, highNum));
          lowNum = nums[lowI];
          highNum = nums[highI];
          System.out.println(MessageFormat.format("new low: {0} new high {1}"
                  ,lowNum, highNum));
          if(lowI + 1 == highI) {
              System.out.println("about to run into pointer");
              lowI++;
              highI = nums.length -1;
          }
      }

      return totalPairs;
    }

}

