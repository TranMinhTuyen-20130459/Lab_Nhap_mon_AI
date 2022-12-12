package Week2;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
	public Node bfsUsingQueue(Node init, int goal) {

		Node result=null;
		Queue<Node> queue = new LinkedList<>();
		init.getNeighbours();				
		queue.offer(init);
		init.setVisited(true);

		while(queue.isEmpty() == false) {
			Node p = queue.poll();
			for(int i = 0; i < p.neighbours.size(); i++) {
				Node node = p.neighbours.get(i);
				node.getNeighbours();			
				if(node.isVisited() == false) {
					node.setParent(p);
					if(node.getState().size() == goal) {
						result = node;
						break;
					}else {
						queue.offer(node);
						node.setVisited(true);
					}
				}
			}
		}
		return result;
	}

}
