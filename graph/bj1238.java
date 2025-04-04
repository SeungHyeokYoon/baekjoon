import java.io.*;
import java.util.*;

public class bj1238 {

    static int n, m, x;
    static ArrayList<Node>[] town, retown;
    static boolean[] check;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        town = new ArrayList[n+1];
        retown = new ArrayList[n+1];
        check = new boolean[n+1];

        for(int i = 0; i<=n; i++){
            town[i] = new ArrayList<>();
            retown[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            town[start].add(new Node(end, weight));
            retown[end].add(new Node(start, weight));
        }

        int[] dist1 = dijkstra(town);
        Arrays.fill(check, false);
        int[] dist2 = dijkstra(retown);

        int ans = 0;
        for(int i = 1; i<=n; i++){
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }

        System.out.println(ans);


    }

    static int[] dijkstra(ArrayList<Node>[] arr){
        int[] dist = new int[n+1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(x, 0));

        Arrays.fill(dist, INF);
        dist[x] = 0;

        while(!q.isEmpty()){
            Node curNode = q.poll();
            int cur = curNode.end;

            if(!check[cur]){
                check[cur] = true;

                for(Node node : arr[cur]){
                    if(!check[node.end] && dist[node.end] > dist[cur]+node.weight){
                        dist[node.end] = dist[cur] + node.weight;
                        q.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist;
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
