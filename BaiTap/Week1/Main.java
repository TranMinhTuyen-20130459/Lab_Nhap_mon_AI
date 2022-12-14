package Week1;

import Utils.Utils;

public class Main {

    public static void main(String[] args) {

        Node node40 = new Node(40);
        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);
        Node node60 = new Node(60);
        Node node50 = new Node(50);
        Node node70 = new Node(70);

        Node[] arrNode = {node10, node20, node30, node40, node50, node60, node70};

        node40.addNeighbours(node20);
        node40.addNeighbours(node10);

        node20.addNeighbours(node50);
        node20.addNeighbours(node30);
        node20.addNeighbours(node10);
        node20.addNeighbours(node60);

        node10.addNeighbours(node30);

        node30.addNeighbours(node60);

        node50.addNeighbours(node70);
        node60.addNeighbours(node70);


        BFS bfs = new BFS();

        Utils.resetVisitedOfNode(arrNode);
        System.out.println(bfs.bfsUsingQueue_1(node10, node70));
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(bfs.bfsUsingQueue_1(node20, node70));
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(bfs.bfsUsingQueue_1(node70, node40));
        Utils.resetVisitedOfNode(arrNode);

        System.out.println("---------------------------------");

        Utils.resetVisitedOfNode(arrNode);
        System.out.println(bfs.bfsUsingQueue_2(node40, node70));
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(bfs.bfsUsingQueue_2(node20, node70));
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(bfs.bfsUsingQueue_2(node70, node40));
        Utils.resetVisitedOfNode(arrNode);

        System.out.println("---------------------------------");

        DFS dfs = new DFS();
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(dfs.dfsUsingStack_1(node10, node70));
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(dfs.dfsUsingStack_1(node20, node70));
        Utils.resetVisitedOfNode(arrNode);
        System.out.println(dfs.dfsUsingStack_1(node70, node40));
        Utils.resetVisitedOfNode(arrNode);

    }

}
