(defun sumlist (L)
  (if (null L) 0
      (if (not (numberp (first L)))
          (sumlist (rest L))
          (+ (first L) (sumlist (rest L))))))

(princ (sumlist '(1 2 3 4)))          ; Returns 10
(print (sumlist '(1 b 3.2 d (100))))  ; Returns 4.2
(print (sumlist '()))                 ; Returns 0