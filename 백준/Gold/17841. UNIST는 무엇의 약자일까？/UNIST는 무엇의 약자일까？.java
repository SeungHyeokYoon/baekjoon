import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[6];
        boolean[] can = new boolean[7];
        can[1] = true;
        dp[0] = 1;

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            int startIdx = -1;
            int endIdx = -1;

            for(int j = 0; j<str.length(); j++){
                int now = trans(str.charAt(j));
                if(!can[now-1]) break;
                if(j == 0) startIdx = now - 1;
                else if(now-1-startIdx != j) break;
                endIdx = now - 1;
                can[now] = true;
            }

            //sb.append("start : ").append(startIdx).append("  end : ").append(endIdx).append("\n");

            if(startIdx == -1) continue;

            for(int j = endIdx; j>=startIdx; j--){
                dp[j] += dp[startIdx-1];
                dp[j] %=1000000007;
            }

            //sb.append(Arrays.toString(dp)).append("\n");

        }

        System.out.println(dp[5]);
    }

    static int trans(char c){
        if(c == 'U') return 2;
        else if(c == 'N') return 3;
        else if(c == 'I') return 4;
        else if(c == 'S') return 5;
        else if(c == 'T') return 6;
        else return 1;
    }
}