import java.io.*;
import java.util.*;

public class bj11779 {

    static int n, m, start, end;
    static int[] dist, prev;
    static boolean[] check;
    static ArrayList<Node>[] list;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[s].add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        prev = new int[n+1];
        check = new boolean[n+1];
        Arrays.fill(dist, INF);

        dijkstra(start);

        sb.append(dist[end]).append('\n');
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(end);
        while(end != start){
            ans.add(prev[end]);
            end = prev[end];
        }

        sb.append(ans.size()).append('\n');
        for(int i = ans.size()-1; i>=0; i--){
            sb.append(ans.get(i)).append(' ');
        }

        System.out.println(sb);
    }


    static void dijkstra(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[start] = 0;
        q.add(new Node(start, 0));

        while(!q.isEmpty()){
            Node curNode = q.poll();
            int cur = curNode.end;

            if(!check[cur]){
                if(cur == end){
                    return;
                }

                check[cur] = true;
                for(Node node : list[cur]){
                    if(!check[node.end] && dist[node.end] > dist[cur] + node.weight){
                        dist[node.end] = dist[cur] + node.weight;
                        prev[node.end] = cur;
                        q.add(new Node(node.end, dist[node.end]));
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
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
}
