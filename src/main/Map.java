package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Map {

    private int x;
    private int y;
    public int bit;
    public static final int SIZE = 46;
    public final Image[] MY_IMAGE = {
            new ImageIcon(getClass().getResource("/images/background.jpg")).getImage(),

    };

    public Map(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
    }

    public int getX() {
        return x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y + 15, SIZE - 10, SIZE - 10);
        return rectangle;
    }

    /*public void draw(Graphics2D g2d) {
        if (bit != 0) {
            g2d.drawImage(MY_IMAGE[0], x, y, SIZE + 2, SIZE + 2, null);


        }

    }*/
}
