import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLetterManager
{
    private ArrayList<Letter> letters;
    private ArrayList<Letter> shuffledLetters;

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
        for (int i = 0; i < 26; i++) {
            int randomNumber = ((int)(Math.random() * 26));
            shuffledLetters.add(letters.get(randomNumber));
        }
    }

    public ArrayList<Letter> getShuffledLetters() {
        return shuffledLetters;
    }
}
