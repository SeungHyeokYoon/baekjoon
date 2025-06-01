import java.io.*;
import java.util.*;

public class bj1562 {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());;

        long[][][] dp = new long[n + 1][10][1 << 10];
        
        for(int i = 1; i <= 9; i++){
            dp[1][i][1 << i] = 1;
        }

        for(int len = 1; len < n; len++){
            for(int lastDigit = 0; lastDigit <= 9; lastDigit++){
                for(int bit = 0; bit < (1 << 10); bit++){
                    long val = dp[len][lastDigit][bit];
                    if(val == 0) continue;

                    if(lastDigit > 0){
                        int nextBit = bit | (1 << (lastDigit - 1));
                        dp[len + 1][lastDigit - 1][nextBit] = (dp[len + 1][lastDigit - 1][nextBit] + val) % MOD;
                    }

                    if(lastDigit < 9){
                        int nextBit = bit | (1 << (lastDigit + 1));
                        dp[len + 1][lastDigit + 1][nextBit] = (dp[len + 1][lastDigit + 1][nextBit] + val) % MOD;
                    }
                }
            }
        }

        long result = 0;
        int fullMask = (1 << 10) - 1;

        for (int i = 0; i <= 9; i++) {
            result = (result + dp[n][i][fullMask]) % MOD;
        }

        System.out.println(result);

    }
}
