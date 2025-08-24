import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] farm;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        N = fs.nextInt();
        M = fs.nextInt();

        farm = new int[N][N];

        for (int m = 0; m < M; m++) {
            int X = fs.nextInt();
            int Y = fs.nextInt();
            int L = fs.nextInt();
            int F = fs.nextInt();

            // 파이썬 코드와 동일하게 (행=Y..Y+L-1, 열=X..X+L-1) 범위 채우기
            for (int i = Y; i < Y + L; i++) {
                for (int j = X; j < X + L; j++) {
                    farm[i][j] = F;
                }
            }
        }

        int result = 0;
        for (int a = 1; a <= 7; a++) {
            for (int b = a + 1; b <= 7; b++) {
                result = Math.max(result, search(a, b));
            }
        }

        System.out.println(result * result);
    }

    // 두 품종(target1, target2)만 허용하는 가장 큰 정사각형의 한 변 길이를 반환
    static int search(int target1, int target2) {
        int[][] dp = new int[N][N];
        int best = 0;

        // 허용 칸(=target1 또는 target2)이면 1로 표기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (farm[i][j] == target1 || farm[i][j] == target2) {
                    dp[i][j] = 1;
                    best = Math.max(best, 1); // 가장자리만 있는 경우 대비
                }
            }
        }

        // 최대 정사각형 DP
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (dp[i][j] == 0) continue;
                dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                best = Math.max(best, dp[i][j]);
            }
        }
        return best; // 변의 길이
    }

    // 빠른 입력
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= ' ' && c != -1) {}
            if (c == -1) return null;
            do {
                sb.append((char)c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do { c = read(); } while (c <= ' ' && c != -1);
            if (c == '-') { s = -1; c = read(); }
            while (c > ' ') {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * s;
        }
    }
}
