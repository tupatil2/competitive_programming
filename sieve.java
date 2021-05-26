import java.io.*;
import java.util.*;

public class Template {
	static boolean[] sieve(int N) {
		boolean[] sieve = new boolean[N+1];
		for(int i = 2; i <= N; i++) sieve[i] = true;

		for(int i = 2; i <= N; i++) {
			if(sieve[i]) {
				for(int j = 2*i; j <= N; j += i) {
					sieve[j] = false; 
				}
			}
		}
		return sieve;
	}
}
