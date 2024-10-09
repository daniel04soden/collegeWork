public class ArrayQueue implements Queue<Object> {
    private Object[] Q;
		private int tail;
		private int head;
		private int n;

    public ArrayQueue(int capacity) {
        // TASK 3.A.a
				this.Q = new Object[capacity]; // The array itself
				this.n = capacity - 1; // The current length (not the capacity)
				this.head = -1;	// Index of the element at the front of the queue
				this.tail = -1; // index of the element at the end of the queue
    }

    public void enqueue(Object x) {
			if (empty()) { // If the tail is at the start of the array
				head = 0; // Points to both head and tail to the start 
				tail = 0;
			}  
        // TASK 3.A.b
				Q[tail] = x; // X is the tail of the array 
				if (tail == n) { // If the tail is equal to the length, then the tail is at the first position
				tail = 0; 
				} else {
				tail++; // otherwise move the index of the tail up to make room for the next value
				}
    }

    public Object dequeue() { // Moving the pointer head over by 1
        // TASK 3.A.c
				Object x = Q[head];
				if (head == n) {
					head = -1;
				} else {
					head++;
				}
		return x;
    }

    public Object next() { // Fn to inspect the next element in the array
        // TASK 3.A.d
			Object x = Q[head];

		if (n == -1) {
			;
		} else {
		x = Q[head+1];
		}
			return x;
    }

    public boolean empty() {
        // TASK 3.A.e
				return head == -1;
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
