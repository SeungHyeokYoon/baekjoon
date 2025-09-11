import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {1, -1, 1, -1};
    static final int[] dy = {1, 1, -1, -1}; // rd, ld, ru, lu

    static char[][] map;
    static int[][][] dp;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean zero = true;
        int max = 0;
        map = new char[n][m];
        
        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<m; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '1') zero = false;
            }
        }
        
        if(zero){
            System.out.println(max);
            return;
        }
        max = 1;
        dp = new int[4][n][m];


        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == '1') {
                    if(i > 0 && j > 0) dp[0][i][j] = dp[0][i - 1][j - 1] + 1;
                    else dp[0][i][j] = 1;
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = m - 1; j >= 0; j--){
                if(map[i][j] == '1') {
                    if(i > 0 && j < m - 1) dp[1][i][j] = dp[1][i - 1][j + 1] + 1;
                    else dp[1][i][j] = 1;
                }
            }
        }
        
        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < m; j++){
                if(map[i][j] == '1') {
                    if(i < n - 1 && j > 0) dp[2][i][j] = dp[2][i + 1][j - 1] + 1;
                    else dp[2][i][j] = 1;
                }
            }
        }
        
        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                if(map[i][j] == '1') {
                    if(i < n - 1 && j < m - 1) dp[3][i][j] = dp[3][i + 1][j + 1] + 1;
                    else dp[3][i][j] = 1;
                }
            }
        }


        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                int now = dp[1][i][j];
                for(int k = max+1; k <= now; k++){
                    int nx = j + 2*k - 2;
                    if(nx < m && dp[3][i][j] >= k){    
                        if(dp[0][i][nx] >= k && dp[2][i][nx] >= k) max = k;
                    }
                }
            }
        }

        System.out.println(max);

    }

}