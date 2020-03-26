// Linked list implementation of the stack

import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {
    // we need an additional one pointer for push and pop operation
    private Node first;
    private int sz; // it returns the number of the string store in

    //
    private class Node {
        public String item;
        public Node next;// next is pointer that hold the address of Node object

        public Node(String x) {
            item = x;

        }
    }

    public StackOfStrings()// Create an empty stack
    {
        first = null;
        sz = 0;
    }

    public void push(String item)//insert a new string onto stack
    {
        Node x = new Node(item);
        x.next = first;
        first = x;

    }

    public String pop()// remove and return the string most recently added
    {
        String y = first.item;
        first = first.next;
        return y;

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return sz;
    }


    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        String[] x = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};
        System.out.println(x.length);

        for (int i = 0; i < x.length; i++) {
            if (x[i] == "-") {
                StdOut.print(stack.pop());
            } else stack.push(x[i] + "\t");
        }
    }

    ;
}

