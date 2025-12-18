package top.liewyoung.view.component;

import com.formdev.flatlaf.FlatClientProperties;
import top.liewyoung.view.ColorSystem.MaterialPalette;

import javax.swing.*;
import java.awt.*;

/**
 * Material Design 按钮
 * 使用FlatLaf特性，自适应内容大小
 * 
 * @author LiewYoung
 * @since 2025/12/14
 */
public class MDbutton extends JButton {
    private MaterialPalette palette = MaterialPalette.MOSS;

    public MDbutton(String text) {
        super(text);

        // 移除固定尺寸，让按钮根据内容自适应
        // 使用FlatLaf的STYLE属性设置圆角、内边距和最小高度
        putClientProperty(FlatClientProperties.STYLE,
                "arc: 40;" + // 圆角半径
                        "margin: 8,20,8,20;" + // 上、左、下、右的内边距
                        "minimumHeight: 40" // 最小高度，确保按钮不会太矮
        );

        setBorderPainted(false);
        setBackground(palette.primary());
        setFont(UIManager.getFont("defaultFont").deriveFont(Font.BOLD, 14f));
        setForeground(palette.onPrimary());
    }
}
