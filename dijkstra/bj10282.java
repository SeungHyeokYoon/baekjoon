import java.io.*;
import java.util.*;

public class bj10282 {

    static int n, d, c, INF = 987654321;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] network;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            network  = new ArrayList[n+1];
            dist  = new int[n+1];
            visited = new boolean[n+1];

            for(int i = 1; i<=n; i++){
                network[i] = new ArrayList<>();
                dist[i]  = INF;
            }

            for(int i = 0; i<d; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                network[v].add(new Node(u, w));
            }

            dijkstra(c);

            int cnt = 0;
            int max = 0;
            for(int i = 1; i<=n; i++){
                if(dist[i] != INF){
                    cnt++;
                    max = Math.max(max, dist[i]);
                }
            }

            sb.append(cnt).append(" ").append(max).append("\n");
            
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
