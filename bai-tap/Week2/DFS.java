package Week2;

import java.util.Stack;

public class DFS {
	
	public Node dfsUsingStack(Node init,int goal) {
		Node result = null;
		Stack<Node> stack = new Stack<>();
		init.getNeighbours();
		stack.add(init);
		init.visited = true;
		while(stack.isEmpty() == false) {
			Node p = stack.pop();
			for(int i = 0; i < p.neighbours.size(); i++) {
				Node node = p.neighbours.get(i);
				node.getNeighbours();
				if(node.visited == false) {
					node.parent = p;
					if(node.state.size() == goal) {
						result = node;
					}else {
						stack.add(node);
						node.visited = true;
					}
				}
			}
		}
		return result;
	}
	
	
}
