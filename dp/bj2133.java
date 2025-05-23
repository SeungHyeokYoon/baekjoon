import java.io.*;
import java.util.*;


public class bj2133 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n%2 == 1){
            System.out.println(0);
            return;
        }


        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[2] = 3;

        for(int i = 4; i <= n; i += 2){
            dp[i] = dp[i-2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[n]);

    }

}
