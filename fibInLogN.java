 
  /*
        the whole idea is to do matrix exponentation of 
        [[1,1],[1,0]] to the power (n-1)
        and return matrix[0][0];
 */
    
 int fibInLogN(int n){
      n--;
      int[][] base = {{1,1},{1,0}};
      int[][] ans = {{1,0},{0,1}};
      while(n != 0){
          if(n%2 == 0){
              // base = base * base and n = n/2
              int temp[][] = new int[2][2];
              temp[0][0] = base[0][0]*base[0][0] + base[0][1]*base[1][0];
              temp[0][1] = base[0][0]*base[0][1] + base[0][1]*base[1][1];
              temp[1][0] = base[1][0]*base[0][0] + base[1][1]*base[1][0];
              temp[1][1] = base[1][0]*base[0][1] + base[1][1]*base[1][1];
              base[0][0] = temp[0][0]; base[0][1] = temp[0][1]; base[1][0] = temp[1][0]; base[1][1] = temp[1][1];
              n = n/2;
          }
          else {
              // ans = ans * base and n = n-1
              int temp[][] = new int[2][2];
              temp[0][0] = ans[0][0]*base[0][0] + ans[0][1]*base[1][0];
              temp[0][1] = ans[0][0]*base[0][1] + ans[0][1]*base[1][1];
              temp[1][0] = ans[1][0]*base[0][0] + ans[1][1]*base[1][0];
              temp[1][1] = ans[1][0]*base[0][1] + ans[1][1]*base[1][1];
              ans[0][0] = temp[0][0]; ans[0][1] = temp[0][1]; ans[1][0] = temp[1][0]; ans[1][1] = temp[1][1];
              n = n-1;
          }
      }
      return ans[0][0];
 }

// for long

static long fibInLogN(long n){
   n--;
   long[][] base = {{1l,1l},{1l,0l}};
   long[][] ans = {{1l,0l},{0l,1l}};
   while(n != 0){
       if(n%2 == 0){
           // base = base * base and n = n/2
           long temp[][] = new long[2][2];
           temp[0][0] = add(mul(base[0][0],base[0][0]), mul(base[0][1],base[1][0]));
           temp[0][1] = add(mul(base[0][0],base[0][1]), mul(base[0][1],base[1][1]));
           temp[1][0] = add(mul(base[1][0],base[0][0]), mul(base[1][1],base[1][0]));
           temp[1][1] = add(mul(base[1][0],base[0][1]), mul(base[1][1],base[1][1]));
           base[0][0] = temp[0][0]; base[0][1] = temp[0][1]; base[1][0] = temp[1][0]; base[1][1] = temp[1][1];
           n = n/2;
       }
       else {
           // ans = ans * base and n = n-1
           long temp[][] = new long[2][2];
           temp[0][0] = add(mul(ans[0][0],base[0][0]), mul(ans[0][1],base[1][0]));
           temp[0][1] = add(mul(ans[0][0],base[0][1]), mul(ans[0][1],base[1][1]));
           temp[1][0] = add(mul(ans[1][0],base[0][0]), mul(ans[1][1],base[1][0]));
           temp[1][1] = add(mul(ans[1][0],base[0][1]), mul(ans[1][1],base[1][1]));
           ans[0][0] = temp[0][0]; ans[0][1] = temp[0][1]; ans[1][0] = temp[1][0]; ans[1][1] = temp[1][1];
           n = n-1;
       }
   }
   return ans[0][0];
}

public static long add(long a, long b){
   return ((a+b)+mod)%mod;
}

public static long mul(long a, long b){
   return ((a*b)+mod)%mod;
}
