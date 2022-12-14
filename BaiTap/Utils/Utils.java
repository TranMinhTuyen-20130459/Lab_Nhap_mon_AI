package Utils;

import Week1.Node;

import java.util.List;

public class Utils {

    public static void resetVisitedOfNode(Node[] arrNode) {

        for (Node n : arrNode) {

            n.visited = false;
        }
    }

    public static boolean isValidPro(List<Integer> state) {

        /*
         * kiểm tra một state có hợp lệ hay không ?
         * biết rằng chỉ có:
         * -> tất cả các column đều chưa được kiểm tra
         */

        if (state.size() == 1) {
            return true;
        } else if (state.size() > 1) {

            int lastIndex = state.size() - 1; // index cuối cùng

            for (int i = lastIndex; i > 0; i--) {

                int valueLastIndex = state.get(i); // value của index cuối cùng

                for (int k = 0; k < i; k++) {

                    // check hàng ngang
                    if (state.get(k) == valueLastIndex) return false;

                    /*
                     * check hàng chéo |index - index| = |giá trị - giá trị|
                     */
                    if (Math.abs(i - k) == Math.abs(valueLastIndex - state.get(k)))
                        return false;

                }
            }


        }
        return true;

    }

    public static boolean isValid(List<Integer> state) {

        /*
         * kiểm tra một state có hợp lệ hay không ?
         * biết rằng chỉ có :
         * -> column cuối cùng trong state là chưa được kiểm tra
         * -> còn tất cả các column trước đều đã hợp lệ
         */

        if (state.size() == 1) {
            return true;
        } else if (state.size() > 1) {

            int lastIndex = state.size() - 1;
            int valueLastIndex = state.get(lastIndex);

            for (int i = 0; i < lastIndex; i++) {

                // check hàng ngang
                if (state.get(i) == valueLastIndex) return false;

                /*
                 * check hàng chéo |index-index| = |giá trị - giá trị|
                 */

                if (Math.abs(i - lastIndex) == Math.abs(state.get(i) - valueLastIndex)) {

                    return false;
                }

            }

        }
        return true;
    }

}
