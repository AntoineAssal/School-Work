()                                                      ; The empty list.
(1 3 5 7)                                               ; A list of four elements, the numbers 1, 3, 5, and 7.
((1 2) (3 4))                                           ; A list of two elements, the list (1 2) and the list (3 4).
(((1 2) (3 4)))                                         ; A list of one element, the list ((1 2)(3 4)) which is made of 2 lists of 2 elements each.
(a (b 1) 2)                                             ; A list with three elements: the symbol a, the list (b 1) and the number 2.
(- 14 (*2 3))                                           ; Meaning 14 - (2*3) so this returns 8. Multipy 2 and 3 then subtract result from 14.
(+ 1 2 3 4)                                             ; Meaning (1+2+3+4), returns 10.
(* 2 3 4)                                               ; Meaning (2*3*4), returns 24.
(< 1 3 2)                                               ; Meaning (1<3<2), equivalent to ((1<3) && (3<2)) returns NIL (false).
(/ (* 2 6) 3)                                           ; Meaning (6*2)/3, Returns 4.
(/ (* 2 6) 3)                                           ; Returns (/ (* 2 6) 3). Quotation means do not evaluate the expression. List of 3 elements.
(let 
  ((x 5))
  (or (< x 2) (> x 3))
)                                                       ; Returns True. (if 1st is true OR doesnt care about 2nd and opposite for AND).

(let 
  ((x 5))
  (and (< x 7) (< x 3))                                 
)                                                       ; Returns NIL.
#| Other built-in Maths functions. |#
(mod 7 3)                                               ; Meaning (7%3), returns 1. Arity is 2.
(sin 0)                                                 ; Meaning (sin radians=0) Returns 0. Same for cos, tan. Arity of 1
(asin 1)                                                ; Meaning arcsin of 1, equivalent to sin^-1 of 1, which is 90 degrees or pi/2. Returns 1.5707964 (pi/2). The argument can only be in range (-1 to 1) returns NaN if not, and return is between (-pi/2 to pi/2). 
(acos 0)                                                ; Meaning arccos of 0, equivalent to cos^-1 of 0, which is 90 degrees or pi/2. Returns 1.5707964 (pi/2). The argument can only be in range (-1 to 1) returns NaN if not,and return is between (0 and pi).
(atan 1)                                                ; Meaning arctan of 1, equivalent to tan^-1 of 1, which is 45 degrees or pi/4. Returns 0.7853981 (pi/4). Returns a number between (-pi/2 to pi/2) exclusive whose tangent is the argument.
(atan 1 2)                                              ; Can take a second optional argument then the return of atan becomes the angle in radians between the vector [argument1, argument2] and the x-axis. 
(log 100)                                               ; Meaning (ln of 100), returns 4.605. Argument cant be negative.
(log 100 10)                                            ; We can pass a second argument as base instead of using the default base e. This would return 2.
(expt 2 3)                                              ; Meaning 2^3. Returns 8, first argument raised to power second argument. If both arguments are integers and second argument is nonnegative, the result is an integer. If first argument is negative and second argument isn't an integer we get NaN.
(sqrt 25)                                               ; Meaning square root of 25. If argument is less than 0, returns NaN.

                                                   
#|  Some common procedures to create lists.
    cons                                                : Creates a list by adding an element as the head of an existing list. Arity of 2 first element passed becomes head and the second becomes the tail.
    list                                                : Creates a list comprised of its arguments. Arguments dont have to be a list.
    append                                              : Creates a list by concatenating existing lists (so its not actually creating a new list just merging/appending the second to first. Arguments can only be lists).
|#
(cons 'a ' ())                                          ; Returns (A). We are constructing a list by adding a to an existing (empty) list taking. ` to not look for a variable
(cons 'a (cons 'b '()))                                 ; Returns (A B).
(cons 'a 
      (cons (cons 'b (cons 'c '()))  
            (cons 'd (cons 'e '()))
      )
)                                                       ; Returns (A (B C) D E).

(cons (+ 2 3) '(b c))                                   ; Returns (5 b c).
(cons '(+ 2 3) (b c))                                   ; Returns ((+ 2 3) b c).
(cons a)                                                ; Error, we dont have a definition for variable a.         
(cons `a)                                               ; Error, missing a second argument. You can use ' or ` as quote.

(list 1 2 'a 3)                                         ; Returns (1 2 A 3).
(list 1 '(2 3) 4)                                       ; Returns (1 (2 3) 4).
(list 1 (2 3) 4)                                        ; Error cuz lisp is trying to evaluate (2 3) as an expression not a list of 2 items. Staghforallah.
(list '(+ 2 1) (+ 2 1))                                 ; Returns ((+ 2 1) 3).
(list 1 2 3 (list 'a 'b 4) 5)                           ; Returns (1 2 3 (A B 4) 5) .

(append '(1 2) '(3 4))                                  ; Returns (1 2 3 4). list creates a new list with the elements. append adds to existing list
(append '(1 2 3) '() '(a) '(5 6))                       ; Returns (1 2 3 A 5 6).
(append '(1 2 3 '(a b c)) '() '(d) '(4 5))              ; Returns (1 2 3 ('(A B C)) D 4 5). ' inside a ' so its taken literally.
(append 1 '(4 5 6))                                     ; Error, 1 is not in a list. Append expects lists as arguments.

(append (list 1) '(4 5 6))                              ; Returns (1 4 5 6)

#|  Some common procedures to manipulate lists.
    car or first : takes a list as an argument and returns the head of the list. Term CAR = Contents of Address of Register.
    cdr or rest  : takes a list as an argument and returns the tail of the list.
    Always use the same variations so car/cdr or first/rest.                     Term CDR = Contents of Decrement of Register.
|#
(car '(a s d f))                                        ; Returns A.
(car '((a s) d f))                                      ; Returns (A S).
(first '(a s d f))                                      ; Returns A
(cdr '(a s d f))                                        ; Returns (S D F).
(cdr '((a s) d f))                                      ; Returns (D F).
(cdr '((a s) (d f)))                                    ; Returns ((D F)).
(rest '((a s) (d f)))                                   ; Returns ((D F)).
(car (cdr '(1 (3 5) (7 11))))                           ; Returns (3 5). First we're getting the tail of (1 (3 5) (7 11)) which is ((3 5)(7 11)) then we take the head which is (3 5).

#| Predicate functions are boolean functions and their names end with 'p' by convention.
   listp(argument)                                      : True if argument is a list.
   numberp(argument)                                    : True if argument is a number.
   zerop(argument)                                      : True if argument is zero.
   evenp(argument)                                      : True if argument is even.
   oddp(argument)                                       : True if argument is odd.
   integerp(argument)                                   : True if argument is an integer.
   realp(argument)                                      : True if argument is a real number.
   rationalp(argument)                                  : True if argument is a rational number, either a ratio or a number.
   atom(argument)                                       : True if argument is an atom, NIL if its a list.
   lessp(argument, argument2, moreargs)                 : True if there is only 1 argument or if all arguments are successively smaller from left to right.
   boundp(argument)                                     : True if argument symbol has been defined (is being used in scope)
|#

#|To declare/define our variables we use these functions.
  defvar                                               : To declare a variable at a global level that will have a permenant value (in effect throughout the whole run, until a new value is specified).
  let                                                  : To declare local variable that will be used only for the procedure it is being defined in. If we have multiple variables they run in parallel in no order.
  let*                                                 : Similar to let but if we have multiple variables then they're assigned sequentially.
  set or setq                                          : To set or change the value of a variable (local or global), we need to add a quote before the name of the variable so (set 'x 20) OR we use setq instead which does this for us.  setf can be used to modify part of a given structure not just symbols.

  Some more info on variables. 
  Global is visible everywhere as opposed to local which is only visible within the code block in which it is defined. 
  A global variable is accessbile everywhere except the expressions that create a new local variable with te same name.
  Inside a code block local variables have priority, they are looked at first. If not found, then a global variable is searched.
  (defparameter name value)                           ; Used to define a global variable, can be used again to modify the value
  (defconstant name value)                            ; Cannot modify the constant after its declared KEK
  (eql argument1 argument2..)                         ; Returns true if the arguments point to the same object
  (equal argument1 argument2..)                       ; Returns true if the arguments have the same value
|#

(defvar x 234)                                         ; Creates a global value x and instantiate with value 234. There is no type checking so this value can be primitive(int, char, float, string...) or it can be a function too.

(let 
  ((x 'a) 
    (y 'b)
    (z 'c)
  )
)                                                      ; Assigns value a to var a, b to y and c to z. We can declare multiple local variables in one let, each variable is assigned the respective value. If there is no value specified it is set to NIL by default.

(let ((x 5) (y x)))                                    ; Error, the variables are not assigned in order. So x cannot be seen when we're trying to assign it as a value to y.
(let* ((x 5) (y x)))                                   ; Legal binding now because let* will bind sequentially so when we get to (y x) x has already been assigned the value 5.

(let 
  (x)
  (setq x 5)
)                                                      ; Same thing as doing int x = new int then x=5 or int x = 5 in java but x is not defined outside of this let

(let 
  ((a 1))
  (let 
    ((a 2))
    (let 
      ((a 3))                                          ; We're using same variable name but they're all independent and have nothing to do with each other
      (+ a a)
    )
    (* a a)
  )
)                                                      ; Every let has it's own scope. So by the time we get to the (+ a a) a is 3 so it returns 6. for the (* a a) we left the scope of 3rd let and now we're in 2nd let where a is 2 so returns 4.  

(let 
  ((x 3))                                              ; Assign value 3 to x.
  (let 
    ((x (+ x 1)))                                      ; We're using same variable name but they have nothing to do with each other so we're not changing the value of x,
                                                       ; We're creating a new symbol/variable called x and assigning it the value (+ x 1) where this x is whatever we assigned earlier (3). So now x is 4
    (+ x x)
  )
)                                                      ; Returns 8.


(prog 
  ((x '(a b c)) 
    (y '(1 2 3))
    (z '(p q 10))
  )
)                                                      ; Assigns list (A B C) to var x, (1 2 3) to y and (P Q 10) to z.

(setf x '(a b c))                                      ; (A B C)
(setf x (append x '(d e)))                             ; (A B C D E)
(setf y '(A B C D E))                                  ; (A B C D E)
(setf z x)                                             ; (A B C D E)
(eql x y)                                              ; NIL different objects
(equal x y)                                            ; T same value
(eql z x)                                              ; T same objects
(equal z x)                                            ; T same value
(eql y z)                                              ; NIL different objects
(setf (car x) '(a b c))                                ; ((A B C) B C D E)

|# Conditionals and control flow statements.  
   if                                                  : Simplest single conditional 
   cond                                                : Multiple selection, basically a Java switch statement. Contains a list of clauses and each clause contains two expressions (condition and answer). Conditions are evaluated sequentially.
                                                       : The first condition that returns true, lisp runs its action and skips the rest. Can optionally have an else for a default case.
   dotimes                                             : Repeats for some fixed number of times. 
   loop                                                : Repeats until a condition is satisfied or when an explicit exit statement (return) is reached.
   (if testExpression thenExpression )
   (if testExpression thenExpression
                      elseExpression )                 : The testExpression is a predicate(boolean value) while the thenExpression and the (optional) elseExpression are expressions (stuff to be executed). 
                                                       : We use if when we have trivial conditions or like 2 test cases. If we have more than two conditions to test then we should use cond. So we dont get a mess of parantheses for all the elses.

  (cond ((predicate1) (do something1))                 ; If this predicate is true then do something1 is executed
        ((predicate2) (do something2))                 ; If predicate1 is true we won't get this far. if its false and predicate2 is true then do something2 is executed.
        ((t) (do something))                           ; The t can be replaced by an "else". do something is executed if none of the predicates return true, this is default case.
  )

  (dotimes (<counter> <limit> <result>) <body>)        ; Counter is the variable to keep track, always initiated with 0, <limit> is when to break, <result> is optional return value.
|#


(if (> a 20) 
  (format t "~% a is greater than 20")
  (format t "~% a is less than 20")
)                                                      ; Prints "is greater than 20" if condition is true, else prints "is less than 20"



(dotimes 
  (n 3)                                                ; for (int n = 0 ; i < 3 ; i++)      
  (print n)
  (write (* n n n))
)


(let 
  ((n 0))
  (loop 
    (when (> n 3) (return))                            ; Nothing assigned to return to whole expression evaluated to NIL, when it gets to n > 3
    (print n)
    (write (* n n n))                                  ; print stays on same line, write prints it and goes to next line
    (incf n)                                           ; n++ 
  )
) 

|# 
   Three different operations to group statements together in code blocks.
   progn                                               ; Expressions withing the body are evaluated in sequential order and the value of the last one is returned
   block                                               ; 
   tagbody
|#

(progn 
  (format t "x")                                       ; Formatted output, same as print but without any spaces.
  (format t "y")
  (+ 1 2))                                             ; Prints xy, the value of this whole expression would be assigned 3. 













|# 
  Defun macro is used to define functions. It needs 3 arguments 1-Name of the function 2- parameters of the function 3- body of the function                                   
 (defun name (parameter list) 
  doStuff
)
|#

(defun absdiff (x y)                                   ; When defining functions we do not use quotations for the symbols.
  (if (> x y) 
    (- x y)
    (- y x)
  )
)                                                      ; Returns absolute difference. (Bigger number - Smaller number). To run this we call (absdiff 3 5).

(defun isPalindrome (list) 
  (equal list (reverse list))
)                                                      ; equal and reverse are built in functions. (isPalindrome '(a b b a)) will return True. (isPalindrome '()) will return True. (isPalindrome '(a b b)) will return NIL.

(defun construct-list (thing &rest args)               ; Defining function that takes 1 argument and assign it to thing and 1 or more arguments that are assigned to args via &rest.
  (cons thing args)                                    ; Call cons function on variable thing and variable(s) args.
)                                                      ; (construct-list 'a) Returns (A). (construct-list 'a '()) Returns (A NIL) because now we're passing a list containing the empty list as an argument. (construct-list 'a 'b 'c 'd) Returns (A B C D)

(defun make-quote (thing &optional arg)                ; Defining function that takes 1 argument and assign it to thing and an optional argument assigned to arg via &optional
  (list thing arg))                                    ; (make-quote 'all) returns (ALL NIL)

(defun make-quote (thing &optional (arg 'die))         ; Same as last one but here we're defining a default value for arg, if there isn't one passed when calling the function
  (list thing 'men 'must arg))                         ; (make-quote 'all) returns (ALL MEN MUST DIE). (make-quote 'all 'serve) returns (ALL MEN MUST SERVE)

|#
   Higher-order functions are functions who take one or more functions as their arguments OR return a function.
#|
(sort (list 5 0 7 3 9 1 4 13 23) #'>)                  ; Returns (23 13 9 7 5 4 3 1 0) returning a new list not changing. This is a higher-order function takes a list as an argument, and the comparison operator (>) which is a function. #' is used to reference a function functionname.
(mapcar #'* '(2 3) '(10 10))                           ; Returns (20 30). Takes a function as its argument and one or more lists and applies the function to the elements of the list
(funcall #'+ 1 3 4)                                    ; Returns 8, equivalent to (+ 1 3 4). Calls a function on its arguments which dont have to be a list.
(apply #'+ 3 4 '(1 3 4))                               ; Returns 15, works same way as funcall but requires the last argument passed to be a list. If we pass NIL as last list then apply becomes same as funcall

(defun fn (x) 
  (cond 
    ((numberp x) #'+)
    ((listp x) #'append)
  )
)                                                      ; Function depends on variable X and it returns a function (simple operation here). If x is a number returns addition, if its a list return append, else NIL.
(defun combine (&rest args) 
  (apply (fn (car args)) args)
)                                                      ; If head of the list passed is a number then fn is addition if list we append. (we're only checking first element of a passed list)

|#
   Anonymous functions or Lambda functions are functions that are defined and possible called without being bound to a name. Unlike functions that are defined with (defun ), Anonymous functions are not stored in memory.
   The general syntax looks like this:
   (lambda (formal parameter list) (expressionToBeEvaluated)) 
#|
((lambda (x) (* x x)) 3)                               ; Returns 9. x is the parameter of the expression and the result is (* x x) and we're calling the whole (lambda (x) (* x x)) function on 3. 3 is passed as an argument to this function
(mapcar (lambda (n) (* n 2)) '(2 3 5 7))               ; Returns (4 6 10 14). mapcar is taking the lambda function as its argument and applies it on the list


|#
   Keyword parameters (named parameters) allows to specify which values go with which particular parameter using &key, the point is asking the user to pass parameters by name
   instead of having to pass parameters in a certain order.
   When we send values to the function we need to do it after specifying the name, everything after the & in the the same () is the name and we assign with the :
#|
(defun make-pairs (&key a b c d) 
  (list (list a b) (list c d))
)                                                      ; Creates a pair of pairs.(a b)(c d)
(make-pairs :c 3 :a 5 :d 1 :b 9)                       ; Returns ((5 9) (3 1)). Order of arguments doesn't matter.
(make-pairs)                                           ; Returns ((NIL NIL) (NIL NIL)). Arguments set to NIL by default.
(make-pairs :a 7 :d 6)                                 ; Returns ((7 NIL)(NIL 6)) Missing variables.

|# 
   Function composition like f(g(x)). 
   In this example we're trying to define a function consR, which is basically like cons but instead we want to insert an element to the right. Psuedocode looks like this:
   consR(List,element):
        if L = <> then <element>
        else concatenate(head(L), consR(tail(L), e))    ; Since tail will reduce the size of the list by 1 every time, this recursion consumes the list until its empty and adds e at the end.
#|

(defun consr (lst elt) 
  (if (null lst) 
    (list elt)
    (cons (car lst) (consr (cdr lst) elt))
  )
)

(defun product (lst1 lst 2) 
  (if (or (null lst1) (null lst2)) 
    NIL
    (let 
      ((a (* (car lst1) (car lst2))))
      (cons a (product (cdr lst1) (cdr lst2)))
    )
  )
)                                                          ;Takes 2 lists and returns a list whose elements are the products of the corresponding pairs of its arguments. Ignores the rest of the list if no pairs.



|# A function is called a Pure function if it satisfies both conditions
1) Always has same return given same arguments
2) The return doesn't cause any observable side effects or ouputs.
What is a side-effect?
  When a function changes something outside of it's own scope. Or it depends on something other than the argument. A pure/mathemtical function maps the domain(argument) to a range(return). 
Examples:
  length(string)                                      ; Pure. Length will always return the same value given the same input
  today()                                             ; Impure. Depends on the date and won't always return the same value.
  print(arg)                                          ; Impure. It doesn't have a return (Void) and it prints something, mutates something.

Referentially transparent VS Referentially Opaque.
  Transparent means that we can replace an expression by it's value, without changing the program. Like replacing (* 5 5) by 25 or (+ 5 4) by 9 (+ X Y) won't change if we replace it with the result.
  Another way to look at it is that no matter how many times you run that expression, it doesn't matter. Even running the expression 0 times (by straight up replacing it). If the compiler only has to run it once
  during compile-time and never needs to evaluate the expression again at run-time then its transparent. It has to be pure to be transparent.
  Opaque expressions are Impure expressions that, if replaced, will change the program. We actually need the program to run it we can't just replace it once in compile time and get the same return every time it's called.

|#


