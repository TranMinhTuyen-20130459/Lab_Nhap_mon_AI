package mysudoku.view;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.Stream;

public class MainView extends JFrame {
    public final JFormattedTextField[][] textFields = new JFormattedTextField[9][9];
    public JButton solveButton, clearButton;

    private final NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance()) {{
        setValueClass(Integer.class);
        setMinimum(1);
        setMaximum(9);
        setAllowsInvalid(false);
        setCommitsOnValidEdit(true);
    }};

    public MainView() {
        super("Sudoku Solver");
        setSize(500, 572);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initGUI();
        setLocationRelativeTo(null);
    }

    private void initGUI() {
        GameBoard board = new GameBoard(new GridLayout(9, 9, 4, 4));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = createTextField();
                board.add(textFields[i][j]);
            }
        }

        JPanel controlPanel = new JPanel(new GridLayout(1, 2, 50, 0));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        clearButton = createControlButton("Clear");
        solveButton = createControlButton("Solve");
        controlPanel.add(clearButton);
        controlPanel.add(solveButton);

        add(board, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        setResizable(false);
    }

    private JFormattedTextField createTextField() {
        return new JFormattedTextField(formatter) {{
            setHorizontalAlignment(JTextField.CENTER);
            setBorder(null);
            setFont(new Font("Mono", Font.PLAIN, 24));
            setForeground(Color.RED);
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            setHighlighter(null);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_SPACE) {
                        ((JFormattedTextField) e.getSource()).setValue(null);
                    }
                    super.keyPressed(e);
                }
            });
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    JFormattedTextField tf = (JFormattedTextField) e.getSource();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (textFields[i][j].equals(tf)) {
                                for (int l = 0; l < 9; l++) {
                                    textFields[i][l].setBackground(Color.decode("#F0F8FF"));
                                    textFields[l][j].setBackground(Color.decode("#F0F8FF"));
                                }
                                for (int k = 0; k < 3; k++) {
                                    for (int l = 0; l < 3; l++) {
                                        int i1 = k + (i / 3) * 3, i2 = l + (j / 3) * 3;
                                        textFields[i1][i2].setBackground(Color.decode("#F0F8FF"));
                                    }
                                }
                                break;
                            }
                        }
                    }
                    tf.setBackground(Color.decode("#BBDEFB"));
                    tf.setCaretColor(Color.decode("#BBDEFB"));
                    super.focusGained(e);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (textFields[i][j].equals(e.getSource())) {
                                for (int l = 0; l < 9; l++) {
                                    textFields[i][l].setBackground(Color.WHITE);
                                    textFields[l][j].setBackground(Color.WHITE);
                                }
                                for (int k = 0; k < 3; k++) {
                                    for (int l = 0; l < 3; l++) {
                                        int i1 = k + (i / 3) * 3, i2 = l + (j / 3) * 3;
                                        textFields[i1][i2].setBackground(Color.WHITE);
                                    }
                                }
                                break;
                            }
                        }
                    }
                    super.focusLost(e);
                }
            });
        }};
    }

    private JButton createControlButton(String label) {
        return new JButton(label) {{
            setFont(new Font("Mono", Font.BOLD, 16));
            setForeground(Color.WHITE);
            setBackground(Color.decode("#0067CB"));
            setFocusPainted(false);
            setBorder(BorderFactory
                    .createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        }};
    }

    public void reset() {
        Arrays.stream(textFields).flatMap(Stream::of)
                .forEach(tf -> {
                    tf.setValue(null);
                    tf.setForeground(Color.RED);
                    tf.setEditable(true);
                });
    }
}
