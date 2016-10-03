# Codelike
A 2D programming language

## Some notes

Specify the file to run by changing the `source` string at the begining of the file [(x)](https://github.com/dospunk/codelike/blob/master/Compiler.java#L14)

When reading a source file, the compiler will always start at the top left corner and will be going down.

Y coordinates increase going down, X coordinates increase going left

Please note that tabs only count as one character! Make sure to use spaces.

If you get a `j` error first thing when running but your file does not start with a `j`, it means that the file could not be found

turn on debugging mode by changing the `debugging` boolean at the begining of the file to `true` [(x)](https://github.com/dospunk/codelike/blob/master/Compiler.java#L13)

## Commands

-: continue horizontally

|: continue vertically

\: continue up+left or down+right

/: continue down+left or up+right

+: increment top value on the stack and continue

_: decrement top value on the stack and continue

\>: If moving to the left, will continue up+left if the top value on the stack is greater than 0, or down+left if it is less than zero

<: If moving to the right, will continue up+right if the top value on the stack is greater than 0, or down+right if it is less than zero

^: If moving to the down, will continue down+right if the top value on the stack is greater than 0, or down+left if it is less than zero

*: pop the top two values from the stack, multiply them, and push the result to the stack

a: pop the top two values from the stack, add them, and push the result to the stack

c: change direction: scan clockwise and continue

d: pop the top two values from the stack, divide the top by the second, and push the result rounded down to the stack

f: discard the top value from the stack

j: take the top two values from the stack as coordinates (top val = x, second val = y) and go to those coordinates in the grid

n: push a 0 onto the stack

o: change direction: scan counter-clockwise and continue

p: print the ASCII character corresponding to the top value

r: reverses the direction

s: pop the top two values from the stack, subtract them, and push the result to the stack

u: push a number from user input to the stack

e: end the program
