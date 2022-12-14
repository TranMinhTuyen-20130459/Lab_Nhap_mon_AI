package Week2;

import java.util.Stack;

public class DFS {
	
	public Node dfsUsingStack(Node init,int goal) {

		Node result = null;
		Stack<Node> stack = new Stack<>();
		init.getNeighbours();
		stack.add(init);
		init.setVisited(true);

		while(stack.isEmpty() == false) {
			Node p = stack.pop();
			for(int i = 0; i < p.neighbours.size(); i++) {
				Node node = p.neighbours.get(i);
				node.getNeighbours();
				if(node.isVisited() == false) {
					node.setParent(p);
					if(node.getState().size() == goal) {
						result = node;
					}else {
						stack.add(node);
						node.setVisited(true);
					}
				}
			}
		}
		return result;
	}
	
	
}
