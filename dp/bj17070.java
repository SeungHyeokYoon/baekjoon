import java.io.*;
import java.util.*;

public class bj17070{

    static int[][] board;
    static int[][][] dp;
    static int n, ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dp = new int[n][n][3];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;

        for(int i = 0; i<n; i++){
            for(int j = 2; j<n; j++){
                if(board[i][j] == 1) continue;

                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

                if(i - 1 >= 0){
                    dp[i][j][2] = dp[i - 1][j][2] + dp[i - 1][j][1];
                }

                if(i - 1 >= 0 && j - 1 >= 0){
                    if(board[i - 1][j] == 0 && board[i][j - 1] == 0){
                        dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
                
            }
        }

        ans = dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2];

        System.out.println(ans);
    }




    
}