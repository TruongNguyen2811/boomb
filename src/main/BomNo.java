package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static main.Map.SIZE;

public class BomNo {
    private int x;
    private int y;
    //private int daibom;
    private int daitren;
    private Image image;
    public final Image[] IMAGE_NO={
            new ImageIcon(getClass().getResource("/images/bombbang_mid_2.png")).getImage(),
    };
    public BomNo(int x, int y,int daibom) {
        this.x = x;
        this.y = y;
        //this.daibom=daibom;
        this.daitren=daibom;

        this.image=IMAGE_NO[0];
    }
    public Rectangle getRect(int x,int y){
        Rectangle rectangle= new Rectangle(x,y,SIZE-10,SIZE-10);
        return  rectangle;
    }
    public void drawGiua(Graphics2D g2d) {
        g2d.drawImage(IMAGE_NO[0], x , y , null);
    }
    public void drawup(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=daitren;i++){
            int pointy=y-i*SIZE;
            int pointx=x;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            /*for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = daitren - (daitren - i);
                }
            }
            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);*/

        }
    }
    public void drawleft(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=daitren;i++){
            int pointy=y;
            int pointx=x-i*SIZE;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = daitren - (daitren - i);
                }
            }

        }
    }
    public void drawdown(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=daitren;i++){
            int pointy=y+i*SIZE;
            int pointx=x;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = daitren - (daitren - i);
                }
            }

        }
    }
    public void drawright(Graphics2D graphics2D,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for(int i=1;i<=daitren;i++){
            int pointy=y;
            int pointx=x+i*SIZE;
            for(int j=0;j<arrGrass.size();j++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrGrass.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = i;
                    arrGrass.remove(j);
                }
            }

            graphics2D.drawImage(IMAGE_NO[0], pointx,pointy,null);
            for(int k=0;k<arrWall.size();k++){
                Rectangle rectangle = getRect(pointx,pointy).intersection(arrWall.get(k).getRect());
                if (rectangle.isEmpty() == false) {
                    daitren = daitren - (daitren - i);
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




}
