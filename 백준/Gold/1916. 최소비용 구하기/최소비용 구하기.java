import java.io.*;
import java.util.*;

public class Main{

    static int n, m;
    static ArrayList<Edge>[] map;
    static int[] dist;
    static boolean[] visited;

    static class Edge implements Comparable<Edge>{
        int end, weight;

        Edge(int end, int weight){
            this.end = end; this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new ArrayList[n+1];
        dist = new int[n+1];
        visited = new boolean[n+1];


        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from].add(new Edge(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end));
    }

    static int dijkstra(int start, int end){
        PriorityQueue<Edge> q = new PriorityQueue<>();
        dist[start] = 0;
        q.add(new Edge(start, 0));

        while(!q.isEmpty()){
            Edge now = q.poll();

            if(visited[now.end]) continue;
            visited[now.end] = true;

            if(now.end == end) return now.weight;

            for(Edge next : map[now.end]){
                if(dist[next.end] > next.weight + now.weight){
                    dist[next.end] = next.weight + now.weight;
                    q.add(new Edge(next.end, dist[next.end]));
                }
            }
        }

        return -1;

    }
}