import java.util.LinkedList;

public class BinarySearchTreeImpl<T> {

    protected static class Node<T> {

        public Node(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int key;
        public T value;
        public Node<T> parent = null;
        public Node<T> left = null;
        public Node<T> right = null;
    }

    protected Node<T> root = null;

    protected void insert(Node<T> x, int key, T value) {
        // TODO: Task 3-A
        if (x == null) { // If the root node is null
            x.key = key; // Set the root as our given key
            x.value = value; // And set the given value as the value of the root
        }

        if (key < x.key) { // If the given key is less than our root key
            insert(x.left, key, value); // Insert is on the left - less than root
        } else { // Otherwise
            insert(x.right, key, value); // Insert it on the right - greater than root
        }
    }

    protected LinkedList<T> inorderTreeWalk(Node<T> x) {
        // TODO: Task 3-B
        throw new RuntimeException("Not yet implemented!");
    }

    protected Node<T> search(Node<T> x, int key) {
        // TODO: Task 3-C
        throw new RuntimeException("Not yet implemented!");
    }

    protected int depth(Node<T> x) {
        // TODO: Task 3-D
        throw new RuntimeException("Not yet implemented!");
    }

    protected Node<T> minimum(Node<T> x) {
        // TODO: Task 3-E
        throw new RuntimeException("Not yet implemented!");
    }

    protected Node<T> maximum(Node<T> x) {
        // TODO: Task 3-F
        throw new RuntimeException("Not yet implemented!");
    }

    protected Node<T> successor(Node<T> x) {
        // TODO: Task 3-G
        throw new RuntimeException("Not yet implemented!");
    }

    protected Node<T> predecessor(Node<T> x) {
        // TODO: Task 3-H
        throw new RuntimeException("Not yet implemented!");
    }

    protected void delete(Node<T> z) {
        // TODO: Task 3-I
        throw new RuntimeException("Not yet implemented!");
    }
}
