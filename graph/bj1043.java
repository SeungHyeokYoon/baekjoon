import java.io.*;
import java.util.*;

public class bj1043{

    static int n, m, ans;
    static int[] know;
    static boolean[] visited;
    static int[][] graph;
    static ArrayList<Integer>[] meeting;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int o = Integer.parseInt(st.nextToken());
        if(o == 0){
            System.out.println(m);
            return;
        }

        ans = 0;
        know = new int[o];
        visited = new boolean[n];
        graph = new int[n][n];
        meeting = new ArrayList[m];
        for(int i = 0; i<o; i++){
            know[i] = Integer.parseInt(st.nextToken())-1;
        }
        

        for(int i = 0; i<m; i++){
            meeting[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j<cnt; j++){
                meeting[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }

        for(int i = 0; i<m; i++){
            for(int j = 0; j<meeting[i].size()-1; j++){
                for(int k = j+1; k<meeting[i].size(); k++){
                    graph[meeting[i].get(j)][meeting[i].get(k)] = 1;
                    graph[meeting[i].get(k)][meeting[i].get(j)] = 1;
                }
            }
        }

        for(int i = 0; i<o; i++){
            dfs(know[i]);
        }

        for(int i = 0; i<m; i++){
            boolean find = false;
            for(int j = 0; j<meeting[i].size(); j++){
                if(visited[meeting[i].get(j)]){
                    find = true;
                    break;
                }
            }
            if(!find){
                ans++;
            }
        }

        System.out.println(ans);
        


    }

    static void dfs(int x){
        visited[x] = true;

        for(int i = 0; i<n; i++){
            if(!visited[i] && graph[x][i] == 1){
                dfs(i);
            }
        }
    }

    
}