package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static main.Map.SIZE;


public class Grass extends Map{
    //private ArrayList<Grass> arrGrass;
    private int x;
    private int y;
    private int checkGrass;

    private Image image;
    public final Image[] IMAGE_GRASS={
            new ImageIcon(getClass().getResource("/images/cay1.png")).getImage(),

    };

    public Grass(int x, int y) {
        super(x,y);
        this.x = x;
        this.y = y;

    }

    @Override
    public int getX() {
        return x;
    }

    public int getCheckGrass() {
        return checkGrass;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }



    public void draw(Graphics2D g2d){
        image = IMAGE_GRASS[0];
        g2d.drawImage(image,x,y,SIZE,SIZE,null);
    }

    @Override
    public Rectangle getRect(){
        Rectangle rectangle= new Rectangle(x,y,SIZE-10,SIZE-10);
        return  rectangle;
    }



}
