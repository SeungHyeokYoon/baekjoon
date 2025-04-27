import java.io.*;
import java.util.*;

public class bj1068{

    static int n, root, remove, ans;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        remove = Integer.parseInt(br.readLine());


        for(int i = 0; i<n; i++){
            int parent = Integer.parseInt(st.nextToken());

            if(i == remove){
                continue;
            }

            if(parent == -1){
                root = i;
            }
            else{
                graph[parent].add(i);
            }
        }

        if(remove == root){
            System.out.println(0);
            return;
        }

        ans = 0;
        dfs(root);
        System.out.println(ans);


    }

    static void dfs(int x){
        if(graph[x].isEmpty()){
            ans++;
        }
        else{
            for(Integer i : graph[x]){
                dfs(i);
            }
        }
    }
}