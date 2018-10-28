# MineSweeper
The “MineSweeper” game (as in Windows) without the GUI. It is an interactive game where the user needs to know the grid properly. The user has to provide input every time to proceed.

SampleInput file contains a sample input to create the grid having the bombs at specific positions. The contents of file:
```First Line: Number of Rows of the grid
Second Line: Number of Columns of the grid
Third Line: Number of Bombs(b)
Next b lines contain the coordinates of the bombs
Last Line: Number of undoes allowed.
```
Understanding the rules of playing:
A cell in the grid can be flagged or checked/tested. Flagging a cell is displayed as ```|```. Checking the sell displays the content of the cell. A cell can have a value or a bomb. In this version, there are no empty cells. The value of a cell denotes the number of bombs in its neighbourhood(here 8 neighbouring cells according to Moore's neighbourhood). If the cell has ```*```, it denotes it has a bomb.
The cells containing bombs needs to be flagged and all the number cells need to be checked in order to win. A player is allowed a number of undoes. If he/she checks a cell which has bomb, the move can be undone provided the undoes are left otherwise the player looses the game. Checking a flagged position is allowed. 

The player after executing the given .java files need to start to feed the input as follows:
  ```
  1. x y 0/1
    , where 0 denotes flagging the coordinate and 1 denotes checking the coordinate
  2. undo
  ```
  After each input entered, the player is shown the current configuration of the grid or is indicated whether the input was invalid.
  If the user does undo the move of checking the bomb cell, he/she looses the game.
  A possible set of moves along with the grid configurations are shown below for the SampleInput file given.
  ```
  INPUT 1 0 1
Undoes left 8
- - - 
2 - - 
- - - 
INPUT 1 1 2
Invalid move
INPUT 1 1 1
Undoes left 8
- - - 
2 * - 
- - - 
INPUT undo
Undoes left 7
- - - 
2 - - 
- - - 
INPUT 1 1 0
Undoes left 7
- - - 
2 | - 
- - - 
INPUT 0 1 0
Undoes left 7
- | - 
2 | - 
- - - 
INPUT 0 1 1
Undoes left 7
- 3 - 
2 | - 
- - - 
INPUT 0 0 1
Undoes left 7
1 3 - 
2 | - 
- - - 
INPUT 0 2 0
Undoes left 7
1 3 | 
2 | - 
- - - 
INPUT 1 2 0
Undoes left 7
1 3 | 
2 | | 
- - - 
INPUT 2 1 1
Undoes left 7
1 3 | 
2 | | 
- 4 - 
INPUT 2 0 1
Undoes left 7
1 3 | 
2 | | 
* 4 - 
INPUT undo
Undoes left 6
1 3 | 
2 | | 
- 4 - 
INPUT 2 0 0
Undoes left 6
1 3 | 
2 | | 
| 4 - 
INPUT 2 2 0
Undoes left 6
1 3 | 
2 | | 
| 4 | 
You won the game with 15 moves and 2 undoes.
Hurray!
