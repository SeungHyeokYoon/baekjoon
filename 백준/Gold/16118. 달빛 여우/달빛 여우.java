import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Node>[] map;
    static int n, m;
    static int[] fox;
    static int[][] wolf;

    static class Node implements Comparable<Node>{
        int end;
        int weight;
        int flag;

        Node(int end, int weight, int flag){
            this.end = end;
            this.weight = weight;
            this.flag = flag;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new ArrayList[n+1];
        fox = new int[n+1];
        wolf = new int[n+1][2];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
            fox[i] = Integer.MAX_VALUE;
            wolf[i][0] = Integer.MAX_VALUE;
            wolf[i][1] = Integer.MAX_VALUE;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken())*2;

            map[start].add(new Node(end, weight, 0));
            map[end].add(new Node(start, weight, 0));
        }

        dijkstraF(1);
        dijkstraW(1);

        int cnt = 0;
        for(int i = 1; i<=n; i++){
            if(Integer.compare(fox[i], Math.min(wolf[i][0], wolf[i][1])) < 0) cnt++;
        }

        System.out.println(cnt);
    }

    static void dijkstraF(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, 0));
        fox[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(fox[now.end] < now.weight) continue;

            for(Node next : map[now.end]){
                if(fox[next.end] > now.weight + next.weight){
                    fox[next.end] = now.weight + next.weight;
                    pq.add(new Node(next.end, fox[next.end], 0));
                }
            }
        }
    }


    static void dijkstraW(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, 0)); // 0 is mul 1/2 and 1 is mul 2
        wolf[start][1] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(wolf[now.end][Math.abs(now.flag-1)] < now.weight) continue;

            for(Node next : map[now.end]){

                if(now.flag == 0){
                    if(wolf[next.end][now.flag] > now.weight + next.weight/2){
                        wolf[next.end][now.flag] = now.weight + next.weight/2;
                        pq.add(new Node(next.end, wolf[next.end][now.flag], 1));
                    }
                }
                else{
                    if(wolf[next.end][now.flag] > now.weight + next.weight*2){
                        wolf[next.end][now.flag] = now.weight + next.weight*2;
                        pq.add(new Node(next.end, wolf[next.end][now.flag], 0));
                    }
                }

            }
        }
    }
}
