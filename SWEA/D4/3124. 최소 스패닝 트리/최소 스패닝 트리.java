import java.io.*;
import java.util.*;
 
public class Solution {

    static int n, m;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge>{
        int a, b, cost;
        public Edge(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            parents = new int[n+1];
            pq = new PriorityQueue<>();

            for(int i = 1; i<=n; i++){
                parents[i] = i;
            }

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                pq.add(new Edge(a, b, cost));
            }

            long totalCost = 0;
            int edgesUsed = 0;
            while(!pq.isEmpty() && edgesUsed < n-1){
                Edge edge = pq.poll();
                int aRoot = find(edge.a);
                int bRoot = find(edge.b);

                if(aRoot != bRoot){
                    parents[bRoot] = aRoot;
                    totalCost += edge.cost;
                    edgesUsed++;
                }
            }

            sb.append("#").append(tc).append(" ").append(totalCost).append("\n");
        }

        System.out.print(sb);
    }

    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }



 

}