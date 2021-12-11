package main;
import javax.swing.*;
import java.awt.*;

public class wall extends Map {
    private int x;
    private int y;


    public wall(int x, int y) {
        super(x,y);
        this.x = x;
        this.y = y;

    }

    private Image image;
    public final Image[] IMAGE_GRASS={
            new ImageIcon(getClass().getResource("/images/da1.png")).getImage(),

    };
    public void draw(Graphics2D g2d){
        image = IMAGE_GRASS[0];
        g2d.drawImage(image,x,y,SIZE,SIZE,null);
    }



}
