import java.io.*;
import java.util.*;

public class Main {

    static int k, t;
    static int[][] dp, coin;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        coin = new int[k + 1][2];

        for(int i = 1; i<=k; i++){
            st = new StringTokenizer(br.readLine());
            coin[i][0] = Integer.parseInt(st.nextToken());
            coin[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[k+1][t+1];
        dp[0][0] = 1;

        for(int i = 1; i<=k; i++){
            int val = coin[i][0];
            for(int j = 0; j<=coin[i][1]; j++){
                for(int x = 0; x<=t; x++){
                    int cost = x + val*j;
                    if(cost > t) break;
                    dp[i][cost] += dp[i-1][x];
                }
            }
        }

        System.out.println(dp[k][t]);

    }
}
