import java.io.*;
import java.util.*;

public class Main{

    static int n, m, h;
    static ArrayList<Edge>[] map;
    static int[][] dp;
    static int[] dist, depth;

    static class Edge{
        int end, weight;

        Edge(int end, int weight){
            this.end = end; this.weight = weight;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        h = (int)Math.ceil(Math.log(n)/Math.log(2))+1;
        map = new ArrayList[n+1];
        dp = new int[n+1][h];
        dist = new int[n+1];
        depth = new int[n+1];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[from].add(new Edge(to, w));
            map[to].add(new Edge(from, w));
        }

        init(1, 1, 0);
        fill();

        m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int res = LCA(a, b);
            sb.append(dist[a] + dist[b] - 2 * dist[res]).append("\n");

        }

        System.out.println(sb);

    }

    static void init(int cur, int height, int parent){
        depth[cur] = height;

        for(Edge next : map[cur]){
            if(next.end != parent){
                dist[next.end] = dist[cur] + next.weight;
                init(next.end, height+1, cur);
                dp[next.end][0] = cur;
            }
        }
    }

    static void fill(){
        for(int i = 1; i<h; i++){
            for(int j = 1; j<=n; j++){
                dp[j][i] = dp[dp[j][i-1]][i-1];
            }
        }
    }

    static int LCA(int a, int b){
        int ah = depth[a];
        int bh = depth[b];

        if(ah < bh){
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = h-1; i>=0; i--){
            if((1<<i) <= depth[a] - depth[b]){
                a = dp[a][i];
            }
        }

        if(a == b) return a;

        for(int i = h - 1; i >= 0; i--){
            if(dp[a][i] != dp[b][i]){
                a = dp[a][i];
                b = dp[b][i];
            }
        }

        return dp[a][0];
    }


}