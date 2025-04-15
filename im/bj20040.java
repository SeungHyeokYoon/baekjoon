import java.io.*;
import java.util.StringTokenizer;

public class bj20040 {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for(int i = 0; i<n; i++){
            parent[i] = i;
        }


        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(find(a) == find(b)){
                System.out.println(i+1);
                return;
            }

            union(a, b);
        }

        System.out.println(0);
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        parent[y] = x;
    }
}
