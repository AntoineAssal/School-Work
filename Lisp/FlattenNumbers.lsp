(defconstant list01 (list '1.5 '() '5 '(1.5 '(1.5 a 3 '()) 1.5 6) 'a 'b '3 '4 '5 '('a 3)))
(defparameter temp (list ))
(defparameter checking 0)

(defconstant test (list '1 '2 '3 '4))
(defun flatten-numbers(listt)
    (loop for x in listt do
        (if (numberp x)
            (progn
                (loop for y in temp do
                    (if (and (equal x y) (numberp y) (equal checking 0))
                        (setq checking 1)                        
                    )
                )
                (if (and (numberp x) (equal checking 0))
                    (progn
                        (setq temp (append temp (list x)))
                        (setq checking 0)
                    )
                    (setq checking 0)

                )
            )
            (if (listp x)
                (progn
                    (flatten-numbers x)
                    ()
                )
            )
        )
    )
)
(flatten-numbers list01)
(print temp)