static long rabinKarp(String s, String t) {
		
  // check how many times s occurs in t in O(s+t)

  int S = s.length(), T = t.length();

  long[] power = new long[n];
  power[0] = 1;
  int p = 31;
  for(int i = 1; i < n; i++) {
    power[i] = (power[i-1]%mod * p%mod)%mod;
  }

  long[] tHash = new long[T+1];

  for(int i = 0; i < T; i++) {
    tHash[i+1] = (tHash[i] + (t.charAt(i)-'a' + 1) * power[i])%mod;
  }

  long sHash = 0;
  for(int i = 0; i < S; i++) {
    sHash = (sHash + (s.charAt(i)-'a'+1) * power[i]) % mod;
  }

  long count = 0;
  for(int i = 0; i < T - S + 1; i++) {
    long curr = (tHash[i+S] - tHash[i] + mod)%mod;
    if(curr == (sHash%mod * power[i]%mod)%mod) {
      count++;
    }
  }
  return count;
}
