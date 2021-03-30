package CPU_Scheduling_Simulator;
public class UnsortedListPriorityQueue<K, V>  extends AbstractPriorityQueue{

  // My work is modifying the Implementation of a priority queue with an un-sorted list (page 367) to match assignment instructions.

  // Collect the entries in the queue
  private PositionalList<Pair<K, V>> list = new LinkedPositionalList<>();

  // Creates an empty priority queue based on the natural ordering of its keys.
  public UnsortedListPriorityQueue() {
    super();
  }

  /// Returns the Position of an entry with the minimal key.
  private Position<Pair<K, V>> findMin() {
    Position<Pair<K, V>> small = list.first();
    for (Position<Pair<K, V>> walk : list.positions()) {
      if (compare(walk.getElement(), small.getElement()) < 0) {
        small = walk;
      }
    }
    return small;
  }

  // Returns the number of items in the priority queue.
  @Override
  public int size() {
    return list.size();
  }

  // Inserts a key-value pair and returns the entry created.
  @Override
  public Pair<K, V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    Pair<K, V> newest = new Pair<>(key, value);
    list.addLast(newest);
    return newest;
  }

  // Returns (but does not remove) an entry with minimal key.
  @Override
  public Pair<K, V> min() {
    if (list.isEmpty()) {
      return null;
    }
    return findMin().getElement();
  }

  // Removes and returns an entry with minimal key.
  @Override
  public Pair<K, V> removeMin() {
    if (list.isEmpty()) {
      return null;
    }
    return list.remove(findMin());
  }

  //public Iterator<Position<Pair<K, V>>> iterator() {
    //LinkedPositionalList<Pair<K, V>> linkedList = (LinkedPositionalList<Pair<K, V>>) list;
    //return linkedList.positions().iterator();
  //}
}
