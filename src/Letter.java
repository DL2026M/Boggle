import javax.swing.*;
import java.awt.*;

public class Letter {
    /** #5: Can write a class containing instance variables, constructors, and methods, using access modifiers
     * (private vs public) appropriately.
     * An example of this content standard is my Letter class. I have instance variables (that are all private) that
     * tell each letter their (x,y) cords, position on the grid, their specific image, if they have been visited, their
     * name, and the ascii value associated with that letter. I have a constructor that takes their name as a char
     *(an if statement to see check for qu). In addition, I have a bunch of getter and setters for all of these instance
     * variables. All the getters and setters are public as they need to be accessed from other classes and the
     * instance variables are private because they are just used in the letter class.
     */
   private Image image;
   private int x;
   private int y;
   private int gridX;
   private int gridY;
   private String name;
   private boolean isVisted;
   private int asciiValue;


   public Letter(char name) {
       this.asciiValue = (int) (name);
       if (name != 'q') {
           this.name = name + "";
       } else {
           this.name = "qu";
       }
       isVisted = false;
       int i = name - 'a';
       image = new ImageIcon("Resources/Letters/letter" + i + ".png").getImage();
   }
    /** #7: Can write methods, including accessors (getters) and mutators (setters), with correct return types.
     * All the getters in the Letter class have their correct data type as a return type and the correct parameters
     * (which is nothing for getters). All the setters in the Letter have a void return type and take in the correct
     * data type (to then be set!). For example, the getter for the image instance variable doesn’t take in anything
     * and returns an image while the setter for the setter for the image takes in an image and sets the current image
     * to the new image.
     */
    // Getters & setter methods
   public Image getImage() {
        return image;
   }
   public void setImage(Image image) {
        this.image = image;
   }
   public int getX() {
        return x;
   }

   public int getY() {
        return y;
   }
   public void setX(int x) {
        this.x = x;
   }

   public void setY(int y) {
        this.y = y;
   }

   public String getName() {
        return name;
   }

   public void setName(String name) {
        this.name = name;
   }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public boolean getIsVisted() {
        return isVisted;
    }

    public void setVisted(boolean visted) {
        isVisted = visted;
    }

    public int getAsciiValue() {
        return asciiValue;
    }
}

