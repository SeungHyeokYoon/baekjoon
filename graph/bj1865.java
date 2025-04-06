import java.io.*;
import java.util.*;

public class bj1865{

    static int n, m, w;
    static ArrayList<Edge> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new ArrayList<>();

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map.add(new Edge(a, b, c));
                map.add(new Edge(b, a, c));
            }

            for(int i = 0; i<w; i++){
                st = new StringTokenizer(br.readLine());
                map.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken())));
            }

            BellmanFord();
        }

    }

    static void BellmanFord(){
        int[] dist = new int[n+1];

        for(int i = 1; i<n; i++){
            for(int j = 0; j<map.size(); j++){
                Edge cur = map.get(j);

                if(dist[cur.end] > dist[cur.start] + cur.weight){
                    dist[cur.end] = dist[cur.start] + cur.weight;
                }
            }
        }

        for(int i = 0; i<map.size(); i++){
            Edge cur = map.get(i);

            if(dist[cur.end] > dist[cur.start] + cur.weight){
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");

    }

    static class Edge{
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}