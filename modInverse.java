import java.io.*;
import java.util.*;

public class Template {
 
	static int mod = (int)1e9+7;
	
	static int power(int a, int b) {
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		if (b % 2 == 0) {
			int ans = power(a, b / 2);
			return ans * ans;
		} else {
			int ans = power(a, (b - 1) / 2);
			return a * ans * ans;
		}

	}
	
	static int inv(int x){
	    return power(x,mod-2);
	}
	
}
