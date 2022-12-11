package Week1;

import java.util.Stack;

public class DFS {

	public String dfsUsingStack_1(Node init, Node goal) {

		String result = null;
		Stack<Node> stack = new Stack<>();
		stack.push(init);
		init.visited = true;

		if (init.state == goal.state) {
			result = init.state + "";
		}

		loop: while (!stack.isEmpty()) {

			Node p = stack.pop();

			for (Node nbNode : p.getNeighbours()) {

				if (nbNode.visited == false) {

					nbNode.parent = p;
					nbNode.visited = true;
					stack.push(nbNode);
				}

				// back trackking
				if (nbNode.state == goal.state) {

					String s = "";
					while (nbNode != init) {

						s = nbNode.state + " " + s;
						nbNode = nbNode.parent;

					}

					result = init.state + " " + s;
					break loop;
				}

			}

		}
		return result;
	}

}
