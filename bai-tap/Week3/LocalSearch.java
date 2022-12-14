package Week3;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class LocalSearch {

    public static int checkHorizontal(Node node) {
        int conflict = 0;
        List<Integer> state = node.getState();
        for (int i = 0; i < state.size() - 1; i++) {

            for (int k = i + 1; k < state.size(); k++) {
                if (state.get(i) == state.get(k)) {
                    conflict += 1;
                }
            }
        }

        return conflict; // trả về tổng xung đột ngang trong một node
    }

    public static int checkDiagonal(Node node) {
        int conflict = 0;
        List<Integer> state = node.getState();
        for (int i = 0; i < state.size() - 1; i++) {

            int nextIndex = i + 1;
            /*
             * |index-index| = |value-value| => dùng để check xung đột theo đường chéo
             */
            if (Math.abs(i - nextIndex) == Math.abs(state.get(i) - state.get(nextIndex))) {
                conflict += 1;
            }

        }
        return conflict; // trả về tổng xung đột chéo trong một node
    }

    public static int heuristic(Node node) {
        int conflictHorizontal = LocalSearch.checkHorizontal(node);
        int conflictDiagonal = LocalSearch.checkDiagonal(node);
        return conflictHorizontal + conflictDiagonal;
        /*
         * đánh giá mức độ hợp lý của một node
         * trả về tổng xung đột ngang và chéo
         */
    }

    public static int tryMovingOneQueen(Node node, int x, int y) {
        List<Integer> state = node.getState();
        state.set(y, x); // => thay đổi vị của quân hậu (đang ở bất kì dòng nào) tại cột y thành dòng x
        Node new_node = new Node(node.getN(),state);
        return LocalSearch.heuristic(new_node);
    }

    public static SortedMap<Integer, Node> generateNeighbours(Node node) {
        List<Integer> state = node.getState(); // state ban đầu
        SortedMap<Integer, Node> result = new TreeMap<>();
        int nQueens = node.getN();
        for (int col = 0; col < nQueens; col++) {
            for (int row = 0; row < nQueens; row++) {


                List<Integer> new_state = new ArrayList<>(node.getState());
                Node new_node = new Node(node.getN(),new_state); // new_node bản chất chính là node
                int heuristic = LocalSearch.tryMovingOneQueen(new_node, row, col);
                result.put(heuristic,new_node);

            }
        }
        return result;
    }

    public static void run() {

        Node initial = new Node(5); // hoặc 5,6,7,8
        if (heuristic(initial) == 0) { //goal
            System.out.println(initial.state);
            return;
        }
        System.out.println("Initial state is:" + initial.state +" have heuristic: "+ heuristic(initial) );
        Node node = initial;
        SortedMap<Integer, Node> neighbours = generateNeighbours(node);
        Integer bestHeuristic = neighbours.firstKey();
        while (bestHeuristic < heuristic(node)) {

            node = neighbours.get(bestHeuristic);
            neighbours = generateNeighbours(node);
            bestHeuristic = neighbours.firstKey();
        }

        if (heuristic(node) == 0) {
            System.out.println("Goal is: " + node.state);
        } else {

            System.out.println("Cannot find goal state ! Best state is: " + node.state);
        }
    }

    public static void main(String[] args) {

        LocalSearch.run();
    }


}
