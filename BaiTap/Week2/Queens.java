package Week2;

public class Queens {

	private int n;
	private Node goal;

	public Queens(int n) {
		this.n = n;
	}

	public void dfs() {
		DFS dfs = new DFS();
		this.goal = dfs.dfsUsingStack(new Node(n), n);
	}

	public void bfs() {
		BFS bfs = new BFS();
		this.goal = bfs.bfsUsingQueue(new Node(n), n);
	}

	public static void main(String[] args) {
		
		Queens q = new Queens(8);
		q.dfs();
		System.out.println(q.goal);
		q.bfs();
		System.out.println(q.goal);

	}

}
