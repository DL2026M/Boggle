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
    /** #9: Can declare and initialize Arrays, ArrayLists, and 2D Arrays.
     * Here is an example of a time in my code where I declared and initialized an Array (1D): public final String[]
     * DICTIONARY = new String[DICTIONARY_SIZE]; I don’t have any 2D Array’s in my Boggle game, but declaring a 2D Array
     * is the same as a 1D Array besides you add another [] and then fill it in. Here is an example of a time in my code
     * where I declared and initialized an ArrayList: private ArrayList<String> foundWords = new ArrayList<String>();
     * I have another where I do the same thing but have a different data types (Letter is the data type).
     */
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

    /**
     * #12: Can use ArrayList methods.
     * // While I don't use every ArrayList method, I use get(),add(), set(), size(), and contains(). I use the get()
     * a lot throughout my code, and an example of it is in getting a random number and then getting the ascii value of
     * that random letter (this is right after the else statement in shuffle(). I use the add() to add a random letter
     * to the shuffledLetters ArrayList (this is to get a randomly shuffled board every time the game is run).
     * The size() is used on the foundWords ArrayList to see how many words I need to print on the board. The set() is
     * used in updateLetterLocations() to set the X and Y cords for the newly shuffled letters. I used contains() to see
     * if the inputted word (by the user) has already been inputted by the user (and therefore it would be in foundWords
     * ArrayList.
     */
    private void shuffle() {
        shuffledLetters.clear();
        ArrayList<Integer> doubleChecker = new ArrayList<Integer>();
        for (int i = 0; i < LETTERS_PER_BOARD; i++) {
            /**
             * #3: Can use Math class, especially Math.random().
             * /I used Math.random() in my code to generate a random number between [0,26) - this is the amount of
             * letters (and qu) in my game. I will then get the letter that is associated with that randomly generated
             * number in the range. This allows my program to have a random combinations of letters. In isValidMove(),
             * I use Math.abs (absolute value) when seeing if the letter clicked on by the user is valid because the
             * move could be in any direction (and diagonal). This means that they could have moved up 1 or down 1, so
             * I need to check the range (if they moved down 1, the absolute value will return 1, which is valid).
             */
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
        /**
         * #1: Declare, initialize and assign a value to a variable, be it a primitive or object.
         * An example of declaring, initializing, and assigning a value to a primitive data type (int) is int counter
         * = 0; The declaring is int counter, and initializing it and assigning a value is the = 0 part. I also modify
         * this variable by incrementing it by 1 every time through the nested for loop.
         */
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

    /**
     * #8: Can utilize class-scope instance variables and local parameters in methods.
     * An example of utilizing class-scope instance variables and local parameters in a method is seen in the
     * checkWord() method. First, I use foundWords and currentWord, which are both instance variables in the
     * GameLetterManager class. While this method doesn’t take in any parameters,  I create a start and end index that
     * are local to the checkWord() method only.
     * @return
     */
    public boolean checkWord() {
        int startIndex = 0;
        int endIndex = DICTIONARY_SIZE - 1;
        if (currentWord == "" || currentWord == null) {
            return false;
        }
        if (found(currentWord, startIndex, endIndex)) {
            if (!foundWords.contains(currentWord)) {
                // If the word isn't already found, then add it to the found words, update the score, and reset the word
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

    /** #11: Can write algorithms to traverse and modify Arrays and ArrayLists.
     * I recursively traverse (in found()) the 1D array of Dictionary here in order to see if the word inputted by the
     * user is actually a valid word (I use binary search here). In resetWord(), I traverse through the ArrayList
     * shuffledLetters and set each letter to not visited (modifying it).
     */
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

    /** #10: Can use for-each loops, understanding that they make copies of the values/references of the Array or
     *  ArrayList that it traverses.
     * I made this method include a for-each loop instead of a normal for loop in order to improve this competency.
     * This for-each loop traverses through every letter in shuffledLetters (an ArrayList) and sets it to not visited.
     * This for-each directly changes each letter in shuffledLetters isVisited to false because it’s a reference type
     * (if this was a primitive data type, then it wouldn’t actually modify the ArrayList, but rather a copy).
     */
    // Sets every letter to not visited and resets the previous and current word
    public void resetWord() {
        for (Letter shuffledLetter : shuffledLetters) {
            shuffledLetter.setVisted(false);
        }
        previousLetter = null;
        currentWord = "";
    }


    // Getters & setter methods
    public int getScoreTracker() {
        return scoreTracker;
    }
    /** #2: Can use and apply SLICE String Methods
     * In my code I length() a few times, but in setScoreTracker() I use it to calculate the amount of points that a
     * valid word inputted by the user is worth (this is calculated by the subtracting the length of the word from 3).
     * I use .equals() in found() (right above the getters and setters) to check if the current word is equal to middle
     * index of dictionary. The .equals() method is used to compare if two strings are equal to each other. The other
     * String methods are substring(can modify strings by splitting them an index), indexOf(returns the first index of
     * something (-1 if not found)), and charAt(returns the character at a certain index).
     */
    public void setScoreTracker(String word) {
        if (word.length() >= MINIMUM_LENGTH) {
            this.scoreTracker += word.length() - (MINIMUM_LENGTH - 1);
        }
    }
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


