package mysudoku.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameState {
    public final List<List<Boolean>> fixed = new ArrayList<>();
    public final List<List<Integer>> state;

    public GameState(List<List<Integer>> state) {
        this.state = state;
        initFixed();
    }

    private void initFixed() {
        for (int i = 0; i < 9; i++) {
            fixed.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                if (state.get(i).get(j) != 0)
                    fixed.get(i).add(true);
                else
                    fixed.get(i).add(false);
            }
        }
    }

    public static boolean isValid(List<List<Integer>> state) {
        Set<Integer> rowSet = new HashSet<>(), colSet = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            rowSet.clear();
            colSet.clear();
            for (int j = 0; j < 9; j++) {
                int eRow = state.get(i).get(j), eCol = state.get(j).get(i);
                if (eRow != 0 && !rowSet.add(eRow)) return false;
                if (eCol != 0 && !colSet.add(eCol)) return false;
            }
        }

        Set<Integer> blockSet = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blockSet.clear();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int eBlock = state.get(k + i * 3).get(l + j * 3);
                        if (eBlock != 0 && !blockSet.add(eBlock)) return false;
                    }
                }
            }
        }

        return true;
    }

    private static List<List<Integer>> toList(int[][] array2D) {
        List<List<Integer>> state = new ArrayList<>();
        for (int[] arr : array2D) {
            List<Integer> row = new ArrayList<>();
            for (int e : arr) row.add(e);
            state.add(row);
        }
        return state;
    }

    public static GameState from(int[][] state2D) {
        return new GameState(toList(state2D));
    }
}
