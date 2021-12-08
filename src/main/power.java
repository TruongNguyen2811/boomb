package main;

import javax.swing.*;
import java.awt.*;

import static main.Map.SIZE;

public class power extends Items{
    private int x;
    private int y;

    private Image image;
    public final Image[] IMAGE_POWER={
            new ImageIcon(getClass().getResource("/images/power.png")).getImage(),

    };

    public power(int x, int y) {
        super(x,y);
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



    public void draw(Graphics2D g2d){
        image = IMAGE_POWER[0];
        g2d.drawImage(image,x,y,SIZE-20,SIZE-20,null);
    }

    public Rectangle getRect(){
        Rectangle rectangle= new Rectangle(x,y,SIZE-10,SIZE-10);
        return  rectangle;
    }

}

