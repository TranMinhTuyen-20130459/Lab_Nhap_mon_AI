package Utils;

import Week1.Node;

public class Utils {

	public static void resetVisitedOfNode(Node[] arrNode) {

		for (Node n : arrNode) {

			n.visited = false;
		}
	}

}
