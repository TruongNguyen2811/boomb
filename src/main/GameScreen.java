package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;


public class GameScreen extends JPanel implements KeyListener,Runnable{
    private RunGame runGame=new RunGame();
    private BitSet bitSet=new BitSet(10000);
    boolean isRunning=true;
    public static final int TIME_DAT=20;
    public void initPanelGame() {
        runGame.initGame();
        Thread t= new Thread(this);
        t.start();
        setFocusable(true);
    }
    private MyContainer myContainer;
    public GameScreen(MyContainer myContainer){
        initPanelGame();

        this.myContainer=myContainer;
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        runGame.draw(g2d);
    }
    @Override
    public void run() {
        int time=0;
        int t=0;
        while (isRunning){
            t++;
            if (bitSet.get(KeyEvent.VK_LEFT)){
                runGame.movePlayer(bomber.LEFT);
            }else if (bitSet.get(KeyEvent.VK_RIGHT)){
                runGame.movePlayer(bomber.RIGHT);
            }else if (bitSet.get(KeyEvent.VK_UP)){
                runGame.movePlayer(bomber.UP);
            }else if (bitSet.get(KeyEvent.VK_DOWN)){
                runGame.movePlayer(bomber.DOWN);
            }
            if (bitSet.get(KeyEvent.VK_SPACE)){

                    runGame.createboom(t);
                time=t;
            }

            isRunning=true;
            runGame.CreateBommNo(t);
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
