package Week1;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public int state;
	public boolean visited;
	public List<Node> neighbours;
	public Node parent;

	// constructor
	public Node(int state) {
		super();
		this.state = state;
		this.neighbours = new ArrayList<>();
		this.parent = null;
	}

	public void addNeighbours(Node nodeNeighbour) {

		neighbours.add(nodeNeighbour);
	}

	public List<Node> getNeighbours() {
		return this.neighbours;
	}

	public void resetVisited() {

		this.neighbours.forEach((nbNode) -> {

			nbNode.visited = false;
		});
	}

}
