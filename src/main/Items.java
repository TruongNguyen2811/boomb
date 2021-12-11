package main;

import javax.swing.*;
import java.awt.*;

import static main.Map.SIZE;

public abstract class  Items {
    private int x;
    private int y;
    public Items(int x, int y) {
        this.x = x;
        this.y = y;

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



    public Rectangle getRect(){
        Rectangle rectangle= new Rectangle(x,y,SIZE-10,SIZE-10);
        return  rectangle;
    }
}
