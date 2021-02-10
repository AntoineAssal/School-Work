(defun triangle (n)
    (cond
        ((zerop n) (princ "invalid number: please enter a positive or a negative integer"))
        ((not(integerp n)) (princ "invalid number: please enter a positive or a negative integer"))
        ((plusp n)  ;if positive size
         (let ((i n)) 
            (loop
                (when (< i 1) (return))
                (let ((j i)) 
                    (loop
                       (when (< j 1) (return))
                       (princ "*")    ; (print) adds a preceding new line and a space after. Using princ instead.
                       (decf j)       ; j--
                      )
                    )
               (terpri)     ;newline
               (decf i)     ;i--
              ); end loop
           )
        ) ; end if

        ((minusp n) ;if negative size
         (let ((newN(abs n)) (i 0))
              (loop
               (when (>= i newN) (return))
               (let ((j 0)) 
                    (loop
                     (when (>= j i) (return))
                     (princ " ")
                     (incf j);j++
                     )
                    )
               (let ((k newN)) 
                    (loop
                     (when (<= k i) (return))
                     (princ "*")
                     (decf k);k--
                     )
                    )
               (terpri);newline
               (incf i);i++
               ) ;end loop
             )
           ) ; end if
         )  
)

;(triangle 8)
;(triangle -10)
;(triangle 0)
;(terpri)
;(triangle 5.5)

(triangle 8)
(terpri)
(triangle -8)



