import java.util.Objects;

public class Pair<K, V> {

  // My work is modifying the implementation of a Priority Queue using an Array-based Heap in the textbook (page 377).

  private K key;
  private V value;

  public Pair(K k, V v) {
    this.key = k;
    this.value = v;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void setKey(K k) {
    this.key = k;
  }

  public void setValue(V v) {
    this.value = v;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pair<?, ?> pair = (Pair<?, ?>) o;

    if (!Objects.equals(key, pair.key)) return false;
    return Objects.equals(value, pair.value);
  }

  @Override
  public String toString() {
    return "Pair{" + "key=" + key + ", value=" + value + '}';
  }
}
