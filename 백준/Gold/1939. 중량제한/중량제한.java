import java.io.*;
import java.util.*;

public class Main{

    static int n, m;
    static ArrayList<Node>[] map;
    static int[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new ArrayList[n+1];
        visited = new int[n+1];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[v].add(new Node(u, w));
            map[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bfs(start, end);

        System.out.println(visited[end]);

    


    }

    static void bfs(int start, int end){
        PriorityQueue<Node> q = new PriorityQueue<>();
        visited[start] = Integer.MAX_VALUE;
        q.add(new Node(start, visited[start]));

        while(!q.isEmpty()){
            Node now = q.poll();

            //System.out.println(Arrays.toString(visited));

            if(now.weight <= visited[end]) continue;
            if(now.end == end) continue;

            for(Node next : map[now.end]){
                if(next.weight > visited[next.end]){
                    if(now.weight >= next.weight){
                        visited[next.end] = next.weight;
                        q.add(new Node(next.end, next.weight));
                    }
                    else if(now.weight > visited[next.end]){
                        visited[next.end] = now.weight;
                        q.add(new Node(next.end, now.weight));
                    }
                }
            }
        }
    }



    static class Node implements Comparable<Node>{
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return o.weight - this.weight;
        }
    }



}