package Week2;

import Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestWeek2 {

    public static void main(String[] args) {

        Integer[] arr = {1, 3, 0, 2};
        List<Integer> state = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Kiểm tra state " + state + " có hợp lệ hay không ?");
        System.out.println(Utils.isValid(state));
        System.out.println("--------------------------------------");
        Node node_1 = new Node(4, state);
        System.out.println("State ban đầu");
        System.out.println(node_1.getState());
        System.out.println("--------------------------------------");
        System.out.println("Thêm con hậu mới vào vị trí x");
        System.out.println(node_1.place(3));
        System.out.println("--------------------------------------");
        Node node_2 = new Node(8);
        System.out.println(node_2.getNeighbours());

    }

}
