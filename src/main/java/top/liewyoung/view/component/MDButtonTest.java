package top.liewyoung.view.component;

import com.formdev.flatlaf.FlatLightLaf;
import top.liewyoung.view.ColorSystem.MaterialPalette;
import javax.swing.*;
import java.awt.*;

/**
 * 测试MDbutton的自适应大小功能
 */
public class MDButtonTest {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("MDbutton 自适应测试");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // 测试不同长度的文本
        String[] testTexts = {
                "短",
                "确定",
                "取消",
                "投资 (500元)",
                "创业 (1500元)",
                "购买房地产 (5000元)",
                "这是一个很长的按钮文本用来测试自适应效果"
        };

        for (String text : testTexts) {
            MDbutton button = new MDbutton(text);
            frame.add(button);

            // 打印按钮的首选尺寸
            Dimension preferredSize = button.getPreferredSize();
            System.out.println(String.format("按钮[%s] - 宽度: %d, 高度: %d",
                    text, preferredSize.width, preferredSize.height));
        }

        // 添加一个次要样式的按钮测试
        MDbutton secondaryButton = new MDbutton("次要按钮");
        secondaryButton.setBackground(MaterialPalette.MOSS.surfaceVariant());
        secondaryButton.setForeground(MaterialPalette.MOSS.onSurface());
        frame.add(secondaryButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
