import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLetterManager {
    private ArrayList<Letter> letters;
    private ArrayList<Letter> shuffledLetters;
    private String currentWord;
    MouseInput input = new MouseInput(this);

    // change so that there isn't 2 constants of the same constant
    private final int alphabetSize = 26;

    public GameLetterManager() {
        letters = new ArrayList<Letter>();
        shuffledLetters = new ArrayList<Letter>();
        for (int i = 0; i < alphabetSize; i++) {
            Image letterImage = new ImageIcon("Resources/Letters/letter" + i + ".png").getImage();
            Letter filler = new Letter(letterImage);
            letters.add(filler);
        }
        shuffle();
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<Letter> letters) {
        this.letters = letters;
    }

    private void shuffle() {
        for (int i = 0; i < 25; i++) {
            int randomNumber = ((int) (Math.random() * 26));
            shuffledLetters.add(letters.get(randomNumber));
        }
        updateLetterLocations();
    }

    public ArrayList<Letter> getShuffledLetters() {
        return shuffledLetters;
    }

    public void updateLetterLocations() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shuffledLetters.get(counter).setX(245 + (j * 88));
                shuffledLetters.get(counter).setY(170 + (i * 87));
                counter++;
            }
        }
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }
}


