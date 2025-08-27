import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Star[] stars;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static double result = 0;

    static class Star{
        double x;
        double y;
        
        public Star(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int to;
        double weight;

        public Edge(int to, double weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Double.compare(this.weight, o.weight);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        stars = new Star[n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars[i] = new Star(x, y);
        }

        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
                graph[i].add(new Edge(j, dist));
                graph[j].add(new Edge(i, dist));
            }
        }

        prim(0);

        System.out.printf("%.2f", result);




    }

    static void prim(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        int cnt = 0;

        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(visited[edge.to]) continue;

            visited[edge.to] = true;
            result += edge.weight;
            cnt++;

            if(cnt == n) break;

            for(Edge next : graph[edge.to]){
                if(!visited[next.to]){
                    pq.add(next);
                }
            }
        }
    }






}
