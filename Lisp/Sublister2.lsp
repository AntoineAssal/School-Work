(defparameter *letters* 0)
(defparameter checking 0)

( defun reverse2 ( lst)
    (cond ((null lst) '() )
         (t (append (reverse2 (cdr lst )) (list (car lst ))))
    )
)

(defun checkIndex(parent from to)
(if (equal nil from) 
    (print '(1))
    (if (and (<= from to) (<= to (length parent))) ;if index is out of
        (defparameter *letters* (+ (- to from) 1))
        (progn
            (print (reverse2 parent))
            (setq  checking 1)
        ) 
    )
)
)
(defparameter sublist1 (list () ))
(defparameter counter1 0)
(defun sub-lister(parent &optional (from 1) (to (length parent)))

(if (equal (length parent) 0) 
        (progn
            (defvar checking 1)
            (+ 2 6)
        ) ; return an empty list
        (checkIndex parent from to)
    )
    (if (equal checking 0)
        (progn
            (loop for x in parent
                do (progn
                        (setq counter1 (+ counter1 1))
                        (if (= counter1 from)
                            (setq sublist1 (append (list x)))
                        )
                        (if (and (> counter1 from) (<= counter1 to)) 
                            (setq sublist1 (append sublist1 (list x)))
                        ) 
                    )
            )
            (princ sublist1) 
        )
    )

)
(sub-lister '(1 6 12) 2)
(sub-lister '(1 6 12) 3 1)
(sub-lister '(1 6 12) nil 1)
