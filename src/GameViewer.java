import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GameViewer extends JFrame {

    private Game game;
    private Image board;
    private int WINDOW_WIDTH = 1000;
    private int WINDOW_HEIGHT = 1000;
    private int STARTING_XCORD = 100;
    private int STARTING_YCORD = 100;
    private int STARTING_BOARD_XCORD = 750;
    private int STARTING_BOARD_YCORD = 750;


    public GameViewer(Game game) {
        this.game = game;
        this.board = new ImageIcon("Resources/BoggleBoard.png").getImage();


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Boggle");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawImage(board, STARTING_XCORD, STARTING_YCORD, STARTING_BOARD_XCORD, STARTING_BOARD_YCORD,this);
    }
}

