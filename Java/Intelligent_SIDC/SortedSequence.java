package Intelligent_SIDC;
public class SortedSequence {

  // My work is modifying the Sequence ADT definition at https://www2.hawaii.edu/~esb/2000fall.ics211/sep05.html
  public MyArrayList<Student> studentSequence;

  // Using Array-based implementation of an ArrayList to make the sequence since it has better runtime.
  // (Hannas lecture 6, slide 38 compares it to linkedlist based)
  public SortedSequence() {
    studentSequence = new MyArrayList<>();
  }

  // https://www.geeksforgeeks.org/binary-search/
  private int binarySearch(int key, int min, int max) {
    if (max >= min) {
      int mid = min + (max - min) / 2;
      if (this.studentSequence.get(mid).getKey() == key) {
        return mid;
      } else if (this.studentSequence.get(mid).getKey() > key) {
        return binarySearch(key, min, max - 1);
      } else {
        return binarySearch(key, min + 1, max);
      }
    }
    return -1;
  }

  public void add(int key, Student student) {
    if (binarySearch(key, 0, this.studentSequence.size() - 1) != -1) {
      System.out.println("This key is already taken");
    }
    if (studentSequence.isEmpty()) {
      studentSequence.add(student);
    } else {
      int j = studentSequence.size();
      for (; j > 0; j--) {
        if (key > studentSequence.get(j - 1).getKey()) {
          break;
        }
      }
      studentSequence.add(j, student);
    }
  }

  // remove element, returns true if removed.
  public boolean remove(int key) {
    int index = binarySearch(key, 0, this.studentSequence.size() - 1);
    if (index == -1) {
      return false;
    } else {
      this.studentSequence.remove(index);
      return true;
    }
  }

  // Get the student
  public Student getValues(int key) {
    int index = binarySearch(key, 0, this.studentSequence.size() - 1);
    if (index == -1) {
      System.out.println("No such student that matches this key.");
    }
    return this.studentSequence.get(index);
  }

  public int nextKey(int key) {
    int index = binarySearch(key, 0, this.studentSequence.size() - 1);
    if (index < 0 || index > studentSequence.size() - 1) {
      System.out.println("No such student that matches this key.");
      return -1;
    }
    return this.studentSequence.get(index + 1).getKey();
  }

  public int previousKey(int key) {
    int index = binarySearch(key, 0, this.studentSequence.size() - 1);
    return this.studentSequence.get(index - 1).getKey();
  }

  public MyArrayList<Integer> allKeys() {
    MyArrayList<Integer> keys = new MyArrayList<>();
    for (int i = 0; i < this.studentSequence.size(); i++) {
      keys.add(studentSequence.get(i).getKey());
    }
    return keys;
  }

  public void clear() {
    studentSequence.clear();
  }
}
