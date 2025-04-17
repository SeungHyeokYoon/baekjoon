import java.io.*;
import java.util.*;

public class bj9466 {

    static int T, n;
    static int[] students;
    static boolean[] visited, finished;
    static int count;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            n = Integer.parseInt(br.readLine());
            students = new int[n+1];
            visited = new boolean[n+1];
            finished = new boolean[n+1];
            count = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                students[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= n; i++){
                if(!visited[i]){
                    dfs(i);
                }
            }

            System.out.println(n - count);
        }
    }

    static void dfs(int x){
        visited[x] = true;
        int next = students[x];

        if(!visited[next]){
            dfs(next);
        } else if(!finished[next]){
            
            int temp = next;
            while(temp != x){
                count++;
                temp = students[temp];
            }
            count++;
        }

        finished[x] = true;
    }
}