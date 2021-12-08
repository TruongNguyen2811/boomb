package main;
import javax.swing.*;

public class newFrame extends JFrame {
    public static final int ScreenWidth = 740;
    public static final int ScreenHeight = 720;

    public newFrame(){
        initMyFarme();
    }

    private void initMyFarme() {
        setTitle("boombl4");
        setSize(ScreenWidth,ScreenHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MyContainer());
    }
}
