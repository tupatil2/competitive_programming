import java.io.*;
import java.util.*;

public class Template {
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
