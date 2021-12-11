package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
//import main.sound;
import javax.sound.sampled.Clip;
import static main.newFrame.ScreenHeight;
import static main.newFrame.ScreenWidth;
import static main.Map.SIZE;


public class RunGame {
    private bomber player;
    private ArrayList<monster> arrayMonster;
    private ArrayList<Grass> arrGrass;
    private ArrayList<power> arrayPower;
    private ArrayList<Tangtoc> arrayBoots;
    private ArrayList<wall> arrWall;
    private ArrayList<Bom> arrBoom;
    private ArrayList<BomNo> bomNos ;
    private ArrayList<boss> arrayBoss;
    public static final int TIME_BANG=120;
    public static final int TIME_WAVE=15;
    private boolean checkDieWin;
    private Random random=new Random();
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeWave;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/images/snow.jpg")).getImage(),
            new ImageIcon(getClass().getResource("/images/background.jpg")).getImage(),
    };

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
        arrayBoss=new ArrayList<>();
        player=new bomber(0,ScreenHeight-90-SIZE, bomber.DOWN,1);
        CreateGrass();
        CreateBoots(arrGrass);
        CreatePower(arrGrass);
        Createwall();
        CreateMonster(numberMonster,arrGrass,arrWall);
        //createMonstercon();
        Clip clip1 =sound.getSound(getClass().getResource("/sound/play.wav"));
        clip1.start();

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
                int xRaw = arrGrass.get(position).getX();
                int yRaw = arrGrass.get(position).getY();
                Tangtoc Boots = new Tangtoc(xRaw, yRaw);
                arrayBoots.add(Boots);
            }
    }
    public void  CreateMonster(int numberMonster,ArrayList<Grass> arrGrass,ArrayList<wall> arrWall){
        for (int i=0;i<2+numberMonster;i++) {
            boolean trung = true;
            while (trung) {
                int c=0;
                Random rand = new Random();
                monster monster = new monster((rand.nextInt(15)+1) * SIZE, (rand.nextInt(15)) * SIZE, rand.nextInt(3), 1);
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
                }
                else {
                    arrayMonster.remove(i);
                }
            }
        }

    }
    public void CreatePower(ArrayList<Grass> arrGrass){
        Random rand=new Random();
        int max;
        max=rand.nextInt(4)+1;
        for (int i = 1; i <=max ; i++) {
            int position = rand.nextInt(10) + 1;
                int xRaw = arrGrass.get(position).getX();
                int yRaw = arrGrass.get(position).getY();
                power power = new power(xRaw, yRaw);
                arrayPower.add(power);
        }
    }
    public void CreateBommNo(int t){
        for (int i = 0; i < arrBoom.size(); i++) {
            if (t - timeBoom.get(i) >= TIME_BANG) {
                BomNo bomNo= arrBoom.get(i).bomNo();
                arrBoom.remove(i);
                bomNos.add(bomNo);
                Clip clip = sound.getSound(getClass().getResource("/sound/explosion.wav"));
                clip.start();
                timeBoom.remove(i);
                try {
                } catch (IndexOutOfBoundsException e) {
                }
                timeWave.add(t);
            }
        }
        for (int i=0;i<bomNos.size();i++){
            bomNos.get(i).checkBoomtoMonster(arrayMonster);
            bomNos.get(i).checkBoomtoBoss(arrayMonster,arrayBoss);

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
        Clip clip = sound.getSound(getClass().getResource("/sound/set_boom.wav"));
        clip.start();
        timeBoom.add(t);
    }
    public boolean checkwin() {
        if(arrayMonster.isEmpty()==true&&arrayBoss.isEmpty()==true){
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
        //Random rand = new Random();
        //int r= rand.nextInt(2);
        //if(r==0){
        g2d.drawImage(MY_IMAGE[0],0,0,ScreenWidth,ScreenHeight,null);//}
        //else if(r==1){
        //g2d.drawImage(MY_IMAGE[1],0,0,ScreenWidth,ScreenHeight,null);}
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
            for (boss boss: arrayBoss){
                boss.draw(g2d);
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
        for(int i=0;i<arrayBoss.size();i++){
            arrayBoss.get(i).changeOrient(newOrient);

            arrayBoss.get(i).move(arrGrass,arrWall,arrBoom,1);
            if(newOrient== boss.LEFT){
                newOrient = boss.DOWN;
            }
            else if(newOrient== boss.DOWN){
                newOrient = boss.RIGHT;
            }
            else if(newOrient== boss.RIGHT){
                newOrient = boss.UP;
            }
            else if(newOrient== boss.UP){
                newOrient = boss.LEFT;
            }
        }

    }
    public void createMonstercon(int number){
        //if(arrayBoss.isEmpty()==true){
        for (int i=0;i<number;i++) {
            Clip clip1 =sound.getSound(getClass().getResource("/sound/creMonster.wav"));
            clip1.start();
            boolean trung = true;
            while (trung) {
                int c = 0;
                Random rand = new Random();
                boss boss = new boss((rand.nextInt(15)+1) * SIZE, (rand.nextInt(15)) * SIZE, rand.nextInt(3), 1);
                arrayBoss.add(boss);
                for (int j = 0; j < arrGrass.size(); j++) {
                    Rectangle rectangle = boss.getRect().intersection(arrGrass.get(j).getRect());
                    if (rectangle.isEmpty() == false) {
                        c++;
                    }
                }
                for (int j = 0; j < arrWall.size(); j++) {
                    Rectangle rectangle = boss.getRect().intersection(arrWall.get(j).getRect());
                    if (rectangle.isEmpty() == false) {
                        c++;
                    }
                }
                if (c == 0) {
                    trung = false;
                } else {
                    arrayBoss.remove(i);
                }
            }
        }
    }
    public boolean checkdie(){
        for (int i=0;i<bomNos.size();i++){
            if(bomNos.get(i).checkBoomtoBomber(player)==true) {
                Clip clip1 =sound.getSound(getClass().getResource("/sound/over.wav"));
                clip1.start();
                return false;
            }
        }
        for (int i=0;i<arrayMonster.size();i++){
            if(arrayMonster.get(i).checkdietoBomber(player)==true){
                Clip clip1 =sound.getSound(getClass().getResource("/sound/over.wav"));
                clip1.start();
                return false;
            }
        }
        for (int i=0;i<arrayBoss.size();i++){
            if(arrayBoss.get(i).checkdietoBomber(player)==true){
                Clip clip1 =sound.getSound(getClass().getResource("/sound/over.wav"));
                clip1.start();
                return false;
            }
        }
        if(arrayMonster.isEmpty()==true&&arrayBoss.isEmpty()==true){
            return false;
        }
        //if(arrayBoss.isEmpty()==true){
           // return false;
        //}
        return true;
    }
    
}
 



