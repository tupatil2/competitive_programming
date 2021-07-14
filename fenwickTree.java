// 1 based Indexing is followed.

int[] fen;

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
