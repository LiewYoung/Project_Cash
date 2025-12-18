package top.liewyoung.view.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import top.liewyoung.view.ColorSystem.MaterialPalette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Material Design 3 风格对话框
 * 支持多种弹窗类型：消息、选项、输入对话框
 * 
 * @author LiewYoung
 * @since 2025/12/14
 */
public class MDialog extends JDialog {
    private MaterialPalette palette = MaterialPalette.MOSS;
    private int selectedOption = -1; // 用户选择的选项索引，-1表示未选择或关闭
    private String inputValue = null; // 用户输入的值
    private JTextField inputField = null; // 输入框

    /**
     * 消息类型枚举
     */
    public enum MessageType {
        INFO, // 信息提示
        WARNING, // 警告
        ERROR, // 错误
        QUESTION // 问题
    }

    /**
     * 简单消息对话框构造函数（单按钮）
     * 
     * @param title   对话框标题
     * @param content 消息内容
     * @param type    消息类型
     */
    public MDialog(String title, String content, MessageType type) {
        this(title, content, new String[] { "确定" }, type);
    }

    /**
     * 选项对话框构造函数（多按钮）
     * 
     * @param title   对话框标题
     * @param content 消息内容
     * @param options 按钮选项数组
     * @param type    消息类型
     */
    public MDialog(String title, String content, String[] options, MessageType type) {
        this(title, content, options, type, false);
    }

    /**
     * 输入对话框构造函数
     * 
     * @param title   对话框标题
     * @param message 提示消息
     * @param type    消息类型
     * @param isInput 是否为输入对话框
     */
    public MDialog(String title, String message, MessageType type, boolean isInput) {
        this(title, message, new String[] { "确定", "取消" }, type, isInput);
    }

    /**
     * 完整构造函数（支持所有功能）
     * 
     * @param title   对话框标题
     * @param content 消息内容
     * @param options 按钮选项数组
     * @param type    消息类型
     * @param isInput 是否为输入对话框
     */
    private MDialog(String title, String content, String[] options, MessageType type, boolean isInput) {
        setTitle(title);
        setModal(true); // 设置为模态对话框
        setBackground(palette.surface());
        setLayout(new BorderLayout(0, 10));
        setSize(new Dimension(450, isInput ? 220 : 200));
        setLocationRelativeTo(null); // 居中显示

        Container contentPanel = getContentPane();
        contentPanel.setBackground(palette.surface());

        // 内容面板
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(palette.surface());
        textPanel.setBorder(new EmptyBorder(20, 30, 10, 30));

        // 添加消息标签
        JLabel messageLabel = contentLabelFactory(content, type);
        messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(messageLabel);

        // 如果是输入对话框，添加输入框
        if (isInput) {
            textPanel.add(Box.createVerticalStrut(15)); // 间距
            inputField = new JTextField();
            inputField.setPreferredSize(new Dimension(360, 35));
            inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            inputField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            inputField.setBackground(palette.surfaceVariant());
            inputField.setForeground(palette.onSurface());
            inputField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(palette.outline(), 1),
                    new EmptyBorder(5, 10, 5, 10)));
            inputField.setAlignmentX(Component.LEFT_ALIGNMENT);
            textPanel.add(inputField);
        }

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(palette.surface());
        buttonPanel.setBorder(new EmptyBorder(0, 20, 15, 20));

        // 创建按钮
        for (int i = 0; i < options.length; i++) {
            final int optionIndex = i;
            JButton button = buttonFactory(options[i], i == 0); // 第一个按钮为主按钮
            button.addActionListener(e -> {
                selectedOption = optionIndex;
                if (isInput && inputField != null) {
                    inputValue = inputField.getText();
                }
                dispose();
            });
            buttonPanel.add(button);
        }

        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * 兼容旧版本的简单构造函数
     * 
     * @param content         消息内容
     * @param closeButtonText 关闭按钮文本
     */
    public MDialog(String content, String closeButtonText) {
        this("提示", content, new String[] { closeButtonText }, MessageType.INFO);
    }

    /**
     * 创建按钮
     * 
     * @param text      按钮文本
     * @param isPrimary 是否为主按钮
     * @return 按钮实例
     */
    protected JButton buttonFactory(String text, boolean isPrimary) {
        JButton button = new MDbutton(text);

        // 如果不是主按钮，使用次要样式
        if (!isPrimary) {
            button.setBackground(palette.surfaceVariant());
            button.setForeground(palette.onSurface());
        }

        return button;
    }

    /**
     * 创建内容标签
     * 
     * @param content 内容文本
     * @param type    消息类型
     * @return 标签实例
     */
    protected JLabel contentLabelFactory(String content, MessageType type) {
        // 将\n转换为HTML的<br>以支持换行
        String htmlContent = "<html>" + content.replace("\n", "<br>") + "</html>";

        JLabel label = new JLabel(htmlContent);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

        // 根据消息类型设置颜色
        switch (type) {
            case ERROR:
                label.setForeground(palette.error());
                break;
            case WARNING:
                label.setForeground(new Color(205, 127, 50)); // 警告橙色
                break;
            case INFO:
            case QUESTION:
            default:
                label.setForeground(palette.onSurface());
                break;
        }

        return label;
    }

    /**
     * 在旧版本中保持兼容
     */
    protected JLabel contentLabelFactory(String content) {
        return contentLabelFactory(content, MessageType.INFO);
    }

    /**
     * 获取用户选择的选项索引
     * 
     * @return 选项索引，-1表示未选择或关闭
     */
    public int getSelectedOption() {
        return selectedOption;
    }

    /**
     * 获取用户输入的值
     * 
     * @return 输入的文本，null表示未输入或取消
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * 静态方法：显示消息对话框
     */
    public static void showMessageDialog(Component parent, String message, String title, MessageType type) {
        MDialog dialog = new MDialog(title, message, type);
        dialog.setVisible(true);
    }

    /**
     * 静态方法：显示选项对话框
     * 
     * @return 用户选择的选项索引
     */
    public static int showOptionDialog(Component parent, String message, String title,
            String[] options, MessageType type) {
        MDialog dialog = new MDialog(title, message, options, type);
        dialog.setVisible(true);
        return dialog.getSelectedOption();
    }

    /**
     * 静态方法：显示输入对话框
     * 
     * @return 用户输入的文本
     */
    public static String showInputDialog(Component parent, String message, String title, MessageType type) {
        MDialog dialog = new MDialog(title, message, type, true);
        dialog.setVisible(true);
        return dialog.getInputValue();
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame frame = new JFrame("MD3 Dialog Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // 测试消息对话框
        JButton infoButton = new JButton("信息对话框");
        infoButton.addActionListener(e -> {
            MDialog.showMessageDialog(frame, "这是一条信息提示", "信息", MessageType.INFO);
        });
        frame.add(infoButton);

        // 测试警告对话框
        JButton warningButton = new JButton("警告对话框");
        warningButton.addActionListener(e -> {
            MDialog.showMessageDialog(frame, "这是一条警告信息", "警告", MessageType.WARNING);
        });
        frame.add(warningButton);

        // 测试错误对话框
        JButton errorButton = new JButton("错误对话框");
        errorButton.addActionListener(e -> {
            MDialog.showMessageDialog(frame, "这是一条错误信息", "错误", MessageType.ERROR);
        });
        frame.add(errorButton);

        // 测试选项对话框
        JButton optionButton = new JButton("选项对话框");
        optionButton.addActionListener(e -> {
            String[] options = { "选项1", "选项2", "取消" };
            int choice = MDialog.showOptionDialog(frame, "请选择一个选项", "选择",
                    options, MessageType.QUESTION);
            System.out.println("用户选择了：" + (choice >= 0 ? options[choice] : "关闭"));
        });
        frame.add(optionButton);

        // 测试输入对话框
        JButton inputButton = new JButton("输入对话框");
        inputButton.addActionListener(e -> {
            String input = MDialog.showInputDialog(frame, "请输入您的名字", "输入", MessageType.QUESTION);
            System.out.println("用户输入：" + (input != null ? input : "取消"));
        });
        frame.add(inputButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
