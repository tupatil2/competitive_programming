class Solution
{
    static public class Node implements Comparator<Node>{
        int v;
        int w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
        
        public Node(){}
        
        @Override
        public int compare(Node n1, Node n2){
            return n1.w-n2.w;
        }
        
        public int getV(){
            return v;
        }
        public int getW(){
            return w;
        }
    }
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        ArrayList<ArrayList<Node>> al = new ArrayList<>();
        for(int i = 0; i < V; i++){
            al.add(new ArrayList<Node>());
        }
        int index = 0;
        for(ArrayList<ArrayList<Integer>> l1 : adj){
            for(ArrayList<Integer> l2 : l1){
                al.get(index).add(new Node(l2.get(0),l2.get(1)));
            }
            index++;
        }
        
        int[] dist = new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[S] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(V,new Node());
        pq.add(new Node(S,0));
        // here node in pq means source,dist
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int u = node.getV();
            for(Node it : al.get(u)){
                if(dist[u] + it.getW() < dist[it.getV()]){
                    dist[it.getV()] = dist[u] + it.getW();
                    pq.add(new Node(it.getV(),dist[it.getV()]));
                }
            }
        }
        return dist;
    }
}
