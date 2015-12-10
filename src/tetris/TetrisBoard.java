/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.layout.Pane;

/**
 * A Pane in which TetrisSquares can be displayed.
 *
 * @author pipWolfe
 */
public class TetrisBoard extends Pane {

    // The size of the side of a tetris square
    public static final int SQUARE_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int X_DIM_SQUARES = 15;
    public static final int Y_DIM_SQUARES = 30;
    // An array of all pieces currently on the board
    public static TetrisSquare[][] squareArray
            = new TetrisSquare[X_DIM_SQUARES][Y_DIM_SQUARES];

    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    public TetrisBoard() {
        this.setPrefHeight(Y_DIM_SQUARES * SQUARE_SIZE);
        this.setPrefWidth(X_DIM_SQUARES * SQUARE_SIZE);
    }

    /**
     * Checks for any full rows and, if a row is full, clears the row and moves
     * everything down. Returns how many rows were cleared.
     *
     * @return number of rows cleared
     */
    public int clearRows() {
        //initialize rowsCleared
        int rowsCleared = 0;
        //go through each row from the top
        for (int j = 0; j < Y_DIM_SQUARES; j++) {
            //check if each row is full
            boolean full = true;
            for (int i = 0; i < X_DIM_SQUARES; i++) {
                if (squareArray[i][j] == null) {
                    full = false;
                }
            }
            //if the row is full
            if (full) {
                //increment number of rows
                rowsCleared += 1;
                //clear full row
                for (int i = 0; i < X_DIM_SQUARES; i++) {
                    squareArray[i][j].removeFromDrawing();
                    squareArray[i][j] = null;
                }
                //move every row above the full row down, then clear that row
                for (int k = j - 1; k >= 0; k--) {
                    for (int i = 0; i < X_DIM_SQUARES; i++) {
                        if (!(squareArray[i][k] == null)) {
                            squareArray[i][k].moveToTetrisLocation(i, k + 1);
                        }
                        squareArray[i][k + 1] = squareArray[i][k];
                        squareArray[i][k] = null;
                    }
                }
            }
        }
        return rowsCleared;
    }

    /**
     * Clears the board of all squares and removes them from the data structure.
     */
    public void clearBoard() {
        for (int j = 0; j < Y_DIM_SQUARES; j++) {
            for (int i = 0; i < X_DIM_SQUARES; i++) {
                if (!(squareArray[i][j] == null)) {
                    squareArray[i][j].removeFromDrawing();
                }
                squareArray[i][j] = null;
            }
        }
    }
}
