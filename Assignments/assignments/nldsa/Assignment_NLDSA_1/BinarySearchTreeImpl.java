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
        Node<T> node = new Node<T>(key, value);
        if (key == x.key) { // If the key of the new value is the same from the node we are adding it to (keep recursion in mind)
            x.value = value; // The value of x is made as the value of the key - ie replacing the original value
        } else if (key < x.key) { // If our key is less than the key from where we are adding it to
            if (x.left == null) { // we go the left and add insert there if
                // null
                x.left = node;
                node.parent = x;
            } else { // Otherwise recurse to the left
                insert(x.left, key, value);
            }
        } else { // Essentially if key > x.key
            if (x.right == null) { // we check if the right is null and if so
                x.right = node;
                node.parent = x; // Assign x to the right
            } else { // Otherwise recurse to the right
                insert(x.right, key, value);
            }
        }
    }

    protected LinkedList<T> inorderTreeWalk(Node<T> x) {
        LinkedList<T> treePath = new LinkedList<T>();
        if (x != null) { // If x isnt null
            LinkedList<T> left = inorderTreeWalk(x.left); // walk to the left
            // firsat
            LinkedList<T> right = inorderTreeWalk(x.right); // then the right
            treePath.addAll(left); // add all values from the left to th elist
            treePath.add(x.value); // add x itself
            treePath.addAll(right); // then add right
            return treePath;
        } else {
            return new LinkedList<T>(); // if null - return empty linked list
        }
    }

    protected Node<T> search(Node<T> x, int key) {
        if ((x == null) || (key == x.key)) { // Using bst property, easily
            // perform binary search
            return x;
        } else if (key < x.key) { // if less than x, recurse left
            return search(x.left, key);
        } else { // if greater than x, recurse right
            return search(x.right, key);
        }
    }

    protected int depth(Node<T> x) {
        int depth = 0; // Init depth
        if (x == null) { // if null
            return depth; // Return current depth
        } else { // Otherwise, recurse the non null direction + 1 - accoutning
            // for the current node
            if (x.left != null) {
                return depth(x.left) + 1;
            } else {
                return depth(x.right) + 1;
            }
        }
    }

    protected Node<T> minimum(Node<T> x) {
        while (x.left != null) {
            x = x.left; // Finding most left value
        }
        return x;
    }

    protected Node<T> maximum(Node<T> x) {
        while (x.right != null) {
            x = x.right; // Finding most right value
        }
        return x;
    }

    protected Node<T> successor(Node<T> x) {
        if (x.right != null) {
            return minimum(x.right); // If the right isnt null - successor is
            // simply minimum from there
        }
        Node<T> y = x.parent; // Otherwise find parent

        while ((y != null) && (x == y.right)) { // While parent isnt null and
            // x is the right of its parent
            x = y;
            y = y.parent; // swap nodes
        }
        return y; // Return sucessor
    }

    protected Node<T> predecessor(Node<T> x) {
        // Same idea, reverse
        if (x.left != null) {
            return maximum(x.left);
        }

        Node<T> y = x.parent;

        while ((y != null) && (x == y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    protected void transplant(Node<T> u, Node<T> v) {
        if (u.parent == null) { // if parent of u is null, v becomes the root node
            root = v;
        } else {
            if (u == u.parent.left) { // if u is on the left
                u.parent.left = v; // make v the value u's  left
            } else {
                u.parent.right = v; // Otherwise, make v te value u's right
            }
        }
        if (v != null) { // if v isnt null, just make the parent of u, the parent of 
            // v
            v.parent = u.parent;
        }
    }

    protected void delete(Node<T> z) {
        if (z.left == null) { // if left of node to delete is null
            transplant(z, z.right); // swap up to right
        } else if (z.right == null) { // inverse of previous statement
            transplant(z, z.left);
        } else { // otherwise (if both left and right arent null
            Node<T> y = minimum(z.right); // find min of right (smallest bigger
            // value)
            if (y != z.right) { // if the min isnt the right of z
                transplant(y, y.right); // swap y and its right value
                y.right = z.right; // the right of y now becomes the original
                // right of z
                y.right.parent = y; // y.right.parent then becomes y
            }

            transplant(z, y); // transplant z and y
            y.left = z.left; // swap left and rights
            y.left.parent = y; // make y the left parent
        }
    }
}
