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
    private final int BOARD_STARTING_XCORD = 245;
    private final int BOARD_STARTING_YCORD = 170;


    public static void main(String[] args) {
        Game newGame = new Game();
    }

    public Game() {
        this.window = new GameViewer(this);
        this.letterManager = window.getLetterManager();
        letterManager.getViewer().addMouseListener(this);
    }

        // enter here cord range: x(0, 205)
        // y(518, 555)
        // end cord range x(750, 930)
        // y(900, 1000)
        // play cord range on intro page x(500, 930)
        // y(~850,100)


        @Override
        public void actionPerformed(ActionEvent e) {

        }
        // using
        @Override
        public void mouseClicked(MouseEvent e) {
            // width = 88
            // height = 87;
            // Getting the x and y cords of wherever the user clicked
            int x = e.getX();
            int y = e.getY();
            int xVariable = 0;
            int yVariable = 0;
            int counter = 0;
            if (0 < x && x < 205) {
                if (518 < y && y < 555) {
                    if (!letterManager.checkWord()) {
                        letterManager.resetWord();
                    }
                }
            }

            if (245 < x && x < 685) {
                if (170 < y && y < 605) {
                    xVariable = (x - BOARD_STARTING_XCORD) / 88;
                    yVariable = (y - BOARD_STARTING_YCORD) / 87;
                    yVariable = yVariable * 5;
                    counter = xVariable + yVariable;
                    Letter variable = letterManager.getShuffledLetters().get(counter);
                    if (letterManager.isValidMove(variable)) {
                        letterManager.addStringToCurrentWord(variable.getName());
                        System.out.println(letterManager.getCurrentWord());
                        letterManager.move(variable);
                    }
                   // else {
                   //     letterManager.setCurrentWord("");
                   //     letterManager.setPreviousLetter(null);
                    //}
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
