import edu.princeton.cs.algs4.StdOut;
// Geric can be used for any types of data, it just create the copy of that class to specific datatype

public class GenericStack<Item> {
    private Item[] s;
    private int N = 0;// to keep the track of push and pop operation
    private int k;

    public GenericStack()// for fixed capacity
    {
        s = (Item[]) new Object[1];// it is type of ugly typecasting
        k = s.length;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // Time complexity of both push and pop operation is n;
    public void push(Item item) {
        if (k == N) {
            resize(2 * k);
        }
        s[N++] = item;//N++ is postincrement so assign value first
        // then increment it
    }

    private void resize(int a) {
        Item[] copy = (Item[]) new Object[a];
        for (int i = 0; i < k; i++) {
            copy[i] = s[i];
        }
        k = a;
        s = copy;
    }

    private void shrink(int a) {
        Item[] copy = (Item[]) new Object[a];
        for (int i = 0; i < s.length / 4; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }


    public Item pop() throws Myexception {
        if (N == 0) {
            throw new Myexception("Stack UnderFlow Nothing is more to pop");
        }
        if (N > 0 && N == s.length / 4) {
            shrink(s.length / 2);
        }
        Item item = s[--N];//At first decrement N then use to index into array
        s[N] = null;
        return item;
    }

    public static void main(String[] args) {
        ResizingArrayStack stack = new ResizingArrayStack();
        String[] x = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is", "-", "-", "-", "-", "-", "-"};
        System.out.println(x.length);

        for (int i = 0; i < x.length; i++) {
            try {
                if (x[i] == "-") {
                    StdOut.print(stack.pop());
                } else stack.push(x[i]);
            } catch (Myexception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}


