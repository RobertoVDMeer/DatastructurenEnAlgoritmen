import java.lang.reflect.Array;
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

//    nums = new int[] {30, 14, 3, 20, 4};
//    targetSum = 64;

    // The old
//    System.out.println(MessageFormat.format(
//        "Total unique {0} found for sum {1}: {2}"
//        , "pairs",  targetSum, frankenPairs(nums, targetSum, 0)));

    // the new
//    System.out.println(MessageFormat.format(
//        "Total unique {0} found for sum {1}: {2}"
//        , "pairs",  targetSum, findPairs(nums, targetSum, 0)));

//    System.out.println(MessageFormat.format(
//        "Total unique {0} found for sum {1}: {2}"
//        , "triplets",  targetSum, findTriplets(nums, targetSum)));

    fishyFishy();
  }

  public static int findPairs(int[] nums, int targetSum, int low) {
    Arrays.sort(nums);
    int matches = 0;

    while (low < nums.length - 1) {
      // check for duplicates
      if (low != 0 && nums[low] == nums[low -1]) { low++; continue; }

      int high = binSearchRec(nums, targetSum - nums[low], low + 1, nums.length -1);

      // binSearch returns -1 if no match
      if (high != -1) {
        // check for duplicates
        if ( nums[high -1] == nums[high]) continue;

        matches++;
      }
      low++;
    }

    return matches;
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

  // Binary search: find middle of range and check, then ignore half the array
  // depending on if it was higher or lower, until the target number is found.
  // Recursive implementation: method will call itself until solved, before returning
  // to caller
  public static int binSearchRec(int[] nums, int target, int low, int high) {
    if (high < low) return -1;
    int middle = low + ((high - low) /2);

    if(nums[middle] == target) {
      return middle;
    }

    if(nums[middle] > target) {
      return binSearchRec(nums, target, low, middle - 1);
    } else {
      return binSearchRec(nums, target, middle + 1, high);
    }
  }

  public static void fishyFishy() {

//    byte[] school = new byte[] {3, 4, 3, 1, 2};
    byte[] school = new byte[] {3,5,1,2,5,4,1,5,1,2,5,5,1,3,1,5,1,3,2,1,5,1,1,1,2,3,1,3,1,2,1,1,5,1,5,4,5,5,3,3,1,5,1,1,5,5,1,3,5,5,3,2,2,4,1,5,3,4,2,5,4,1,2,2,5,1,1,2,4,4,1,3,1,3,1,1,2,2,1,1,5,1,1,4,4,5,5,1,2,1,4,1,1,4,4,3,4,2,2,3,3,2,1,3,3,2,1,1,1,2,1,4,2,2,1,5,5,3,4,5,5,2,5,2,2,5,3,3,1,2,4,2,1,5,1,1,2,3,5,5,1,1,5,5,1,4,5,3,5,2,3,2,4,3,1,4,2,5,1,3,2,1,1,3,4,2,1,1,1,1,2,1,4,3,1,3,1,2,4,1,2,4,3,2,3,5,5,3,3,1,2,3,4,5,2,4,5,1,1,1,4,5,3,5,3,5,1,1,5,1,5,3,1,2,3,4,1,1,4,1,2,4,1,5,4,1,5,4,2,1,5,2,1,3,5,5,4,5,5,1,1,4,1,2,3,5,3,3,1,1,1,4,3,1,1,4,1,5,3,5,1,4,2,5,1,1,4,4,4,2,5,1,2,5,2,1,3,1,5,1,2,1,1,5,2,4,2,1,3,5,5,4,1,1,1,5,5,2,1,1};
    int days = 80;

//    fishyOne(school, days);
    fishyTwo(school, days);
  }

  private static void fishyOne(byte[] school, int days) {
    final byte BIRTHING = 0;
    final byte REST = 6;
    final byte BORN = 8;

    for (int i = 0; i < days; i++) {
      int newlyBorn = 0;

      for (int k = 0; k < school.length; k++) {
          if (school[k] == BIRTHING) {
            newlyBorn++;
            school[k] = REST;
          } else {
            school[k]--;
          }
      }

      if (newlyBorn > 0) {
        school = Arrays.copyOf(school, school.length + newlyBorn);
        Arrays.fill(school, school.length - newlyBorn, school.length, BORN);
      }
    }
    System.out.println(school.length);
  }

  private static void fishyTwo (byte[] origSchool, int days) {
    final byte BIRTHING = 0;
    final byte REST = 5;
    final byte BORN = 7;

    System.out.println("Love is in the air!");
  }

  // startI is used by findTriplets, basically saying which number it is
  // and ignore anything before / lower / left of that
  public static int frankenPairs(int[] nums, int targetSum, int startI) {
    Arrays.sort(nums);
    int lowI = startI;
    int highI = nums.length - 1;
    int totalPairs = 0;

    // TODO On refactoring, realized these highNum and lowNum aren't needed,
    // TODO will leave it though, as I plan to rename the method to frankenCode anyway!
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
}