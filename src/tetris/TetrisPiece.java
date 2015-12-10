/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.paint.Color;

/**
 * A collection of TetrisSquares in a certain shape that move together
 *
 * @author Alex Grech IV
 */
public class TetrisPiece {

    //array of 4 squares that make up the piece
    private TetrisSquare[] squares = new TetrisSquare[4];
    //x and y values of the center of the piece
    private int locationX;
    private int locationY;
    //arrays of the relative x and y locations of the squares to the center of the piece
    private int[] relativeX = new int[4];
    private int[] relativeY = new int[4];
    //array of colors corresponding to each piece
    private Color[] colors = new Color[7];
    //the number that corresponds to the piece shape
    private int piece;

    /**
     * Creates a piece composed of 4 TetrisSquares on a TetrisBoard. The piece
     * being created corresponds to the number input.
     *
     * @param board the board the squares will be constructed on
     * @param number corresponds to the piece being created
     */
    public TetrisPiece(TetrisBoard board, int number) {
        //sets the colors to change to
        colors[0] = Color.RED;
        colors[1] = Color.ORANGE;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.BLUE;
        colors[5] = Color.INDIGO;
        colors[6] = Color.VIOLET;
        //creates 4 new squares within the piece
        for (int i = 0; i < 4; i++) {
            squares[i] = new TetrisSquare(board);
        }
        //chooses piece shape based on the given number
        this.choosePiece(number);
    }

    /**
     * Creates a piece composed of 4 TetrisSquares on a TetrisBoard. The shape
     * of the piece created is random.
     *
     * @param board
     */
    public TetrisPiece(TetrisBoard board) {
        //sets the colors to change to
        colors[0] = Color.RED;
        colors[1] = Color.ORANGE;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.BLUE;
        colors[5] = Color.INDIGO;
        colors[6] = Color.VIOLET;
        //creates 4 new squares within the piece
        for (int i = 0; i < 4; i++) {
            squares[i] = new TetrisSquare(board);
        }
        //selects a random number from 0 to 6 to choose piece shape
        int random = (int) (Math.random() * 7);
        this.choosePiece(random);
    }

    /**
     * Selects the shape of the piece based on the input number
     *
     * @param number
     */
    private void choosePiece(int number) {
        switch (number) {
            //relative locations of square piece
            case 0:
                relativeX[1] = -1;
                relativeY[1] = 0;
                relativeX[2] = -1;
                relativeY[2] = -1;
                relativeX[3] = 0;
                relativeY[3] = -1;
                piece = 0;
                this.setColor(colors[0]);
                break;
            //relative locations of upercase 'L' piece
            case 1:
                relativeX[1] = -1;
                relativeY[1] = -1;
                relativeX[2] = 0;
                relativeY[2] = -1;
                relativeX[3] = 0;
                relativeY[3] = 1;
                piece = 1;
                this.setColor(colors[1]);
                break;
            //relative locations of 'J' piece
            case 2:
                relativeX[1] = 0;
                relativeY[1] = -1;
                relativeX[2] = 1;
                relativeY[2] = -1;
                relativeX[3] = 0;
                relativeY[3] = 1;
                piece = 2;
                this.setColor(colors[2]);
                break;
            //relative locations of 'z' piece
            case 3:
                relativeX[1] = -1;
                relativeY[1] = -1;
                relativeX[2] = 0;
                relativeY[2] = -1;
                relativeX[3] = 1;
                relativeY[3] = 0;
                piece = 3;
                this.setColor(colors[3]);
                break;
            //relative locations of 's' piece
            case 4:
                relativeX[1] = -1;
                relativeY[1] = 0;
                relativeX[2] = 0;
                relativeY[2] = -1;
                relativeX[3] = 1;
                relativeY[3] = -1;
                piece = 4;
                this.setColor(colors[4]);
                break;
            //relative locations of lowercase 'l' piece
            case 5:
                relativeX[1] = 0;
                relativeY[1] = -1;
                relativeX[2] = 0;
                relativeY[2] = 1;
                relativeX[3] = 0;
                relativeY[3] = 2;
                piece = 5;
                this.setColor(colors[5]);
                break;
            //relative locations of 'T' piece
            case 6:
            default:
                relativeX[1] = -1;
                relativeY[1] = 0;
                relativeX[2] = 1;
                relativeY[2] = 0;
                relativeX[3] = 0;
                relativeY[3] = 1;
                piece = 6;
                this.setColor(colors[6]);
                break;
        }
    }

    /**
     * Moves the piece to the location entered
     *
     * @param x x-value of location
     * @param y y-value of location
     */
    public void moveToLocation(int x, int y) {
        locationX = x;
        locationY = y;
        for (int i = 0; i < 4; i++) {
            squares[i].moveToTetrisLocation(relativeX[i] + locationX,
                    relativeY[i] + locationY);
        }
    }

    /**
     * Moves the piece by the amount entered, first checking to see if the piece
     * can move. Returns whether or not the piece was moved.
     *
     * @param x movement in x direction
     * @param y movement in y direction
     * @return whether the piece was moved or not
     */
    public boolean move(int x, int y) {
        boolean canMove = true;
        for (int i = 0; i < 4; i++) {
            if (!isEmpty(locationX + relativeX[i] + x, locationY + relativeY[i] + y)) {
                canMove = false;
            }
        }
        if (canMove) {
            this.moveToLocation(locationX + x, locationY + y);
        } else {
            System.out.println("Cannot Move There");
        }
        return canMove;
    }

    /**
     * Rotates the piece left 90 degrees, first checking if it can
     */
    public void rotateLeft() {
        if (piece != 0) {
            int[] tempX = new int[4];
            int[] tempY = new int[4];
            for (int i = 0; i < 4; i++) {
                tempX[i] = relativeX[i];
                tempY[i] = relativeY[i];
                relativeX[i] = tempY[i];
                relativeY[i] = -tempX[i];
            }
            if (!this.move(0, 0)) {
                for (int i = 0; i < 4; i++) {
                    relativeX[i] = tempX[i];
                    relativeY[i] = tempY[i];
                }
            }
        }
    }

    /**
     * Rotates the piece right 90 degrees, first checking if it can
     */
    public void rotateRight() {
        if (piece != 0) {
            int[] tempX = new int[4];
            int[] tempY = new int[4];
            for (int i = 0; i < 4; i++) {
                tempX[i] = relativeX[i];
                tempY[i] = relativeY[i];
                relativeX[i] = -tempY[i];
                relativeY[i] = tempX[i];
            }
            if (!this.move(0, 0)) {
                for (int i = 0; i < 4; i++) {
                    relativeX[i] = tempX[i];
                    relativeY[i] = tempY[i];
                }
            }
        }
    }

    /**
     * Sets the color for all of the squares in the piece
     *
     * @param color
     */
    public void setColor(Color color) {
        for (int i = 0; i < 4; i++) {
            squares[i].setColor(color);
        }
    }

    /**
     * Checks if a square can move to the target location
     *
     * @param x x-value of target location
     * @param y y-value of target location
     * @return true if location is empty, false if it is occupied or out of
     * bounds
     */
    boolean isEmpty(int x, int y) {
        if (x >= 0 && x < TetrisBoard.X_DIM_SQUARES && y < TetrisBoard.Y_DIM_SQUARES) {
            if (TetrisBoard.squareArray[x][y] == null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * dumps the squares from the TetrisPiece to the TetrisBoard data structure
     */
    public void dumpSquares() {
        for (int i = 0; i < 4; i++) {
            TetrisBoard.squareArray[locationX + relativeX[i]][locationY + relativeY[i]]
                    = squares[i];
        }
    }

    /**
     * removes the squares of the piece from the board
     */
    public void clearPiece() {
        for (int i = 0; i < 4; i++) {
            squares[i].removeFromDrawing();
        }
    }
}
