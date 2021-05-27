class Solution{
  static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] res = new int[V];
        int[] inDegree = new int[V];
        for(int i = 0; i < V; i++){
            ArrayList<Integer> ne = adj.get(i);
            for(int e : ne){
                inDegree[e]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < V; i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }
        
        
        int index = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            res[index++] = curr;
            for(int i = 0; i < adj.get(curr).size(); i++){
                inDegree[adj.get(curr).get(i)]--;
                if(inDegree[adj.get(curr).get(i)] == 0){
                    q.add(adj.get(curr).get(i));
                }
                
            }
        }
        return res;
  }
}
