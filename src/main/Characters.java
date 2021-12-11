package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.Map.SIZE;

public abstract class Characters {
    private int x;
    private int y;
    private int orient;
    private int speed = 3;
    private int timeMove;
    private boolean isRun = false;
    private int imageIndex = 0;

    public Characters(int x, int y, int orient, int timeMove) {
        this.x = x;
        this.y = y;
        this.timeMove = timeMove;
        this.orient = orient;
    }
    public Characters(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void changeOrient(int newOrient) {
        orient = newOrient;
        isRun = true;
    }
    public boolean isIrun() {
        return isRun;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y , SIZE-5, SIZE-5 );
        return rectangle;
    }


}
