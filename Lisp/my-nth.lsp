(defun my-nth (n l) 
  (if (or (< (length l) n) (< n 0)) 
    nil
    (cond 
      ((= n 0)
       (first l)
      )
      ((my-nth (decf n) (rest l)))
    )
  )
)
(princ (my-nth 0 '(a b c d))) ; returns A
(print (my-nth 2 '(a b c d))) ; returns C  
(print (my-nth 4 '(a b c d))) ; returns NIL 
(print (my-nth -1 '(a b c d))) ; returns NIL 