import java.awt.event.*;
import java.util.ArrayList;

public class MouseInput implements MouseListener, MouseMotionListener, ActionListener {
    private GameLetterManager letterManager;
    private final int BOARD_STARTING_XCORD = 245;
    private final int BOARD_STARTING_YCORD = 170;


    public MouseInput(GameLetterManager letterManager) {
        this.letterManager = letterManager;
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
        int xVariable;
        int yVariable;
        for (int i = 0; i < 5; i++) {
            xVariable = (x - (i * 88) + BOARD_STARTING_XCORD);
            if (0 > xVariable && xVariable < 88) {
                for (int j = 0; j < 5; j++) {
                    yVariable = (y - (j * 87) + BOARD_STARTING_YCORD);
                    if (0 > yVariable && yVariable < 87) {
                        // make sure that the letters know what they are (like what letter)
                        letterManager.setCurrentWord(letterManager.getShuffledLetters().get((i + 1)* j));
                    }
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
