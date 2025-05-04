import java.awt.event.*;
import java.util.ArrayList;

public class MouseInput implements MouseListener, MouseMotionListener, ActionListener {
    private GameLetterManager letterManager;
    private final int BOARD_STARTING_XCORD = 245;
    private final int BOARD_STARTING_YCORD = 170;



    public MouseInput(GameLetterManager letterManager) {
        this.letterManager = letterManager;
        letterManager.getViewer().addMouseListener(this);
    }

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
       // int counter = 0;
        int counter;


        if (245 < x && x < 685) {
            if (170 < y && y < 605) {
                xVariable = ((x - BOARD_STARTING_XCORD) / 88);
                yVariable = ((((y - BOARD_STARTING_YCORD) / 87)));
                yVariable = yVariable * 5;
                counter = xVariable + yVariable;
                Letter variable = letterManager.getShuffledLetters().get(counter);
                if (letterManager.isValidMove(variable)) {
                    letterManager.addStringToCurrentWord(variable.getName());
                    System.out.println(variable.getName());
                    letterManager.move(variable);
                }
            }
        }
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
