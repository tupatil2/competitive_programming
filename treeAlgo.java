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


// Q1-----------------------------------------------
def upgrade(place,uid):
    indx = solution.status[place]
    node = solution.lst[indx]
    if node.locked==True:
        return False
    temp = indx
    queue = []
    queue.append(temp)
    m = solution.child
    while queue:
        j = queue.pop(0)
        if solution.lst[j].uid != uid and j!=indx:
            return False
        for k in range(j*m+1,j*m+m+1):
            if k<=solution.n-1:
                queue.append(k)
            
        
    temp = indx
    queue = []
    queue.append(temp)
    cchild = 0
    while queue:
        cchild+=1
        j = queue.pop(0)
        solution.lst[j].countDesc = 0
        solution.lst[j].uid = None
        solution.lst[j].locked = False
        for k in range(j*m+1,j*m+m+1):
            if k<=solution.n-1:
                queue.append(k)
    node.countDesc  = 1
    node.locked = True
    node.uid = uid

    temp = indx
    while temp>=0:
        if temp!=indx:
            solution.lst[temp].countDesc +=1
            solution.lst[temp].countDesc -=cchild
        temp = (temp-1)//solution.child
    return True

def unlock(place,uid):
    indx = solution.status[place]
    node = solution.lst[indx]
    if node.locked == False or node.uid!=uid:
        return False
    else:
        node.locked = False
        node.countDesc +=1
        node.uid = None
        temp = indx
        
        while temp>0:
            solution.lst[temp].countDesc -=1
            temp = (temp-1)//solution.child
        return True

def lock(place,uid):
    indx = solution.status[place]
    node = solution.lst[indx]
    if node.locked==True:
        return False
    elif node.countDesc>0:
        return False
    else:
        temp = indx
        while temp>=0:
            if solution.lst[temp].locked == True:
                return False
            temp = (temp-1)//solution.child
        node.locked = True
        node.countDesc-=1
        node.uid = uid
        
        temp = indx
        while temp>=0:
            solution.lst[temp].countDesc +=1
            temp = (temp-1)//solution.child
        return True

class Node:
    def __init__(self,data):
        self.data = data
        self.locked = False
        self.countDesc = 0
        self.uid = None
    

class Narray:
    def __init__(self,n,child):
        self.lst = []
        self.status = {}
        self.child = child
        self.n = n

n = int(input())
cn = int(input())

apis = int(input())
solution = Narray(n,cn)

for i in range(n):
    place = input()
    node = Node(place)
    solution.status[node.data] = i
    solution.lst.append(node)

for _ in range(apis):
    op,place,uid = map(str,input().split())
    op = int(op)
    uid = int(uid)
    if op==1:
        print(lock(place,uid))
    elif op==2:
        print(unlock(place,uid))
    elif op==3:
        print(upgrade(place,uid))
		
		
// -Q2-----------------------------------------------

    static int mod = 1000000007;
    static Map<String,Node> map;
    static String[] nodes;
    static int n, m, q;
	public static void main(String[] args){
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        nodes = new String[n];
        map = new HashMap<>();
        for(int i = 0; i < n; i++){
            nodes[i] = sc.next();
            map.put(nodes[i], new Node(nodes[i],i));
        }
        
        while(q-- > 0){
            int op = sc.nextInt();
            String place = sc.next();
            int uid = sc.nextInt();
            if(!map.containsKey(place)){ // if place doesn't exists
                out.println(false);
            }
            else if(op == 1){ // lock
                out.println(lock(place,uid));
            }
            else if(op == 2){ // unlock
                out.println(unlock(place,uid));
            }
            else { // upgrade
                out.println(upgrade(place,uid));
            }
        }
        
        out.close();
	}
    
    static class Node {
        String place;
        Set<Integer> uids;
        Set<Node> lockedNodes;
        boolean isLocked;
        int lockedDesc; // counts the locked descendents 
        int index;
        public Node(String place, int index){
            this.index = index;
            this.place = place;
            this.isLocked = false;
            uids = new HashSet<>();
            lockedNodes = new HashSet<>();
        }
    }
    
    private static boolean lock(String place, int uid){
        Node curr = map.get(place);
        
        if(curr.isLocked){ // if curr is already locked
            return false;
        }
        else if(curr.lockedDesc > 0){ // if any desc is already locked
            return false;
        }
        
        // check if the ancestor is locked or not - O(h)
        int currIndex = curr.index;
        while(currIndex >= 0){
            String anc = nodes[currIndex];
            Node ancNode = map.get(anc);
            if(ancNode.isLocked){
                return false;
            }
            if(currIndex == 0) break;
            currIndex = (currIndex-1)/m;
        }
        
        // now we can lock the curr node as ancestor and desc are not locked
        curr.isLocked = true;
        currIndex = curr.index;
        while(currIndex >= 0){
            String anc = nodes[currIndex];
            Node ancNode = map.get(anc);
            if(!anc.equals(place)){
                ancNode.lockedNodes.add(curr);
            }
            ancNode.lockedDesc += 1;
            if(currIndex == 0) break;
            currIndex = (currIndex-1)/m;
        }
        curr.lockedDesc -= 1;
        curr.uids.add(uid);
        
        return true;
    }
    
    private static boolean unlock(String place, int uid){
        Node curr = map.get(place);
        
        // if curr is not locked
        if(!curr.isLocked){
            return false;
        }
        // if curr is locked but not by uid
        if(!curr.uids.contains(uid)){
            return false;
        }
        
        // remove desc count from the ancestors
        int currIndex = curr.index;
        while(currIndex >= 0){
            String anc = nodes[currIndex];
            Node ancNode = map.get(anc);
            if(!anc.equals(place)){
                ancNode.lockedNodes.remove(curr);
            }
            ancNode.lockedDesc -= 1;
            if(currIndex == 0) break;
            currIndex = (currIndex-1)/m;
        }
        
        curr.isLocked = false;
        curr.uids.remove(uid);
        
        return true;
    }
    
    private static boolean upgrade(String place, int uid){
        Node curr = map.get(place);
        
        if(curr.isLocked){ // already locked 
            return false;
        }
        
        // get all the lockedNodes and check
        Set<Node> lockedNodes = curr.lockedNodes;
        int countNodesLockedByUid = 0;
        for(Node node : lockedNodes){
            if(node.uids.size() != 1 || !node.uids.contains(uid)){
                return false;
            }
            countNodesLockedByUid++;
        }
        
        if(countNodesLockedByUid == 0) return false; // no descendent is present
        
        // unlock all the lockedNodes
        List<Node> list = new ArrayList<>(lockedNodes);
        for(Node node : list){
            unlock(node.place,uid);
        }
        
        // lock the main Node
        lock(place,uid);
        
        return true;
    }
}
