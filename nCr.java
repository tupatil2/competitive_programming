public class Template {
 
	static int mod = (int)1e9+7;
	
	static int N = (int)(1e6+10);
	static int fact[] = new int[N];
	
	static void init(int n, int mod) {
	    fact[0] = 1;

	    for (int i = 1; i <= n; ++i) {
	        fact[i] = (int)((fact[i - 1] * 1l * i) % mod);
	    }
	}
	
	static int nCr(int n, int r, int mod) {
	    return (int)((fact[n] * modInverse(fact[r] * 1l * fact[n - r])) % mod);
	}
	

	static long power(long base, long exp, long mod) {
	    long ans = 1;
	    base %= mod;

	    while (exp > 0) {
	        if ((exp & 1) != 0) ans = (ans * base) % mod;
	        exp >>= 1;
	        base = (base * base) % mod;
	    }

	    return ans;
	}
	
	static long modInverse(long x){
	    return power(x,mod-2,mod);
	}
	
}
