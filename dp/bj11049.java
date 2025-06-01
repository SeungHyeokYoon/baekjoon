import java.io.*;
import java.util.StringTokenizer;

public class bj11049 {

    static long[][] dp;
    static int n;
    static int[][] matrix;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        matrix = new int[n+1][2];

        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new long[n+1][n+1];

        System.out.println(multiple(1, n));
    }

    static long multiple(int y, int x){
        if(dp[y][x] != 0) return dp[y][x];
        if(x == y) return 0;
        if(x-y == 1){
            return dp[y][x] = matrix[y][0] * matrix[y][1] * matrix[x][1];
        }
        
        long min = Long.MAX_VALUE;
        for(int i = 1; i<=x-y; i++){
            long sum = multiple(y, x-i) + multiple(x-i+1, x) + matrix[y][0] * matrix[x-i][1] * matrix[x][1];
            min = Math.min(min, sum);
        }

        return dp[y][x] = min;
    }
}
