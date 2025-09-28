import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1000000007;
    static int t, c, v, l;
    static long[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());

            c = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            dp = new long[l][2];
            dp[0][0] = c;
            dp[0][1] = v;


            for(int i = 1; i<l; i++){
                dp[i][0] = dp[i-1][1]*c%MOD;
                dp[i][1] = (dp[i-1][0]+dp[i-1][1])*v%MOD;
            }

            sb.append("Case #").append(tc).append(": ").append(dp[l-1][1]).append("\n");

        }
        System.out.println(sb);

    }
    

}
