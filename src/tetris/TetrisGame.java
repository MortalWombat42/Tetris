/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 * This should be implemented to include your game control.
 *
 * @author pipWolfe
 */
public class TetrisGame {

    private final Tetris tetrisApp;
    private final TetrisBoard board;
    private TetrisPiece piece;
    private int score = 0;
    private boolean gameOver = false;

    /**
     * Initializes the game.
     *
     * @param tetrisApp A reference to the application (use to set messages).
     * @param board A reference to the board on which Squares are drawn
     */
    public TetrisGame(Tetris tetrisApp, TetrisBoard board) {
        //stores a Tetris and a TetrisBoard in the class
        this.tetrisApp = tetrisApp;
        this.board = board;
        // Constructs a random tetris piece
        newPiece();
        // You can use this to show the score, etc.
        tetrisApp.setMessage("Game has started!");
    }

    /**
     * Animate the game, by moving the current tetris piece down.
     */
    void update() {
        //checks if game has finished
        if (!gameOver) {
            System.out.println("updating");
            //moves the piece down by 1
            if (!piece.move(0, 1)) {
                //if the piece cant move, dumps the squares onto the board
                piece.dumpSquares();
                //clears the rows if any are full
                int rows = board.clearRows();
                //increases score if rows have been cleared
                if (rows != 0) {
                    score += (4 + ((rows - 1))) * rows * 25;
                    tetrisApp.setMessage("Score: " + score);
                }
                //adds a new piece to the board
                newPiece();
            }
        } else {
            tetrisApp.setMessage("GAME OVER! Final Score: " + score
                    + ". . . Play again? (press space)");
        }
    }

    /**
     * Move the current tetris piece left.
     */
    void left() {
        System.out.println("left key was pressed!");
        if (!gameOver) {
            piece.move(-1, 0);
        }
    }

    /**
     * Move the current tetris piece right.
     */
    void right() {
        System.out.println("right key was pressed!");
        if (!gameOver) {
            piece.move(1, 0);
        }
    }

    /**
     * Drop the current tetris piece.
     */
    void drop() {
        System.out.println("drop key was pressed!");
        if (!gameOver) {
            while (piece.move(0, 1)) {
            }
        } else {
            board.clearBoard();
            piece.clearPiece();
            score = 0;
            gameOver = false;
            tetrisApp.setMessage("Game has started!");
            newPiece();

        }
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
    void rotateLeft() {
        System.out.println("rotate left key was pressed!");
        if (!gameOver) {
            piece.rotateLeft();
        }
    }

    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight() {
        System.out.println("rotate right key was pressed!");
        if (!gameOver) {
            piece.rotateRight();
        }
    }

    /**
     * Creates a new piece at the top of the screen. If it can't, ends the game.
     */
    void newPiece() {
        piece = new TetrisPiece(board);
        piece.moveToLocation(TetrisBoard.X_DIM_SQUARES / 2, 1);
        if (!piece.move(0, 1)) {
            gameOver = true;
        }
    }
}
