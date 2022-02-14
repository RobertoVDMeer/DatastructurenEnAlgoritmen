import java.text.MessageFormat;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Les1 {
//  public static String filePath = "./src/data/sample-01.txt";
  public static String filePath = "./src/data/real-01.txt";

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
        "Total unique {0} found for sum {1}: {2}"
        , "triplets",  targetSum, findTriplets(nums, targetSum)));
  }

  // startI is used by findTriplets, basically saying which number it is
  // and ignore anything before / lower / left of that
  public static int findPairs(int[] nums, int targetSum, int startI) {
    Arrays.sort(nums);
    int lowI = startI;
    int highI = nums.length - 1;
    int totalPairs = 0;

    // TODO On refactoring, realized these highNum and lowNum aren't needed,
    // TODO will leave it though, as I plan to rename the method into frankenCode anyway!
    int highNum = nums[highI];
    int lowNum = nums[lowI];

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
        // found match, move to next low number and reset the high number
        totalPairs++;
        lowI++;
        highI = nums.length - 1;

        if (startI == 0) {
          System.out.println(MessageFormat.format("Found a match: " +
            "{0} + {1} = {2}", lowNum, highNum, sum));
        } else {
          System.out.println(MessageFormat.format("Found a match: " +
              "{0} + {1} + {2} = {3}",nums[startI - 1], lowNum, highNum
              , sum + nums[startI -1]));
        }
      } else if (sum > targetSum) {
        highI--;
      } else {
        lowI++;
      }

      lowNum = nums[lowI];
      highNum = nums[highI];

      // if the pointers meet and the left (low) isn't at the end, move it and
      // reset the high pointer
      if (lowI == highI && lowI != nums.length - 1) {
        lowI++;
        highI = nums.length - 1;
        lowNum = nums[lowI];
        highNum = nums[highI];
      }
    }
    return totalPairs;
  }

  public static int findTriplets(int[] nums, int targetSum) {
    Arrays.sort(nums);
    int matches = 0;
    int i = 0;

    while (nums[i] < targetSum && i != nums.length - 2) {
      // just find pairs for the sum minus the current number
      matches += findPairs(nums, targetSum - nums[i], i + 1);
      i++;
    }

    return matches;
  }
}

