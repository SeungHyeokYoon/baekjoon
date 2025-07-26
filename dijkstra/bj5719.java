import java.io.*;
import java.util.*;

public class bj5719 {

    static int n, m, s, d, INF = 987654321;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] network;
    static ArrayList<Integer>[] prev;
    static boolean[][] notused;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            network  = new ArrayList[n];
            prev = new ArrayList[n];
            dist  = new int[n];
            visited = new boolean[n];
            notused = new boolean[n][n];

            for(int i = 0; i<n; i++){
                network[i] = new ArrayList<>();
                prev[i]= new ArrayList<>();
                dist[i]  = INF;
            }

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                network[u].add(new Node(v, w));
            }

            dijkstra(s);

            remove(d);

            dist  = new int[n];
            visited = new boolean[n];
            Arrays.fill(dist, INF);
            dijkstra(s);

            sb.append(dist[d]!=INF?dist[d]:-1).append("\n");
        }

        System.out.println(sb);
    }

    static void remove(int now){
        if(now == s) return;

        for(Integer next : prev[now]){
            if(notused[next][now]) continue;
            notused[next][now] = true;
            remove(next);
        }
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
                if(notused[now.end][next.end]) continue;
                if(dist[next.end] > next.time + now.time){
                    dist[next.end] = next.time + now.time;
                    prev[next.end].clear();
                    prev[next.end].add(now.end);
                    pq.add(new Node(next.end, dist[next.end]));
                }
                else if(dist[next.end] == next.time + now.time){
                    prev[next.end].add(now.end);
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
