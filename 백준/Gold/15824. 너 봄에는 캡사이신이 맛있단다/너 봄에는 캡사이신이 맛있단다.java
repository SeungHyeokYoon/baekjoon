import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;
    static int n;
    static int[] score;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        score = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(score);

        long ans = 0;
        for(int i = 0; i<n; i++){
            ans += score[i]*pow2(i);
            ans -= score[i]*pow2(n-i-1);
            ans %= MOD;
        }

        System.out.println(ans);
    }

    static long pow2(int exp){
        if(exp == 0) return 1L;

        long half = pow2(exp/2);
        long val = half * half % MOD;
        if(exp%2 == 0) return val;

        val = val * 2 % MOD;
        return val;
    }

}