package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public abstract class Map {

    private int x;
    private int y;
    public int bit;
    public static final int SIZE = 45;


    public Map(int x, int y) {
        this.x = x;
        this.y = y;

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
        Rectangle rectangle = new Rectangle(x, y , SIZE - 10, SIZE - 10);
        return rectangle;
    }

    /*public void draw(Graphics2D g2d) {
        if (bit != 0) {
            g2d.drawImage(MY_IMAGE[0], x, y, SIZE + 2, SIZE + 2, null);


        }

    }*/
}
