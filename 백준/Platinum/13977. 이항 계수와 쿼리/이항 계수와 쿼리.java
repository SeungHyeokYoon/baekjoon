import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 1000000007;
    private static final int MAX = 4_000_001;
    private static long[] F = new long[MAX];
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        setFactorial();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            sb.append(combination(N, K)).append('\n');
        }

        out.print(sb);
        out.flush();
        out.close();
    }


    private static long combination(int N, int K) {
        long numer = F[N];
        long denom = (F[N - K] * F[K]) % MOD;
        denom = pow(denom, MOD - 2);
        return (numer * denom) % MOD;
    }

    private static long pow(long base, int exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    private static void setFactorial() {
        F[0] = 1;
        for (int i = 1; i < MAX; i++) {
            F[i] = (F[i - 1] * i) % MOD;
        }
    }
}