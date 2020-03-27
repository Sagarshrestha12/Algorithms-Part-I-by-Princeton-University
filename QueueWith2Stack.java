public class QueueWith2Stack {
    private ResizingArrayStack st1;
    private ResizingArrayStack st2;

    public QueueWith2Stack() {
        st1 = new ResizingArrayStack();
        st2 = new ResizingArrayStack();
    }

    // in queue there are two operation
    // enqueue and dequeue
    // Stack 1 is used for both enqueue and dequeue operation
    // while enqueuing we move all elts of stack1 to stack2 and perform push operation to stack1 and
    // after that we move all elts from stack2 to stack 1 again
    //Time complexity in enqueuning is O(n) and for dequeuing, time complexity is O(1)
    public void enqueue(String x) {
        try {
            while (!st1.isEmpty()) {
                st2.push(st1.pop());
            }
            st1.push(x);
            while (!st2.isEmpty()) {
                st1.push(st2.pop());
            }

        } catch (Myexception l) {
            System.out.println(l.getMessage());
        }
    }

    public String dequeue() throws Exception {
        String x1 = st1.pop();
        try {
            x1 = st1.pop();
            return (st1.pop());
        } catch (Myexception l) {
            System.out.println(l.getMessage());
        }
        return (x1);
    }

    public static void main(String[] args) {
        QueueWith2Stack que = new QueueWith2Stack();
        String[] x = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};
        for (int i = 0; i < x.length; i++) {
            que.enqueue(x[i]);
        }
        for (int i = 0; i < x.length; i++) {
            try {
                System.out.print(que.dequeue());
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
    }

}
