import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GameViewer extends JFrame {

    private Game game;
    private Image board;
    private Image introPage;
    private Image outroPage;
    private GameLetterManager letterManager;

    private int WINDOW_WIDTH = 930;
    private int WINDOW_HEIGHT = 1000;
    private int STARTING_XCORD = 0;
    private int STARTING_YCORD = 25;
    private int STARTING_BOARD_XCORD = 750;
    private int STARTING_BOARD_YCORD = 750;
    public static final int TOTAL_LETTERS = 26;
    public static final int LETTERS_PER_COL = 5;
    public static final int LETTERS_PER_ROW = 5;


    public GameViewer(Game game) {
        this.game = game;
        this.board = new ImageIcon("Resources/BoggleBoard.png").getImage();
        this.introPage = new ImageIcon("Resources/BoggleBoardIntro.png").getImage();
        this.outroPage = new ImageIcon("Resources/BoggleBoardOutro.png").getImage();
        this.letterManager = new GameLetterManager();


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Boggle");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawImage(board, STARTING_XCORD, STARTING_YCORD, WINDOW_WIDTH, WINDOW_HEIGHT,this);
        int counter = 0;
        for (int i = 0; i < LETTERS_PER_ROW; i++) {
            for (int j = 0; j < LETTERS_PER_COL; j++) {
                g.drawImage(letterManager.getShuffledLetters().get(counter).getImage(), 245 + (j*88),  170 + (i*87), 88,87,this);
                counter++;
            }
        }
    }
}

