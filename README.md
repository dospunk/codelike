# Codelike
A 2D programming language

When reading a source file, the compiler will always start at the top left corner and will be going down.

Y coordinates increase going down, X coordinates increase going left

Please note that tabs only count as one character! Make sure to use spaces.

##Commands

-: continue horizontally

|: continue vertically

\: continue up+left or down+right

/: continue down+left or up+right

o: change direction: scan counter-clockwise and continue

c: change direction: scan clockwise and continue

+: increment top value on the stack and continue

_: decrement top value on the stack and continue

p: print the ASCII character corresponding to the top value

f: discard the top value from the stack

n: push a 0 onto the stack

u: push a number from user input to the stack

*: pop the top two values from the stack, multiply them, and push the result to the stack

d: pop the top two values from the stack, divide the top by the second, and push the result rounded down to the stack

a: pop the top two values from the stack, add them, and push the result to the stack

s: pop the top two values from the stack, subtract them, and push the result to the stack

j: take the top two values from the stack as coordinates (top val = x, second val = y) and go to those coordinates in the grid

e: end the program
