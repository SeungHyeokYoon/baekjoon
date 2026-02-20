import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] p;
    static boolean[] pick;
    static long totalX, totalY;
    static double ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            p = new int[n][2];
            pick = new boolean[n];

            totalX = 0;
            totalY = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                p[i][0] = Integer.parseInt(st.nextToken());
                p[i][1] = Integer.parseInt(st.nextToken());
                totalX += p[i][0];
                totalY += p[i][1];
            }

            ans = Double.MAX_VALUE;

            // 대칭 제거: 0번은 무조건 +그룹에 넣기
            pick[0] = true;
            comb(1, 1);

            System.out.println(ans);
        }
    }

    // idx부터 보면서, 현재까지 cnt개를 +그룹으로 뽑은 상태
    static void comb(int idx, int cnt) {
        if (cnt == n / 2) {
            calc();
            return;
        }
        if (idx == n) return;

        // 가지치기: 남은 걸 다 뽑아도 n/2 못 채우면 중단
        if (cnt + (n - idx) < n / 2) return;

        // idx를 +그룹에 포함
        pick[idx] = true;
        comb(idx + 1, cnt + 1);

        // idx를 -그룹에 포함
        pick[idx] = false;
        comb(idx + 1, cnt);
    }

    static void calc() {
        long ax = 0, ay = 0;
        for (int i = 0; i < n; i++) {
            if (pick[i]) {
                ax += p[i][0];
                ay += p[i][1];
            }
        }
        long vx = 2 * ax - totalX;
        long vy = 2 * ay - totalY;

        double len = Math.sqrt((double) vx * vx + (double) vy * vy);
        ans = Math.min(ans, len);
    }
}