import java.text.MessageFormat;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Les1 {
  public static String filePath = "./src/real-01.txt";

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
        , targetSum, pairsTotalNum(nums, targetSum)));
  }

  public static int pairsTotalNum(int[] nums, int targetSum) {
    Arrays.sort(nums);

    int lowI = 0;
    int highI = nums.length - 1;
    int highNum = nums[highI];
    int lowNum = nums[lowI];
    int totalPairs = 0;

    while (lowI < highI) {
      // check for doubles and skip
      if (lowI != 0 && lowNum == nums[lowI + 1]) {
        lowI++;
        lowNum = nums[lowI];
        continue;
      }
      if (highI != nums.length - 1 && highNum == nums[highI - 1]) {
        highI--;
        highNum = nums[highI];
        continue;
      }

      int sum = nums[lowI] + nums[highI];
      if (sum == targetSum) {
        totalPairs++;
        lowI++;
        highI = nums.length - 1;
        System.out.println(MessageFormat.format("Found a match: " +
            "{0} + {1} = {2}", lowNum, highNum, sum));
      } else if (sum > targetSum) {
        highI--;
      } else {
        lowI++;
      }
      lowNum = nums[lowI];
      highNum = nums[highI];
      if (lowI == highI && lowI != nums.length - 1) {
        lowI++;
        highI = nums.length - 1;
        lowNum = nums[lowI];
        highNum = nums[highI];
      }
    }
    return totalPairs;
  }

}

