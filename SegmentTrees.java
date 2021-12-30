static class SegTree{
        static int[] seg;
        public SegTree(int n){
            // approx 4 * n is sufficient size of segment tree
            seg = new int[4 * n + 1];
        }
        
        static void build(int index, int low, int high, int[] arr, int n){
            if(low == high){
                seg[index] = arr[low];
                return;
            }
            int mid = low + (high-low)/2;
            build(2 * index + 1, low, mid, arr, n);
            build(2 * index + 2, mid+1, high, arr, n);
            seg[index] = Math.min(seg[2*index+1],seg[2*index+2]);
        }
        
        static void update(int index, int low, int high, int i, int val, int[] arr, int n){
            if(low == high){
                seg[index] = val;
                return;
            }
            int mid = low + (high-low)/2;
            if(i <= mid){
                update(2 * index + 1, low, mid, i, val, arr, n);
            }
            else {
                update(2 * index + 2, mid + 1, high, i, val, arr, n);
            }
            seg[index] = Math.min(seg[2 * index + 1], seg[2 * index + 2]);
        }
    
        static int findMin(int index, int low, int high, int l, int r, int[] arr, int n){
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
            int left = findMin(2 * index + 1, low, mid, l, r, arr, n);
            int right = findMin(2 * index + 2, mid+1, high, l, r, arr, n);
            return Math.min(left,right);
        }
    }
    
