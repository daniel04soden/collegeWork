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
    private int length; // Current length of the doubly linked list (Not max capacity)

    public DoubleLinkedList() {
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
        if (empty()) { // If the list is empty we return no values
            return null;
        } else {
            return head.key; // Otherwise we return the first value, the head's key
        }
    }

    public Object getLast() {
        // TASK 1.F
        if (empty()) { // If the list is empty we return no values
            return null;
        } else {
            return tail.key; // Otherwise we return the first value, the head's key
        }
    }

    public void append(Object x) {
        // TASK 1.E
        ListNode newestNode = new ListNode(x); // Creating a new node of the value x
        if (empty()) { // If the linked list is already empty then just let it be the head
            head = newestNode;
        } else { // Otherwise the next node after the current tail is x
            newestNode.prev = tail; // Now the previous node of newestNode is tail
            newestNode.next = null;
            tail.next = newestNode;
        }
        tail = newestNode; // Finally the tail becomes the newNode and the length increases
        length++;
    }

    public void deleteFirst() {
        // TASK 1.D
        if (empty()) { // If it is empty
        } else if (tail == head) { // If theres only one value in the list (head is the same as tail)
            head = null;
            tail = null;
        } else {
            head.next.prev = null; // The next and previous value of the first value is the first value
            head = head.next;
        }
    }

    public void deleteLast() {
        // TASK 1.G
        if (empty()) { // If it is empty
        } else if (tail == head) { // If theres only one value in the list (head is the same as tail)
            head = null;
            tail = null;
        } else {
            tail.prev.next = null; // The next and previous value of the first value is the first value
            tail = tail.prev;
        }
    }

    public boolean empty() {
        // TASK 1.H
        return tail == null && head == null; // Tail and head must be null for it to be empty
    }

    public static void main(String[] args) {
        List<Object> test = new DoubleLinkedList();
        System.out.println(test.empty());
        for (int i = 0; i < 10; i++) {
            test.prepend(i + 100);
        }
        System.out.println(test.empty());
        for (int i = 0; i < 5; i++) {
            int x = (int) test.getFirst();
            System.out.print(x + " ");
            test.deleteFirst();
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            test.append(i + 200);
        }
        while (!test.empty()) {
            int x = (int) test.getLast();
            System.out.print(x + " ");
            test.deleteLast();
        }
    }
}
