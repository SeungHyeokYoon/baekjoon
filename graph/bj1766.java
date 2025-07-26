import java.io.*;
import java.util.*;


public class bj1766 {

    static int n, m;
    static ArrayList<Integer>[] problem;
    static int[] refcnt;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        problem  = new ArrayList[n+1];
        refcnt = new int[n+1];

        for(int i = 1; i<=n; i++){
            problem[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            problem[a].add(b);
            refcnt[b]++;
        }

        for(int i = 1; i<=n; i++){
            if(refcnt[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int now = pq.poll();
            sb.append(now).append(" ");
            for(Integer next : problem[now]){
                if(--refcnt[next] == 0){
                    pq.add(next);
                }
            }
        }

        System.out.println(sb);
        
    }
}
