package main;

import javax.swing.*;
import java.awt.*;

    public class MyContainer extends JPanel {
        public static final String PANEL_GAME = "PanelGame";
        private GameScreen GameScreen;
        private CardLayout cardLayout;

        //private Clip clip;
        public MyContainer() {
            cardLayout = new CardLayout();
            GameScreen = new GameScreen(this);
            setLayout(cardLayout);
            add(GameScreen, PANEL_GAME);
            cardLayout.show(this,PANEL_GAME);
            addKeyListener(GameScreen);
            setFocusable(true);
        }
    }

