// Author, David Lutch

import java.util.ArrayList;
import java.util.Dictionary;

public class Game {
    private boolean gameOver;
    private int pointValue;
    private GameViewer window;
    private ArrayList<String> words;
    // private ArrayList<Words> Words;

//    public boolean isGameOver() {
//
//    }
//    public int pointValueWord() {
//
//    }
//    public boolean isValidWord() {
//
//    }


    public static void main(String[] args) {
        Game newGame = new Game();
    }

    public Game() {
        this.window = new GameViewer(this);
    }
}
