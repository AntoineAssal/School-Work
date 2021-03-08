package CPU_Scheduling_Simulator;
public class ArrayListHeapPriorityQueue<K, V> {
    // My work is modifying the implementation of a Priority Queue using an Array-based Heap in the textbook (page 377).
    public class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private MyArrayList<Pair<K, V>> heap = new MyArrayList<>();

    // Since we can think of heaps as a binary tree, we can use tree-like terminology for the utility methods
    public int parent(int kek) {
        return (kek - 1) / 2;
    }

    public int left(int kek) {
        return 2 * kek + 1;
    }

    public int right(int kek) {
        return 2 * kek + 2;
    }

    public boolean hasLeft(int kek) {
        return left(kek) < heap.size();
    }

    public boolean hasRight(int kek) {
        return right(kek) < heap.size();
    }

    public Pair<K, V> get(int i) {
        return heap.get(i);
    }

    public Pair<K, V> root() {
        if (heap.size() == 0)
            return null;
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public void swap(int i, int j) {
        Pair<K, V> temp = heap.get(i);
        heap.insert(i, heap.get(j));
        heap.insert(j, temp);
    }

    public int compare(Pair<K, V> object, Pair<K, V> otherObject) {
        int key1 = (int) object.getKey();
        int key2 = (int) otherObject.getKey();
        return Integer.compare(key1, key2); // returns 1 if key1>key2, -1 if key1<key2 else 0.
    }

    // Moves the Pair at index kek higher to restore the heap property
    public void upheap(int kek) {
        while (kek > 0) {
            int parent = parent(kek);
            if (compare(heap.get(kek), heap.get(parent)) >= 0)   // Check heap property
                break;
            swap(kek, parent);
            kek = parent;       // Continue from parent
        }
    }

    // Moves the Pair at index kek lower to restore the heap property
    public void downheap(int kek) {
        while (hasLeft(kek)) {
            int leftIndex = left(kek);
            int smallchildIndex = leftIndex;
            if (hasRight(kek)) {
                int rightIndex = right(kek);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallchildIndex = rightIndex;
            }
            if (compare(heap.get(smallchildIndex), heap.get(kek)) >= 0)  // Check heap property
                break;
            swap(kek, smallchildIndex);
            kek = smallchildIndex;            // Continue from child
        }
    }

    // Insert a key-value pair. Adds it to end of list then upheaps
    public void insert(K key, V value) {
        Pair<K, V> newest = new Pair<>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
    }

    public void remove(int i) {
        swap(i, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
    }

    // Removes minimal key. Puts minimum item at the end then removes it and fixes new root
    public void removeMin() {
        if (heap.size() == 0)
            return;
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
    }

}



