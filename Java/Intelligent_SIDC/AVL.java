package Intelligent_SIDC;

public class AVL {

  // My work is modifying the sorted map implementation of an AVL from the textbook (page 486)
  private class Node {

    int height;
    Node left, right;
    Student student;

    Node(Student student) {
      this.student = student;
      height = 1;
    }
  } // End inner class

  private Node root;
  private Node greater;
  private Node lower;
  private MyArrayList<Integer> keys;

  // Returns the height of a passed tree
  private int height(Node node) {
    if (node == null) return 0;
    return node.height;
  }

  // Returns the balance of a tree [-1,1] inclusive
  private int getBalance(Node node) {
    if (node == null) return 0;
    return height(node.left) - height(node.right);
  }

  // Returns the maximum of two ints
  private int max(int x, int y) {
    return Math.max(x, y);
  }

  // Pimping some stuff from here https://www.baeldung.com/java-avl-trees to build our AVL

  // Right rotates subtree at passed node
  private Node rightRotate(Node node) {
    Node node1 = node.left;
    Node temp = node1.right;
    // flip
    node1.right = node;
    node.left = temp;
    // put new heights
    node.height = max(height(node.left), height(node.right)) + 1;
    node1.height = max(height(node1.left), height(node1.right)) + 1;
    // return new node
    return node1;
  }

  // Left rotates subtree at passed node
  private Node leftRotate(Node node) {
    Node node1 = node.right;
    Node temp = node1.left;
    // flip
    node1.left = node;
    node.right = temp;
    // put new heights
    node.height = max(height(node.left), height(node.right)) + 1;
    node1.height = max(height(node1.left), height(node1.right)) + 1;
    // return new node
    return node1;
  }

  // Add node into the AVL
  private Node insert(Node node, int key, Student student) {
    if (node == null) return (new Node(student));
    if (key < node.student.getKey()) node.left =
      insert(node.left, key, student); else if (
      key > node.student.getKey()
    ) node.right = insert(node.right, key, student);
    // wont allow equal keys
    else return node;
    // update the height of this node
    node.height = 1 + max(height(node.left), height(node.right));
    // get balance of this node to check if its still balanced or not
    int balance = getBalance(node);
    // 4 checks in case now it becomes unbalanced
    // First check left left
    if (balance > 1 && key < (node.left.student.getKey())) return rightRotate(
      node
    );

    // Second right right
    if (balance < -1 && key > (node.right.student.getKey())) return leftRotate(
      node
    );

    // Third Left right
    if (balance > 1 && key > (node.left.student.getKey())) {
      node.right = leftRotate(node.left);
      return rightRotate(node);
    }
    // Fourth right left
    if (balance < -1 && key < (node.right.student.getKey())) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    // return the ref
    return node;
  } // worst case is O(log(N)) cuz the tree is balanced.

  // Delete node from AVL
  private Node deleteNode(Node root, int key) {
    if (root == null) return root;
    // if key we're checking is smaller than the root's key, its in the left subtree
    if (key < root.student.getKey()) root.left = deleteNode(root.left, key);
    // if greater its in the right one
    else if (key > root.student.getKey()) root.right =
      deleteNode(root.right, key);
    // if theyre the same then its what we want to deletede
    else {
      // no child or only one
      if ((root.left == null) || root.right == null) {
        Node temp = null;
        if (temp == root.left) temp = root.right; else temp = root.left;
        // no child
        if (temp == null) {
          temp = root;
          keys.remove(temp);
          root = null;
        }
        // one child
        else root = temp;
      } else {
        // we have a node with two children get the smallest on the right
        Node temp = minNode(root.right);
        // copy
        root.student = temp.student;
        //delete next
        root.right = deleteNode(root.right, temp.student.getKey());
      }
    }
    // only one node
    if (root == null) return root;
    // update height
    root.height = max(height(root.right), height(root.right)) + 1;
    // get balance of this node to check if its still balanced or not
    int balance = getBalance(root);
    // 4 checks in case now it becomes unbalanced
    // left left
    if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);
    // left right
    if (balance > 1 && getBalance(root.right) < 0) {
      root.left = leftRotate(root.left);
      return rightRotate(root);
    }
    // right right
    if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);
    // right left
    if (balance < -1 && getBalance(root.right) > 0) {
      root.right = rightRotate(root.right);
      return leftRotate(root);
    }
    return root;
  } // worst case is O(log(N)) cuz the tree is balanced.

  // Find the node with the smallest key
  Node minNode(Node node) {
    Node current = node;
    // loop through the whole tree and find the leftmost leaf
    while (current.left != null) {
      current = current.left;
    }
    return current;
  }

  // Search through the tree to find the node with corresponding key
  private Node find(Node root, int key) {
    while (root != null) {
      if (key == root.student.getKey()) return root; else if (
        key > root.student.getKey()
      ) root = root.right; else root = root.left;
    }
    return null;
  } // worst case is a function of the height. O(log(N))

  // find the next key given a key
  private void findNext(Node root, int key) {
    while (root != null) {
      if (root.student.getKey() > key) {
        greater = root;
        root = root.left;
      } else {
        root = root.right;
      }
    }
  }

  // find the previous key given a key.
  private void findPrevious(Node root, int key) {
    while (root != null) {
      if (root.student.getKey() < key) {
        lower = root;
        root = root.right;
      } else if (root.student.getKey() > key) {
        root = root.left;
      } else {
        if (root.left != null) {
          lower = root.left;
        }
        root = root.left;
      }
    }
  }

  // Returns all keys in the tree.
  public MyArrayList<Integer> allKeys() {
    keys = new MyArrayList<Integer>();
    inOrder(this.root);
    return keys;
  }

  // Pre-order traversal of the tree: root, left, right.
  private void preOrder(Node node) {
    if (node != null) {
      System.out.println(node.student.getKey() + " ");
      preOrder(node.left);
      preOrder(node.right);
    }
  }

  // In-order traversal of the tree: left, root, right.
  private void inOrder(Node root) {
    if (root == null) return;
    inOrder(root.left);
    keys.add(root.student.getKey());
    inOrder(root.right);
  }

  // Calls find to return student
  public Student getValues(int key) {
    return find(this.root, key).student;
  }

  // Call insert with the student and its key.
  public void add(int key, Student student) {
    this.root = this.insert(this.root, key, student);
  }

  // Call findNext and return the key.
  public int nextKey(int key) {
    findNext(this.root, key);
    int nextKey = greater.student.getKey();
    greater = null;
    return nextKey;
  }

  // Call findPrevious and return the key.
  public int previousKey(int key) {
    findPrevious(this.root, key);
    int previousKey = lower.student.getKey();
    lower = null;
    return previousKey;
  }

  // Call deleteNode on the key specified.
  public boolean remove(int key) {
    try {
      this.deleteNode(this.root, key);
      return true;
    } catch (NullPointerException e) {
      System.out.println("This key doesn't exist in our ISIDC");
      return false;
    }
  }

  // Empty the tree.
  public void clear() {
    root.left = null;
    root.right = null;
    root = null;
  }
} // End AVL Class
