public class ArrayStack implements Stack<Object> {
	// Last in first out data structure
    private Object[] S;
		private int top;
		private int ArrayStackCapacity;
		private int n;

    public ArrayStack(int capacity) {
        // TASK 2.A.a
				this.ArrayStackCapacity = capacity; // reassigning capacity of the array stack to constructor
				this.S = new Object[capacity]; // Max capacity the array can hold (not length)
				this.top = -1; // the most recently added value (top of array)
				this.n = S.length - 1; // Getting the length of the array (-1 as 0 based)
    }

    public void push(Object x) {
        // TASK 2.A.b
				if (this.top == this.n)  {
        System.out.println("Stack overflow"); // Capacity of the stack has exceeded
				} else {
					this.top++; // Moving the top value index up in the stack 
					this.S[this.top] = x; // Changing 
				}
    }

    public Object pop() {
        // TASK 2.A.c
				if (this.top == -1) {
				System.out.println("Stack underflow");
				return null;
				} else {
					this.top--;
					return S[this.top + 1];
				}
    }

    public Object peek() {
        // TASK 2.A.d
			return this.S[this.top]; // Peek returns the most recently added value so it would be at index 0 i.e. top
    }

    public boolean empty() {
        // TASK 2.A.e
		if (this.ArrayStackCapacity== 0) { // Checking if the capacity is 0 then we already know it is empty
			return true;
			
		} else {
			return false;
		}
    }

    public static void main(String[] args) {
        Stack<Object> test = new ArrayStack(20);
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.push(i+100);
        }
        System.out.println(test.empty());
        System.out.println(test.peek());
        for (int i=0; i<5; i++) {
            int x = (int)test.pop();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i=0; i<15; i++) {
            test.push(i);
        }
        while (!test.empty()) {
            int x = (int)test.pop();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
