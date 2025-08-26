import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static ArrayList<int[]>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            while(true){
                int end = Integer.parseInt(st.nextToken());
                if(end == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph[m].add(new int[]{end, weight});
            }
        }

        int[] init = bfs(1);

        Arrays.fill(visited, false);
        int[] ans = bfs(init[0]);

        System.out.println(ans[1]);


    }

    static int[] bfs(int start){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;
        int node = -1;
        int weight = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[1] > weight){
                weight = cur[1];
                node = cur[0];
            }

            for(int[] next : graph[cur[0]]){
                if(!visited[next[0]]){
                    visited[next[0]] = true;
                    queue.add(new int[]{next[0], cur[1] + next[1]});
                }
            }
        }

        return new int[]{node, weight};
    }




}
