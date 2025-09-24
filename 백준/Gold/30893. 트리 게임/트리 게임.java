import java.io.*;
import java.util.*;

public class Main{

    static int n, start, goal;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        tree = new ArrayList[n+1];

        for(int i = 1; i<=n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        System.out.println(bfs() ? "First" : "Second");

    }

    static boolean bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start, 0});
        visited[start] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            int node = now[0];
            int turn = now[1];

            if(node == goal) return true;

            int cnt = 0;

            for(Integer next : tree[node]){
                if(!visited[next]){
                    visited[next] = true;
                    q.add(new int[]{next, (turn+1)%2});
                    cnt++;
                }
            }

            if(cnt > 1 && turn == 1) return false;
        }

        return false;

    }
}