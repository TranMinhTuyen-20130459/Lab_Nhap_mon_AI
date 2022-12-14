package Week1;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public String bfsUsingQueue_1(Node initial, Node goal) {

		String result = null;
		Queue<Node> queue = new LinkedList<>();
		queue.add(initial);
		initial.visited = true;

		if (initial.state == goal.state) {
			result = initial.state + "";

		}

		loop: while (!queue.isEmpty()) {

			Node p = queue.poll(); // tra ve node dau tien trong hang doi va xoa no di
			for (Node nbNode : p.neighbours) {

				if (nbNode.visited == false) {

					nbNode.parent = p;
					nbNode.visited = true;
					queue.add(nbNode);
				}

				if (nbNode.state == goal.state) {

					String s = "";
					while (nbNode != initial) {

						s = nbNode.state + " " + s;
						nbNode = nbNode.parent;

					}
					result = initial.state + " " + s;
					break loop;
				}

			}

		}
		return result;

	}

	public String bfsUsingQueue_2(Node init, Node goal) {

		String result = null;
		Node s = init;
		if (s.state == goal.state) {
			result = s.state + "";

		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(s);
		s.visited = true;

		loop: while (!queue.isEmpty()) {

			Node p = queue.poll();

			for (Node nbNode : p.getNeighbours()) {

				if (nbNode.visited == false) {

					nbNode.parent = p;
					nbNode.visited = true;
					queue.add(nbNode);
				}

				if (nbNode.state == goal.state) {

					String text = "";
					while (nbNode != init) {
						text = nbNode.state + " " + text;
						nbNode = nbNode.parent;
					}

					result = init.state + " " + text;
					break loop;

				}

			}

		}
		return result;

	}

}
