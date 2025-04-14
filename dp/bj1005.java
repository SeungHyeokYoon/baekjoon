import java.io.*;
import java.util.*;

public class bj1005 {

    static int T, n, k, goal;
    static int[] time;
    static int[] dp;
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
            dp = new int[n+1];
            graph = new ArrayList[n+1];
            Arrays.fill(dp, -1);

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

                graph[to].add(from);
            }

            goal = Integer.parseInt(br.readLine());

            for(int i = 1; i<=n; i++){
                if(graph[i].size() == 0){
                    dp[i] = time[i];
                }
            }

            dpfunc(goal);

            System.out.println(dp[goal]);


        }
    }

    static int dpfunc(int idx){
        if(dp[idx] != -1){
            return dp[idx];
        }

        int max = -1;
        for(Integer var : graph[idx]){
            max = Math.max(max, dpfunc(var));
        }

        return dp[idx] = max + time[idx];
    }
}
