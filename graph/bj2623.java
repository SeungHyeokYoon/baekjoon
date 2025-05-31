import java.io.*;
import java.util.*;

public class bj2623 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            int child;
            for(int j = 0; j<cnt-1; j++){
                child = Integer.parseInt(st.nextToken());
                graph[parent].add(child);
                indegree[child]++;
                parent = child;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0) q.add(i);
        }

        int cnt = 0;
        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now).append("\n");
            cnt++;

            for(int next : graph[now]){
                indegree[next]--;
                if(indegree[next] == 0) q.add(next);
            }
        }

        if(cnt == n) System.out.print(sb);
        else System.out.println(0);


    }
}
