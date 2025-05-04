import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLetterManager {
    private ArrayList<Letter> letters;
    private ArrayList<Letter> shuffledLetters;
    private String currentWord = "";
    private final int LETTERS_PER_BOARD = 25;
    private GameViewer viewer;
    private MouseInput input;
    private final int asciiStarting = 97;
    private Letter previousLetter;

    public GameLetterManager(GameViewer viewer) {
        letters = new ArrayList<Letter>();
        shuffledLetters = new ArrayList<Letter>();
        this.viewer = viewer;
        input = new MouseInput(this);
        for (int i = 0; i < GameViewer.TOTAL_LETTERS; i++) {
            Image letterImage = new ImageIcon("Resources/Letters/letter" + i + ".png").getImage();
            Letter filler = new Letter(letterImage, (char) (i + asciiStarting));
            letters.add(filler);

        }
        shuffle();
    }
    private void shuffle() {
        for (int i = 0; i < LETTERS_PER_BOARD; i++) {
            int randomNumber = ((int) (Math.random() * GameViewer.TOTAL_LETTERS));
            shuffledLetters.add(letters.get(randomNumber));
        }
        updateLetterLocations();
    }

    public void updateLetterLocations() {
        int counter = 0;
        Letter currentLetter;
        for (int i = 0; i < GameViewer.LETTERS_PER_ROW; i++) {
            for (int j = 0; j < GameViewer.LETTERS_PER_COL; j++) {
                currentLetter = shuffledLetters.get(counter);
                currentLetter.setX(245 + (j * 88));
                currentLetter.setGridX(j);
                currentLetter.setY(170 + (i * 87));
                currentLetter.setGridY(i);
                counter++;
            }
        }
    }
    public boolean isValidMove(Letter move) {
        if (previousLetter == null) {
            return true;
        }
        if (Math.abs(previousLetter.getGridX() - move.getGridX()) <= 1) {
            if (Math.abs(previousLetter.getGridY() - move.getGridY()) <= 1) {
                if (!move.getIsVisted()) {
                    return true;
                }
            }
        }
        return false;
    }
    public void move(Letter move) {
        previousLetter = move;
        previousLetter.setVisted(true);
    }


    public void addStringToCurrentWord(String letter) {
        currentWord += letter;
    }
    // Getters & setter methods
    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }
    public ArrayList<Letter> getLetters() {
        return letters;
    }
    public void setLetters(ArrayList<Letter> letters) {
        this.letters = letters;
    }

    public GameViewer getViewer() {
        return viewer;
    }
    public ArrayList<Letter> getShuffledLetters() {
        return shuffledLetters;
    }
}


