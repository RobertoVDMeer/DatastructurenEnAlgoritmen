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
              ,targetSum , pairsTotalNum(nums, targetSum)));
   }

    public static int pairsTotalNum(int[] nums, int total) {
      Arrays.sort(nums);
        System.out.println(MessageFormat.format("original array: {0}",
                Arrays.toString(nums)));
      flipArray(nums);
        System.out.println(MessageFormat.format("sorted array: {0}",
                Arrays.toString(nums)));
      System.out.println(MessageFormat.format("flipped array: {0}",
              Arrays.toString(nums)));

      int lowI = nums.length - 1;
      int highNum = nums[0];
      int lowNum = nums[lowI];
      int totalPairs = 0;

      for(int highI = 0; highI < nums.length && highI < lowI; highI++, lowI--){
          if(highI !=0 && lowI != nums.length -1) {
              // check for doubles
              if (highNum == nums[highI]) { lowI++; continue; }
              if (lowNum == nums[lowI]) { highI--; continue; }
          }

          highNum = nums[highI];
          lowNum = nums[lowI];

//          System.out.println(MessageFormat.format("{0} + {1} = {2}",
//                  highNum, lowNum, highNum + lowNum));
          if (lowNum > highNum) break;

          if(highNum + lowNum == total){
              System.out.println(MessageFormat.format("{0} + {1} = {2}",
                      highNum, lowNum, total));
              totalPairs++;
          }
      }

       return totalPairs;
    }

    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
    public static void flipArray(int[] a){
        int N = a.length;
        for (int i = 0; i < N / 2; i++) {
            int leftNum = a[i];
            int rightIndex = N - 1 - i;
            a[i] = a[rightIndex];
            a[rightIndex] = leftNum;
        }
    }
}

