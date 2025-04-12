import java.io.*;
import java.util.*;

public class bj1197prim {

    static int v, e, ans;
    static ArrayList<Node>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];
        visited = new boolean[v+1];
        for(int i=1; i<=v; i++){
			list[i] = new ArrayList<>();
		}

        for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,w));
			list[b].add(new Node(a,w));
		}

        prim(1);
        System.out.println(ans);

    }

    static void prim(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(start, 0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            int end = cur.end;
            int weight = cur.weight;

            if(!visited[end]){
                visited[end] = true;
                ans += weight;

                for(Node node : list[end]){
                    if(!visited[node.end]){
                        q.add(node);
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

        @Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
    }
}
