import java.text.MessageFormat;
import java.util.*;

public class Les2 {
  public static void main(String[] args) {
//    checkParens("(()(()[])[])[[(())()]])[[[((([])[[]])]]]");
//    checkParens("([()][");
    calculate("(2 + 2)");
    calculate("(2 + (1 + 1))");
    calculate("((8 / 4) + (1 + 1))");
    calculate("(1 + ((2 + 3) * (4 * 5))");
  }

  public static void calculate(String input) {
    System.out.println(checkParens(input));
  }

  public static boolean checkParens(String input) {
    // opener's value is same as key (we add it to the stack)
    final Map<Integer, Integer> openers = Map.of((int) '(', (int) '(', (int) '[', (int) '[');
    // the closers's value is the corresponding opener to check against
    final Map<Integer, Integer> closers = Map.of((int) ')', (int) '(', (int) ']', (int) '[');

    boolean balanced = true;

    Stack<Integer> parensStack = new Stack<Integer>();

    Iterator<Integer> c = input.chars().iterator();
    while (c.hasNext()) {
      int car = c.next();
      if (openers.get(car) != null) {
        parensStack.add(car);
      }
      else if (closers.get(car) != null) {
        int prev = parensStack.isEmpty() ? 0 : parensStack.pop();
        if (closers.get(car) != prev) {
          System.out.println(MessageFormat.format("unbalanced {0} found, prev = {1} "
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
