import java.awt.*;

public class Letter {

   private Image image;
   private int x;
   private int y;
   private String name;

    // Getters & setter methods
   public Letter(Image image, char name) {
       this.image = image;
       if (name != 'q') {
           this.name = name + "";
       }
       else {
           this.name = "qu";
       }
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
}

