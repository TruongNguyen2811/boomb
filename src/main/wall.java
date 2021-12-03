package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static main.Map.SIZE;

public class wall  {
    private int x;
    private int y;
    private int checkWall;
    public wall(int x, int y, int checkWall) {
        this.x = x;
        this.y = y;
        this.checkWall=checkWall;
    }

    public int getCheckWall() {
        return checkWall;
    }

    private Image image;
    public final Image[] IMAGE_GRASS={
            new ImageIcon(getClass().getResource("/images/da1.png")).getImage(),

    };

    /*public wall(int x, int y, int checkWall) {
        super(x, y, checkWall);
    }*/

    public void draw(Graphics2D g2d){
        image = IMAGE_GRASS[0];
        g2d.drawImage(image,x,y,SIZE,SIZE,null);
    }

    public Rectangle getRect(){
        Rectangle rectangle= new Rectangle(x,y,SIZE-10,SIZE-10);
        return  rectangle;
    }

}
