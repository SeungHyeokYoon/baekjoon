import java.io.*;
import java.util.*;

public class bj14938 {

    static int n, m, r;
    static int[] vertex;
    static ArrayList<Node>[] list;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        vertex = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++){
            vertex[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n+1];

        for(int i = 1; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }


        int ans = 0;
        for(int i = 1; i<=n; i++){
            ans = Math.max(ans, dijkstra(i));
        }

        System.out.println(ans);


    }

    static int dijkstra(int start){
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> q = new PriorityQueue<>();

        Arrays.fill(dist, INF);
        dist[start] = 0;
        q.add(new Node(start, 0));

        while(!q.isEmpty()){
            Node curNode = q.poll();
            int cur = curNode.end;

            if(!visited[cur]){
                visited[cur] = true;
                for(Node node : list[cur]){
                    if(!visited[node.end] && dist[node.end] > dist[cur] + node.weight){
                        dist[node.end] = dist[cur] + node.weight;
                        q.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        int sum = 0;
        for(int i = 1; i<=n; i++){
            if(dist[i] <= m){
                sum += vertex[i];
            }
        }

        return sum;

    }

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node e){
            return this.weight - e.weight;
        }
    }
}
