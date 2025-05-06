import java.awt.*;

public class Letter {

   private Image image;
   private int x;
   private int y;
   private int gridX;
   private int gridY;
   private String name;
   private boolean isVisted;

    // Getters & setter methods
   public Letter(Image image, char name) {
       this.image = image;
       if (name != 'q') {
           this.name = name + "";
       }
       else {
           this.name = "qu";
       }
       isVisted = false;
   }
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
}

