// check levels of each node
public static void level(int curr, int[] level, List<List<Integer>> list, int parent, int currLevel) {
	level[curr] = currLevel;
	List<Integer> neighbours = list.get(curr);
	for(int it : neighbours) {
		if(it != parent) {
			level(it,level,list,curr,currLevel+1);
		}
	}
}
	

// check sub-tree Size and make sure to do -1 on whole "size" array	
private static void subTreeSize(int curr, int[] size, List<List<Integer>> list, int parent) {
  List<Integer> neighbours = list.get(curr);
  for(int e : neighbours) {
    if(e != parent) {
      subTreeSize(e,size,list,curr);
      size[curr] += size[e];
    }
  }
  size[curr] += 1;
}

// calculate the centroid(s) in tree. And we can have atleast 1 and atmost 2 centroid in tree.
static List<Integer> cent = new ArrayList<>();
private static void centroid(int curr, int[] size, List<List<Integer>> list, int parent) {
  List<Integer> neighbours = list.get(curr);
  boolean is = true;
  int n = size.length;
  for(int e : neighbours) {
    if(e != parent) {
      centroid(e,size,list,curr);
      size[curr] += size[e];
      if(size[e] > n/2) is = false;
    }
  }
  size[curr] += 1;
  if(size[curr] < n/2) is = false;
  if(is) cent.add(curr);
}

// lca using binary lifting - helps to find lca of multiple nodes in nlogn
static int[][] lca;
static int[] level;

static void binaryLiftingForLCA(List<List<Integer>> list, int n, PrintWriter out, FastScanner sc){
	// preprocessing
	int maxN = log2(n);
	lca = new int[n][maxN+1];
	preprocess(list,maxN,n);
	
	// calculate the level
	level = new int[n];
	level(0, level, list, -1, 0);

	// handle the query of nodes
	int q = sc.nextInt();
	while(q-- > 0){
	    // two nodes whose lca we have to calculate
	    int a = sc.nextInt()-1;
	    int b = sc.nextInt()-1;
	    int lcaNode = lca(a,b,maxN);
	    // int dist = level[a]-level[lcaNode] + level[b]-level[lcaNode];    
	}    
}

static int lca(int a, int b, int maxN){
	if(level[a] > level[b]) {
	    int temp = a;
	    a = b;
	    b = temp;
	}

	int d = level[b] - level[a];

	while(d > 0){
	    int i = log2(d);
	    b = lca[b][i];
	    d -= (1<<i);
	}

	if(a == b){
	    return a;
	}

	for(int i = maxN; i >= 0; i--){
	    if(lca[a][i] != -1 && lca[a][i] != lca[b][i]){
		a = lca[a][i];
		b = lca[b][i];
	    }
	}

	// return parent of a
	return lca[a][0];
}

static void preprocess(List<List<Integer>> list, int maxN, int n){
	// find the 1st parent of every node
	dfs(list,0,-1);

	// find all the "power of 2s" parent of every node
	for(int j = 1; j <= maxN; j++){
	    for(int i = 0; i < n; i++){
		if(lca[i][j-1] != -1){
		    int par = lca[i][j-1];
		    lca[i][j] = lca[par][j-1];
		}
	    }
	}
}

static void dfs(List<List<Integer>> list, int curr, int par){
	lca[curr][0] = par;
	List<Integer> neighbours = list.get(curr);
	for(int e : neighbours){
	    if(e == par){
		continue;
	    }
	    dfs(list,e,curr);
	}
}

public static void level(int curr, int[] level, List<List<Integer>> list, int parent, int currLevel) {
	level[curr] = currLevel;
	List<Integer> neighbours = list.get(curr);
	for(int it : neighbours) {
	    if(it != parent) {
		level(it,level,list,curr,currLevel+1);
	    }
	}
}

//kth ancestor of the node
class TreeAncestor {
    
    private int P;
    private int[][] dp;

    public TreeAncestor(int n, int[] parent) {
        P = (int) (Math.log(n)/Math.log(2)) + 1;
        dp = new int[P][n];

        for(int i = 0; i < P; ++i) {
            Arrays.fill(dp[i], -1);
        }

        dp[0] = parent;

        for(int p = 1; p < P; ++p) {
            for(int i = 0; i < n; ++i) {
                if(dp[p - 1][i] != - 1) {
                    dp[p][i] = dp[p - 1][dp[p - 1][i]];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for(int p = P - 1; p >= 0; --p) {
            if(dp[p][node] != -1 && (1 << p) <= k) {
                node = dp[p][node];
                k -= (1 << p);
            }
        }
        return k > 0 ? -1 : node;
    }
}
