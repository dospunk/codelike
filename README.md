# Codelike
A 2D programming language

*Note: I made this back in 2016, and haven't touched it much since. Even still, I'm proud of it as it was my first attempt at file IO and it works fairly well.*

## Some notes

Specify the file to run by passing it as an argument when running Interpreter

When reading a source file, the interpreter will always start at the top left corner and will be going down.

Y coordinates increase going down, X coordinates increase going left

Please note that tabs only count as one character! Make sure to use spaces.

If you get a `j` error first thing when running but your file does not start with a `j`, it means that the file could not be found

turn on debugging mode by changing the `debugging` boolean at the begining of the Interpreter file to `true` [(x)](https://github.com/dospunk/codelike/blob/master/Interpreter.java#L13)

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

b: print the top value from the stack

c: change direction: scan clockwise and continue

d: pop the top two values from the stack, divide the top by the second, and push the result rounded down to the stack

f: discard the top value from the stack

j: take the top two values from the stack as coordinates (top val = x, second val = y) and go to those coordinates in the grid

n: push a 0 onto the stack

o: change direction: scan counter-clockwise and continue

p: print the ASCII character corresponding to the top value on the stack

r: reverses the direction

s: pop the top two values from the stack, subtract them, and push the result to the stack

u: push a number from user input to the stack

e: end the program
