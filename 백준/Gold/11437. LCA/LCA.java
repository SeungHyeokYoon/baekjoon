import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[][] parents;
    static int n, height;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        height = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;

        tree = new ArrayList[n+1];
        parents = new int[n+1][height];
        depth = new int[n+1];

        for(int i = 1; i<=n; i++) tree[i] = new ArrayList<>();

        for(int i = 1; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        init(1, 1, 0);
        setParent();

        int m = Integer.parseInt(br.readLine());

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b)).append("\n");
        }

        System.out.println(sb);

    }

    static void init(int node, int d, int parent){
        depth[node] = d;
        parents[node][0] = parent;

        for(int next : tree[node]){
            if(next == parent) continue;
            init(next, d+1, node);
        }
    }

    static void setParent(){
        for(int i = 1; i<height; i++){
            for(int j = 1; j<=n; j++){
                parents[j][i] = parents[parents[j][i-1]][i-1];
            }
        }
    }

    static int LCA(int a, int b){
        int ah = depth[a];
        int bh = depth[b];

        if(ah < bh){
            int temp = b;
            b = a;
            a = temp;
        }

        for(int i = height-1; i>=0; i--){
            if((1<<i) <= depth[a] - depth[b]) a = parents[a][i];
        }

        if(a == b) return a;

        for(int i = height-1; i>=0; i--){
            if(parents[a][i] != parents[b][i]){
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        return parents[a][0];
    }
}
