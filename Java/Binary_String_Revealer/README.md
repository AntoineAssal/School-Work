# Binary_String_Revealer

This is a program that takes as input a string of any length of random number of binary characters `0` and `1` and a masked `*` character at some positions, and finds all possible sequences of binary strings that can be constructed by replacing the masked `*` character by either `0` or `1`.

## Pseudocode 
### Recursive Version
`Algorithm` **RevealStr**(`S`,`I`)\
Input: A String `S` composed of `1`s, `0`s and `*`s. With a non-negative integer `I` representing the starting index.\
Output: Print all possible combinations of binary strings replacing the `*`s with `1`s and `0`s.
```java
size ← s.length
Let A be an array of the characters forming the string s
Let B be the conversion of A back into a string.
if I=size then
    return s
if A[i] is not * then
    RevealStr(B,i+1)
else
    RevealStr(B,i+1)
```

### Iterative version
`Algorithm` **RevealStr**(`S`)\
Input: A String `S` composed of `1`s, `0`s and `*`s.\
Output: Print all possible combinations of binary strings replacing the `*`s with `1`s and `0`s.
``` java       
Let inputString be a Stack <String>
Let out be a String
push S into inputString
while inputString is not empty do
    out← inputString.pop
    if out contains a * then
        push String with 0 to out
        push String with 1 to out
    else
        Print out
```

