package main;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import static main.newFrame.ScreenHeight;
import static main.newFrame.ScreenWidth;
import static main.Map.SIZE;


public class RunGame {
    private bomber player;
    private monster monster1;
 
    private ArrayList<monster> arrayMonster;
    private ArrayList<Grass> arrGrass;
    private ArrayList<power> arrayPower;
    private ArrayList<Tangtoc> arrayBoots;
    private ArrayList<wall> arrWall;
    private ArrayList<Bom> arrBoom;
    private ArrayList<BomNo> bomNos ;

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

    public void initGame(int numberMonster){
        timeBoom=new ArrayList<>();
        arrGrass= new ArrayList<>();
        arrWall=new ArrayList<>();
        arrBoom=new ArrayList<>();
        bomNos=new ArrayList<>();
        timeWave=new ArrayList<>();
        arrayBoots=new ArrayList<>();
        arrayPower=new ArrayList<>();
        arrayMonster=new ArrayList<>();
        player=new bomber(0,ScreenHeight-90-SIZE, bomber.DOWN,1);
        //monster1 = new monster (90,540, monster.DOWN,1);
        //CreatePlayer();
        CreateGrass();
        CreateBoots(arrGrass);
        CreatePower(arrGrass);
        Createwall();
        CreateMonster(numberMonster,arrGrass,arrWall);
        //checkdeadmonster();

    }
    public void  CreateGrass(){
        for (int i=0;i<80;i++){
            Random rand = new Random();
            Grass grass=new Grass((rand.nextInt(15)+1)*SIZE ,(rand.nextInt(15))*SIZE);
            arrGrass.add(grass);
        }
    }
    public void  Createwall(){
        for (int i=0;i<20;i++){
            Random rand = new Random();
            wall wall=new wall((rand.nextInt(15)+1)*SIZE ,(rand.nextInt(15))*SIZE);
            arrWall.add(wall);
        }
    }
    public void CreateBoots(ArrayList<Grass> arrGrass){
        Random rand=new Random();
        int max;
        max=rand.nextInt(4)+1;
        for (int i = 1; i <=max ; i++) {
            int position = rand.nextInt(80) ;
            //for (int j = 0; j < arrGrass.size(); j++) {
                int xRaw = arrGrass.get(position).getX();
                int yRaw = arrGrass.get(position).getY();
                Tangtoc Boots = new Tangtoc(xRaw, yRaw);
                arrayBoots.add(Boots);
                //}
            }
    }
    public void  CreateMonster(int numberMonster,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for (int i=0;i<2+numberMonster;i++) {
            boolean trung = true;
            //int c=0;
            while (trung) {
                int c=0;
                Random rand = new Random();
                monster monster = new monster((rand.nextInt(16)) * SIZE, (rand.nextInt(15)) * SIZE, rand.nextInt(3), 1);
                arrayMonster.add(monster);
                for(int j=0;j<arrGrass.size();j++){
                    Rectangle rectangle = monster.getRect().intersection(arrGrass.get(j).getRect());
                    if(rectangle.isEmpty() == false){
                        c++;
                    }
                }
                for(int j=0;j<arrWall.size();j++){
                    Rectangle rectangle = monster.getRect().intersection(arrWall.get(j).getRect());
                    if(rectangle.isEmpty() == false){
                        c++;
                    }
                }
                if(c==0){
                    trung = false;
                    //arrayMonster.add(monster);
                }
                else {
                    //trung=true;
                    arrayMonster.remove(i);
                }


                //arrayMonster.add(monster);
            }
        }
        /*for (int i=0;i<2+numberMonster;i++) {
            Random rand = new Random();
            monster monster = new monster((rand.nextInt(16)) * SIZE, (rand.nextInt(15)) * SIZE, rand.nextInt(3), 1);
            arrayMonster.add(monster);
        }*/
        //checkdeadmonster();
    }
    public void CreatePower(ArrayList<Grass> arrGrass){
        Random rand=new Random();
        int max;
        max=rand.nextInt(4)+1;
        for (int i = 1; i <=max ; i++) {
            int position = rand.nextInt(10) + 1;
            //for (int j = 0; j < arrGrass.size(); j++) {
                int xRaw = arrGrass.get(position).getX();
                int yRaw = arrGrass.get(position).getY();
                power power = new power(xRaw, yRaw);
                arrayPower.add(power);
            //}
        }
    }
    /*public void CreatePlayer(){
        Grass point= arrGrass.get(random.nextInt(50));
        while (point.getCheckGrass()!=0) {
            point= arrGrass.get(random.nextInt(50));
        }
        player=new bomber(point.getX()*SIZE,point.getY()*SIZE, bomber.DOWN,1);

    }*/
    public void CreateBommNo(int t){
        for (int i = 0; i < arrBoom.size(); i++) {
            if (t - timeBoom.get(i) >= TIME_BANG) {
                BomNo bomNo= arrBoom.get(i).bomNo();
                arrBoom.remove(i);
                bomNos.add(bomNo);
                timeBoom.remove(i);
                try {
                    //bomNo.checkBoomToBoom(arrBoom, timeBoom);
                } catch (IndexOutOfBoundsException e) {
                }
                timeWave.add(t);
            }
        }
        for (int i=0;i<bomNos.size();i++){
            bomNos.get(i).checkBoomtoMonster(arrayMonster);
            if (t-timeWave.get(i)>=TIME_WAVE){
                bomNos.remove(i);
                timeWave.remove(i);
            }
        }
    }
    /*public void checkdeadmonster(){
        try{
            for(int i=0;i<bomNos.size();i++){
                bomNos.get(i).checkBoomtoMonster(arrayMonster);
            }
        }
        catch (IndexOutOfBoundsException e) {
        }
    }*/
    public void createboom(int t) {
        Bom boom= player.datbom(arrBoom);
        arrBoom.add(boom);
        timeBoom.add(t);
    }
    public boolean checkwin() {
        if(arrayMonster.isEmpty()==true){
            return true;
        }
        return false;
    }


    public void movePlayer(int newOrient){
        player.changeOrient(newOrient);
        player.move(arrGrass,arrWall,1);
        player.CheckItems(arrayBoots,arrayPower);

    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(MY_IMAGE[0],0,0,ScreenWidth,ScreenHeight,null);
        try {
            for (Tangtoc boots: arrayBoots) {
                boots.draw(g2d);
            }
            for (power power:arrayPower) {
                power.draw(g2d);
            }
            for (Grass grass : arrGrass) {
                grass.draw(g2d);
            }
            for (wall wall : arrWall) {
                wall.draw(g2d);
            }
            for (Bom boom :arrBoom){
                boom.draw(g2d);
            }
            for (BomNo bomNo:bomNos){
                bomNo.draw(g2d, arrGrass, arrWall);
            }
            for( monster monster:arrayMonster){
                monster.draw(g2d);
            }
            player.draw(g2d);
            //monster1.draw(g2d);

        }catch (ConcurrentModificationException e){

        }
    }

    public void moveMonster(int newOrient){
        for(int i=0;i<arrayMonster.size();i++){
            arrayMonster.get(i).changeOrient(newOrient);
            
            arrayMonster.get(i).move(arrGrass,arrWall,arrBoom,1);
            if(newOrient== monster.LEFT){
                newOrient = monster.DOWN;
            }
            else if(newOrient== monster.DOWN){
                newOrient = monster.RIGHT;
            }
            else if(newOrient== monster.RIGHT){
                newOrient = monster.UP;
            }
            else if(newOrient== monster.UP){
                newOrient = monster.LEFT;
            }
        }

    }
    public boolean checkdie(){
        for (int i=0;i<bomNos.size();i++){
            if(bomNos.get(i).checkBoomtoBomber(player)==true) {
                return false;
            }
        }
        for (int i=0;i<arrayMonster.size();i++){
            if(arrayMonster.get(i).checkdietoBomber(player)==true){
                return false;
            }
        }
        if(arrayMonster.isEmpty()==true){
            return false;
        }
        return true;
    }
    
}
 



