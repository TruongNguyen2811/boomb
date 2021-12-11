package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


import static main.Map.SIZE;
import static main.newFrame.ScreenHeight;

public class BomNo {
    private int x;
    private int y;
    //private int daibom;
    private int tren;
    private int duoi;
    private int phai;
    private int trai;
    private Image image;
    public final Image[] IMAGE_NO={
            new ImageIcon(getClass().getResource("/images/bombbang_mid_2.png")).getImage(),
    };
    public BomNo(int x, int y,int daibom) {
        this.x = x;
        this.y = y;
        //this.daibom=daibom;
        this.tren=daibom;
        this.duoi=daibom;
        this.trai=daibom;
        this.phai=daibom;

        //this.image=IMAGE_NO[0];
    }
    public Rectangle getRect(int x,int y){
        Rectangle rectangle= new Rectangle(x,y,SIZE-10,SIZE-10);
        return  rectangle;
    }
    public void drawGiua(Graphics2D g2d) {
        g2d.drawImage(IMAGE_NO[0], x , y , null);
    }
    public void drawup(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=tren;i++){
            int pointy=y-i*SIZE;
            int pointx=x;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    tren = i;
                    arrGrass.remove(j);
                }
            }

            //graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    tren = tren - (tren - i);
                }
            }
            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);

        }
    }
    public void drawleft(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=trai;i++){
            int pointy=y;
            int pointx=x-i*SIZE;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    trai = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    trai = i;
                }
            }

        }
    }
    public void drawdown(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=duoi;i++){
            int pointy=y+i*SIZE;
            int pointx=x;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    duoi = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    duoi=i;
                }
            }

        }
    }
    public void drawright(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=phai;i++){
            int pointy=y;
            int pointx=x+i*SIZE;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    phai = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    phai=i;
                }
            }

        }
    }
    public void draw(Graphics2D g2d,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        drawGiua(g2d);
        drawup(g2d, arrGrass,arrWall);
        drawleft(g2d, arrGrass,arrWall);
        drawdown(g2d, arrGrass,arrWall);
        drawright(g2d, arrGrass,arrWall);
    }
    public boolean checkBoomtoBomber(bomber bomber) {

        for (int j = 1; j <= trai; j++) {
            int xRaw = x - j * SIZE;
            int yRaw = y;
            Rectangle rectangle = getRect(xRaw, yRaw).intersection(bomber.getRect());
            if (rectangle.isEmpty() == false) {
                return true;
            }
        }
        for (int j = 1; j <= phai; j++) {
            int xRaw = x + j * SIZE;
            int yRaw = y;
            Rectangle rectangle = getRect(xRaw, yRaw).intersection(bomber.getRect());
            if (rectangle.isEmpty() == false) {
                return true;
            }
        }
        for (int j = 1; j <= tren; j++) {
            int xRaw = x ;
            int yRaw = y- j * SIZE;
            Rectangle rectangle = getRect(xRaw, yRaw).intersection(bomber.getRect());
            if (rectangle.isEmpty() == false) {
                return true;
            }
        }
        for (int j = 1; j <= duoi; j++) {
            int xRaw = x ;
            int yRaw = y+ j * SIZE;
            Rectangle rectangle = getRect(xRaw, yRaw).intersection(bomber.getRect());
            if (rectangle.isEmpty() == false) {
                return true;
            }
        }
        return false;

    }
    public void checkBoomtoMonster (ArrayList<monster> monster) {
        for (int i = 0; i < monster.size(); i++) {
            try {
                for (int j = 1; j <= trai; j++) {
                    int xRaw = x - j * SIZE;
                    int yRaw = y;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(monster.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        monster.remove(i);
                    }
                }
                for (int j = 1; j <= phai; j++) {
                    int xRaw = x + j * SIZE;
                    int yRaw = y;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(monster.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        monster.remove(i);
                    }
                }
                for (int j = 1; j <= tren; j++) {
                    int xRaw = x;
                    int yRaw = y - j * SIZE;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(monster.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        monster.remove(i);
                    }
                }
                for (int j = 1; j <= duoi; j++) {
                    int xRaw = x;
                    int yRaw = y + j * SIZE;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(monster.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        monster.remove(i);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }
    public void checkBoomtoBoss (ArrayList<monster> monster,ArrayList<boss> boss) {
        for (int i = 0; i < boss.size(); i++) {
            try {
                for (int j = 1; j <= trai; j++) {
                    int xRaw = x - j * SIZE;
                    int yRaw = y;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(boss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        boss.remove(i);

                    }
                }
                for (int j = 1; j <= phai; j++) {
                    int xRaw = x + j * SIZE;
                    int yRaw = y;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(boss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        boss.remove(i);

                    }
                }
                for (int j = 1; j <= tren; j++) {
                    int xRaw = x;
                    int yRaw = y - j * SIZE;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(boss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        boss.remove(i);


                    }
                }
                for (int j = 1; j <= duoi; j++) {
                    int xRaw = x;
                    int yRaw = y + j * SIZE;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(boss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        boss.remove(i);

                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }





}
