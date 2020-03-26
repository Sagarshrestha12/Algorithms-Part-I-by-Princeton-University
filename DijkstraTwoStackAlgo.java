//Performing the arthemetic operation
public class DijkstraTwoStackAlgo {
    public static void main(String[] args) {
        String str = "(1 + ((2 + 3) * (4 * 5)))";
        // for this we need to create a two stack
        GenericStack<Character> ops = new GenericStack<Character>();
        GenericStack<Double> vals = new GenericStack<Double>();// To store the value
        //  System.out.println(str.length());
        char[] ch = new char[str.length()];
        try {
            for (int i = 0; i < str.length(); i++) {
                ch[i] = str.charAt(i);
                if (ch[i] == '(') {
                } else {
                    if (ch[i] == ')') {
                        if (ops.pop() == '+') {
                            vals.push(vals.pop() + vals.pop());
                        }
                        if (ops.pop() == '-') {
                            vals.push(vals.pop() - vals.pop());
                        }
                        if (ops.pop() == '*') {
                            vals.push(vals.pop() * vals.pop());
                        }

                    } else {
                        if (ch[i] == '+') ops.push(ch[i]);
                        else if (ch[i] == '*') ops.push(ch[i]);
                        else if (ch[i] == '-') ops.push(ch[i]);
                        else {
                            System.out.print(ch[i]);
                            vals.push((double) ch[i]);// typecasting from the character to double datatype
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {
                System.out.println(vals.pop());
            } catch (Exception k) {
            }
            System.out.println("Something went wrong");
        }
    }
}
