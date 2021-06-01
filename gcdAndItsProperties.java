static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
}

/*
Properties of GCD
1. gcd(0,a) = a
2. gcd(a,b) = gcd(b,a%b)
3. gcd(a,b) = gcd(b,abs(a-b)) = gcd(a,abs(a-b))
4. gcd(a,b,c) = gcd(a,gcd(b,c))
*/
