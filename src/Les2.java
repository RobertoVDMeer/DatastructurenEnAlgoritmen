import java.text.MessageFormat;
import java.util.*;

public class Les2 {
  public static void main(String[] args) {
    checkParens("(()(()[])[])[[(())()]])[[[((([])[[]])]]]");
//    checkParens("([()][");
  }

  public static void checkParens(String input) {
    // opener's value is same as key (we add it to the stack)
    final Map<Integer, Integer> openers = Map.of((int)'(', (int)'(', (int)'[', (int)'[');
    // the closers's value is the corresponding opener to check against
    final Map<Integer, Integer> closers = Map.of((int)')', (int)'(', (int)']', (int)'[');

    Stack<Integer> parensStack = new Stack<Integer>();

    input.chars().forEach(i -> {
          if (openers.get(i) != null) {
            parensStack.add(i);
          } else if (closers.get(i) != null){
            int prev = parensStack.isEmpty() ? 0 : parensStack.pop();
            if (closers.get(i) != prev) {
              System.out.println(MessageFormat.format("unbalanced {0} found, prev = {1} "
                  , (char) i, (char)prev));
            }
          }
        }
    );

    if(!parensStack.isEmpty()) {
      Iterator<Integer> i = parensStack.iterator();
      while(i.hasNext()){

        System.out.println(MessageFormat.format("this paren is never closed: {0}"
            , Character.toString(i.next())));
      }
    }
  }
}
