import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLetterManager {
    private ArrayList<Letter> letters;
    private ArrayList<Letter> shuffledLetters;
    private String currentWord;
    private final int LETTERS_PER_BOARD = 25;

    MouseInput input = new MouseInput(this);


    public GameLetterManager() {
        letters = new ArrayList<Letter>();
        shuffledLetters = new ArrayList<Letter>();
        for (int i = 0; i < GameViewer.TOTAL_LETTERS; i++) {
            Image letterImage = new ImageIcon("Resources/Letters/letter" + i + ".png").getImage();
            Letter filler = new Letter(letterImage);
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

    public ArrayList<Letter> getShuffledLetters() {
        return shuffledLetters;
    }

    public void updateLetterLocations() {
        int counter = 0;
        for (int i = 0; i < GameViewer.LETTERS_PER_ROW; i++) {
            for (int j = 0; j < GameViewer.LETTERS_PER_COL; j++) {
                shuffledLetters.get(counter).setX(245 + (j * 88));
                shuffledLetters.get(counter).setY(170 + (i * 87));
                counter++;
            }
        }
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
}


