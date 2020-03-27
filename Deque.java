import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Node preNode;
        Node nextNode;
        Item item;

        public Node(Item x) {
            item = x;
        }
    }

    private Node first;// are the pointer
    private Node last;//this variable is declared to
    private int N;
    // private Node x;

    // construct an empty deque
    public Deque() {// since we are using the double linked list we didn't need to worry about resize of deque// since we are using
        N = 0;// doubly linked list it consumes more power
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("null object can't be added");
        }
        Node n = new Node(item);
        if (isEmpty()) {
            n.preNode = null;
            n.nextNode = null;
            first = n;
            last = n;
        } else {
            n.preNode = null;
            n.nextNode = first;
            first.preNode = n;
            first = n;
        }
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("null object can't be added");
        }
        Node n = new Node(item);
        if (isEmpty()) {
            n.preNode = null;
            n.nextNode = null;
            first = n;
            last = n;
        } else {
            n.nextNode = null;
            n.preNode = last;
            last.nextNode = n;
            last = n;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        Item x = null;
        if (N == 1) {
            x = first.item;
            first = null;
            last = null;
        } else {
            Node t = first;
            x = t.item;
            first = t.nextNode;
            t.item = null;
            t.nextNode = null;
            t.preNode = null;
        }
        N--;
        return x;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        Item x = null;
        if (N == 1) {
            x = first.item;
            first = null;
            last = null;
        } else {
            Node t = last;
            x = t.item;
            last = t.preNode;
            t.preNode = null;
            t.nextNode = null;
            t.item = null;
        }
        N--;
        return x;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new reverse();
    }

    private class reverse implements Iterator<Item> {
        private Node i = first;

        @Override
        public boolean hasNext() {
            return i != null;
        }

        @Override
        public Item next() {
            if (i == null) {
                throw new NoSuchElementException();
            }
            Item x = i.item;
            i = i.nextNode;
            return x;

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int[] x = new int[]{
                1, 5, 32, 2, 5, 6
        };
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < x.length; i++) {
            d.addLast(x[i]);
        }
        System.out.println(" Printing for addlast");
        Iterator<Integer> t = d.iterator();
        while (t.hasNext()) {
            System.out.print(t.next() + "\t");
        }
        System.out.println(" Removing from last\n");
        for (int i = 0; i < x.length; i++) {
            System.out.print(d.removeLast() + "\t");
        }


        for (int i = 0; i < x.length; i++) {
            d.addFirst(x[i]);
        }
        System.out.println("The size of the deque is " + d.size());
        System.out.println(" Printing for addFirst");
        Iterator<Integer> l = d.iterator();
        while (l.hasNext()) {
            System.out.print(l.next() + "\t");
        }
        System.out.println("Printing by removing First");
        for (int i = 0; i < x.length; i++) {
            System.out.print(d.removeFirst() + "\t");
        }

    }

}
