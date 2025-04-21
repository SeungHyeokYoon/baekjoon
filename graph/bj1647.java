import java.io.*;
import java.util.*;

public class bj1647 {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i<=n; i++){
            parent[i] = i;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int cnt = 0;
        int sum = 0;

        while(cnt < n-2){
            Node node = pq.poll();
            if(find(node.start) != find(node.end)){
                cnt++;
                sum += node.weight;
                union(node.start, node.end);
            }
        }

        System.out.println(sum);

    }

    static int find(int x){
        if(x == parent[x]) return x;
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
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }


}
