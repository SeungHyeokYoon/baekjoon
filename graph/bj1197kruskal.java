import java.io.*;
import java.util.*;

public class bj1197kruskal{

    static int v, e;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v+1];

        for(int i = 1; i<=v; i++){
            parent[i] = i;
        }

        PriorityQueue<Node> q = new PriorityQueue<>();

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            q.add(new Node(start, end, weight));
        }

        int cnt = v-1;
        int ans = 0;
        while(cnt > 0){
            Node node = q.poll();
            if(find(node.start) != find(node.end)){
                ans += node.weight;
                union(node.start, node.end);
                cnt--;
            }
        }

        System.out.println(ans);
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
        }
    }

    static class Node implements Comparable<Node>{
        int start;
        int end;
        int weight;

        Node(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n){
            return this.weight - n.weight;
        }
    }
}