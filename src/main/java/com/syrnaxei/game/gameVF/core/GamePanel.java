package com.syrnaxei.game.gameVF.core;

import com.syrnaxei.game.gameVF.api.GameVFListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    private Thread gameThread;
    private boolean isRunning;
    private int score;
    private int countdown = 30;
    private long lastSecond = 0;
    private final JFrame gameFrame;
    private GameVFListener gameVFListener;

    Random random = new Random();

    private java.util.List<Target> targetList;

    public GamePanel(JFrame gameFrame) {
        setPreferredSize(new Dimension(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT));
        setBackground(new Color(249, 239, 245));
        setFocusable(true);
        requestFocusInWindow();
        this.gameFrame = gameFrame;

        addMouseListener();
    }

    private void addMouseListener() {
        // 添加鼠标监听器，但根据游戏状态处理不同逻辑
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isRunning) {
                    // 如果游戏未开始，点击任意位置开始游戏
                    startGame();
                } else {
                    // 如果游戏已开始，处理正常的打靶逻辑
                    checkMouseHit(e.getX(), e.getY());
                }
            }
        });
    }

    private void startGame() {
        if (!isRunning) {
            targetList = new java.util.ArrayList<>();

            initFixedTargets();

            isRunning = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    private void initFixedTargets() {
        for(int i = 0;i < 5;i++){
            int x = random.nextInt(GameConfig.WINDOW_WIDTH - GameConfig.TARGET_SIZE - 50 + 1) + 50;
            int y = random.nextInt(GameConfig.WINDOW_HEIGHT - GameConfig.TARGET_SIZE - 50 + 1) + 50;
            int size = random.nextInt(GameConfig.TARGET_SIZE - 40 + 1) + 40;
            if(GameConfig.DEBUG_MODE){
                System.out.println(x + " " + y);
            }
            targetList.add(new Target(x,y,size));
        }
    }

    private void checkMouseHit(int mouseX, int mouseY) {
        for (Target target : targetList) {
            if (target.isHit(mouseX, mouseY, target.getSize())) {
                if(GameConfig.DEBUG_MODE){
                    System.out.println("Hit");
                }

                target.setAlive(false);
                score += GameConfig.SCORE_PER_TARGET + (GameConfig.TARGET_SIZE - target.getSize());
                targetList.remove(target);  // 移除已命中的目标
                return;
            }
        }

        // 如果执行到这里说明没有命中任何目标
        if(GameConfig.DEBUG_MODE){
            System.out.println("Not hit");
        }
        int penalty = 20 + (score / 100) * 5;
        score -= penalty;
    }

    // 更新游戏状态
    private void updateGameState() {
        if (targetList.isEmpty()) {
            if(GameConfig.DEBUG_MODE){
                System.out.println("TargetList is empty");
            }
            initFixedTargets();
        }
    }

    private void updateCountdown() {
        if (isRunning && countdown > 0) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSecond >= 1000) { // 每秒更新一次
                countdown--;
                lastSecond = currentTime;

                if (countdown <= 0) {
                    triggerGameOver();
                }
            }
        }
    }

    // 添加设置回调的方法
    public void setGameOverCallback(GameVFListener callback) {
        this.gameVFListener = callback;
    }

    // 修改 triggerGameOver 方法
    public void triggerGameOver(){
        isRunning = false;
        // 触发回调
        if (gameVFListener != null) {
            gameVFListener.onGameEnd(score);
        }
        gameFrame.dispose();
    }



    // 绘制游戏（被run()调用）
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(!isRunning){
            drawHomePage(g2d);
        }else{
            drawGameUI(g2d);
            drawTargets(g2d);
        }
    }

    private void drawHomePage(Graphics2D g2d) {
        // 绘制网格背景
        g2d.setColor(new Color(253, 253, 245));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 绘制网格线
        g2d.setColor(new Color(230, 220, 235)); // 浅灰色网格线
        g2d.setStroke(new BasicStroke(1));

        // 绘制垂直线
        for (int x = 0; x < getWidth(); x += 20) {
            g2d.drawLine(x, 0, x, getHeight());
        }

        // 绘制水平线
        for (int y = 0; y < getHeight(); y += 20) {
            g2d.drawLine(0, y, getWidth(), y);
        }

        // 绘制标题
        g2d.setColor(new Color(0, 0, 0));
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        String title = "VF";
        FontMetrics fm = g2d.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        g2d.drawString(title, (getWidth() - titleWidth) / 2, getHeight() / 2 - 50);

        // 绘制提示文字
        g2d.setColor(new Color(85, 85, 85)); // 设置为灰色
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        String subtitle = "在30s内尽可能快地点击屏幕出现的小球以获取分数";
        fm = g2d.getFontMetrics();
        int subtitleWidth = fm.stringWidth(subtitle);
        g2d.drawString(subtitle, (getWidth() - subtitleWidth) / 2, getHeight() / 2);

        // 绘制提示文字
        g2d.setColor(new Color(0, 0, 0));
        String prompt = "点击任意位置开始游戏";
        fm = g2d.getFontMetrics();
        int promptWidth = fm.stringWidth(prompt);
        g2d.drawString(prompt, (getWidth() - promptWidth) / 2, getHeight() / 2 + 100);
    }

    private void drawGameUI(Graphics2D g2d) {
        // 绘制网格背景（游戏界面）
        g2d.setColor(new Color(253, 253, 245));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 绘制网格线
        g2d.setColor(new Color(230, 220, 235)); // 浅灰色网格线
        g2d.setStroke(new BasicStroke(1));

        // 绘制垂直线
        for (int x = 0; x < getWidth(); x += 20) {
            g2d.drawLine(x, 0, x, getHeight());
        }

        // 绘制水平线
        for (int y = 0; y < getHeight(); y += 20) {
            g2d.drawLine(0, y, getWidth(), y);
        }

        // 绘制分数和倒计时
        g2d.setColor(new Color(0, 0, 0));
        g2d.setFont(new Font("微软雅黑", Font.BOLD, 24));
        g2d.drawString("分数:" + score, 40, 40);

        // 绘制倒计时
        g2d.setFont(new Font("微软雅黑", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        String timeText = "剩余时间: " + countdown + "s";
        int timeWidth = fm.stringWidth(timeText);
        g2d.drawString(timeText, getWidth() - timeWidth - 40, 40); // 右边距40像素
    }

    private void drawTargets(Graphics2D g2d) {
        for(Target target : targetList){
            if(target.isAlive()){
                // 绘制主圆形
                g2d.setColor(new Color(105, 240, 174));
                g2d.fillOval(target.getX(), target.getY(), target.getSize(), target.getSize());

                // 白色边框
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(target.getX(), target.getY(), target.getSize(), target.getSize());

                //test
                if(GameConfig.DEBUG_MODE){
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial",Font.BOLD,10));
                    g2d.drawString(target.getX() + "<-x" + target.getSize() +"y->" + target.getY(), target.getX(), target.getY());
                }
            }
        }
    }

    @Override
    public void run() {
        while(isRunning && !Thread.currentThread().isInterrupted()){
            updateGameState();
            updateCountdown();
            repaint();  // 调用paintComponent()方法
            try {
                Thread.sleep(GameConfig.FRAME_DELAY);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break; // 线程被中断时退出循环
            }
        }
    }
}
