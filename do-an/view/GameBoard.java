package mysudoku.view;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    public GameBoard(LayoutManager layout) {
        super(layout);
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4f));
        g2d.setColor(Color.decode("#344861"));

        g2d.drawLine(0, 4, getWidth() - 3, 4);
        g2d.drawLine(0, 153, getWidth() - 3, 153);
        g2d.drawLine(0, 304, getWidth() - 3, 304);
        g2d.drawLine(0, 454, getWidth() - 3, 454);

        g2d.drawLine(2, 4, 2, getHeight() - 5);
        g2d.drawLine(163, 4, 163, getHeight() - 5);
        g2d.drawLine(322, 4, 322, getHeight() - 5);
        g2d.drawLine(484, 4, 484, getHeight() - 5);
    }
}
