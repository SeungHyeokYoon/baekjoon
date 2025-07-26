import java.io.*;
import java.util.*;

public class bj2211 {

    static int n, m, c, INF = 987654321;
    static int[] dist;
    static int[] prev;
    static boolean[] visited;
    static ArrayList<Node>[] network;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        network  = new ArrayList[n+1];
        dist  = new int[n+1];
        prev = new int[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i<=n; i++){
            network[i] = new ArrayList<>();
            dist[i]  = INF;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            network[u].add(new Node(v, w));
            network[v].add(new Node(u, w));
        }

        dijkstra(1);
        sb.append(n-1).append("\n");

        for(int i = 2; i<=n; i++){
            sb.append(i).append(" ").append(prev[i]).append("\n");
        }
        
        System.out.println(sb);
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.end]) continue;
            visited[now.end] = true;



            for(Node next : network[now.end]){
                if(dist[next.end] > next.time + now.time){
                    dist[next.end] = next.time + now.time;
                    prev[next.end] = now.end;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int end;
        int time;
        
        Node(int end, int time){
            this.end = end;
            this.time = time;
        }

        @Override
        public int compareTo(Node o){
            return  this.time - o.time;
        }
    }
}
