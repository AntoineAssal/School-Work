# IntelligentSIDC 

This program creates a Dynamic Student Database (Intelligent `ADT` called `IntelligentSIDC`) that depending on the input size, adapts to its usage and keeps the balance between memory and runtime requirements. For instance, if an ``IntelligentSIDC` contains only a small number of entries (e.g., few hundreds), it might use less memory overhead but slower (sorting) algorithms. On the other hand, if the number of contained entries is large (greater than 1000 or even in the range of tens of thousands of elements), it might have a higher memory requirement but faster (sorting) algorithms. `IntelligentSIDC` might be almost constant in size or might grow and/or shrink dynamically. This smart `ADT`'s underlying structure is built using `Sequence` and `AVL ADT`.

The `IntelligentSIDC` implements the following methods:

- **SetSIDCThreshold (`Size`)**: where 100 ≤ Size ≤ ~500,000 is an integer number that defines the size of
the list. This size is very important as it will determine what data types or data structures will be used
(i.e. a `Tree, Hash Table, AVL tree, binary tree, sequence`, etc.);
- **generate()**: randomly generates new non-existing key of 8 digits;
- **allKeys(`IntelligentSIDC`)**: return all keys in IntelligentSIDC as a sorted sequence;
- **add(`IntelligentSIDC,key,value`)**: add an entry for the given key and value;
- **remove(`IntelligentSIDC,key`)**: remove the entry for the given key;
- **getValues(`IntelligentSIDC,key`)**: return the values of the given key;
- **nextKey(`IntelligentSIDC,key`)**: return the key for the successor of key;
- **prevKey(`IntelligentSIDC,key`)**: return the key for the predecessor of key;

## Design
When the number of keys to be included in the `IntelligentSIDC` is lower than the threshold set at the beginning of the program, we decided to use a `sorted sequence`.
Because its easier to build and implement the methods we need since we're only using it for a small number of keys.
When the number of keys is higher, we decided to use an `AVL`.
An `AVL` will always sort itself and searching through it is faster compared to the sequence especially for large numbers.
The `AVL` also has methods to balance itself so whatever order we insert elements in won't matter and the complexity will not be affected.

## Complexity Analysis of Methods
Time and Space complexity for every method used in the program.
### IntelligentSIDC
**generate(n)** - Time complexity: `O(n)` Space complexity: `O(n)`\
**setThreshold(size)** - Time complexity: `O(1)` Space complexity: `O(1)`\
**type()** - Time complexity: `O(1)` Space complexity: `O(1)`
### Sorted Sequence
**allKeys()** - Time complexity: `O(n)` Space complexity: `O(n)`\
**add(key,student)** - Time complexity: `O(n)` Space complexity: `O(1)`\
**remove(key)** - Time complexity: `O(n)` Space complexity: `O(1)`\
**getValues(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`\
**nextKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`\
**prevKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`\
### AVL
**allKeys()** - Time complexity: `O(n)` Space complexity: `O(n)`\
**add(key,student)** - Time complexity: `O(log n)` Space complexity: `O(1`)\
**remove(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`\
**getValues(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`\
**nextKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`\
**prevKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`

