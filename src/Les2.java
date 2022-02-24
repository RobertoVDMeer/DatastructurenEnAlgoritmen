import javax.print.DocFlavor;
import java.text.MessageFormat;
import java.util.*;

public class Les2 {
  // opener's value is same as key (we add it to the stack)
  final static Map<Integer, Integer> openers = Map.of((int) '(', (int) '(', (int) '[', (int) '[');
  // the closers's value is the corresponding opener to check against
  final static Map<Integer, Integer> closers = Map.of((int) ')', (int) '(', (int) ']', (int) '[');

  public static void main(String[] args) {
//    checkParens("(()(()[])[])[[(())()]])[[[((([])[[]])]]]");
//    checkParens("([()][");

//    calculate("(2 + 2)");
//    calculate("(2 + (1 + 1))");
//    calculate("((8 / 4) + (1 + 1))");
    calculate("(1 + ((2 + 3) * (4 * 5)))");
  }

  // TakeAways: Trying to keep Chars (because of faster, int-based iteration),
  // caused a crapload of extra bookkeeping, especially when the sum is more then
  // one digit (or, god forbid, lower than 0? this will break)
  public static void calculate(String input) {
    if (!checkParens(input)) return;

    System.out.println("calculating for: " + input);

    final List<Integer> ops = List.of((int)'+', (int)'-', (int)'*', (int)'/');

    int spaceChar = Character.hashCode(' ');
    Stack<Integer> resultStack = new Stack<>();
    Stack<Integer> mathStack = new Stack<>();

    Iterator<Integer> c = input.chars().iterator();
    while (c.hasNext()) {
      int car = c.next();
      if (car == spaceChar) continue;

      if (Character.isDigit(car) || ops.contains(car)
          || openers.containsKey(car)) { resultStack.add(car); }

      if (closers.containsKey(car)) {
        // pop from the queue until the corresponding opener, add that to a stack
        // to keep correct order.
        int popped;
        do {
          popped = resultStack.pop();
          if(ops.contains(popped) || Character.isDigit(popped)) {
            mathStack.add(popped);
          }
        }
        while (closers.get(car) != popped);

        // it might be more than 1 char (e.g. 20)
        String leftAsString = "";
        String rightAsString = "";
        int operator = 0;

        while (!mathStack.isEmpty()) {
          int ms = mathStack.pop();

          if (ops.contains(operator)) { rightAsString += Character.getNumericValue(ms); }
          else if (ops.contains(ms)) { operator = ms; }
          else { leftAsString += Character.getNumericValue(ms); }
        }

        int left = Integer.parseInt(leftAsString);
        int right = Integer.parseInt(rightAsString);
//        System.out.println(MessageFormat.format("left= {0}, right = {1}", left, right));

        // a string, because if it's more than 2 digits, 2 chars need to be added
        // back to the resultStack
        String mathStackResult = "";

        switch (operator) {
          case '+':
            mathStackResult += left + right;
            break;
          case '-':
            mathStackResult += left - right;
            break;
          case '*':
            mathStackResult += left * right;
            break;
          case '/':
            mathStackResult += left / right;
            break;
        }

        mathStackResult.chars().forEach(r -> resultStack.add(r));

//        System.out.println("resultStack after adding mathqueue: " + resultStack.toString());
      }
    }
//    System.out.println("resultqueue at end: " + resultStack.toString());
    System.out.print("result: ");
    resultStack.forEach(num -> System.out.print(Character.toString(num)));
  }

  public static boolean checkParens(String input) {

    boolean balanced = true;

    Stack<Integer> parensStack = new Stack<>();

    Iterator<Integer> c = input.chars().iterator();
    while (c.hasNext()) {
      int car = c.next();
      if (openers.get(car) != null) {
        parensStack.add(car);
      } else if (closers.get(car) != null) {
        int prev = parensStack.isEmpty() ? 0 : parensStack.pop();
        if (closers.get(car) != prev) {
          System.out.println(MessageFormat.format("unbalanced {0} found, within = {1} "
              , (char) car, (char) prev));
          balanced = false;
        }
      }
    }

    if (!parensStack.isEmpty()) {
      Iterator<Integer> i = parensStack.iterator();
      while (i.hasNext()) {
        System.out.println(MessageFormat.format("this paren is never closed: {0}"
            , Character.toString(i.next())));
      }
      balanced = false;
    }
    return balanced;
  }
}
