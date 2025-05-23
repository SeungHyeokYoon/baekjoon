import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2225 {

    static int n, k;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n+1][k+1];

        System.out.println(func(n, k));
    }

    static int func(int num, int count){
        if(count == 1){
            return 1;
        }
        else if(dp[num][count] != 0){
            return dp[num][count];
        }
        else{
            int sum = 0;
            for(int i = 0; i<num+1; i++){
                sum = (sum + func(num-i, count-1)) % 1000000000;
            }
            dp[num][count] = sum;
            return sum;
        }
    }
}
