import java.io.*;
import java.util.*;

public class Main {

    static int n, m, first, last, cnt;
    static int[] dist, indegree;
    static ArrayList<Integer>[] prev;
    static ArrayList<Edge>[] map;
    static boolean[] visited;

    static class Edge{
        int end, weight;
        
        Edge(int end, int weight){
            this.end = end; this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n+1];
        indegree = new int[n+1];
        visited = new boolean[n+1];
        prev = new ArrayList[n+1];
        map = new ArrayList[n+1];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
            prev[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[start].add(new Edge(end, weight));
            indegree[end]++;
        }

        st = new StringTokenizer(br.readLine());
        first = Integer.parseInt(st.nextToken());
        last = Integer.parseInt(st.nextToken());


        Queue<Integer> q = new ArrayDeque<>();
        q.add(first);

        while(!q.isEmpty()){
            Integer now = q.poll();

            for(Edge next : map[now]){
                if(dist[next.end] < dist[now] + next.weight){
                    dist[next.end] = dist[now] + next.weight;
                    prev[next.end].clear();
                    prev[next.end].add(now);
                }
                else if(dist[next.end] == dist[now] + next.weight){
                    prev[next.end].add(now);
                }

                if(--indegree[next.end] == 0){
                    q.add(next.end);
                }
            }
        }

        sb.append(dist[last]).append("\n");
        find(last);
        sb.append(cnt);

        System.out.println(sb);

    }

    static void find(int node){
        if(visited[node]) return;
        visited[node] = true;

        for(Integer pr : prev[node]){
            cnt++;
            find(pr);
        }
    }


}