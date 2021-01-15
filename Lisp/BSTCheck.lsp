(defun isLeaf (L)
    (cond
        ((null L) t)
    (t(and (= (list-length L) 1) (listp L) ))
        )
    )

(defun isNode (L)
    (and (= (list-length L) 3) (listp L))
    )

(defun getnode(L)
    (car L)   
    )

(defun getleftSub (L)
   (cadr L) ; Equivalent to (car (cdr lst))
    )

(defun getrightSub (L)
     (caddr L) ; Equivalent to (car (cdr (cdr lst)))
    )

(defun checkLeftSub (L)
    (cond
        ((null(getleftSub L)) t)
        ((<= (car(getleftSub L)) (getnode L))t)
        )
    )

(defun checkRightSub (L)
    (cond
        ((null(getrightSub L)) t)
        ((> (car(getrightSub L)) (getnode L))t)
       )  
    )

(defun isBinaryTree (L)
    (cond
        ((null L) t)
        ((isLeaf L) t)
        ((and (isNode L) (isLeaf (getleftSub L)) (isLeaf (getrightSub L)))   (and (checkLeftSub L) (checkRightSub L)))
        ((and (isNode L) (isNode (getleftSub L)) (isLeaf (getrightSub L)))   (and (isBinaryTree (getleftSub L)) (checkLeftSub L) (checkRightSub L)))
        ((and (isNode L) (isLeaf (getleftSub L)) (isNode (getrightSub L)))   (and (checkLeftSub L) (isBinaryTree (getrightSub L)) (checkRightSub L)))
        ((and (isNode L) (isNode (getleftSub L)) (isNode (getrightSub L)))   (and (isBinaryTree (getleftSub L)) (isBinaryTree (getrightSub L)) (checkLeftSub L) (checkRightSub L)))
        )  
    )


;(princ(isBinaryTree '(8 (3 (1 () ()) (6 (4 () ())(7 () ()))) (10 () (14 (13) ())))))    ;should work
;(print(isBinaryTree '(8 (3 (3 () ()) (6 (4 () ())(7 () ()))) (10 () (14 (13) ())))))    ;should work
;(print(isBinaryTree '(8 (3 (9 () ()) (6 (4 () ())(7 () ()))) (10 () (14 (13) ())))))    ;should not work
;(print(isBinaryTree '(8 (3 (1 () ()) (6 (4 () ())(7 () ()))) (10 () (8 (13) ())))))     ;should not work
;(print(isBinaryTree '(8 (3 (1 () ()) (6 (4 () ())(7 () ()))) (10 () (20 (13) ())))))    ;should work
;(print(isBinaryTree '(8 (3 (1 () ()) (6 (4 () ())(7 () ()))) (10 (10) (20 (13) ())))))  ;should work
;(print(isBinaryTree '(8 (3 (1 () ()) (9 (4 () ())(7 () ()))) (10 () (14 (13) ())))))    ;should not work


(princ(isBinaryTree ''(8 (3 (1 () ()) (6 (4 () ()) (7 () ()))) (10 () (14 (10 () ()) ()))) )) 
