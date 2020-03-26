import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack {
    private String[] s;
    private int N = 0;
    private int k;

    public ResizingArrayStack(int capacity)// for fixed capacity
    {
        k = capacity;
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) throws Myexception {
        if (N == k) {
            throw new Myexception("Stack Overflow");
        }
        s[N++] = item;//N++ is postincrement so assign value first
        // then increment it
    }

    public String pop() throws Myexception {
        if (N == 0) {
            throw new Myexception("Stack UnderFlow Nothing is more to pop");
        }
        String item = s[--N];//At first decrement N then use to index into array
        s[N] = null;
        return item;
    }

    public static void main(String[] args) {
        ResizingArrayStack stack = new ResizingArrayStack(4);
        String[] x = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};
        System.out.println(x.length);

        for (int i = 0; i < x.length; i++) {
            try {
                if (x[i] == "-") {
                    StdOut.print(stack.pop());
                } else stack.push(x[i] + "\t");
            } catch (Myexception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}

// Creating an user defined exception
class Myexception extends Exception {
    public Myexception(String x) {
        super(x);
    }
}

