lucas(0, []):-!.
lucas(1, [2]):-!.
lucas(2, [2, 1]):-!. 
lucas(N,L) :-
    Y is N-1,
    lucas(Y, X),
    append(_, [A,B], X),
    C is A + B,
    append(X, [C], L).