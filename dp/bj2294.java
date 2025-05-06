import java.io.*;
import java.util.*;

public class bj2294 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];
        for(int i = 0; i<n; i++){
            set.add(Integer.parseInt(br.readLine()));
        }

        for(int i = 1; i<=k; i++){
            for(Integer s : set){
                if(s>i) continue;
                else if(s==i) dp[i] = 1;
                else if(dp[i-s] == 0) continue;
                else{
                    int min = dp[i-s] + 1;
                    if(dp[i] == 0){
                        dp[i] = min;
                    }
                    else{
                        dp[i] = Math.min(min, dp[i]);
                    }
                }
            }
        }

        if(dp[k] == 0){
            System.out.println(-1);
        }
        else{
            System.out.println(dp[k]);
        }



    }
}
