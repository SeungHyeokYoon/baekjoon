import java.io.*;
import java.util.*;

public class swea1238 {

    static Set<Integer>[] graph;
    static boolean[] visited;
    static int n, start;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc<=10; tc++){
            graph = new Set[101];
            visited = new boolean[101];
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i<101; i++){
                graph[i] = new HashSet<>();
            }

            for(int i = 0; i<n/2; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }

            sb.append("#").append(tc).append(" ").append(bfs()).append('\n');


        }

        System.out.println(sb);
    }

    static int bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        visited[start] = true;

        int last = -1;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            last = now.num;

            for(Integer i : graph[now.num]){
                if(!visited[i]){
                    visited[i] = true;
                    pq.add(new Node(i, now.depth + 1));
                }
            }

        }

        return last;

    }


    static class Node implements Comparable<Node>{
        int num;
        int depth;

        Node(int num, int depth){
            this.num = num;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node n){
            if(this.depth == n.depth){
                return this.num - n.num;
            }

            return this.depth - n.depth;
        }
    }
}
