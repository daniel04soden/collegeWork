public class DynamicQueue implements Queue<Object> {

    List<Object> Q = new DoubleLinkedList();

    public void enqueue(Object x) {
        // TASK 3.B.a
        Q.prepend(x); // Prepending a value is the same as enqueuing a value at the start of a queue
    }

    public Object dequeue() {
        // TASK 3.B.b
        Object dequeuedValue = Q.getLast(); // Defined for return
        Q.deleteLast(); // This is a FIFO algorithm so the first value in must be deleted
        return dequeuedValue; // Returns deleted value
    }

    public Object next() {
        // TASK 3.B.c
        return Q.getLast();
    }

    public boolean empty() {
        // TASK 3.B.d
        return Q.empty();
    }

    public static void main(String[] args) {
        Queue<Object> test = new DynamicQueue();
        System.out.println(test.empty());
        for (int i = 0; i < 10; i++) {
            test.enqueue(i + 100);
        }
        System.out.println(test.empty());
        System.out.println(test.next());
        for (int i = 0; i < 5; i++) {
            int x = (int) test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i = 0; i < 15; i++) {
            test.enqueue(i);
        }
        while (!test.empty()) {
            int x = (int) test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
