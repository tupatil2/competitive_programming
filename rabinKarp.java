class Solution {
    public int rabinKarp(String s, String t) {
	// check the occurence of first t substring in s.
		
        int n = s.length(), m = t.length();
        if(n == 0 && m == 0) return 0;
        
        long prime = 31;
        long mod = 1000000007;
        
        long tHash = 0;
        long pr = 1;
        for(int i = 0; i < m; i++){
            tHash = (tHash + (t.charAt(i)-'a'+1) * pr) % mod;
            pr = (pr * prime) % mod;
        }
        
        int index = -1;
        long sHash = 0;
        pr = 1;
        for(int i = n-1; i >= 0; i--){
            sHash = ((sHash * prime)%mod + s.charAt(i)-'a' + 1)%mod;
            
            if(i+m >= n){
                pr = (pr * prime)%mod;
            }
            else {
                sHash = (sHash - ((s.charAt(i+m)-'a'+1)*pr)%mod + mod) % mod;
            }
            if(sHash == tHash){
                index = i;
            }
        } 
        
        return index;
    }
}
