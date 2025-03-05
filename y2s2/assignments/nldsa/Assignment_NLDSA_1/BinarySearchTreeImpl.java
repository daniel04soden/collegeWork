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
    if (key == x.key){
      x.value = value;
    }else if(key < x.key){
      if(x.left == null){
        x.left = Node(key,value);
      }else{
        insert(x.left, key, value);
      }
    }else{
      if (x.right == null) {
       x.right = Node(key,value);
      } else {
        insert(x.right, key, value);
      }
    }


  }

  protected LinkedList<T> inorderTreeWalk(Node<T> x) {
    LinkedList<T> treePath = new LinkedList<T>();
    if (x != null){
      left = inorderTreeWalk(x.left);
      right = inorderTreeWalk(x.right);
      return left + x.value + right;
    }else{
      return new LinkedList<T>();
    }
  }

  protected Node<T> search(Node<T> x, int key) {
    if ((x == null )|| (key == x.key)) {
      return x; 
    }else if(key < x.key){
      return search(x.left,key);
    }else{
      return search(x.right, key);
    }
  }

  protected int depth(Node<T> x) {
    // Base case
    int depthVal = 0;
     if (root == null){
      return depthVal;
    }else{
      int leftVal = depth(x.left);
      int rightVal = depth(x.right);
      depthVal += leftVal + rightVal;
    }
    return depthVal;
  }

  protected Node<T> minimum(Node<T> x) {
    while (x.left!=null) {
      x = x.left;
    }
    return x;
  }

  protected Node<T> maximum(Node<T> x) {
    while (x.right!=null) {
      x = x.right;
    }
    return x;
  }

  protected Node<T> successor(Node<T> x) {
    if (x.right !=null){
      return minimum(x.right);
    }
    Node<T> y = x.parent;

    while ((y!=null) && (x == y.right)) {
      x = y;
      y = y.parent;
    }
    return y;
  }

  protected Node<T> predecessor(Node<T> x) {
    if (x.left) {
      return maximum(x.left);
    }

    Node<T> y = x.parent;

    while ((y!=null) && (x == y.left)) {
     x = y; 
     y = y.parent;
    }

    return y;
  }
  
  protected void transplant(Node u,Node v){
    if (u.parent == null) {
      root = v; 
    }else{
      if (u == u.parent.left) {
        u.parent.left = v;
      }else{
        u.parent.right = v;
      }
      if (v != null) {
        v.parent = u.parent; 
      }
    }
  }; 

  protected void delete(Node<T> z) {
    if (z.left == null) {
      transplant(z,z.right); 
    }else if (z.right == null){
      transplant(z,z.left); 
    }else{
      y = minimum(z.right);
      if (y != z.right) {
      transplant(y,y.right);
      y.right = z.right;
      y.parent.right = y;
      }

      transplant(z,y);
      y.left = z.left;
      y.parent.left = y;
    }
  }
}
