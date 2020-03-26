import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
// Geric can be used for any types of data, it just create the copy of that class to specific datatype

public class StackIteratorLinked_List<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;// to keep the track of push and pop operation
    private int k;

    public StackIteratorLinked_List()// for fixed capacity
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
        //   if (N == 0) {
        //     throw new Myexception("Stack UnderFlow Nothing is more to pop");
        //}
        if (N > 0 && N == s.length / 4) {
            shrink(s.length / 2);
        }
        Item item = s[--N];//At first decrement N then use to index into array
        s[N] = null;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() { /* not supported */ }

        public Item next() {
            return s[--i];
        }
    }


    public static void main(String[] args) {
        StackIteratorLinked_List stack = new StackIteratorLinked_List();
        String[] x = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};
        System.out.println(x.length);

        for (int i = 0; i < x.length; i++) {
            stack.push(x[i] + "\t");
        }


        Iterator<String> i = stack.iterator();
        while (i.hasNext()) {
            String s = i.next();
            StdOut.println(s);
        }
        for (Object s : stack) {// for each loop i.e for each item in stack
            StdOut.println(s);
        }

    }

}



