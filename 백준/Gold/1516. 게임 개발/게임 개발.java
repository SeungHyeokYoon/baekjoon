import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] dist, time, indegree;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        dist = new int[n];
        time = new int[n];
        indegree = new int[n];
        map = new ArrayList[n];
        for(int i = 0; i<n; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int now = Integer.parseInt(st.nextToken());
            time[i] = now;
            while((now = Integer.parseInt(st.nextToken())) != -1){
                indegree[i]++;
                map[now-1].add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 0; i<n; i++){
            if(indegree[i] == 0) q.add(i);
            dist[i] = time[i];
        }

        while(!q.isEmpty()){
            int now = q.poll();

            for(Integer next : map[now]){
                dist[next] = Math.max(dist[next], dist[now] + time[next]);
                if(--indegree[next] == 0){
                    q.add(next);
                }
            }
        }

        for(int i = 0; i<n; i++){
            sb.append(dist[i]).append("\n");
        }

        System.out.println(sb);

    }


}