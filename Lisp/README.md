## [Sublister](Sublister.lsp)
A function that takes a `list` and two indexes `from` and `to`, then returns a sub-list with elements that are within the given indexes.
Sample run:
```lisp
> (sub-list '(1 6 12) 2 3)
(6 12)
> (sub-list '(1 6 12) 4 2)
NIL
```
## [Sublister modified](Sublister2.lsp)
A function that takes a `list` and two indexes `from` and `to`, then returns a sub-list with elements that are within the given indexes. If `from` is missing the it is assigned a default value of `1` and if `to` is missing it is assigned the length of the `list` as a default value. If the `from` is greater than the `to` index then the function returns the sublist in reverse order.
Sample run:
```lisp
> (sub-list '(1 6 12) 2)
(6 12)
> (sub-list '(1 6 12) 3 1)
(12 6 1)
> (sub-list '(1 6 12) nil 1)
(1)
```
## [Flatten Numbers](FlattenNumbers.lsp)
A function that takes a `list`, and returns a flattened `list` containing only the atomic elements that are numbers, discarding duplicates.
Sample run:
```lisp
>(flatten-numbers '(1 2 (3 1) (a 2.5) (2 4.5) ((1 2))))
(1 2 3 2.5 4.5)
```
## [BST Checker](BSTCheck.lsp)
A function that checks whether a binary tree is a Binary Search Tree (BST). Here a BST is defined as a tree in which all the nodes respect these conditions:
- The left sub-tree of a node has a key less than or equal to its parent node's key.
- The right sub-tree of a node has a key greater than to its parent node's key.
Sample run:
```lisp
>(isBinaryTree '(8 (3 (3 () ()) (6 (4 () ())(7 () ()))) (10 () (14 (13) ()))))
T
>(isBinaryTree '(8 (3 (1 () ()) (9 (4 () ())(7 () ()))) (10 () (14 (13) ()))))
NIL
```