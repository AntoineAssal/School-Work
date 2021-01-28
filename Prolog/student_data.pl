student("AA", 123).
student("R", 456).
student("A", 789).
student("S", 459).
enrolled("AA","X","21").
enrolled("AA","X","33").
enrolled("AA","X","18").
enrolled("AA","Y","36").
enrolled("AA","Engr","97").
enrolled("R","X","21").
enrolled("R","X","33").
enrolled("R","X","18").
enrolled("R","Y","36").
enrolled("R","Engr","97").
enrolled("S","X","21").
enrolled("S","X","33").
enrolled("S", "X","65").
enrolled("S","Y","87").
enrolled("A","X","21").
enrolled("A","X","100").
enrolled("A","X","250").
enrolled("A","Y","87").

lasslist(Student):-findall(X,enrolled(Student,X),ClassList).
teamSize(Size):- findall(X, student(X), Team),length(Team,Size).
unique(Set):- findall(X,enrolled(_,X),ClassList), list_to_set(ClassList,Set).
sorted(Sorted):- findall(X,enrolled(_,X), ClassList), list_to_set(ClassList,Set), sort(Set, Sorted).

unifying([A,B|C]) :- member(A, [A|_]),!,write(A),
    member(B,[A,B|_]),!, write("  "), write(B),
    member(C,[[_,_]|L]),!, write(C).