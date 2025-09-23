import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10007;
    static int[][] dp;       // -1이면 미계산, 0..MOD-1이면 계산 완료
    static char[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.length() == 0) {
            System.out.println(0);
            return;
        }

        s = str.toCharArray();
        int n = s.length;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        System.out.println(go(0, n - 1));
    }

    static int go(int i, int j) {
        if (i > j) return 0;      // 공집합 구간
        if (i == j) return 1;     // 한 글자 -> 팰린드롬 1개
        if (dp[i][j] != -1) return dp[i][j];

        long res;
        if (s[i] == s[j]) {
            // 양 끝을 모두 포함하는 새 팰린드롬(+1)까지 포함
            res = (long) go(i + 1, j) + go(i, j - 1) + 1;
        } else {
            // 포함-배제
            res = (long) go(i + 1, j) + go(i, j - 1) - go(i + 1, j - 1);
        }

        res %= MOD;
        if (res < 0) res += MOD; // 음수 정규화

        return dp[i][j] = (int) res;
    }
}