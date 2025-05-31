import java.io.*;
import java.util.*;

public class bj10942{

    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[n+1][n+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int value = pallendrom(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append((value == 1)?1:0).append("\n");
        }

        System.out.println(sb);
    }

    static int pallendrom(int start, int end){
        if(dp[start][end] != 0){
            return dp[start][end];
        }
        if(start == end) return 1;
        if(start-end == 1){
            return dp[start][end] = (arr[start] == arr[end]) ? 1 : -1;
        }

        return dp[start][end] = (pallendrom(start+1, end-1) == 1 && arr[start] == arr[end]) ? 1 : -1;
    }
}