package mysudoku.model;

import mysudoku.Utils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Individual implements Comparable<Individual> {
    private final GameState gameState;

    public final List<List<Integer>> state;
    protected int fitness;

    public Individual(GameState gameState) {
        this.gameState = gameState;
        this.state = gameState.state.stream().map(ArrayList::new).collect(Collectors.toList());    // copy state
        fillState();
    }

    private void fillState() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (state.get(i).get(j) == 0) {
                    do state.get(i).set(j, Utils.random(1, 9));
                    while (inBlock(i, j));
                }
            }
        }
        heuristic();
    }

    private boolean inBlock(int row, int col) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int i1 = i + (row / 3) * 3, i2 = j + (col / 3) * 3;
                if (Objects.equals(state.get(row).get(col), state.get(i1).get(i2)) && (i1 != row || i2 != col))
                    return true;
            }
        }
        return false;
    }

    private void heuristic() {
        fitness = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (Objects.equals(state.get(i).get(j), state.get(i).get(k))) fitness++;
                    if (Objects.equals(state.get(j).get(i), state.get(k).get(i))) fitness++;
                }
            }
        }
    }

    private int conflictAt(int row, int col) {
        int conflicts = -2;
        for (int i = 0; i < 9; i++) {
            if (Objects.equals(state.get(row).get(col), state.get(row).get(i))) conflicts++;
            if (Objects.equals(state.get(row).get(col), state.get(i).get(col))) conflicts++;
        }
        return conflicts;
    }

    private List<List<Integer>> statistical() {
        List<List<Integer>> listConflict = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Integer> conflicts = new ArrayList<>();
            for (int j = 0; j < 9; j++)
                conflicts.add(conflictAt(i, j));
            listConflict.add(conflicts);
        }
        return listConflict;
    }

    private List<Map<Point, Integer>> maxConflictOfBlocks(List<List<Integer>> statistical) {
        List<Map<Point, Integer>> blocks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int max = 0;
                Map<Point, Integer> block = new HashMap<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int a = k + i * 3, b = l + j * 3;
                        if (!gameState.fixed.get(a).get(b)) {
                            int value = statistical.get(a).get(b);
                            if (value > max) {
                                block.clear();
                                block.put(new Point(a, b), value);
                                max = value;
                            } else if (value == max && value != 0) {
                                block.put(new Point(a, b), value);
                            }
                        }
                    }
                }
                blocks.add(block);
            }
        }
        return blocks;
    }

    @SuppressWarnings("unused")
    public void mutate2() {
        int row, col, i1, i2, j1, j2;
        do {
            row = Utils.random(3);
            col = Utils.random(3);
            i1 = Utils.random(3) + row * 3;
            j1 = Utils.random(3) + col * 3;
            i2 = Utils.random(3) + row * 3;
            j2 = Utils.random(3) + col * 3;
        } while (gameState.fixed.get(i1).get(j1) || gameState.fixed.get(i2).get(j2));

        int value1 = state.get(i1).get(j1), value2 = state.get(i2).get(j2);
        state.get(i1).set(j1, value2);
        state.get(i2).set(j2, value1);
    }

    public void mutate() {
        if (fitness == 0) return;

        List<Map<Point, Integer>> maxConflictMatrix = maxConflictOfBlocks(statistical());
        int indexBlock;
        Map<Point, Integer> block;

        do {
            indexBlock = Utils.random(9);
            block = maxConflictMatrix.get(indexBlock);
        } while (block.size() == 0);

        block = Utils.sortByValue(block);

        if (block.size() < 3) {
            List<Map.Entry<Point, Integer>> entryList = new ArrayList<>(block.entrySet());
            Point p = entryList.get(Utils.random(entryList.size())).getKey();

            int row = indexBlock / 3, col = indexBlock % 3;
            int i1 = Utils.random(3) + row * 3, i2 = Utils.random(3) + col * 3;
            while (gameState.fixed.get(i1).get(i2)) {
                i1 = Utils.random(3) + row * 3;
                i2 = Utils.random(3) + col * 3;
            }

            int value1 = state.get(p.x).get(p.y), value2 = state.get(i1).get(i2);
            state.get(p.x).set(p.y, value2);
            state.get(i1).set(i2, value1);
        } else {
            List<Map.Entry<Point, Integer>> entryList = new ArrayList<>(block.entrySet());
            Point p1 = entryList.get(Utils.random(entryList.size())).getKey();
            Point p2 = entryList.get(Utils.random(entryList.size())).getKey();

            int value1 = state.get(p1.x).get(p1.y), value2 = state.get(p2.x).get(p2.y);
            state.get(p1.x).set(p1.y, value2);
            state.get(p2.x).set(p2.y, value1);
        }

        heuristic();
    }

    public Individual crossover(Individual parent) {
        Individual child1 = new Individual(gameState), child2 = new Individual(gameState);
        int crossoverPoint = Utils.random(1, 8);

        for (int k = 0; k < crossoverPoint; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int i1 = i + (k / 3) * 3, i2 = j + (k % 3) * 3;
                    child1.state.get(i1).set(i2, state.get(i1).get(i2));
                    child2.state.get(i1).set(i2, parent.state.get(i1).get(i2));
                }
            }
        }

        for (int k = crossoverPoint; k < 9; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int i1 = i + (k / 3) * 3, i2 = j + (k % 3) * 3;
                    child1.state.get(i1).set(i2, parent.state.get(i1).get(i2));
                    child2.state.get(i1).set(i2, state.get(i1).get(i2));
                }
            }
        }

        child1.heuristic();
        child2.heuristic();

        if (child1.fitness < child2.fitness) return child1;
        else return child2;
    }

    public void printConsole(int no) {
        System.out.println(">> GENERATION: " + no);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0)
                    System.out.print(" " + state.get(i).get(j) + " ");
                else if (j == 3 || j == 6)
                    System.out.print("| " + state.get(i).get(j) + " ");
                else
                    System.out.print(state.get(i).get(j) + " ");
            }
            if (i == 2 || i == 5) {
                System.out.println();
                for (int k = 0; k < 23; k++) {
                    if (k == 7 || k == 15)
                        System.out.print("|");
                    else
                        System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println("--> Fitness: " + fitness);
    }

    @Override
    public int compareTo(@NotNull Individual o) {
        return Integer.compare(fitness, o.fitness);
    }
}
