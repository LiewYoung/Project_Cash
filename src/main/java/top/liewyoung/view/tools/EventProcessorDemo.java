package top.liewyoung.view.tools;

import org.atom.Player;
import top.liewyoung.strategy.TitlesTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EventProcessor演示程序
 * 展示事件处理器如何工作
 *
 * @author LiewYoung
 * @since 2025/12/17
 */
public class EventProcessorDemo extends JFrame {

    private Player currentPlayer;
    private EventProcessor eventProcessor;
    private JTextArea infoArea;
    private JButton[] eventButtons;

    public EventProcessorDemo() {
        setTitle("事件处理器演示");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 初始化玩家
        currentPlayer = new Player("演示玩家", 5000, 3000, 1500);
        eventProcessor = new EventProcessor(currentPlayer);

        initUI();
        updatePlayerInfo();
    }

    private void initUI() {
        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 玩家信息面板
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("玩家信息"));

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("事件类型"));

        TitlesTypes[] eventTypes = TitlesTypes.values();
        eventButtons = new JButton[eventTypes.length];

        for (int i = 0; i < eventTypes.length; i++) {
            final TitlesTypes type = eventTypes[i];
            JButton button = new JButton(getEventTypeName(type));
            button.setFont(new Font("微软雅黑", Font.BOLD, 14));
            button.setBackground(getEventColor(type));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    triggerEvent(type);
                }
            });

            eventButtons[i] = button;
            buttonPanel.add(button);
        }

        // 控制按钮面板
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton resetButton = new JButton("重置玩家");
        resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPlayer();
            }
        });

        JButton randomButton = new JButton("随机事件");
        randomButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triggerRandomEvent();
            }
        });

        JButton exitButton = new JButton("退出");
        exitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        controlPanel.add(resetButton);
        controlPanel.add(randomButton);
        controlPanel.add(exitButton);

        // 日志面板
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("事件日志"));

        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setPreferredSize(new Dimension(400, 200));
        logPanel.add(logScrollPane, BorderLayout.CENTER);

        // 布局
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(logPanel, BorderLayout.CENTER);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    private String getEventTypeName(TitlesTypes type) {
        switch (type) {
            case START: return "起点事件";
            case OPPORTUNITY: return "机会事件";
            case MARKET: return "市场事件";
            case FATE: return "命运事件";
            case BANK: return "银行事件";
            case FUNGAME: return "趣味游戏";
            default: return "未知事件";
        }
    }

    private Color getEventColor(TitlesTypes type) {
        switch (type) {
            case START: return new Color(46, 204, 113);      // 翠绿色
            case OPPORTUNITY: return new Color(192, 202, 51); // 黄绿色
            case MARKET: return new Color(76, 175, 80);      // 绿色
            case FATE: return new Color(0, 137, 123);        // 青色
            case BANK: return new Color(27, 94, 32);         // 深绿色
            case FUNGAME: return new Color(105, 240, 174);   // 浅绿色
            default: return Color.GRAY;
        }
    }

    private void triggerEvent(TitlesTypes type) {
        // 保存事件前的状态
        int oldCash = currentPlayer.getCash();
        int oldPassiveIncome = currentPlayer.getPassiveIncome();

        // 触发事件
        eventProcessor.processEvent(type);

        // 更新显示
        updatePlayerInfo();

        // 记录事件结果
        String message = String.format(
            "触发%s：\n" +
            "  现金变化：%d → %d (%s%d)\n" +
            "  被动收入变化：%d → %d (%s%d)\n" +
            "  当前现金流：%d元\n",
            getEventTypeName(type),
            oldCash,
            currentPlayer.getCash(),
            currentPlayer.getCash() >= oldCash ? "+" : "",
            currentPlayer.getCash() - oldCash,
            oldPassiveIncome,
            currentPlayer.getPassiveIncome(),
            currentPlayer.getPassiveIncome() >= oldPassiveIncome ? "+" : "",
            currentPlayer.getPassiveIncome() - oldPassiveIncome,
            currentPlayer.calculateCashflow()
        );

        appendToLog(message);
    }

    private void triggerRandomEvent() {
        TitlesTypes[] allTypes = TitlesTypes.values();
        int randomIndex = (int) (Math.random() * allTypes.length);
        triggerEvent(allTypes[randomIndex]);
    }

    private void resetPlayer() {
        currentPlayer = new Player("演示玩家", 5000, 3000, 1500);
        eventProcessor.setCurrentPlayer(currentPlayer);
        updatePlayerInfo();
        appendToLog("玩家已重置为初始状态\n");
    }

    private void updatePlayerInfo() {
        String info = eventProcessor.getPlayerCashflowInfo();
        infoArea.setText(info);
    }

    private void appendToLog(String message) {
        // 在实际实现中，这里应该更新日志区域
        System.out.println(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                EventProcessorDemo demo = new EventProcessorDemo();
                demo.setVisible(true);
            }
        });
    }
}
