import java.io.*;
import java.util.*;

public class Template {
	
	// boolean[] sieve -> we have to calculate sieve till 1e5 and pass it to the function
	static boolean[] segmentedSieve(int L, int R, boolean[] sieve) {
		
		// 1. all the primes till sqrt(R)
		List<Integer> primes = new ArrayList<>();
		for(int i = 2; i*i <= R; i++) {
			if(sieve[i]) primes.add(i);
		}
		
		// 2. create the range sieve
		boolean[] res = new boolean[R-L+1];
		for(int i = L; i <= R; i++) res[i-L] = true;
		
		// 3. make sure all the primes mark their multiples in range L to R
		for(int e : primes) {
			int firstMultiple = (L/e)*e;
			if(firstMultiple < L) firstMultiple += e;
			
			firstMultiple = Math.max(firstMultiple, e*e);
			
			for(int j = firstMultiple; j <= R; j += e) {
				res[j-L] = false;
			}
		}

		return res;
	}
	
	
	static boolean[] sieve(int N) {
		boolean[] sieve = new boolean[N];
		for(int i = 2; i < N; i++) sieve[i] = true;

		for(int i = 2; i < N; i++) {
			if(sieve[i]) {
				if(i*i < 0) continue;
				for(int j = i*i; j < N; j += i) {
					sieve[j] = false; 
				}
			}
		}
		return sieve;
	}

}
