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

// lca using binary lifting
public static void main(String[] args){
	int n = sc.nextInt();
	int log = (int)Math.ceil(Math.log(n) / Math.log(2));
	int[][] memo = new int[n + 1][log + 1];
	dfs(0,0,list,log,level,memo); // for 0-indexed
	// call lca from here. Also check if vertex is 0 index or 1 indexed and change it in dfs function accordingly.
}

static void dfs(int u, int p, List<List<Integer>> list, int log, int[] level, int[][] memo)
{
    memo[u][0] = p;
    for (int i = 1; i <= log; i++)
	memo[u][i] = memo[memo[u][i - 1]][i - 1];
    List<Integer> neighbours = list.get(u);
    for (int v : neighbours) {
	if (v != p) {
	    level[v] = level[u] + 1;
	    dfs(v, u, list, log, level, memo);
	}
    }
}

static int lca(int u, int v, List<List<Integer>> list, int[] level, int log, int[][] memo)
{
    if (level[u] < level[v]) {
	int temp = u;
	u = v;
	v = temp;
    }
    for (int i = log; i >= 0; i--) {
	if ((level[u] - (int)Math.pow(2, i)) >= level[v])
	    u = memo[u][i];
    }
    if (u == v)
	return u;
    for (int i = log; i >= 0; i--) {
	if (memo[u][i] != memo[v][i]) {
	    u = memo[u][i];
	    v = memo[v][i];
	}
    }
    return memo[u][0];
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
