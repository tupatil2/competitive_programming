static int hash(String s) {
	int ans = 0;
	int power = 1;
	for(int i = 0; i < s.length(); i++) {
		ans = (ans%mod + (((s.charAt(i)-'a')+1) * power) % mod)%mod;
		power = (power%mod * 31%mod)%mod;
	}
	return ans;
}
