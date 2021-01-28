start(s1).
transition(s1, a, s2).
transition(s2, b, s1).
transition(s2, a, s2).
transition(s2, c, s4).
transition(s3, b, s4).
transition(s4, a, s3).
transition(s3, a, s1).
final(s1).
accept(Xs):- start(Q), path(Q,Xs).
path(Q1,[X1|X2]):- transition(Q1,X1,Q2), path(Q2,X2).
path(Qf,[]):- final(Qf).