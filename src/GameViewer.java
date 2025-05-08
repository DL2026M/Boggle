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
    public static final int STARTING_LETTER_XCORD = 245;
    public static final int STARTING_LETTER_YCORD = 170;
    public static final int LETTER_DISTANCE_X = 88;


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
                g.drawImage(introPage, 0,0,930,1000, this);
                break;
            case 1:
                g.drawRect(0,0,930,1000);
                g.setColor(Color.black);
                g.drawImage(board, STARTING_XCORD, STARTING_YCORD, WINDOW_WIDTH, WINDOW_HEIGHT,this);
                int counter = 0;
                for (int i = 0; i < LETTERS_PER_ROW; i++) {
                    for (int j = 0; j < LETTERS_PER_COL; j++) {
                        // make 2D array if time permits
                        g.drawImage(letterManager.getShuffledLetters().get(counter).getImage(), 245 + (j*88),  170 + (i*87), 88,87,this);
                        counter++;
                    }
                }
                g.setColor(Color.black);
                g.setFont(new Font("Serif", Font.PLAIN, 40));
                g.drawString(letterManager.getCurrentWord(), 230, 650);
                g.drawString(String.valueOf(letterManager.getScoreTracker()), 860, 170);
                printFoundWords(g);
                break;
            case 2:
                g.setFont(new Font("Serif", Font.PLAIN, 50));
                g.setColor(Color.green);
                g.drawImage(outroPage, 0,0,930,1000, this);
                g.drawString(String.valueOf(letterManager.getScoreTracker()), 130, 730);
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

