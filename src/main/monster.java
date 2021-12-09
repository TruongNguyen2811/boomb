
package main;

import main.Map;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static main.Map.SIZE;

import static main.newFrame.ScreenHeight;
import static main.newFrame.ScreenWidth;
import static main.bomber.DOWN;
import static main.bomber.LEFT;
import static main.bomber.RIGHT;
import static main.bomber.UP;

public class monster {
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
    
    public final Image[] IMAGES_MONSTER_LEFT = {
            new ImageIcon(getClass().getResource("/images/covid.png")).getImage(),

    };
    public final Image[] IMAGES_MONSTER_RIGHT = {
            new ImageIcon(getClass().getResource("/images/covid.png")).getImage(),

    };
    public final Image[] IMAGES_MONSTER_UP = {
            new ImageIcon(getClass().getResource("/images/covid.png")).getImage(),

    };
    public final Image[] IMAGES_MONSTER_DOWN = {
            new ImageIcon(getClass().getResource("/images/covid.png")).getImage(),

    };
    public monster(int x, int y, int orient, int timeMove) {
        this.x = x;
        this.y = y;
        this.timeMove = timeMove;
        this.orient = orient;
    }
    public monster(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void changeOrient(int newOrient) {
        orient = newOrient;
        isMonsterRun = true;
    }
    public boolean isIrun() {
        return isMonsterRun;
    }
    public void draw(Graphics2D g2d) {
        switch (orient) {
            case LEFT: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_MONSTER_LEFT[0], x, y, SIZE, SIZE , null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_MONSTER_LEFT[0], x, y, SIZE, SIZE , null);
                }
                break;
            }
            case RIGHT: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_MONSTER_RIGHT[0], x, y, SIZE, SIZE , null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_MONSTER_RIGHT[0], x, y, SIZE, SIZE, null);
                }
                break;
            }
            case UP: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_MONSTER_UP[0], x, y, SIZE, SIZE, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_MONSTER_UP[0], x, y, SIZE, SIZE , null);
                }
                break;
            }
            case DOWN: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_MONSTER_DOWN[0], x, y, SIZE, SIZE , null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_MONSTER_DOWN[0], x, y, SIZE, SIZE , null);
                }
            }
            break;
        }
        isMonsterRun = false;

    }
     public void move(ArrayList<Grass>arrGrass,ArrayList<wall>arrWall,ArrayList<Bom> arrBom,int t) {
        if (t % timeMove != 0) {
            return;
        }
        int xRaw = x;
        int yRaw = y;
        /*switch (orient) {
            case LEFT:
                xRaw -= speed;
                break;
            case RIGHT:
                xRaw += speed;
                break;
            case UP:
                yRaw -= speed;
                break;
            case DOWN:
                yRaw += speed;
            default:
        }*/
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
