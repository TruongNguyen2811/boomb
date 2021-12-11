
package main;

import main.Map;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static main.Map.SIZE;

public class monster extends Characters{
    private int x;
    private int y;
    private int orient;
    private int speed = 3;
    private int timeMove;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    private boolean isMonsterRun = false;
     private int imageIndex = 0;
    
    public final Image[] IMAGES_MONSTER = {
            new ImageIcon(getClass().getResource("/images/covid.png")).getImage(),
    };
    public monster(int x, int y, int orient, int timeMove) {
        super(x,y,orient,timeMove);
        this.x = x;
        this.y = y;
        this.timeMove = timeMove;
        this.orient = orient;
    }
    public monster(int x,int y){
        super(x,y);
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics2D g2d) {
        g2d.drawImage(IMAGES_MONSTER[0], x, y, SIZE, SIZE , null);
    }
    public void changeOrient(int newOrient) {
        orient = newOrient;
        isMonsterRun = true;
    }
    public boolean isIrun() {
        return isMonsterRun;
    }

     public void move(ArrayList<Grass>arrGrass,ArrayList<wall>arrWall,ArrayList<Bom> arrBom,int t) {
        if (t % timeMove != 0) {
            return;
        }
        int xRaw = x;
        int yRaw = y;

         if(orient==LEFT){
             if(xRaw==0){
                 orient=RIGHT;
                 xRaw+=speed;
             }
             else xRaw-=speed;
         }
         if(orient==RIGHT){
             if(xRaw==675){
                 orient=LEFT;
                 xRaw-=speed;
             }
             else xRaw+=speed;
         }
         if(orient==DOWN){
             if(yRaw==630){
                 orient=UP;
                 yRaw-=speed;
             }
             else yRaw+=speed;
         }
         if(orient==UP){
             if(yRaw==0){
                 orient=DOWN;
                 yRaw+=speed;
             }
             else yRaw-=speed;
         }
        int xRaw1 = x;
        int yRaw1 = y;
        x = xRaw;
        y = yRaw;
        boolean checkMovePlayer= checkMoveMap(arrGrass,arrWall,arrBom);
        if (checkMovePlayer==true ){
            x=xRaw1;
            y=yRaw1;
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y , SIZE-5, SIZE-5 );
        return rectangle;
    }

    public boolean checkMoveMap(ArrayList<Grass> arrGrass,ArrayList<wall>arrWall,ArrayList<Bom> arrBom) {
        for (Grass grass : arrGrass) {

                Rectangle rectangle = getRect().intersection(grass.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }

        }
        for (wall wall : arrWall) {

                Rectangle rectangle = getRect().intersection(wall.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        for (Bom bom : arrBom) {

                Rectangle rectangle = getRect().intersection(bom.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        
        return false;
    }
    public boolean checkdietoBomber(bomber bomber) {
        Rectangle rectangle = getRect().intersection(bomber.getRect());
        if (rectangle.isEmpty() == false) {
            return true;
        }
        return false;

    }
     
}
