import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Implementinng Randomized queue with the help of the Resizingarray queue
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] x;
    private int N;
    private int k;

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
        x = (Item[]) new Object[2];// this what called ugly type casting
        k = 2;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        x[N++] = item;
        if (N == k) {
            resize(2 * k);
        }

    }

    private void resize(int a) {
        k = a;
        Item[] copy = (Item[]) new Object[a];
        for (int i = 0; i < N; i++) {
            copy[i] = x[i];
        }
        x = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        validity();
        int r = StdRandom.uniform(N);
        Item ret = x[r];
        x[r] = x[N - 1];
        return ret;

    }

    private void validity() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validity();
        int r = StdRandom.uniform(N);
        return x[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        int[] x = new int[]{
                1, 5, 32, 2, 5, 6
        };
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for (int i = 0; i < x.length; i++) {
            d.enqueue(x[i]);
        }
        System.out.println("Printing queue randomly\n");
        Iterator<Integer> t = d.iterator();
        while (t.hasNext()) {
            it
            System.out.print(t.next() + "\t");
        }
        System.out.println("The size of the RandomizedQueue is" + d.size() + "\n");

        for (int i = 0; i < x.length; i++) {
            System.out.print(d.dequeue() + "\t");
        }

    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] ran;
        private int i;

        RandomIterator() {
            ran = (Item[]) new Object[N];//since Generic array can't be created becoz
            // it is not the subtype of the object as like other data type
            for (int k = 0; k < N; k++) {
                ran[k] = x[k];
            }
            k = N;
            StdRandom.shuffle(ran);
        }

        @Override
        public boolean hasNext() {
            return k != 0;
        }

        public Item next() {
            if (k == 0) {
                throw new NoSuchElementException();
            }
            return ran[--k];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
