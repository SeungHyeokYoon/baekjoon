import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] parerts;

    static class Edge implements Comparable<Edge>{
        int start, end, weight;

        Edge(int start, int end, int weight){
            this.start = start; this.end = end; this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parerts = new int[n+1];
        for(int i = 1; i<=n; i++) parerts[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Edge(u, v, w));
        }

        int cnt = 0;
        int ans = 0;

        while(true){
            if(cnt == n-1) break;

            Edge now = pq.poll();

            int x = find(now.start);
            int y = find(now.end);

            if(x != y){
                cnt++;
                ans += now.weight;
                parerts[y] = x;
            }

        }

        System.out.println(ans);
    }

    static int find(int x){
        if(parerts[x] == x) return x;
        return parerts[x] = find(parerts[x]);
    }
    

}
