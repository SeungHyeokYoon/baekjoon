import java.io.*;
import java.util.*;

public class bj2252 {

    static int n, m;
    static int[] indegree;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        indegree = new int[n+1];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            graph[front].add(back);
            indegree[back]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<=n; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now).append(' ');

            for(Integer i : graph[now]){
                indegree[i]--;
                if(indegree[i] == 0){
                    q.add(i);
                }
            }
        }

        System.out.println(sb);


    }
}
