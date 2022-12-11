package Week2;

import java.util.ArrayList;
import java.util.List;

public class Node {

	int n;
	List<Integer> state;
	List<Node> neighbours;
	boolean visited;
	Node parent;
	
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

	public void addNeighbours(Node nb) {
		this.neighbours.add(nb);
	}
	
	@Override
	public String toString() {
		return "Node [state=" + state + "]";
	}


	public static boolean isValid(List<Integer> state) {

		if (state.size() == 1) {
			return true;
		} else if (state.size() > 1) {

			int lastIndex = state.size() - 1; // index cuối cùng
			int valueLastIndex = state.get(lastIndex); // value của index cuối cùng

			for (int i = 0; i < state.size() - 1; i++) {

				// check hàng ngang
				if (state.get(i) == valueLastIndex) {

					return false;

				}
				/*
				 * check hàng chéo |index - index| = |giá trị - giá trị|
				 */
				if (Math.abs(lastIndex - i) == Math.abs(valueLastIndex - state.get(i))) {

					return false;

				}

			}

		}
		return true;

	}

	// kiểm tra có thể đặt con hậu mới vào bàn cờ hay không ?
	public List<Integer> place(int x) {

		List<Integer> stateCopy = new ArrayList<>();
		stateCopy.addAll(state);
		stateCopy.add(x);
		boolean isValidStateCopy = isValid(stateCopy);

		if (stateCopy.size() <= n && isValidStateCopy == true) {

			return stateCopy;

		} else
			return null;
	}

	/*
	 * trả về danh sách các Node con của Node đang xét
	 */
	public List<Node> getNeighbours() {

		if (state.size() == n)
			return null;
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

}
