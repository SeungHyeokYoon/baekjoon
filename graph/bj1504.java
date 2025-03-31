import java.io.*;
import java.util.*;

public class bj1504{

    static int n, e;
    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] check;
    static final int INF = 200000000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        dist = new int[n+1];
        check = new boolean[n+1];

        for(int i = 0; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int ans1 = 0;
        ans1 += dijkstra(1, v1);
        ans1 += dijkstra(v1, v2);
        ans1 += dijkstra(v2, n);

        int ans2 = 0;
        ans2 += dijkstra(1, v2);
        ans2 += dijkstra(v2, v1);
        ans2 += dijkstra(v1, n);

        int ans = (ans1 >= INF && ans2 >= INF) ? -1 : Math.min(ans1, ans2);

        System.out.println(ans);

        

        
    }

    static int dijkstra(int start, int end){
        Arrays.fill(dist, INF);
        Arrays.fill(check, false);

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()){
            Node curNode = q.poll();
            int cur = curNode.end;
            if(!check[cur]){
                check[cur] = true;

                for(Node node : list[cur]){
                    if(!check[node.end] && dist[node.end] > dist[cur] + node.weight){
                        dist[node.end] = dist[cur] + node.weight;
                        q.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    
}