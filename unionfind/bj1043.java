import java.io.*;
import java.util.*;

public class bj1043{

    static int n, m;
    static int[] know;
    static int[] parent;
    static ArrayList<Integer>[] meeting;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int personcnt = Integer.parseInt(st.nextToken());
        if(personcnt == 0){
            System.out.println(m);
            return;
        }

        know = new int[personcnt];
        parent = new int[n+1];
        meeting = new ArrayList[m];
        for(int i = 0; i<personcnt; i++){
            know[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<m; i++){
            meeting[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int meeting_size = Integer.parseInt(st.nextToken());
            for(int j = 0; j<meeting_size; j++){
                meeting[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 0; i<=n; i++){
            parent[i] = i;
        }

        for(int i = 0; i<m; i++){
            int first = meeting[i].get(0);
            for(int j = 1; j<meeting[i].size(); j++){
                union(first, meeting[i].get(j));
            }
        }

        int cnt = 0;
        for(int i = 0; i<m; i++){
            int leader = meeting[i].get(0);
            boolean is = false;
            for(int j = 0; j<personcnt; j++){
                if(find(leader) == find(know[j])){
                    is = true;
                    break;
                }
            }

            if(!is){
                cnt++;
            }
        }

        System.out.println(cnt);


    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        else{
            return parent[a] = find(parent[a]);
        }
    }
}