package main;

import main.Map;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.newFrame.ScreenHeight;
import static main.newFrame.ScreenWidth;
import static main.Map.SIZE;

public class bomber  {
    private int x;
    private int y;
    private int orient;
    private int soBoom = 2;
    private int speed = 3;
    private int timeMove;
    private int daibom = 1;
    private boolean isPlayerRun = false;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    private int imageIndex = 0;

    public final Image[] IMAGES_PLAYER_LEFT = {
            new ImageIcon(getClass().getResource("/images/player_left_1.png")).getImage(),

    };
    public final Image[] IMAGES_PLAYER_RIGHT = {
            new ImageIcon(getClass().getResource("/images/player_right_1.png")).getImage(),

    };
    public final Image[] IMAGES_PLAYER_UP = {
            new ImageIcon(getClass().getResource("/images/player_up_1.png")).getImage(),

    };
    public final Image[] IMAGES_PLAYER_DOWN = {
            new ImageIcon(getClass().getResource("/images/player_down_1.png")).getImage(),

    };

    public bomber(int x, int y, int orient, int timeMove) {
        this.x = x;
        this.y = y;
        this.timeMove = timeMove;
        this.orient = orient;
    }

    public int getSoBoom() {
        return soBoom;
    }

    public void setSoBoom(int soBoom) {
        this.soBoom = soBoom + 1;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void changeOrient(int newOrient) {
        orient = newOrient;
        isPlayerRun = true;
    }

    public boolean isIrun() {
        return isPlayerRun;
    }

    public void draw(Graphics2D g2d) {
        switch (orient) {
            case LEFT: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_LEFT[0], x, y, SIZE, SIZE + 10, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_LEFT[0], x, y, SIZE, SIZE + 10, null);
                }
                break;
            }
            case RIGHT: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_RIGHT[0], x, y, SIZE, SIZE + 10, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_RIGHT[0], x, y, SIZE, SIZE + 10, null);
                }
                break;
            }
            case UP: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_UP[0], x, y, SIZE, SIZE + 10, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_UP[0], x, y, SIZE, SIZE + 10, null);
                }
                break;
            }
            case DOWN: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_DOWN[0], x, y, SIZE, SIZE + 10, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_DOWN[0], x, y, SIZE, SIZE + 10, null);
                }
            }
            break;
        }
        isPlayerRun = false;

    }

    public void move(ArrayList<Grass>arrGrass,ArrayList<wall>arrWall,int t) {
        if (t % timeMove != 0) {
            return;
        }
        int xRaw = x;
        int yRaw = y;
        switch (orient) {
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
        }
        int xRaw1 = x;
        int yRaw1 = y;
        x = xRaw;
        y = yRaw;
        boolean checkMovePlayer= checkMoveMap(arrGrass,arrWall);
        if (checkMovePlayer==true ){
            x=xRaw1;
            y=yRaw1;
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y , SIZE - 15, SIZE );
        return rectangle;
    }

    public boolean checkMoveMap(ArrayList<Grass> arrGrass,ArrayList<wall>arrWall) {
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
        
        return false;
        }
    
    public void CheckItems(ArrayList<Tangtoc>arrayboots,ArrayList<power>arrayPower){
        int c=0;
        for(int i=0;i<arrayboots.size();i++){
            Rectangle rectangle = getRect().intersection(arrayboots.get(i).getRect());
            if(rectangle.isEmpty() == false) {
                c=1;
                arrayboots.remove(i);

                //setSpeed(getSpeed()+1);
                //break;
            }

        }
        if(c==1){

            speed=speed+1;

        }
        for(int i=0;i<arrayPower.size();i++){
            Rectangle rectangle = getRect().intersection(arrayPower.get(i).getRect());
            if(rectangle.isEmpty() == false) {
                arrayPower.remove(i);
                daibom++;
               // break;
            }
        }
    }
    public Bom datbom (ArrayList<Bom>bom) {
        int x= this.x+SIZE/2;
        int y= this.y+SIZE/2;
        int xBoom= x-x%SIZE+15;
        int yBoom= y-y%SIZE;
        int daiBoom=this.daibom;
        Bom boom = new Bom(xBoom, yBoom,daiBoom);
        return boom;
    }
}

