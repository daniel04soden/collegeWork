public class ArrayQueue implements Queue<Object> {
    private Object[] Q;
		private int tail;
		private int head;
		private int n;
		private int maxCap;

    public ArrayQueue(int capacity) {
        // TASK 3.A.a
				this.maxCap = capacity;
				this.n = Q.length;
				
    }

    public void enqueue(Object x) {
        // TASK 3.A.b
        throw new RuntimeException("Not implemented yet!");
    }

    public Object dequeue() {
        // TASK 3.A.c
        throw new RuntimeException("Not implemented yet!");
    }

    public Object next() {
        // TASK 3.A.d
        throw new RuntimeException("Not implemented yet!");
    }

    public boolean empty() {
        // TASK 3.A.e
        throw new RuntimeException("Not implemented yet!");
    }

    public static void main(String[] args) {
        Queue<Object> test = new ArrayQueue(20);
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.enqueue(i+100);
        }
        System.out.println(test.empty());
        System.out.println(test.next());
        for (int i=0; i<5; i++) {
            int x = (int)test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i=0; i<15; i++) {
            test.enqueue(i);
        }
        while (!test.empty()) {
            int x = (int)test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
