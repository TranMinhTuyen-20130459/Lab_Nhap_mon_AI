package mysudoku;

import mysudoku.controller.Controller;
import mysudoku.controller.GameController;
import mysudoku.model.Population;
import mysudoku.view.MainView;

import javax.swing.*;

public class Sudoku {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new GameController(new MainView(), new Population());
            controller.showGame();
        });
    }
}
