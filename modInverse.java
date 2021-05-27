import java.io.*;
import java.util.*;

public class Template {
 
	static int mod = (int)1e9+7;
	
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
