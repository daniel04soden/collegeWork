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
            x.value = value; // The value of x is made as the value of the key - ie replacing the new value
        } else if (key < x.key) { // If our key is less than the key from where we are adding it to 
            if (x.left == null) { // we go the left 
                x.left = node;
                node.parent = x;
            } else {
                insert(x.left, key, value);
            }
        } else {
            if (x.right == null) {
                x.right = node;
                node.parent = x;
            } else {
                insert(x.right, key, value);
            }
        }
    }

    protected LinkedList<T> inorderTreeWalk(Node<T> x) {
        LinkedList<T> treePath = new LinkedList<T>();
        if (x != null) {
            LinkedList<T> left = inorderTreeWalk(x.left);
            LinkedList<T> right = inorderTreeWalk(x.right);
            treePath.addAll(left);
            treePath.add(x.value);
            treePath.addAll(right);
            return treePath;
        } else {
            return new LinkedList<T>();
        }
    }

    protected Node<T> search(Node<T> x, int key) {
        if ((x == null) || (key == x.key)) {
            return x;
        } else if (key < x.key) {
            return search(x.left, key);
        } else {
            return search(x.right, key);
        }
    }

    protected int depth(Node<T> x) {
        int depth = 0;
        if (x == null) {
            return depth; // Base
        } else {
            if (x.left != null) {
                return depth(x.left) + 1;
            } else {
                return depth(x.right) + 1;
            }
        }
    }

    protected Node<T> minimum(Node<T> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    protected Node<T> maximum(Node<T> x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    protected Node<T> successor(Node<T> x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        Node<T> y = x.parent;

        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    protected Node<T> predecessor(Node<T> x) {
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
    if (u.parent == null) {
     root = v; 
    }else{
      if (u == u.parent.left) {
       u.parent.left = v; 
      } else {
       u.parent.right = v; 
      }
    }
    if(v !=null) {
      v.parent = u.parent;
      }
    }

    protected void delete(Node<T> z) {
        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node<T> y = minimum(z.right);
            if (y != z.right) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }
}
