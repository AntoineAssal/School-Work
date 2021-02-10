 (defun genlist ( &optional(n 10))
  (if (and (integerp n) (> n 0)) 
  (progn
    (defparameter r nil)
    (defparameter i 1)
    (loop 
    (if (> i n) (return r))
    (defparameter r (append r (list i) ))
    (defparameter i (+ 1 i))))) 
 )

(princ (genlist 2)) ; Returns (1 2)
(print (genlist 1)) ; Returns (1)
(print (genlist 'A)); Returns nil
(print (genlist 0)) ; Returns nil
(print (genlist ))  ; Returns (1 2 3 4 5 6 7 8 9 10)
