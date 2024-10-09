public class ArrayStack implements Stack<Object> {
	// Last in first out data structure
    private Object[] S; // Initialising the array 
		private int top; // The index of the most recently added value 
		private int ArrayStackCapacity;
		private int n; // To be the length of the array 

    public ArrayStack(int capacity) {
        // TASK 2.A.a
				this.ArrayStackCapacity = capacity; // reassigning capacity of the array stack to constructor
				this.S = new Object[capacity]; // constructing the array itself
				this.top = -1; // the most recently added value (top of array)
				this.n = capacity - 1; // Getting the length of the array (-1 as 0 based)
    }

    public void push(Object x) {
        // TASK 2.A.b
				if (top >= n)  { // If the value at the top of the array is equal to the length of the array (we are at the top)
        System.out.println("Stack overflow"); // Capacity of the stack has exceeded
				} else {
					top++; // Moving the top value index up in the stack 
					S[top] = x; // Changing the top to the newly added element
				}
    }

    public Object pop() {
        // TASK 2.A.c
				if (top == -1) { // If the array is already empty 
				return null;
				} else { // Any other condition 
					top--; // Changing the position of the top value
					return S[top+1]; // The value at the top is now the 2nd last value added originally
				}
    }

    public Object peek() {
        // TASK 2.A.d
			return S[top]; // Peek returns the most recently added value so it would be at index 0 i.e. top
    }

    public boolean empty() {
        // TASK 2.A.e
			return top == -1; // This is empty as the value most recently added is at index [-1] which cannot exist
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
