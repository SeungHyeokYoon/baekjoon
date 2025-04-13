import java.io.*;
import java.util.*;

public class bj2342 {

    static int[] move;
    static int[][][] dp;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = st.countTokens()-1;
        move = new int[n];

        for(int i = 0; i<n; i++){
            move[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][5][5];

        System.out.println(bottomupdp(0, 0, 0));

        
    }

    static int bottomupdp(int idx, int left, int right){
        if(idx == n) return 0;
        if(dp[idx][left][right] != 0) return dp[idx][left][right];

        int next = move[idx];
        return dp[idx][left][right] = Math.min(bottomupdp(idx+1, next, right) + moveCost(left, next), bottomupdp(idx+1, left, next) + moveCost(right, next));
    }

    static int moveCost(int from, int to) {
        if (from == 0) return 2;
        if (from == to) return 1;
        if (Math.abs(from - to) == 2) return 4;
        return 3;
    }
}
