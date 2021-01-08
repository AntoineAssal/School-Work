public interface PriorityQueue<K, V> {
  int size();
  boolean isEmpty();
  Pair<K, V> insert(K key, V value) throws IllegalArgumentException;
  Pair<K, V> min();
  Pair<K, V> removeMin();
}
