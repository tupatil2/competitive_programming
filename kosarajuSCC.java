class Solution
{
   
    public void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj,
            Stack<Integer> stack){
        vis[node] = 1;
        for(int e : adj.get(node)){
            if(vis[e] == 0){
                dfs(e,vis,adj,stack);
            }
        }
        stack.add(node);
    }
    
    public void revDfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj){
        vis[node] = 1;
        for(int e : adj.get(node)){
            if(vis[e] == 0){
                revDfs(e,vis,adj);    
            }
        }
    }
    
    public int kosaraju(int n, ArrayList<ArrayList<Integer>> adj) {
        
        // Step 1: Topo sort
        int[] vis = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            if(vis[i] == 0){
                dfs(i,vis,adj,stack);
            }
        }
        
        // Step 2: Tranpose the graph
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for(int i = 0; i< n; i++){
            vis[i] = 0;
            transpose.add(new ArrayList<>());
        }
        
        for(int i = 0; i< n; i++){
            for(int e : adj.get(i)){
                transpose.get(e).add(i);
            }
        }
        
        // Step 3: Dfs on Transpose
        int count = 0;
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(vis[node] == 0){
                count++;
                revDfs(node,vis,transpose);
            }
        }
        return count;
    }
}
