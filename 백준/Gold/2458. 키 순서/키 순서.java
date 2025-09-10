import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer>[] g, rg;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        g = new ArrayList[n + 1];
        rg = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            rg[b].add(a);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int bigger = bfsCount(i, g);
            int smaller = bfsCount(i, rg);
            if (bigger + smaller == n - 1) ans++;
        }
        
        System.out.print(ans);
    }

    static int bfsCount(int start, ArrayList<Integer>[] graph) {
        boolean[] vis = new boolean[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        vis[start] = true;
        q.add(start);
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nx : graph[cur]) {
                if (!vis[nx]) {
                    vis[nx] = true;
                    q.add(nx);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
