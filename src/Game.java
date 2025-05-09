// Author, David Lutch

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements MouseListener, MouseMotionListener, ActionListener {
    private boolean gameOver;
    private int pointValue;
    private GameViewer window;
    private GameLetterManager letterManager;
    // Constants
    private final int PLAY_XCORD = 450;
    private final int PLAY_YCORD = 800;
    private final int END_XCORD = 800;
    private final int END_YCORD = 900;
    private final int ENTER_XCORD = 205;
    private final int ENTER_YCORD_LEAST = 518;
    private final int ENTER_YCORD_MAX = 555;
    private final int LETTER_YCORD_MAX = 605;
    private final int LETTER_XCORD_MAX = 685;


    public static void main(String[] args) {
        Game newGame = new Game();
    }

    public Game() {
        this.window = new GameViewer(this);
        this.letterManager = window.getLetterManager();
        letterManager.getViewer().addMouseListener(this);

    }
        @Override
        public void actionPerformed(ActionEvent e) {

        }
        @Override
        public void mouseClicked(MouseEvent e) {
            // Getting the x and y cords of wherever the user clicked
            int x = e.getX();
            int y = e.getY();
            int xVariable;
            int yVariable;
            int counter;
            // Entering word button coordinates
            if (window.STARTING_XCORD < x && x < ENTER_XCORD) {
                if (ENTER_YCORD_LEAST < y && y < ENTER_YCORD_MAX) {
                    if (!letterManager.checkWord()) {
                        letterManager.resetWord();
                    }
                }
            }
            // Checking to see if a letter on the board was clicked
            if (window.STARTING_LETTER_XCORD < x && x < LETTER_XCORD_MAX) {
                if (window.STARTING_LETTER_YCORD < y && y < LETTER_YCORD_MAX) {
                    xVariable = (x - window.STARTING_LETTER_XCORD) / window.LETTER_DISTANCE_X;
                    yVariable = (y - window.STARTING_LETTER_YCORD) / window.LETTER_DISTANCE_Y;
                    yVariable = yVariable * window.LETTERS_PER_COL;
                    counter = xVariable + yVariable;
                    Letter variable = letterManager.getShuffledLetters().get(counter);
                    // Checking to see if the letter selected by the user is a valid move
                    if (letterManager.isValidMove(variable)) {
                        letterManager.addStringToCurrentWord(variable.getName());
                        letterManager.move(variable);
                    }
                }
            }
            // Clicked on the play button on the intro page
            if (window.STARTING_XCORD < x && x < PLAY_XCORD) {
                if (PLAY_YCORD < y && y < window.WINDOW_HEIGHT) {
                    letterManager.setGameState(1);
                }
            }
            // Clicked on the end button in the game stage of the board
            if (END_XCORD < x && x < window.WINDOW_WIDTH) {
                if (END_YCORD < y && y < window.WINDOW_HEIGHT) {
                    letterManager.setGameState(2);
                }
            }
            window.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
}
