import java.io.*;
import java.util.*;

public class swea1251 {

    static int[] parent, xs, ys;
    static int tc, n;
    static double E;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1, st2;
        StringBuilder sb = new StringBuilder();

        tc = Integer.parseInt(br.readLine());

        for(int T = 1; T<=tc; T++){
            n = Integer.parseInt(br.readLine());
            st1 = new StringTokenizer(br.readLine());
            st2 = new StringTokenizer(br.readLine());
            E = Double.parseDouble(br.readLine());

            xs = new int[n];
            ys = new int[n];
            parent = new int[n];
            for(int i = 0; i<n; i++){
                parent[i] = i;
                xs[i] = Integer.parseInt(st1.nextToken());
                ys[i] = Integer.parseInt(st2.nextToken());
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();

            for(int i = 0; i<n-1; i++){
                for(int j = i+1; j<n; j++){
                    double weight = Math.pow(xs[i]-xs[j], 2) + Math.pow(ys[i]-ys[j], 2);
                    pq.add(new Edge(i, j, weight));
                }
            }

            int cnt = 0;
            double sum = 0;
            while(cnt < n-1){
                Edge now = pq.poll();
                if(find(now.start) != find(now.end)){
                    sum += now.weight;
                    union(now.start, now.end);
                    cnt++;
                }
            }

            sb.append("#" + T + " ").append(Math.round(sum * E)).append('\n');


        }

        System.out.println(sb);
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(x);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
        }
    }

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        double weight;

        Edge(int start, int end, double weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e){
            return Double.compare(this.weight, e.weight);
        }

    }
}
