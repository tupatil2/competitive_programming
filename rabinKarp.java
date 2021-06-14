static long rabinKarp(String s, String t) {
	// check how many times s occurs in t

	s = s.toLowerCase();
	t = t.toLowerCase();

	int S = s.length(), T = t.length();

	long[] power = new long[T];
	long p = 31;
	power[0] = 1;
	for(int i = 1; i < T; i++) {
		power[i] = (power[i-1] * p) % mod;
	}

	long[] tHash = new long[T+1];
	for(int i = 0; i < T; i++) {
		tHash[i+1] = (tHash[i] + (t.charAt(i)-'a'+1) * power[i]) % mod; 
	}

	long sHash = 0;
	for(int i = 0; i < S; i++) {
		sHash = (sHash + (s.charAt(i)-'a'+1) *  power[i]) % mod; 
	}

	long count = 0l;
	for(int i = 0; i < T; i++) {
		if(i+S > T) break;
		long curr = (tHash[i+S] - tHash[i] + mod) % mod;
		if(curr == (sHash * power[i]) % mod) {
			count++;
		}
	}
	return count;
}
