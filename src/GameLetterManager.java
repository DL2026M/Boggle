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
    private GameViewer viewer;
    private Letter previousLetter;
    private ArrayList<String> foundWords = new ArrayList<String>();
    private int scoreTracker = 0;
    private int gameState = 0;
    // Constants
    private final int asciiStarting = 97;
    private final int LETTERS_PER_BOARD = 25;
    private final int DICTIONARY_SIZE = 143091;
    private final int MINIMUM_LENGTH = 4;
    private final String[] DICTIONARY = new String[DICTIONARY_SIZE];

    public GameLetterManager(GameViewer viewer) {
        letters = new ArrayList<Letter>();
        shuffledLetters = new ArrayList<Letter>();
        this.viewer = viewer;
        // Creates all the letters and assigns it a value based on the ascii value
        for (int i = 0; i < GameViewer.TOTAL_LETTERS; i++) {
            Letter filler = new Letter((char) (i + asciiStarting));
            letters.add(filler);
        }
        shuffle();
        loadDictionary();
    }
    // here #12
    private void shuffle() {
        shuffledLetters.clear();
        ArrayList<Integer> doubleChecker = new ArrayList<Integer>();
        for (int i = 0; i < LETTERS_PER_BOARD; i++) {
            int randomNumber = (int) (Math.random() * GameViewer.TOTAL_LETTERS);
            // Checks to see if the random value already exists in doubleChecker
            if (!doubleChecker.contains(randomNumber)) {
                doubleChecker.add(randomNumber);
                shuffledLetters.add(letters.get(randomNumber));
            }
            // Creates a new letter object randomly
            else {
                shuffledLetters.add(new Letter((char) letters.get(randomNumber).getAsciiValue()));
            }
        }
        updateLetterLocations();
    }

    public void updateLetterLocations() {
        int counter = 0;
        Letter currentLetter;
        for (int j = 0; j < viewer.LETTERS_PER_ROW; j++) {
            for (int i = 0; i < viewer.LETTERS_PER_COL; i++) {
                // Updates each letter to know their x and y coordinates and placement on the grid
                currentLetter = shuffledLetters.get(counter);
                currentLetter.setX(viewer.STARTING_LETTER_XCORD + (i * viewer.LETTER_DISTANCE_X));
                currentLetter.setGridX(i);
                currentLetter.setY(viewer.STARTING_LETTER_YCORD + (j * viewer.LETTER_DISTANCE_Y));
                currentLetter.setGridY(j);
                counter++;
            }
        }
    }
    public boolean isValidMove(Letter move) {
        if (previousLetter == null) {
            return true;
        }
        // Checks to see if the letter clicked on by the user is a valid(horizonal, diagonal, or vertical)
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
    // #8 here
    public boolean checkWord() {
        int startIndex = 0;
        int endIndex = DICTIONARY_SIZE - 1;
        if (currentWord == "" || currentWord == null) {
            return false;
        }
        if (found(currentWord, startIndex, endIndex)) {
            if (!foundWords.contains(currentWord)) {
                addFoundWord(currentWord);
                setScoreTracker(currentWord);
                resetWord();
                return true;
            }
        }
        return false;
    }
    // Used from SpellingBee (checkword() (a small part of it), loadDictionary, and found)
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
    // here #11
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
    // Sets every letter to not visited and resets the previous and current word
    public void resetWord() {
        for (Letter shuffledLetter : shuffledLetters) {
            shuffledLetter.setVisted(false);
        }
        previousLetter = null;
        currentWord = "";
    }


    // Getters & setter methods
    public void addStringToCurrentWord(String letter) {
        currentWord += letter;
    }
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

    public int getScoreTracker() {
        return scoreTracker;
    }

    public void setScoreTracker(String word) {
        if (word.length() >= MINIMUM_LENGTH) {
            this.scoreTracker += word.length() - (MINIMUM_LENGTH - 1);
        }
    }
    public ArrayList<String> getFoundWords() {
        return foundWords;
    }

    public void addFoundWord(String word) {
        foundWords.add(word);
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}


