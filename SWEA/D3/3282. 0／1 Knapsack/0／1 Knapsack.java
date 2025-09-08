import java.io.*;
import java.util.*;

public class Solution {

    static int n, k;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            dp = new int[k+1];

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int volume = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                for(int j = k; j>=volume; j--){
                    dp[j] = Math.max(dp[j], dp[j-volume] + value);
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[k]).append("\n");
        }

        System.out.print(sb);
    }



}