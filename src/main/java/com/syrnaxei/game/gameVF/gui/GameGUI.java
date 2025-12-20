package com.syrnaxei.game.gameVF.gui;

import com.syrnaxei.game.gameVF.core.GamePanel;

import javax.swing.*;

public class GameGUI {
    private final GamePanel gamePanel;
    private final JFrame gameFrame;

    public GameGUI() {
        gameFrame = new JFrame("Project_Cash_Game_VF");
        gamePanel = new GamePanel(gameFrame);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    // 在 GameGUI 类中添加以下方法
    public JFrame getGameFrame() {
        return gameFrame;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

}
