package CPU_Scheduling_Simulator;
public class LinkedHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

  protected static class Node<E> implements Position<E> {

    private E element;
    private Node<E> parent; // a reference to the parent node (if any)
    private Node<E> left; // a reference to the left child (if any)
    private Node<E> right; // a reference to the right child (if any)

    // Constructs a node with the given element and neighbors.
    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
      element = e;
      parent = above;
      left = leftChild;
      right = rightChild;
    }

    @Override
    public E getElement() throws IllegalStateException {
      return element;
    }

    // accessor methods
    public Node<E> getParent() {
      return parent;
    }

    public Node<E> getLeft() {
      return left;
    }

    public Node<E> getRight() {
      return right;
    }

    // update methods
    public void setElement(E element) {
      this.element = element;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }
  }

  protected Node<Pair<K, V>> root = null;
  private int size = 0;

  // Returns the number of nodes in the tree.
  @Override
  public int size() {
    return size;
  }

  //public Iterator<Pair<K, V>> iterator() {
   // return null;
 // }

  public Iterable<Position<Pair<K, V>>> positions() {
    return null;
  }

  @Override
  public Pair<K, V> insert(K key, V value) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Pair<K, V> min() {
    return root.element;
  }

  @Override
  public Pair<K, V> removeMin() {
    return null;
  }
}
