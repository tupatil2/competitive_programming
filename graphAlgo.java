//* Bellman Ford Algo //*

// Helps in detecting negative cycle.
// Works with negative edges.
// Logic: Just relax every edge N-1 times. Relax means dist calculation.
// Time: O(N*M), Space: O(N)

public int isNegativeWeightCycle(int n, int[][] arr)
{
	int len = arr.length;
	int[] dist = new int[n];
	Arrays.fill(dist,Integer.MAX_VALUE);
	dist[0] = 0;
	
	for(int i = 0; i < n-1; i++){
		for(int j = 0; j < len; j++){
			int u = arr[j][0];
			int v = arr[j][1];
			int w = arr[j][2];
			if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]){
				dist[v] = dist[u] + w;
			}
		}
	}
	
	for(int i = 0; i < len; i++){
		int u = arr[i][0];
		int v = arr[i][1];
		int w = arr[i][2];
		if(dist[u] + w < dist[v]){
			return 1; // negative cycle exist
		}
	}
	
	// shortest distance 
	for(int i = 0; i < len; i++){
		System.out.println(0 + " " + i + " shortest dist is " + dist[i]);
	}
	
	return 0;
}


//* Djisktra’s Algorithm *//

// Doesn't work for negative weights
// Logic: Node(Vertex, Distance), BFS, PQ
// Time: O((N+E)logN), Space: O(N)

static class Node{
	int v;
	int w;
	public Node(int v, int w){
		this.v = v;
		this.w = w;
	}
	
	public int getW(){
		return w;
	}
	
	public int getV(){
		return v;
	}
}
static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
{
	ArrayList<ArrayList<Node>> list = new ArrayList<>();
	for(int i = 0; i < V; i++){
		list.add(new ArrayList<Node>());
	}
	int node = 0;
	for(ArrayList<ArrayList<Integer>> e1 : adj){
		for(ArrayList<Integer> e2 : e1){
			list.get(node).add(new Node(e2.get(0),e2.get(1)));
		}
		node++;
	}
	
	int[] dist = new int[V];
	Arrays.fill(dist,Integer.MAX_VALUE);
	dist[S] = 0;
	PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> x.w-y.w);
	pq.add(new Node(S,0));
	
	while(!pq.isEmpty()){
		Node curr = pq.poll();
		int v = curr.getV();
		for(Node it : list.get(v)){
			if(dist[v] + it.getW() < dist[it.getV()]){
				dist[it.getV()] = dist[v] + it.getW();
				pq.add(new Node(it.getV(),dist[it.getV()]));
			}
		}
	}
	return dist;
}

//* SCC(using KosaRaju’s algo) *//

// Time: O(V+E), Space: O(V+E)

public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
{
	// topo sort
	int[] vis = new int[V];
	Stack<Integer> stack = new Stack<>();
	for(int i = 0; i < V; i++){
		if(vis[i] == 0){
			dfs(i,V,adj,stack,vis);
		}
	}
	
	// transpose the graph
	ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
	for(int i = 0; i < V; i++) transpose.add(new ArrayList<>());
	
	for(int i = 0; i < V; i++){
		vis[i] = 0;
		for(int e : adj.get(i)){
			transpose.get(e).add(i);
		}
	}
	
	// DFS on transpose with topo's stack
	int count = 0;
	while(!stack.isEmpty()){
		int curr = stack.pop();
		if(vis[curr] == 0){
			count++;
			revDfs(curr,transpose,vis);
			// System.out.println();
		}
	}
	
	return count;
}

public void revDfs(int curr, ArrayList<ArrayList<Integer>> transpose, int[] vis){
	
	vis[curr] = 1;
	// prints nodes
	// System.out.print(curr + " ");
	for(int e : transpose.get(curr)){
		if(vis[e] == 0){
			revDfs(e,transpose,vis);
		}
	}
}

public void dfs(int curr, int V, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack, int[] vis){
	vis[curr] = 1;
	for(int e : adj.get(curr)){
		if(vis[e] == 0){
			dfs(e,V,adj,stack,vis);
		}
	}
	stack.add(curr);
}


//* MST using Kruskal’s Algo  *//

// Logic: DSU
// Time: O(Mlog(M)), Space: O(N)

static void kruskalsUtil(){
	List<Node> list = new ArrayList<>();
	list.add(new Node(0,1,10));
	list.add(new Node(0,2,6));
	list.add(new Node(0,3,5));
	list.add(new Node(1,2,15));
	list.add(new Node(2,3,4));
	
	int cost = kruskals(list);
	out.println(cost);
}

static int kruskals(List<Node> list){
	int n = list.size();
	
	// sort the nodes in inc order w.r.t weight
	Collections.sort(list,(x,y) -> x.wt-y.wt);
	
	// create DSU
	DSU dsu = new DSU(n);
	
	int cost = 0;
	for(Node it : list){
		int u = it.u;
		int v = it.v;
		int wt = it.wt;
		// if par is not equal, merge them.
		if(dsu.find(u) != dsu.find(v)){
			cost += wt;
			// prints edges of mst
			System.out.println(u + " " + v + " " + wt);
			dsu.merge(u,v);
		}
	}
	
	return cost;
}

static class Node{
	int u;
	int v;
	int wt;
	public Node(int u, int v, int wt){
		this.u = u;
		this.v = v;
		this.wt = wt;
	}
}

static class DSU
{
	public int[] dsu;
	public int[] size;

	public DSU(int N)
	{
		dsu = new int[N+1];
		size = new int[N+1];
		for(int i=0; i <= N; i++)
		{
			dsu[i] = i;
			size[i] = 1;
		}
	}
	//with path compression, no find by rank
	public int find(int x)
	{
		return dsu[x] == x ? x : (dsu[x] = find(dsu[x]));
	}
	public void merge(int x, int y)
	{
		int fx = find(x);
		int fy = find(y);
		dsu[fx] = fy;
	}
	public void merge(int x, int y, boolean sized)
	{
		int fx = find(x);
		int fy = find(y);
		size[fy] += size[fx];
		dsu[fx] = fy;
	}
}

//* Floyd Warshall Algorithm  *//

// Q. finds the shortest path b/w all the pairs. -1 denotes that there is no edge b/w i and j in graph.

// Logic: just find --> dist(i,k) + dist(k,j) < dist(i,j)
// Time: O(V^3), Space: O(1)

public void shortest_distance(int[][] dist)
{
	int V = dist.length;
	for(int k = 0; k < V; k++){
		for(int i = 0; i < V; i++){
			for(int j = 0; j < V; j++){
				if(dist[i][k] == -1 || dist[k][j] == -1){ // no edges
					continue;
				}
				else if(dist[i][j] == -1){ // no direct edge 
					dist[i][j] = dist[i][k] + dist[k][j];
				}
				else if(dist[i][k] + dist[k][j] < dist[i][j]){
					dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
	}
}
