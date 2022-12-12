package Week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLocalSearch {

    public static void TestCheckConflict() {

        Integer[] arr = {1, 3, 0, 2};
        List<Integer> state = new ArrayList<>(Arrays.asList(arr));
        Node node = new Node(4, state);
        System.out.println("Trạng thái của node:");
        System.out.println(node.getState());
        System.out.println("Check xung đột theo hàng ngang:");
        System.out.println(LocalSearch.checkHorizontal(node));
        System.out.println("Check xung đột theo hàng chéo:");
        System.out.println(LocalSearch.checkDiagonal(node));
        System.out.println("Tổng xung đột của node");
        System.out.println(LocalSearch.heuristic(node));

    }

    public static void TestTryMovingOneQueen(){

        Integer[] arr = {1, 3, 0, 2};
        List<Integer> state = new ArrayList<>(Arrays.asList(arr));
        Node node = new Node(4, state);
        System.out.println("Node ban đầu: " + node);
        System.out.println(LocalSearch.tryMovingOneQueen(node,3,2));
        System.out.println("Node lúc sau: " + node);

    }

    public static void main(String[] args) {

        //TestCheckConflict();
        TestTryMovingOneQueen();

    }
}
