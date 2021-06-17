public int kmp(String s, String t){
    // check how many times t occurs in s
    int[] prefix = prefixFunction(t+"#"+s);
    // int firstOccur = -1;
    // int count = 0;
    for(int i = 0; i < prefix.length; i++){
        if(prefix[i] == t.length()){
            // for first occur
            // first = i-t.length()-t.length();
            // break;

            // for count
            count++;
        }
    }
    // return firstOcurr;
    return count;
}

public int[] prefixFunction(String s){
    int n = s.length();
    int[] prefix = new int[n];

    for(int i = 1; i < n; i++){
        int j = prefix[i-1];
        while(j > 0 && s.charAt(i) != s.charAt(j)){
            j = prefix[j-1];
        }
        if(s.charAt(i) == s.charAt(j)){
            j++;
        }
        prefix[i] = j;
    }

    return prefix;
}
