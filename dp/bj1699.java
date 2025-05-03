import java.io.*;

public class bj1699 {

    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        for(int i = 1; i*i <= n; i++){
            dp[i*i] = 1;
        }

        System.out.println(dinamic(n));
    }

    static int dinamic(int num){
        if(dp[num] > 0){
            return dp[num]; 
        }

        int min = num;
        for(int i = 1; i*i<=num; i++){
            min = Math.min(min, dinamic(num-i*i)+1);
        }

        dp[num] = min;
        return min;
    }
}
