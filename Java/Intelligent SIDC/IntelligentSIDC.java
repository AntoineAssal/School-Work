import java.util.Random;

public class IntelligentSIDC {

  private int threshold;
  private int size = 0;
  AVL avl = new AVL();
  SortedSequence sequence = new SortedSequence();

  // Default constructor
  public IntelligentSIDC() {
    threshold = 900;
  }

  public void setThreshold(int threshold) {
    if (threshold < 100 || threshold > 500000) {
      System.out.println(
        "This threshold is invalid. Choose a number between 100 and 500 000"
      );
    } else {
      this.threshold = threshold;
    }
  }

  // Parameterized Constructor
  public IntelligentSIDC(int threshold) {
    setThreshold(threshold);
  }

  public MyArrayList<Integer> generate(int n) {
    Random random = new Random();
    String nums = "0123456789";
    MyArrayList<Integer> generatedKeys = new MyArrayList<>(n);
    for (int i = 0; i < n; i++) {
      String newKey = "";
      do {
        while (newKey.length() < 8) {
          newKey += nums.charAt(random.nextInt(nums.length()));
        }
      } while (
        generatedKeys.contains(newKey) || this.allKeys().contains(newKey)
      );
      generatedKeys.add(Integer.parseInt(newKey));
    }
    return generatedKeys;
  }

  private boolean usingAVL() {
    return size >= threshold;
  }

  public MyArrayList<Integer> allKeys() {
    if (usingAVL()) {
      return avl.allKeys();
    } else {
      return sequence.allKeys();
    }
  }

  // To convert ADT to an AVL if our size > Threshold
  private void convertToAVL() {
    MyArrayList<Integer> keys = sequence.allKeys();
    for (int key : keys) {
      avl.add(key, sequence.getValues(key));
    }
    sequence.clear();
  }

  // To convert ADT to a sequence if our size < Threshold
  private void convertToSequence() {
    MyArrayList<Integer> keys = avl.allKeys();
    for (int key : keys) {
      sequence.add(key, avl.getValues(key));
    }
    avl.clear();
  }

  private void convertADT() {
    MyArrayList<Integer> keys;
    if (avl.allKeys().size() == 0) {
      convertToAVL();
    } else {
      convertToSequence();
    }
  }

  public void add(int key, Student student) {
    boolean usingAVL = usingAVL();
    size++;
    if (usingAVL != usingAVL()) {
      convertADT();
    }
    if (usingAVL()) {
      avl.add(key, student);
    } else {
      sequence.add(key, student);
    }
  }

  public void remove(int key) {
    boolean usingAVL = usingAVL();
    if (usingAVL()) {
      if (avl.remove(key)) {
        size--;
      }
    } else {
      if (sequence.remove(key)) {
        size--;
      }
    }
    if (usingAVL != usingAVL()) {
      convertADT();
    }
  }

  public Student getValues(int key) {
    if (usingAVL()) {
      return avl.getValues(key);
    } else {
      return sequence.getValues(key);
    }
  }

  public int nextKey(int key) {
    if (usingAVL()) {
      return avl.nextKey(key);
    } else {
      return sequence.nextKey(key);
    }
  }

  public int previousKey(int key) {
    if (usingAVL()) {
      return avl.previousKey(key);
    } else {
      return sequence.previousKey(key);
    }
  }

  // public int rangeKey(int key1, int key2){

  // }
  public String ADT() {
    if (usingAVL()) {
      return "AVL";
    } else {
      return "Sequence";
    }
  }

  public int getSize() {
    return size;
  }
}
