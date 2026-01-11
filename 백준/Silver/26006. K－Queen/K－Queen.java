import java.io.*;
import java.util.*;

public class Main {

    static int n, k, kx, ky, qx, qy;
    static HashSet<Integer> horizontal, vertical, diagonal, reversedia;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        horizontal = new HashSet<>();
        vertical   = new HashSet<>();
        diagonal   = new HashSet<>();
        reversedia = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        ky = Integer.parseInt(st.nextToken());
        kx = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            qy = Integer.parseInt(st.nextToken());
            qx = Integer.parseInt(st.nextToken());

            horizontal.add(qx);
            vertical.add(qy);
            diagonal.add(qx + qy - 1);
            reversedia.add(qx - qy + n);
        }

        boolean ischeck = check(kx, ky);
        boolean canmove = false;

        int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

        for (int i = 0; i < 8; i++) {
            int nx = kx + dx[i];
            int ny = ky + dy[i];
            if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
                if (!check(nx, ny)) {
                    canmove = true;
                    break;
                }
            }
        }

        if (ischeck) {
            System.out.println(canmove ? "CHECK" : "CHECKMATE");
        } else {
            System.out.println(canmove ? "NONE" : "STALEMATE");
        }
    }

    static boolean check(int x, int y) {
        return horizontal.contains(x)
            || vertical.contains(y)
            || diagonal.contains(x + y - 1)
            || reversedia.contains(x - y + n);
    }
}