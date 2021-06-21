
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
static ArrayList<Integer> g[];
static int memo[][], lev[], log;
// memo -> dp, lev -> level, log -> log(n), g -> graph

static void dfs(int u, int p)
{
    memo[u][0] = p;
    for (int i = 1; i <= log; i++)
        memo[u][i] = memo[memo[u][i - 1]][i - 1];
    for (int v : g[u]) {
        if (v != p) {
            lev[v] = lev[u] + 1;
            dfs(v, u);
        }
    }
}

static int lca(int u, int v)
{
    if (lev[u] < lev[v]) {
        int temp = u;
        u = v;
        v = temp;
    }

    for (int i = log; i >= 0; i--) {
        if ((lev[u] - (int)Math.pow(2, i)) >= lev[v])
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
