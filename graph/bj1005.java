import java.io.*;
import java.util.*;

public class bj1005 {

    static int T, n, k, goal;
    static int[] time;
    static int[] indegree;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            time = new int[n+1];
            indegree = new int[n+1];
            graph = new ArrayList[n+1];

            for(int i = 1; i<=n; i++){
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());

            for(int i = 1; i<=n; i++){
                time[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                indegree[to]++;
                graph[from].add(to);
            }

            goal = Integer.parseInt(br.readLine());

            topological(goal);


        }
    }

    static void topological(int goal){
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n+1];

        for(int i = 1; i<=n; i++){
            result[i] = time[i];

            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();

            for(Integer i : graph[now]){
                indegree[i]--;
                result[i] = Math.max(result[i], result[now] + time[i]);

                if(indegree[i] == 0){
                    if(i == goal){
                        System.out.println(result[goal]);
                        return;
                    }

                    q.add(i);
                }
            }
        }

        System.out.println(result[goal]);

    }

}
