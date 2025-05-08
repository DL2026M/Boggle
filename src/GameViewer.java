import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GameViewer extends JFrame {

    private Game game;
    private Image board;
    private Image introPage;
    private Image outroPage;
    private GameLetterManager letterManager;
    // Constants
    public final int WINDOW_WIDTH = 930;
    public final int WINDOW_HEIGHT = 1000;
    public final int STARTING_XCORD = 0;
    private final int STARTING_YCORD = 0;
    private final int STARTING_BOARD_YCORD = 25;
    private static final int FONT_SIZE = 40;
    public static final int TOTAL_LETTERS = 26;
    public final int LETTERS_PER_COL = 5;
    public final int LETTERS_PER_ROW = 5;
    public final int STARTING_LETTER_XCORD = 245;
    public final int STARTING_LETTER_YCORD = 170;
    public final int LETTER_DISTANCE_X = 88;
    public final int LETTER_DISTANCE_Y = 87;
    public final int WORD_X = 230;
    public final int WORD_Y = 650;
    public final int TRACKER_X = 860;
    public final int TRACKER_Y = 170;
    public final int OUTRO_X = 130;
    public final int OUTRO_Y = 730;


    public GameViewer(Game game) {
        this.game = game;
        this.board = new ImageIcon("Resources/BoggleBoard.png").getImage();
        this.introPage = new ImageIcon("Resources/BoggleBoardIntro.png").getImage();
        this.outroPage = new ImageIcon("Resources/BoggleBoardOutro.png").getImage();
        this.letterManager = new GameLetterManager(this);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Boggle");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g) {
        g.setColor(Color.white);
        switch (letterManager.getGameState()) {
            case 0:
                g.drawImage(introPage, STARTING_XCORD,STARTING_YCORD,WINDOW_WIDTH,WINDOW_HEIGHT, this);
                break;
            case 1:
                g.drawRect(STARTING_XCORD,STARTING_YCORD,WINDOW_WIDTH,WINDOW_HEIGHT);
                g.setColor(Color.black);
                g.drawImage(board, STARTING_XCORD, STARTING_BOARD_YCORD, WINDOW_WIDTH, WINDOW_HEIGHT,this);
                int counter = 0;
                for (int i = 0; i < LETTERS_PER_ROW; i++) {
                    for (int j = 0; j < LETTERS_PER_COL; j++) {
                        g.drawImage(letterManager.getShuffledLetters().get(counter).getImage(), STARTING_LETTER_XCORD
                                + (j*LETTER_DISTANCE_X),  STARTING_LETTER_YCORD + (i*LETTER_DISTANCE_Y),
                                LETTER_DISTANCE_X,LETTER_DISTANCE_Y,this);
                        counter++;
                    }
                }
                g.setColor(Color.black);
                g.setFont(new Font("Serif", Font.PLAIN, FONT_SIZE));
                g.drawString(letterManager.getCurrentWord(), WORD_X, WORD_Y);
                g.drawString(String.valueOf(letterManager.getScoreTracker()), TRACKER_X, TRACKER_Y);
                printFoundWords(g);
                break;
            case 2:
                g.setFont(new Font("Serif", Font.PLAIN, FONT_SIZE + 10));
                g.setColor(Color.green);
                g.drawImage(outroPage, STARTING_XCORD, STARTING_YCORD,WINDOW_WIDTH,WINDOW_HEIGHT, this);
                g.drawString(String.valueOf(letterManager.getScoreTracker()), OUTRO_X, OUTRO_Y);
                break;

        }
    }

    // create a function that is a for loop that is called from outside of paint
    private void printFoundWords(Graphics g) {
        int size = letterManager.getFoundWords().size();
        ArrayList<String> list = letterManager.getFoundWords();
        int previous = 0;
        //for (int i = 0; i < 3; i++) {
            for (int j = 0; j < size; j++) {
                //if (j > 20) {
                previous = 190 + (list.get(j).length() * 30);
                    g.drawString(list.get(j), previous + 40, 800);
               // }
               // else {
                   // g.drawString(list.get(j), 190 + (j * 5), 800 + (i * 80));
                //}
            }
        }
    public GameLetterManager getLetterManager() {
        return letterManager;
    }
}

