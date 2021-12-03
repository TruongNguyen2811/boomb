package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import static main.newFrame.ScreenHeight;
import static main.newFrame.ScreenWidth;
import static main.Map.SIZE;


public class RunGame {
    private Grass grass;
    private bomber player;
    private ArrayList<Grass> arrGrass;
    private ArrayList<wall> arrWall;

    public static final int TIME_BANG=120;
    public static final int TIME_WAVE=15;
    private int timeDie;
    private boolean checkDieWin;
    private Random random=new Random();
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeWave;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/images/background.jpg")).getImage()
    };

    public boolean isCheckDieWin() {
        return checkDieWin;
    }

    public void setCheckDieWin(boolean checkDieWin) {
        this.checkDieWin = checkDieWin;
    }

    public void initGame(){
        arrGrass= new ArrayList<>();
        arrWall=new ArrayList<>();
        player=new bomber(0,ScreenHeight-90-SIZE, bomber.DOWN,1);
        //CreatePlayer();
        CreateGrass();
        Createwall();

    }
    public void  CreateGrass(){
        for (int i=0;i<80;i++){
            Random rand = new Random();
            Grass grass=new Grass((rand.nextInt(15)+1)*SIZE ,(rand.nextInt(15))*SIZE,1);
            arrGrass.add(grass);
        }
    }
    public void  Createwall(){
        for (int i=0;i<20;i++){
            Random rand = new Random();
            wall wall=new wall((rand.nextInt(16))*SIZE ,(rand.nextInt(15))*SIZE,2);
            arrWall.add(wall);
        }
    }
    /*public void CreatePlayer(){
        Grass point= arrGrass.get(random.nextInt(50));
        while (point.getCheckGrass()!=0) {
            point= arrGrass.get(random.nextInt(50));
        }
        player=new bomber(point.getX()*SIZE,point.getY()*SIZE, bomber.DOWN,1);

    }*/


    public void movePlayer(int newOrient){
        player.changeOrient(newOrient);
        player.move(arrGrass,arrWall,1);
        //player.moveItem(arrItem);
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(MY_IMAGE[0],0,0,ScreenWidth,ScreenHeight,null);
        try {
            for (Grass grass : arrGrass) {
                grass.draw(g2d);
            }
            for (wall wall : arrWall) {
                wall.draw(g2d);
            }
            player.draw(g2d);


        }catch (ConcurrentModificationException e){

        }
    }

}
