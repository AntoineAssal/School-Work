(defun lucas (n &optional (a 2) (b 1))
  (if (zerop n)
      nil
      (print (cons a (lucas (1- n) b (+ a b))))))
(lucas 8)


(defun lucas(n)
  (cond
    ((eq n 0) 2)
    ((eq n 1) 1)
    ((print (+ (lucas (- n 1)) (lucas (- n 2)))))))
(lucas 8)