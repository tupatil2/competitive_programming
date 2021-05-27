import java.io.*;
import java.util.*;

public class Template {
 
	static void primeFactorsUsingSieve(int N)
	{
		int[] primeFactors = new int[N+1];
		primeFactors[1] = 1;
	    for (int i=2; i<N; i++)
	    	primeFactors[i] = i;
	    for (int i=4; i<N; i+=2)
	    	primeFactors[i] = 2;
	 
	    for (int i=3; i*i<N; i++)
	        {
	        if (primeFactors[i] == i)
	            {
	            for (int j=i*i; j >= 0 && j<N; j+=i)
	                if (primeFactors[j]==j)
	                	primeFactors[j] = i;
	            }
	        }
	}
}
