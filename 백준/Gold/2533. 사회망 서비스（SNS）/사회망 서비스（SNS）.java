import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;


    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        dp = new int[n+1][2];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    static void dfs(int v){
        visited[v] = true;

        dp[v][0] = 0;
        dp[v][1] = 1;

        for(Integer u : graph[v]){
            if(!visited[u]){
                dfs(u);
                dp[v][0] += dp[u][1];
                dp[v][1] += Math.min(dp[u][0], dp[u][1]);
            }
        }
    }

}
