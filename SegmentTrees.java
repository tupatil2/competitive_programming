class SegTree{
    static int[] seg;
    static int[] arr;
    static int n;
    
    public SegTree(int[] arr, int n){
        // approx 4 * n is sufficient size of segment tree
        this.seg = new int[4 * n + 1];
        this.arr = arr;
        this.n = n;
        build(0, 0, n-1);
    }
    
    static void build(int index, int low, int high){
        if(low == high){
            seg[index] = arr[low];
            return;
        }
        int mid = low + (high-low)/2;
        build(2 * index + 1, low, mid);
        build(2 * index + 2, mid+1, high);
        seg[index] = Math.min(seg[2*index+1],seg[2*index+2]);
    }
    
    static void update(int i, int val) {
        update(0, 0, n-1, i, val);
    } 
    
    static void update(int index, int low, int high, int i, int val){
        if(low == high){
            seg[index] = val;
            return;
        }
        int mid = low + (high-low)/2;
        if(i <= mid){
            update(2 * index + 1, low, mid, i, val);
        }
        else {
            update(2 * index + 2, mid + 1, high, i, val);
        }
        seg[index] = Math.min(seg[2 * index + 1], seg[2 * index + 2]);
    }
    
    static int findMin(int l, int r) {
        return findMin(0, 0, n-1, l, r);
    } 

    static int findMin(int index, int low, int high, int l, int r){
        // no intersection
        if(r < low || l > high){
            return Integer.MAX_VALUE;
        }
        // complete intersection
        if(low >= l && high <= r){
            return seg[index];
        }
        // partial intersection
        int mid = low + (high-low)/2;
        int left = findMin(2 * index + 1, low, mid, l, r);
        int right = findMin(2 * index + 2, mid+1, high, l, r);
        return Math.min(left,right);
    }
}