// 1 based Indexing is followed.

// fenwick tree for sum query
int N = 1000;
int[] fen = new int[N];

public void update(int i, int val, int n){
    while(i < n){
        fen[i] += val;
        i = i + (i&(-i));
    }
}

public int sum(int r){
    int count = 0;
    while(r > 0){
        count += fen[r];
        r = r - (r&(-r));
    }
    return count;
}

// fenwick tree for max query
int N = 10000;
int[] fen = new int[N];

public void update(int i, int val, int n){
    while(i < n){
        fen[i] = Math.max(fen[i],val);
        i = i + (i&(-i));
    }
}

public int max(int r){
    int max = 0;
    while(r > 0){
        max = Math.max(max,fen[r]);
        r = r - (r&(-r));
    }
    return max;
}  
