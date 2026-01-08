import java.io.*;
import java.util.*;

public class Main{

    static ArrayList<Edge>[] graph;
    static int n, m, min;

    static class Edge implements Comparable<Edge>{
        int end;
        int weight;

        Edge(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        min = -1;

        graph = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        ArrayList<Integer> path = getMinPath();

        for(int i = 0; i<path.size()-1; i++){
            dijkstra(path.get(i), path.get(i+1));
        }

        System.out.println(min);


    }

    static ArrayList<Integer> getMinPath(){
        int[] prev = new int[n+1];
        int[] dist = new int[n+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.end == n) break;

            if(now.weight > dist[now.end]) continue;

            for(Edge next : graph[now.end]){
                if(dist[next.end] > next.weight + now.weight){
                    dist[next.end] = next.weight + now.weight;
                    prev[next.end] = now.end;
                    pq.add(new Edge(next.end, dist[next.end]));
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();

        int now = n;

        while(now != 0){
            path.add(now);
            now = prev[now];
        }

        return path;
    }

    static void dijkstra(int u, int v){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.end == n){
                min = Math.max(min, now.weight);
                return;
            }

            if(now.weight > dist[now.end]) continue;

            for(Edge next : graph[now.end]){
                if((now.end == u && next.end == v) || (now.end == v && next.end == u)) continue;

                if(dist[next.end] > next.weight + now.weight){
                    dist[next.end] = next.weight + now.weight;
                    pq.add(new Edge(next.end, dist[next.end]));
                }
            }
        }


    }


}