package main;

import javax.swing.*;
import java.awt.*;


import static main.Map.SIZE;

public class Bom {
    private int x;
    private int y;
    public int checkBoom;
    private Image image;
    private int boomdai;
    public final Image[] IMAGE_BOOM={
            new ImageIcon(getClass().getResource("/images/Boom01.png")).getImage(),

    };
    public final Image[] IMAGE_NO={
            new ImageIcon(getClass().getResource("/images/boomno.png")).getImage(),
    };

    public Bom(int x, int y,int boomdai) {
        this.x = x-20;
        this.y = y;
        this.boomdai=boomdai;
        this.checkBoom=1;
        this.image=IMAGE_BOOM[0];
        // boomBang();
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

    public int isCheckBoom() {
        return checkBoom;
    }

    public void setCheckBoom(int checkBoom) {
        this.checkBoom = checkBoom;
    }

    public void draw(Graphics2D g2d){
        image = IMAGE_BOOM[0];
        g2d.drawImage(image,x,y,SIZE,SIZE,null);
    }

    public Rectangle getRect(){
        Rectangle rectangle= new Rectangle(x+15,y+15,SIZE-10,SIZE-10);
        return  rectangle;
    }

    public BomNo bomNo(){
        int xRaw= x;
        int yRaw= y;
        int daino=this.boomdai;
        BomNo bomNo = new BomNo(xRaw,yRaw,daino);
        return bomNo;
    }
}
