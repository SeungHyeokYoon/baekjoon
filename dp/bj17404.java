import java.io.*;
import java.util.*;

public class bj17404 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][3];
        int[][] dp1 = new int[n][3];
        int[][] dp2 = new int[n][3];
        int[][] dp3 = new int[n][3];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp1[0][0] = cost[0][0];
        dp1[0][1] = 1000000;
        dp1[0][2] = 1000000;
        dp2[0][0] = 1000000;
        dp2[0][1] = cost[0][1];
        dp2[0][2] = 1000000;
        dp3[0][0] = 1000000;
        dp3[0][1] = 1000000;
        dp3[0][2] = cost[0][2];


        for(int i = 1; i<n; i++){
            for(int j = 0; j<3; j++){
                dp1[i][j] = Math.min(dp1[i-1][(j+1)%3], dp1[i-1][(j+2)%3]) + cost[i][j];
                dp2[i][j] = Math.min(dp2[i-1][(j+1)%3], dp2[i-1][(j+2)%3]) + cost[i][j];
                dp3[i][j] = Math.min(dp3[i-1][(j+1)%3], dp3[i-1][(j+2)%3]) + cost[i][j];
            }
        }

        int min = 1000000;

        min = Math.min(dp1[n-1][1], min);
        min = Math.min(dp1[n-1][2], min);
        min = Math.min(dp2[n-1][0], min);
        min = Math.min(dp2[n-1][2], min);
        min = Math.min(dp3[n-1][0], min);
        min = Math.min(dp3[n-1][1], min);

        System.out.println(min);

    }
}
