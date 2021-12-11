package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static main.Map.SIZE;

public class Tangtoc extends Items{
    private int x;
    private int y;

    private Image image;
    public final Image[] IMAGE_Boots={
            new ImageIcon(getClass().getResource("/images/Boots.png")).getImage(),

    };

    public Tangtoc(int x, int y) {
        super(x,y);
        this.x = x;
        this.y = y;
        //this.image=IMAGE_Boots[0];

    }

    public int getX() {
        return x;
    }



    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    public void draw(Graphics2D g2d){
        image = IMAGE_Boots[0];
        g2d.drawImage(image,x,y,SIZE-20,SIZE-20,null);
    }
    
}
