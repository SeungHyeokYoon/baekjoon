import java.io.*;
import java.util.*;

public class bj1707 {

    static int k, v, e;
    static ArrayList<Integer>[] graph;
    static int[] colors;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        Loop:
        while(k-- > 0){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
        
            graph = new ArrayList[v+1];
            colors = new int[v+1];
            for(int i = 1; i<=v; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                graph[to].add(from);
            }

            for(int i = 1; i<=v; i++){
                if(colors[i] == 0){
                    if(!dfs(i, 1)){
                        System.out.println("NO");
                        continue Loop;
                    }
                }
            }

            System.out.println("YES");
        }
    }

    static boolean dfs(int x, int color){
        colors[x] = color;

        for(Integer next : graph[x]){
            if(colors[next] == color){
                return false;
            }
            else if(colors[next] == 0){
                int nextcolor = color == 1 ? -1 : 1;
                if(!dfs(next, nextcolor)){
                    return false;
                }
                
            }
        }

        return true;
    }
}
