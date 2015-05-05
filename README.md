# rubiks-two-two
An algorithm for solving [2x2 Rubik's cubes][rubiks] also known as the pocket or mini cube. I made this for my Algorithm Analysis and Design class taught by Richard Hoshino at [Quest University][quest]. The rather brute-force algorithm was designed by me, but most of the code for representing the cube in Java was provided by my friend [William Bernoudy][will].

## Installing
Simply compile `KitRubik.java` and pass the input that describes your cube to the standard input of your terminal and the script will output the sequence of moves required to solve the given cube.

Example Usage (passing the program an already solved cube):

```
java KitRubik < tests/test1.txt 
F F' 
Number of moves: 2
Cubes in correct position: 8
274 micrcoseconds
```

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


For example, passing an already solved cube to the program looks like so:


## Output

## Algorithm


[rubiks]:https://www.rubiks.com/store/cubes/rubiks-2x2
[quest]:www.questu.ca
[will]:https://www.williambernoudy.com/
