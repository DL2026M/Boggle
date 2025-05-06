import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLetterManager {
    private ArrayList<Letter> letters;
    private ArrayList<Letter> shuffledLetters;
    private String currentWord = "";
    private final int LETTERS_PER_BOARD = 25;
    private GameViewer viewer;
    private MouseInput input;
    private final int asciiStarting = 97;
    private Letter previousLetter;
    public final int DICTIONARY_SIZE = 143091;
    public final String[] DICTIONARY = new String[DICTIONARY_SIZE];


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
        loadDictionary();
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
        //  GameViewer.LETTERS_PER_COL
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                currentLetter = shuffledLetters.get(counter);
                currentLetter.setX(245 + (i * 88));
                currentLetter.setGridX(i);
                currentLetter.setY(170 + (j * 87));
                currentLetter.setGridY(j);
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
    // used from SpellingBee
    public boolean checkWord() {
        int startIndex = 0;
        int endIndex = DICTIONARY_SIZE - 1;
        if (currentWord == "" || currentWord == null) {
            return false;
        }
        return found(currentWord, startIndex, endIndex);


    }
    public void loadDictionary() {
        Scanner s;
        File dictionaryFile = new File("Resources/dictionary.txt");
        try {
            s = new Scanner(dictionaryFile);
        } catch (FileNotFoundException e) {
            return;
        }
        int i = 0;
        while(s.hasNextLine()) {
            DICTIONARY[i++] = s.nextLine();
        }
    }
    // Recursively calls itself to see if each word created is a valid word by using binary search
    private boolean found(String targetWord, int startIndex, int endIndex) {
        if (targetWord == null) {
            return false;
        }
        int midIndex = (startIndex + endIndex) / 2;
        if (targetWord.equals(DICTIONARY[midIndex])) {
            return true;
        }
        // Returns false if the starting index is out of bounds
        if (startIndex > endIndex) {
            return false;
        }
        // Checks to see if the target word is to left of the middle index
        if (targetWord.compareTo(DICTIONARY[midIndex]) < 0) {
            // Shifts the middle index to the left
            return found(targetWord, startIndex,midIndex - 1);
        }
        if (targetWord.compareTo(DICTIONARY[midIndex]) > 0) {
            // Shifts the middle index to the right
            return found(targetWord, midIndex + 1, endIndex);
        }
        return true;
    }
    public void resetWord() {
        for (int i = 0; i < shuffledLetters.size(); i++) {
            shuffledLetters.get(i).setVisted(false);
        }
        previousLetter = null;
        currentWord = "";
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

    public String getCurrentWord() {
        return currentWord;
    }

    public void setPreviousLetter(Letter previousLetter) {
        this.previousLetter = previousLetter;
    }
}


