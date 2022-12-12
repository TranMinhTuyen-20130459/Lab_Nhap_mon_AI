package Week2;

import Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

    private int n; // số quân hậu cần được đặt
    private List<Integer> state; // vị trí các quân hậu hiện tại trên bàn cờ
    List<Node> neighbours; // danh sách các Node con của node đang xét
    private Node parent;
    private boolean visited;

    public Node(int n) {
        this.n = n;
        this.state = new ArrayList<>();
        this.neighbours = new ArrayList<>();
    }

    public Node(int n, List<Integer> state) {
        this.n = n;
        this.state = state;
        this.neighbours = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Node [state=" + state + "]";
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Integer> getState() {
        return state;
    }

    public void setState(List<Integer> state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addNeighbours(Node nb) {
        this.neighbours.add(nb);
    }

    public List<Node> getNeighbours() {

        /*
         * trả về danh sách các Node con của Node đang xét
         */

        if (state.size() == n) return null;
        else if (state.size() < n) {

            for (int i = 0; i < n; i++) {

                List<Integer> new_state = place(i);
                if (new_state != null) {

                    Node new_Node = new Node(n, new_state);
                    this.addNeighbours(new_Node);
                }

            }
        }
        return this.neighbours;
    }

    public List<Integer> place(int x) {

        /*
         * kiểm tra có thể đặt con hậu mới vào bàn cờ hay không ?
         */

        List<Integer> stateCopy = new ArrayList<>();
        stateCopy.addAll(getState());
        stateCopy.add(x);
        boolean isValidStateCopy = Utils.isValid(stateCopy);

        if (isValidStateCopy == true) {

            return stateCopy; // => trả về danh sách các vị trí quân hậu mới nếu hợp lệ

        } else
            return null;
    }

}
