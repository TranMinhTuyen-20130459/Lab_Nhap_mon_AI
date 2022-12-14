package mysudoku.view;

import javax.swing.*;
import java.awt.*;

public class WarningDialog extends JFrame {
    private static WarningDialog instance = null;
    private final JLabel content;

    private WarningDialog() {
        super("Warning");
        add(content = new JLabel(new ImageIcon("../warning.png")) {{
            setHorizontalTextPosition(CENTER);
            setVerticalTextPosition(BOTTOM);
            setFont(new Font("Mono", Font.PLAIN, 16));
        }});
        add(new JButton("OK") {{
            addActionListener(e -> WarningDialog.this.setVisible(false));
        }}, BorderLayout.SOUTH);
        setSize(300, 180);
    }

    public WarningDialog message(String message) {
        content.setText("<html>" + message + "</html>");
        return instance;
    }

    public static WarningDialog of(JComponent component) {
        if (instance == null) instance = new WarningDialog();
        instance.setLocationRelativeTo(component);
        return instance;
    }

    public void showDialog() {
        setVisible(true);
    }
}
