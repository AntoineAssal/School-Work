second_half([H|T], X) :-
    T = [] -> X = [H];
    (length([H|T], Len),
    Mid is round(Len/2),
    L = [],
    get_half([H|T], Mid, L, N),
    append(N, X, [H|T])).
	
get_half([H|T], M, L, N) :-  
    Y is M-1,
    Y > -1,
    append(L, [H], C),
    get_half(T, Y, C, N).
	
get_half([_|_], 0, U, U).