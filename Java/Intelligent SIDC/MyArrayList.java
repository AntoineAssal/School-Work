import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<T> implements Iterable<T> {

  // My work is modifying the Array-based implementation of an ArrayList in the textbook (page 260) for our needs.
  private final int initialCapacity = 10;
  private T[] data;
  private int size = 0;

  // Make a new ArrayList with the default size.
  public MyArrayList() {
    data = (T[]) new Object[initialCapacity];
  }

  public MyArrayList(int capacity) {
    data = (T[]) new Object[capacity];
  }

  // Double the capacity
  T[] increaseCapacity(T[] array) {
    int newCapacity = data.length * 2;
    T[] newArray = (T[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newArray[i] = array[i];
    }
    return newArray;
  }

  // Half the capacity
  T[] cutCapacity(T[] arr) {
    int newCapacity = data.length / 2;
    T[] newArr = (T[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newArr[i] = arr[i];
    }
    return newArr;
  }

  // Get the size
  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  public int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) if (data[i] == null) return i;
    } else {
      for (int i = 0; i < size; i++) if (o.equals(data[i])) return i;
    }
    return -1;
  }

  // Get element at index
  public T get(int i) {
    if (i == size) {
      return null;
    }

    return data[i];
  }

  // Add an element to the specified index
  public void add(int index, T element) {
    // Check if it's in bounds.
    if (index < 0 || index > size + 1) throw new IndexOutOfBoundsException(
      "Index : " + index + " is not in the range"
    );
    // if we're at max capacity increase it
    if (size == data.length) data = increaseCapacity(data);
    // push the elements past the index to the right to have space to add a new one
    for (int i = size - 1; i >= index; i--) {
      data[i + 1] = data[i];
    }
    size++;
    //add the element
    data[index] = element;
  }

  // Add an element to the last slot in the array, returns true when added
  public Boolean add(T element) {
    if (size == data.length) data = increaseCapacity(data);
    data[size] = element;
    size++;
    return true;
  }

  // Insert an element at specified index
  public void insert(int index, T element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(
        "Index: " + index + " not in range of data size " + size
      );
    } else {
      //replace element at index
      data[index] = element;
    }
  }

  public Boolean remove(Object o) {
    //look for the index of the object to delete and remove the object at that index.
    for (int i = 0; i < size; i++) {
      if (data[i].equals(o)) {
        remove(i);
        return true;
      }
    }

    return false;
  }

  // Remove element at specified index
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(
        "Index: " + index + " not in range of data size " + size
      );
    }
    Object removedElement = data[index];
    // Push every element past the position of the element to delete to the left to get rid of the element at index.
    for (int i = index; i < data.length - 1; i++) {
      data[i] = data[i + 1];
    }
    // If the array occupies less than 25% of the cap, half the cap.
    double capacityUsed = (double) size / data.length;
    if (capacityUsed < 0.25) {
      data = cutCapacity(data);
    }
    size--;

    return (T) removedElement;
  }

  // Delete all elements, start new Arraylist
  public void clear() {
    T[] temp = (T[]) new Object[data.length];
    data = temp;
    size = 0;
  }

  public String toString() {
    Iterator<T> it = iterator();
    if (!it.hasNext()) return "[]";

    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (;;) {
      T e = it.next();
      sb.append(e == this ? "(this Collection)" : e);
      if (!it.hasNext()) return sb.append(']').toString();
      sb.append(',').append(' ');
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new MyArrayListIterator();
  }

  private class MyArrayListIterator implements Iterator<T> {

    private int current = 0;

    @Override
    public boolean hasNext() {
      return current < size;
    }

    @Override
    public T next() {
      return data[current++];
    }

    @Override
    public void remove() {
      if (current == 0) {
        throw new IllegalStateException();
      }
      MyArrayList.this.remove(current - 1);
    }

    public int size() {
      return size;
    }
  }

  @Override
  public void forEach(Consumer<? super T> action) {}

  @Override
  public Spliterator<T> spliterator() {
    return null;
  }
}
