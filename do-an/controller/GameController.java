package mysudoku.controller;

import mysudoku.model.GameState;
import mysudoku.model.Population;
import mysudoku.view.MainView;
import mysudoku.view.WarningDialog;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Controller {
    private final MainView view;
    private final Population pop;

    public GameController(MainView view, Population pop) {
        this.view = view;
        this.pop = pop;

        // register listener
        view.solveButton.addActionListener(e -> solve());
        view.clearButton.addActionListener(e -> reset());
    }

    @Override
    public void showGame() {
        view.setVisible(true);
        view.requestFocusInWindow();
    }

    @Override
    public void reset() {
        view.reset();
        pop.reset();
    }

    @Override
    public void solve() {
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                String text = view.textFields[i][j].getText();
                int value = text.isEmpty() ? 0 : Integer.parseInt(text);
                integers.add(value);
            }
            data.add(integers);
        }

        if (!GameState.isValid(data)) {
//            JOptionPane.showMessageDialog(view, "Invalid state!\nGame cannot be solved.", "Warning", JOptionPane.WARNING_MESSAGE);
            WarningDialog.of(view.getRootPane())
                    .message("Invalid state!<br>Game cannot be solved.")
                    .showDialog();
        } else {
            GameState gameState = new GameState(data);
            pop.setData(gameState);
            List<List<Integer>> state = pop.solve();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (gameState.fixed.get(i).get(j)) continue;
                    String num = String.valueOf(state.get(i).get(j));
                    view.textFields[i][j].setForeground(Color.decode("#344861"));
                    view.textFields[i][j].setText(num);
                    view.textFields[i][j].setEditable(false);
                    view.textFields[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
