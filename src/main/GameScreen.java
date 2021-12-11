package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import java.util.Random;
import javax.sound.sampled.Clip;

import static main.newFrame.ScreenHeight;
import static main.newFrame.ScreenWidth;


public class GameScreen extends JPanel implements KeyListener,Runnable{
    private RunGame runGame=new RunGame();
    private BitSet bitSet=new BitSet(10000);
    boolean isRunning=true;
    boolean win=true;
    int numberMonsters=0;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/images/snow.jpg")).getImage(),
            new ImageIcon(getClass().getResource("/images/background.jpg")).getImage(),
    };
    public static final int TIME_DAT=20;
    public void initPanelGame() {

        runGame.initGame(numberMonsters);
        Thread t= new Thread(this);
        t.start();
        setFocusable(true);
    }
    public int moveset=0;
    private MyContainer myContainer;
    public GameScreen(MyContainer myContainer){
        initPanelGame();

        this.myContainer=myContainer;
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        //Random rand = new Random();
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.drawImage(MY_IMAGE[rand.nextInt(2)],0,0,ScreenWidth,ScreenHeight,null);
        runGame.draw(g2d);
    }
    Random random = new Random();
    int i =random.nextInt(100)+1;
    @Override
    public void run() {
        int numberMonsters=0;
        //int time=0;
        //int t=0;
        while (win) {
            int t=0;
            int time=0;
            while (isRunning) {
                t++;
                if (bitSet.get(KeyEvent.VK_LEFT)) {
                    runGame.movePlayer(bomber.LEFT);
                } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
                    runGame.movePlayer(bomber.RIGHT);
                } else if (bitSet.get(KeyEvent.VK_UP)) {
                    runGame.movePlayer(bomber.UP);
                } else if (bitSet.get(KeyEvent.VK_DOWN)) {
                    runGame.movePlayer(bomber.DOWN);
                }
                if (bitSet.get(KeyEvent.VK_SPACE)) {

                    runGame.createboom(t);
                    time = t;
                }
                if(t==500){
                    runGame.createMonstercon(1);
                }
                moveset++;

                if (moveset == 50) {
                    i = random.nextInt(100) + 1;
                    moveset = 0;
                }
                if (i <= 25) {
                    runGame.moveMonster(monster.LEFT);
                }
                if (i > 25 && i <= 50) {
                    runGame.moveMonster(monster.RIGHT);
                }
                if (i > 50 && i <= 75) {
                    runGame.moveMonster(monster.UP);
                }
                if (i > 75) {
                    runGame.moveMonster(monster.DOWN);
                }
                runGame.CreateBommNo(t);
                isRunning = runGame.checkdie();
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(runGame.checkwin()==true){
                numberMonsters++;
                runGame.initGame(numberMonsters);
                isRunning = true;
            }



        }
        }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear();e.getKeyCode();
    }
}
