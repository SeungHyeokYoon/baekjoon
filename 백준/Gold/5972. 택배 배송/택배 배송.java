import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Node>[] map;
    static int[] dist;
    static boolean[] visited;

    static class Node implements Comparable<Node>{
        int end, weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        };
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new ArrayList[n+1];
        dist = new int[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[u].add(new Node(v, w));
            map[v].add(new Node(u, w));
        }

        dijkstra(1);

        System.out.println(dist[n]);

    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.end]) continue;
            visited[now.end] = true;

            for(Node next : map[now.end]){
                if(dist[next.end] > now.weight + next.weight){
                    dist[next.end] = now.weight + next.weight;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }

    }



}

