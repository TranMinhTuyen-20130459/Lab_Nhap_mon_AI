package Week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {

    int n;
    List<Integer> state;

    public Node(int n) {
        this.n = n;
        this.state = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Random r = new Random();
            this.state.add(r.nextInt(n));
        }
    }

    public Node(int n, List<Integer> state) {
        this.n = n;
        this.state = state;
    }

    public int getN() {
        return n;
    }

    public List<Integer> getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Node{" +
                "n=" + n +
                ", state=" + state +
                '}';
    }
}
