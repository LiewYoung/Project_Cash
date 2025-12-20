package com.syrnaxei.game.game2048.core;

import com.syrnaxei.game.game2048.api.Game2048Listener;
import java.util.Random;

public class Board {

    private int[][] board;
    private int score = 0;

    private Game2048Listener endListener;
    private int remainingSeconds = GameConfig.INITIAL_COUNTDOWN; // 添加倒计时实例变量
    private final Random random = new Random();

    //===================================  创建棋盘 方法  ===================================
    public void createBoard() {
        try {
            board = new int[GameConfig.BOARD_SIZE][GameConfig.BOARD_SIZE];
            addNumber();
            addNumber();
        } catch (Exception e) {
            throw new RuntimeException("创建游戏棋盘失败", e);
        }
    }

    //===================================  添加数字 方法  ===================================
    public void addNumber() {
        try {
            int row, col;
            if (!hasEmptyLocation()) {
                return;
            }

            // 寻找空位置
            do {
                row = random.nextInt(GameConfig.BOARD_SIZE);
                col = random.nextInt(GameConfig.BOARD_SIZE);
            } while (board[row][col] != 0);

            // 根据概率生成2或4
            if (random.nextInt(100) > GameConfig.S_FOUR_P) {
                board[row][col] = 2;
            } else {
                board[row][col] = 4;
            }
        } catch (Exception e) {
            throw new RuntimeException("添加数字到棋盘失败", e);
        }
    }

    public boolean hasEmptyLocation() {
        for (int[] row : board) {
            for (int num : row) {
                if (num == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //====================================  计分 方法  ====================================
    public int getScore() {
        return score;
    }

    /**
     * 添加分数到当前总分
     *
     * @param score 要增加的分数
     */
    public void addScore(int score) {
        if (score > 0) {
            this.score += score;
        }
    }

    /**
     * 设置总分为指定值（主要用于重置）
     *
     * @param score 新的总分
     */
    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        }
    }

    //===================================  游戏结束 方法  ===================================
    public boolean isGameOver() {
        try {
            // 检查棋盘上是否有空位
            for (int i = 0; i < GameConfig.BOARD_SIZE; i++) {
                for (int j = 0; j < GameConfig.BOARD_SIZE; j++) {
                    if (board[i][j] == 0) {
                        return false;
                    }
                }
            }

            // 检查棋盘横向是否有相同的可合并的数字
            for (int i = 0; i < GameConfig.BOARD_SIZE; i++) {
                for (int j = 0; j < GameConfig.BOARD_SIZE - 1; j++) {
                    if (board[i][j] == board[i][j + 1]) {
                        return false;
                    }
                }
            }

            // 检查棋盘纵向是否有相同的可合并的数字
            for (int i = 0; i < GameConfig.BOARD_SIZE - 1; i++) {
                for (int j = 0; j < GameConfig.BOARD_SIZE; j++) {
                    if (board[i][j] == board[i + 1][j]) {
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            throw new RuntimeException("检查游戏是否结束时发生错误", e);
        }
    }

    //===================================  检查是否达到2048 方法  ===================================
    public boolean hasReached2048() {
        for (int i = 0; i < GameConfig.BOARD_SIZE; i++) {
            for (int j = 0; j < GameConfig.BOARD_SIZE; j++) {
                if (board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    //===================================  棋盘调用 方法  ===================================
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void resetBoard() {
        try {
            board = new int[GameConfig.BOARD_SIZE][GameConfig.BOARD_SIZE];
            score = 0;
            resetRemainingSeconds(); // 重置倒计时
            addNumber();
            addNumber();
        } catch (Exception e) {
            throw new RuntimeException("重置游戏棋盘失败", e);
        }
    }

    public void setListener(Game2048Listener listener) {
        this.endListener = listener;
    }

    //===================================  倒计时 方法  ===================================
    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(int seconds) {
        this.remainingSeconds = Math.max(0, seconds);
    }

    public void decrementRemainingSeconds() {
        if (remainingSeconds > 0) {
            remainingSeconds--;
        }
    }

    public void resetRemainingSeconds() {
        this.remainingSeconds = GameConfig.INITIAL_COUNTDOWN;
    }

    public boolean isTimeUp() {
        return remainingSeconds <= 0;
    }

    public void triggerGameOver() {
        try {
            // 重置倒计时，为下次游戏做准备
            resetRemainingSeconds();
            if (endListener != null) {
                endListener.onGameEnd(this.score); // 触发回调返回分数
            }
        } catch (Exception e) {
            throw new RuntimeException("触发游戏结束时发生错误", e);
        }

    }

    //==================================  测试 方法  ====================================
    public void boardTest() {
        String input = javax.swing.JOptionPane.showInputDialog("请输入要设置的分数:");
        if(input != null && !input.trim().isEmpty()) {
            try {
                int newScore = Integer.parseInt(input);
                if(newScore >= 0) {
                    this.score = newScore;
                    javax.swing.JOptionPane.showMessageDialog(null, "分数已设置为: " + newScore);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "分数不能为负数!");
                }
            } catch(NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "输入格式错误!");
            }
        }
    }
}

