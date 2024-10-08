import java.util.ArrayList;

public class DoubleLinkedList implements List<Object> {
    private class ListNode {
        public ListNode(Object x) {
            key = x;
        }
        public Object key;
        public ListNode prev = null; // Pointer to previous data 
        public ListNode next = null; // Pointer to next data
    }

		private ListNode head; // Pointer to the value at the top of the linked list
		private ListNode tail; // Pointer to the value at the end of the linked list (At value length)
		private int length; // Current length of the doubly linked list (No max capacity)

    public DoubleLinkedList()
    {
        // TASK 1.A
				this.tail = null;
				this.head = null;
				this.length = 0;
			
    }

    public void prepend(Object x) {
        // TASK 1.B
				ListNode newNode = new ListNode(x); // The new value x becomes a node which will point to some previous and next data

			if (empty()) { 
				tail = newNode; // If the entire doublylinked list is empty then it can simply be assigned as the tail
			} else {
				head.prev = newNode; // Otherwise it becomes the previous pointer of the current head
			}
			newNode.next = head; // The new nodes next pointer is the current head
			head = newNode; // Now this node becomes the head
			length++; // the length is now increased by 1
    }

    public Object getFirst() {
        // TASK 1.C
			if (empty()) {
				return null;
			} else {
				return head;
			}
    }

    public Object getLast() {
        // TASK 1.F
			if (empty()) {
				return null;
			} else {
				return tail;
			}
    }

    public void append(Object x) {
        // TASK 1.E
        throw new RuntimeException("Not implemented yet!");
    }

    public void deleteFirst() {
        // TASK 1.D
        throw new RuntimeException("Not implemented yet!");
    }

    public void deleteLast() {
        // TASK 1.G
        throw new RuntimeException("Not implemented yet!");
    }

    public boolean empty() {
        // TASK 1.H
			return length == 0; // Boolean if the length is 0 it is true it is empty
    }

    public static void main(String[] args) {
        List<Object> test = new DoubleLinkedList();
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.prepend(i + 100);
        }
        System.out.println(test.empty());
        for (int i=0; i<5; i++) {
            int x = (int)test.getFirst();
            System.out.print(x + " ");
            test.deleteFirst();
        }
        System.out.println();
        for (int i=0; i<10; i++) {
            test.append(i + 200);
        }
        while (!test.empty()) {
            int x = (int)test.getLast();
            System.out.print(x + " ");
            test.deleteLast();
        }
    }
}
