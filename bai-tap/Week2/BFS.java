package Week2;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
	public Node bfsUsingQueue(Node init,int goal) {
		Node result=null;
		Queue<Node> queue = new LinkedList<>();
		init.getNeighbours();				
		queue.offer(init);
		init.visited = true;
		while(queue.isEmpty() == false) {
			Node p = queue.poll();
			for(int i = 0; i < p.neighbours.size(); i++) {
				Node node = p.neighbours.get(i);
				node.getNeighbours();			
				if(node.visited == false) {
					node.parent = p;
					if(node.state.size() == goal) {
						result = node;
						break;
					}else {
						queue.offer(node);
						node.visited = true;
					}
				}
			}
		}
		return result;
	}

}
