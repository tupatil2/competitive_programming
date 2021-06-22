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
