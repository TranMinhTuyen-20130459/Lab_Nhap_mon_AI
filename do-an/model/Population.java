package mysudoku.model;
import mysudoku.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Population {
    private static final Random rand = new Random();
    public static final int POP_SIZE = 100;

    public final List<Individual> individuals = new ArrayList<>();
    private GameState gameState = null;
    private int generations = 0;    // used to count the number of genes produced

    private void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Individual individual = new Individual(gameState);
            individuals.add(individual);
        }
    }

    private void nextGeneration() {
        doCrossover();
        Collections.sort(individuals);
        individuals.subList(POP_SIZE, individuals.size()).clear();  // trim to POP_SIZE
        doMutation();
        Collections.sort(individuals);
    }

    private void doCrossover() {
        List<Individual> dads = new ArrayList<>(), moms = new ArrayList<>();
        if (rand.nextInt(2) == 1) {
            for (int i = 0; i < POP_SIZE / 2; i++)
                dads.add(individuals.get(i));
            for (int i = POP_SIZE / 2; i < POP_SIZE; i++)
                moms.add(individuals.get(i));
        } else {
            for (int i = 0; i < POP_SIZE / 2; i++)
                moms.add(individuals.get(i));
            for (int i = POP_SIZE / 2; i < POP_SIZE; i++)
                dads.add(individuals.get(i));
        }

        // crossover
        for (int i = 0; i < dads.size(); i++) {
            Individual child1 = dads.get(i).crossover(moms.get(i));
            Individual child2 = moms.get(i).crossover(dads.get(i));
            individuals.add(child1);
            individuals.add(child2);
        }
    }

    private void doMutation() {
        for (Individual i : individuals) {
            if (Utils.random(100) < 33)    // 33%
                i.mutate();
        }
    }

    public void setData(GameState gameState) {
        this.gameState = gameState;
        initPopulation();
    }

    public List<List<Integer>> solve() {
        while (individuals.get(0).fitness != 0) {
            nextGeneration();
            individuals.get(0).printConsole(++generations);
        }
        return individuals.get(0).state;
    }

    public void reset() {
        individuals.clear();
        gameState = null;
        generations = 0;
    }
}
