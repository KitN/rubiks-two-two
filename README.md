# rubiks-two-two
An algorithm for solving [2x2 Rubik's cubes][rubiks] also known as the pocket or mini cube. I made this for my Algorithm Analysis and Design class taught by Richard Hoshino at [Quest University][quest]. The rather brute-force algorithm was designed by me, but most of the code for representing the cube in Java was provided by my friend [William Bernoudy][will].

## Installing/Running
Simply compile `KitRubik.java` and pass the input that describes your cube to the standard input of your terminal and the script will output the sequence of moves required to solve the given cube.

Example Usage (passing the program an already solved cube):

```
java KitRubik < tests/test1.txt 
F F' 
Number of moves: 2
Cubes in correct position: 8
274 micrcoseconds
```

Where `test1.txt` contains a single line describing a solved cube:
```
y y y y g g g g w w w w b b b b r r r r o o o o
```

To play with the code, 4 test inputs are provided in the `testinputs` folder. Each one is one line containing differently scrambled cubes.

## Input
To make representing the cube easier,  we consider the cube with the Rubik's Cube logo on it fixed in the **bottom, back, left** of the cube. This means the 'cubie' with the white, red and blue stickers/colors on it does not move when manipulating the cube.

A scrambled cube can be passed to the program by giving it a sequence of 24 space seperated characters corresponding to the colors. 

The color characters are:

- y : yellow
- g : green
- w : white
- b : blue
- r : red
- o : orange

The stickers are enumerated (from 1-9 then A-Q) like so using this unfolded 2-d map of the cube:
```
     +---+
     |1|2|
     +---+
     |3|4|
     +---+
+---++---++---+
|H|I||5|6||N|O|
+---++---++---+
|J|K||7|8||P|Q|
+---++---++---+
     +---+
     |9|A|
     +---+
     |B|C|
     +---+
     +---+
     |D|E|
     +---+
     |F|G|
     +---+
```

## Output
The code will output a sequence of moves that will solve the given cube. Since we established the bottom, back, left cube as fixed, the only possible rotations are of the top, front, or right halves of the cube.

Although one quarter-turn clockwise is the same as three quarter-turns counterclockwise (so really it is possible to only define three distinct motions for our cube), we shall distinguish between quarter turns in either direction using the prime `'` tick.

The six moves are:

- T : Turn the top half one quarter turn clockwise.
- T' : Turn the top half one quarter turn counterclockwise.
- F : Turn the front half one quarter turn clockwise.
- F' : Turn the front half one quarter turn counterclockwise.
- R : Turn the right half one quarter turn clockwise.
- R' : Turn the right half one quarter turn counterclockwise.

## Algorithm

The algorithm makes use of *senary numbers* (base 6) to do a breadth-first search of all of the combinations of moves that may solve a cube.  Since it is known that all 2x2 Rubik's cubes can be solved in a maximum of 14 quarter turns, this means that at worst 6^14 sequences of moves are tried before an answer is guaranteed to be found. In practice, the number of moves require to solve the average cube is much less.


For example, `023` in base 6 or 15 in decimal, corresponds to the three-move sequence `T F F'`. By iterating from `0` to `55555555555555` in base 6, the algorithm checks all of the shorter sequences before checking longer ones. The result of this is the algorithm working very quickly on barely-scrambled cubes.

Note that because 6^12 is more than a Java `int` can store, and I use functions that assume type `int`, the code is presently limited to solving cubes that can be solved in 11 moves or less.

[rubiks]:https://www.rubiks.com/store/cubes/rubiks-2x2
[quest]:www.questu.ca
[will]:https://www.williambernoudy.com/
