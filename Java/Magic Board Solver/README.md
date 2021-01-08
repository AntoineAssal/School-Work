# MagicBoard Solver

MagicBoard is a 1-player game using a chess-like board consisting of d×d squares with d between 5 and 20, and each square contains an integer between 0 and d-1. In one step the player can move either n squares east or west or north or south. At each step the chosen direction is fixed. The player cannot move off the board. The only legal start positions are the four corner squares. The board must contain exactly one goal square with zero as value that can be located anywhere on the board, but its position cannot be identical to the start position. The algorithms check if the board is solvable.

## Pseudocode
### Recursive Version

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
Our method covers the base case where the recursion stops if there are no more possible paths to take and we haven't reached a square with value 0. It can call itself more than one time, making it a multiple recursion algorithm. Since the method checks all 4 possible directions from all possible squares it's time complexity would be O(ndd) if we keep looking for a 0. Having an ArrayList to keep track of the squares we have visited, reduces the time complexity to O(nd) by not checking the same square more than once. Since our method uses an existing ArrayList, it doesn't use any auxilary space by creating any new storage types, the time complexity is O(1). A tail Recursive version would be possible if we implement a Linear Recursive method and have the last call be the recursive call. To do so, we can split the program into mutliple classes (Circle, Square, Direction, Game) and have a switch statement in the recursive method to modify the movement value to be positive or negative depending on possible direction then apply it to the index of circle at a square.

### Iterative Version
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
 For this version our method has the same complexity as our recursive method, it might look like we're using more memory to solve the board, but its still the same since the Stack and ArrayList already exist before we run the method and our method modifies the content. We create 2 new pathFrame objects on every iteration and push/pop them to/from our stack of pathframes and add visited indexes to our Arraylist. A Stack was the ideal data structure to solve this problem, to keep our memory utillization as low as possible. In order to use an array, java allocates memory for it and that much memory is consumed of the process stack (which stores local variables and function calls). Whereas a Stack data structure allocates memory more efficiently, since it frees the memory as soon as we're done with an element in it. Being a LIFO data structure helped us manage the data in the opposite order we're storing it, which allowed us to store possible paths to take, then when we reach a point where there are no more paths, we destroy the last position we reached and try another path from the previous position and so on, until we reach the 0 or exhaust all combinations of paths from all reachable positions. Using a queue that controls elements in FIFO order wouldn't suit our needs to solve the puzzle, to backtrack and go to our last position we would have to pop the whole Queue. A queue could've been used in conjunction with the stack. In that case, the Stack would only store the values of the squares and the Queue would contain the reachable values from a given square in said Stack. So we push the start position to the Stack, check reachable squares and add them to the Queue, if there is none pop the square from the Stack. If we added any to the queue then pop a reachable square from the queue (first one) and push it to the Stack, check its possible positions again. Repeat this until the Queue contains a 0 or our Stack is empty.

 ## Finding Unsolvable Boards
 
 To speed up execution time we can keep track of how many of each number we have in the random board. The more of the higher numbers we have, the more we will loop or get out of bounds. We can also split the board into smaller boards since we know that a 2x2 board will be solvable if it has a 1 in any of the 3 other cells and a 3x3 will be solvable if there's a 2 in any of the other 8 cells. Scaling this up to bigger boards requires to keep track of all the "small" boards, possible ways of dividing the original board and movements. Making it harder to implement. Another way of checking if it's solvable is by starting from the 0 and looking if it's reachable from the squares in the same row and column, which is the strategy we're explaining here.

