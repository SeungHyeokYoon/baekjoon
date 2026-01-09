import java.io.*;
import java.util.*;

public class Main{

    static ArrayList<Edge>[] graph;
    static int n, m, max, origin;

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

        ArrayList<Integer> path = first();

        for(int i = 0; i<path.size()-1; i++){
            if(!dijkstra(path.get(i), path.get(i+1))){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(max - origin);
    }

    static ArrayList<Integer> first(){
        ArrayList<Integer> path = new ArrayList<>();

        int[] dist = new int[n+1];
        int[] parents = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.add(new Edge(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.end == n) break;

            if(dist[now.end] < now.weight) continue;

            for(Edge next : graph[now.end]){
                if(dist[next.end] > now.weight + next.weight){
                    dist[next.end] = now.weight + next.weight;
                    parents[next.end] = now.end;
                    pq.add(new Edge(next.end, dist[next.end]));
                }
            }
        }

        origin = dist[n];
        int cur = n;

        while(cur != 0){
            path.add(cur);
            cur = parents[cur];
        }

        return path;
    }

    static boolean dijkstra(int u, int v){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.add(new Edge(1, 0));
        boolean can = false;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.end == n){
                can = true;
                max = Math.max(max, now.weight);
                break;
            }

            if(dist[now.end] < now.weight) continue;

            for(Edge next : graph[now.end]){
                if((now.end == u && next.end == v) || (now.end == v && next.end == u)) continue;
                if(dist[next.end] > now.weight + next.weight){
                    dist[next.end] = now.weight + next.weight;
                    pq.add(new Edge(next.end, dist[next.end]));
                }
            }
        }

        return can;
    }




}