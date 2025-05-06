import java.io.*;
import java.util.*;

public class bj11051 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] pascal = new int[n+1][n+1];

        int MOD = 10007;
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=i; j++){
                if(j == 0 || j == i) pascal[i][j] = 1;
                else{
                    pascal[i][j] = (pascal[i-1][j-1] + pascal[i-1][j]) % MOD;
                }
            }
        }

        System.out.println(pascal[n][k]);
    }
}
