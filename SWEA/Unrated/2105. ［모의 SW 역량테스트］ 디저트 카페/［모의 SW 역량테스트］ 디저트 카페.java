import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] A;                 // 디저트 번호 격자
    static boolean[] tasted;          // 디저트 번호 중복 체크 (0~100 가정)
    static boolean[][] used;  
    static int ans;


    static final int[] dr = { 1, 1, -1, -1 };
    static final int[] dc = { 1, -1, -1, 1 };

    static int sr, sc;


    static void dfs(int r, int c, int dir, int turn, int cnt) {

        for (int nd = dir; nd <= Math.min(3, dir + 1); nd++) {
            int nr = r + dr[nd];
            int nc = c + dc[nd];


            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;


            if (nr == sr && nc == sc) {

                if (nd == 3 && cnt >= 4) {
                    ans = Math.max(ans, cnt);
                }
                return;
            }

            int dessert = A[nr][nc];


            if (tasted[dessert]) continue;
            if (used[nr][nc]) continue;


            int nturn = turn + ((nd == dir) ? 0 : 1);
            if (nturn > 3) continue;


            tasted[dessert] = true;
            used[nr][nc] = true;

            dfs(nr, nc, nd, nturn, cnt + 1);


            tasted[dessert] = false;
            used[nr][nc] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            A = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = -1;
            tasted = new boolean[101];
            used = new boolean[N][N];


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    sr = i; sc = j;

                    Arrays.fill(tasted, false);
                    for (int r = 0; r < N; r++) Arrays.fill(used[r], false);

                    tasted[A[i][j]] = true;
                    used[i][j] = true;

                    dfs(i, j, 0, 0, 1);

                    tasted[A[i][j]] = false;
                    used[i][j] = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb.toString());
    }
}
