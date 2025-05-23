import java.io.*;
import java.util.*;

public class swea1219 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean can;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc<=10; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[100];
            visited = new boolean[100];
            for(int i = 0; i<100; i++){
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<m; i++){
                graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
            }

            can = false;
            dfs(0);

            sb.append("#").append(tc).append(" ").append((can)?"1":"0").append("\n");

        }
        System.out.println(sb);

    }

    static void dfs(int v){
        visited[v] = true;

        for(Integer now : graph[v]){
            if(now == 99){
                can = true;
                return;
            }

            if(!visited[now]){
                dfs(now);
            }
        }
    }
}
