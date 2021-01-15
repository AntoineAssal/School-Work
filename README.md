# School Work
Collection of my projects and assignments submitted for various university courses. 
# Java
## [Bibliography Maker](https://github.com/AntoineAssal/School-Work/tree/master/Java/Bibliography%20Maker)
The  purpose of this program is to practice and make use of java I/O classes and exception handling, this program takes input files, processes them and outputs the sources found in the files to the corresponding format and then create their own files for each format.
## [Intelligent SIDC (Smart Database/ADT)](https://github.com/AntoineAssal/School-Work/tree/master/Java/Intelligent%20SIDC)
This program creates a Dynamic Student Database (Intelligent `ADT` called `IntelligentSIDC`) that depending on the input size, adapts to its usage and keeps the balance between memory and runtime requirements. For instance, if an `IntelligentSIDC` contains only a small number of entries (e.g., few hundreds), it might use less memory overhead but slower (sorting) algorithms. On the other hand, if the number of contained entries is large (greater than 1000 or even in the range of tens of thousands of elements), it might have a higher memory requirement but faster (sorting) algorithms. `IntelligentSIDC` might be almost constant in size or might grow and/or shrink dynamically. This smart `ADT`'s underlying structure is built using `Sequence` and `AVL ADT`.

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

### Design
When the number of keys to be included in the `IntelligentSIDC` is lower than the threshold set at the beginning of the program, we decided to use a `sorted sequence`.
Because its easier to build and implement the methods we need since we're only using it for a small number of keys.
When the number of keys is higher, we decided to use an `AVL`.
An `AVL` will always sort itself and searching through it is faster compared to the sequence especially for large numbers.
The `AVL` also has methods to balance itself so whatever order we insert elements in won't matter and the complexity will not be affected.

### Complexity Analysis of Methods
Time and Space complexity for every method used in the program.
#### IntelligentSIDC:
- **generate(n)** - Time complexity: `O(n)` Space complexity: `O(n)`
- **setThreshold(size)** - Time complexity: `O(1)` Space complexity: `O(1)`
- **type()** - Time complexity: `O(1)` Space complexity: `O(1)`
#### Sorted Sequence:
- **allKeys()** - Time complexity: `O(n)` Space complexity: `O(n)`
- **add(key,student)** - Time complexity: `O(n)` Space complexity: `O(1)`
- **remove(key)** - Time complexity: `O(n)` Space complexity: `O(1)`
- **getValues(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`
- **nextKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`
- **prevKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`
#### AVL:
- **allKeys()** - Time complexity: `O(n)` Space complexity: `O(n)`
- **add(key,student)** - Time complexity: `O(log n)` Space complexity: `O(1`)
- **remove(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`
- **getValues(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`
- **nextKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`
- **prevKey(key)** - Time complexity: `O(log n)` Space complexity: `O(1)`

## [Binary String Revealer](https://github.com/AntoineAssal/School-Work/tree/master/Java/Binary%20String%20Revealer)
This is a program that takes as input a string of any length of random number of binary characters `0` and `1` and a masked `*` character at some positions, and finds all possible sequences of binary strings that can be constructed by replacing the masked `*` character by either `0` or `1`.
### Pseudocode 
### [Recursive Version](https://github.com/AntoineAssal/School-Work/blob/master/Java/Binary%20String%20Revealer/RecursiveRevealStr.java)
`Algorithm` **RevealStr**(`S`,`I`)\
Input: A String `S` composed of `1`s, `0`s and `*`s. With a non-negative integer `I` representing the starting index.\
Output: Print all possible combinations of binary strings replacing the `*`s with `1`s and `0`s.
```java
size ← s.length
Let A be an array of the characters forming the string s
Let B be the conversion of A back into a string.
if I=size then
    return s
if A[i] is not * then
    RevealStr(B,i+1)
else
    RevealStr(B,i+1)
```

### [Iterative version](https://github.com/AntoineAssal/School-Work/blob/master/Java/Binary%20String%20Revealer/IterativeRevealStr.java)
`Algorithm` **RevealStr**(`S`)\
Input: A String `S` composed of `1`s, `0`s and `*`s.\
Output: Print all possible combinations of binary strings replacing the `*`s with `1`s and `0`s.
``` java       
Let inputString be a Stack <String>
Let out be a String
push S into inputString
while inputString is not empty do
    out← inputString.pop
    if out contains a * then
        push String with 0 to out
        push String with 1 to out
    else
        Print out
```
## [CPU Scheduling Simulator](https://github.com/AntoineAssal/School-Work/tree/master/Java/CPU%20Scheduling%20Simulator)
This project uses a custom built Priority Queue(`PQ`) as a tool to simulate the way a CPU would schedule `jobs` according to their priority. The program will loop with ever iteration being a time slice of the CPU where one of the `jobs` is partially executed until the queue is empty. 
### How is it supposed to work?
- All `jobs` must first be inserted in the `PQ`. Each insertion takes `1` unit of time and sets the `entryTime`, starting from `1`. (when `job1` is inserted, `entryTime` is set to `1`, when `job6` is inserted `entryTime` is set to 6.)
- The CPU processes the `jobs` from the `PQ` based on their **current priority**. If two `jobs` have the same `priority`, the priority is given to the `job` that entered the `PQ` first.
- To avoid having jobs wait indefinitely, after processing `30 processes`, the program searches the `PQ` for the oldest `job` that has been waiting and changes it's priority to `1` so its executed. 
### What is a `job`?
In the scope of this project a `Job` is defined as a class with the following properties:
- **jobName**: A ***String***
- **jobLength**: An ***int*** representing how many CPU cycles it takes to be fully processed (between `1` and `70` cycles).
- **currentJobLength**: An ***int*** representing how many CPU cycles are left.
- **jobPriority**: An ***int*** representing the initial priority of a given `job`. (between `1` and `40`, `1` being the highest).
- **finalPriority**: An ***int*** representing the final priority of a `job`.
- **entryTime**: A ***long*** representing the time a `job` was inserted into the `PQ`.
- **endTime**: A ***long*** representing the time a `job` was removed from the `PQ`.
- **waitTime**: A ***long*** representing the time a `job` had to wait. This does not include the execution time of a `job`.
### What happens when a `Job` is executed?
- `currentJobLength` is decremented by `1`.
- If `currentJobLength` is greater than `0` then it is added back to the `PQ`, behind all the other jobs of same priority to guarantee that the other jobs of same priority are picked up to be processed before.
- If `currentJobLength` is equal to `0` then the program records `endTime` and `waitTime`.
- Static counter `currentTime` initialized to `0` at the start of all operations and is incremented by `1` for these cases:
- a) Every time a `job` is inserted to the `PQ` from the input array.
- b) Every time a `job` is executed.
- c) Every time the program looks for a starved process (every `30 processes`). However, the `currentTime` should not be incremented when a priority of a starved process is modified nor the time it takes to update the `PQ`.
### When all jobs are terminated (`PQ` is empty)
The program outputs a report to a new file. This report includes:
- Current system time.
- How many jobs have been executed.
- Average waiting time.
- How many times did the CPU change priorities.
- Real running time to process jobs.

Here is the output generated by a test run for 100, 1 000 and 10 000 jobs by using different structures to implement the logic described:
```
Unsorted List Priority Queue
Current system time (cycles): 3943
Total number of jobs executed: 100 jobs
Average process waiting time: 1923 cycles
Total number of priority changes: 3
Actual system time needed to execute all jobs: 116.0 ms

Sorted List Priority Queue
Current system time (cycles): 3943
Total number of jobs executed: 100 jobs
Average process waiting time: 1923 cycles
Total number of priority changes: 3
Actual system time needed to execute all jobs: 118.0 ms

ArrayList-based Heap Priority Queue
Current system time (cycles): 3943
Total number of jobs executed: 100 jobs
Average process waiting time: 1920 cycles
Total number of priority changes: 3
Actual system time needed to execute all jobs: 88.0 ms

Unsorted List Priority Queue
Current system time (cycles): 54595
Total number of jobs executed: 1000 jobs
Average process waiting time: 29549 cycles
Total number of priority changes: 32
Actual system time needed to execute all jobs: 768.0 ms

Sorted List Priority Queue
Current system time (cycles): 54595
Total number of jobs executed: 1000 jobs
Average process waiting time: 29549 cycles
Total number of priority changes: 32
Actual system time needed to execute all jobs: 1521.0 ms

ArrayList-based Heap Priority Queue
Current system time (cycles): 54595
Total number of jobs executed: 1000 jobs
Average process waiting time: 29419 cycles
Total number of priority changes: 33
Actual system time needed to execute all jobs: 455.0 ms

Unsorted List Priority Queue
Current system time (cycles): 2038180
Total number of jobs executed: 10000 jobs
Average process waiting time: 1294829 cycles
Total number of priority changes: 326
Actual system time needed to execute all jobs: 18341.0 ms

Sorted List Priority Queue
Current system time (cycles): 2038180
Total number of jobs executed: 10000 jobs
Average process waiting time: 1294828 cycles
Total number of priority changes: 326
Actual system time needed to execute all jobs: 23966.0 ms

ArrayList-based Heap Priority Queue
Current system time (cycles): 2038180
Total number of jobs executed: 10000 jobs
Average process waiting time: 1293427 cycles
Total number of priority changes: 333
Actual system time needed to execute all jobs: 3096.0 ms
```
## [Infastructures](https://github.com/AntoineAssal/School-Work/tree/master/Java/Infrastructures)
This is the implementation of my Java knowledge of inheritance, polymorphism, inner classes, exception handling as well as custom Array Lists and Linked Lists built from scratch, to solve the given problem.

## [MagicBoard Solver](https://github.com/AntoineAssal/School-Work/tree/master/Java/Magic%20Board%20Solver)

MagicBoard is a 1-player game using a chess-like board consisting of `d×d` squares with `d` between `5` and `20`, and each square contains an integer between `0` and `d-1`. In one step the player can move either `n` squares east or west or north or south. At each step the chosen direction is fixed. The player cannot move off the board. The only legal start positions are the four corner squares. The board must contain exactly `one` goal square with `0` as value that can be located anywhere on the board, but its position cannot be identical to the start position. The algorithms check if the board is solvable.

### Pseudocode
### [Recursive Version](https://github.com/AntoineAssal/School-Work/blob/master/Java/Magic%20Board%20Solver/MagicBoardRecursive.java)

`Algorithm` : MagicBoardRecursive(int `x`, int `y`, int `n`, int `r)` \
Input: `x`, `y` are indexes of the square in the 2D array. `n` is the value of the square, `r` is the recursion counter.\
Output: Print out a message whether the Board is solvable or not.
``` py
    r ← r+1                            
    if n is not 0 AND r is 1 then:
        Print "not solvable"
        Return
    if right is legal then:                    
        if(x+n,y) is not a visited square then:
             go right.
             mark square as visited.
             print new board.
             if n at x+n is 0 then:
                 Print "solvable"
                 Return
            MagicBoardRecursive(x+n, y, board[x+n][y], r)
    if left is legal then:                    
         if(x-n,y) is not a visited square then:
              go left.
              mark square as visited.
              print new board.
              if n at x-n is 0 then:
                  Print "solvable"
                  Return
              MagicBoardRecursive(x-n, y, board[x-n][y], r)
    if up is legal then:                    
          if(x,y+n) is not a visited square then:
               go up.
               mark square as visited.
               print new board.
               if n at >y+n is 0 then:
                   Print "solvable"
                   Return
               MagicBoardRecursive(x, y+n, board[x][y+n], r)
    if down is legal then:                    
           if(x,y-n) is not a visited square then:
                go down.
                mark square as visited.
                print new board.
                if n at y-n is 0 then:
                    Print "solvable"
                    Return
                MagicBoardRecursive(x, y-n, board[x][y-n], r)
            
```
Our method covers the base case where the recursion stops if there are no more possible paths to take and we haven't reached a square with value `0`. It can call itself more than one time, making it a multiple recursion algorithm. Since the method checks all `4` possible directions from all possible squares it's time complexity would be `O(nd)` if we keep looking for a `0`. Having an `ArrayList` to keep track of the squares we have visited, reduces the time complexity to `O(n)` by not checking the same square more than once. Since our method uses an existing `ArrayList`, it doesn't use any auxilary space by creating any new storage types, the time complexity is `O(1)`. A tail Recursive version would be possible if we implement a Linear Recursive method and have the last call be the recursive call. To do so, we can split the program into mutliple classes (Circle, Square, Direction, Game) and have a switch statement in the recursive method to modify the movement value to be positive or negative depending on possible direction then apply it to the index of circle at a square.

### [Iterative Version](https://github.com/AntoineAssal/School-Work/blob/master/Java/Magic%20Board%20Solver/MagicBoard.java)
Algorithm: MagicBoardIterative(`startX`, `startY`, `valueZ`, `pathDeck`, `pastXY`)\
Input: `startX`, `startY` are indexes of the current square in the 2D array `gameboard`.
`valueZ` is the value of that square, `pathDeck` is a stack of `pathFrame objects` (A class we defined that calculates and stores possible paths to take)\
`PastXY` is an `Arraylist` used to store `X Y` indexes of visited squares.\
Output: Print out a message whether the Board is solvable or not.
```py
    do
        tFrame ← new pathFrame()   
        if pathDeck is not empty then
            tFrame ← pathDeck.pop()
        if possible new move from startX or startY taking valueZthen
            add or substract valueZ to startX or startY
            pastXY.add(startX, startY)
            next ← new pathFrame (startX, startY, valueZ)
            pathDeck.push(next)
            if valueZ is 0 then
                Print "solvable"
                pathDeck.clear()
                break
        else pathDeck.clear()
    until pathDeck is empty
 ```
 For this version our method has the same complexity as our recursive method, it might look like we're using more memory to solve the board, but its still the same since the `Stack` and `ArrayList` already exist before we run the method and our method modifies the content. We create `2` new `pathFrame` objects on every iteration and `push/pop` them to/from our stack of pathframes and add visited indexes to our `Arraylist`. A `Stack` was the ideal data structure to solve this problem, to keep our memory utillization as low as possible. In order to use an `array`, java allocates memory for it and that much memory is consumed of the process `stack` (which stores local variables and function calls). Whereas a `Stack` data structure allocates memory more efficiently, since it frees the memory as soon as we're done with an element in it. Being a `LIFO` data structure helped us manage the data in the opposite order we're storing it, which allowed us to store possible paths to take, then when we reach a point where there are no more paths, we destroy the last position we reached and try another path from the previous position and so on, until we reach the `0` or exhaust all combinations of paths from all reachable positions. Using a `queue` that controls elements in `FIFO` order wouldn't suit our needs to solve the puzzle, to backtrack and go to our last position we would have to `pop` the whole `Queue`. A `queue` could've been used in conjunction with the `stack`. In that case, the `Stack` would only store the values of the squares and the `Queue` would contain the reachable values from a given square in said `Stack`. So we push the start position to the `Stack`, check reachable squares and add them to the `Queue`, if there is none `pop` the square from the `Stack`. If we added any to the `queue` then pop a reachable square from the `queue` (first one) and `push` it to the Stack, check its possible positions again. Repeat this until the `Queue` contains a `0` or our `Stack` is empty.

 ### Finding Unsolvable Boards
 
 To speed up execution time we can keep track of how many of each number we have in the random board. The more of the higher numbers we have, the more we will loop or get out of bounds. We can also split the board into smaller boards since we know that a `2x2` board will be solvable if it has a `1` in any of the `3` other cells and a `3x3` will be solvable if there's a `2` in any of the other `8` cells. Scaling this up to bigger boards requires to keep track of all the "small" boards, possible ways of dividing the original board and movements. Making it harder to implement. Another way of checking if it's solvable is by starting from the `0` and looking if it's reachable from the squares in the same row and column.

# Lisp
