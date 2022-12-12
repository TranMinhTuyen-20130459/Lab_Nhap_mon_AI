package Week3;

import java.util.List;
import java.util.SortedMap;

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
        state.set(y,x); // => thay đổi vị của quân hậu (đang ở bất kì dòng nào) tại cột y thành dòng x
        Node new_node = new Node(node.getN(),state);
        return LocalSearch.heuristic(new_node);
    }

    public static SortedMap<Integer, Node> generateNeighbours(Node node) {
        SortedMap<Integer, Node> result = null;
        return result;
    }


}
