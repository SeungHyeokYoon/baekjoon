import java.io.*;
import java.util.*;

public class bj15681 {

    static int n, r, q;
    static ArrayList<Integer>[] tree;
    static int[] subtreeSize;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        subtreeSize = new int[n+1];
        visited = new boolean[n+1];

        dfs(r);

        for(int i = 0; i < q; i++){
            sb.append(subtreeSize[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.print(sb);
    }

    static int dfs(int v){
        visited[v] = true;
        subtreeSize[v] = 1;

        for(int next : tree[v]){
            if(!visited[next]){
                subtreeSize[v] += dfs(next);
            }
        }

        return subtreeSize[v];
    }

}
