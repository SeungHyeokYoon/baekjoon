import java.io.*;
import java.util.*;

public class bj12851 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int ans = 0;
        int cnt = 0;
        boolean arrive = false;

        if(n == k){
            System.out.println("0\n1");
            return;
        }

        int[] visited = new int[100001];
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 1;

        while(!q.isEmpty()){
            int cur = q.poll();
            int depth = visited[cur];


            if(!arrive && cur == k){
                ans = depth;
                cnt++;
                arrive = true;
            }
            else if(arrive){
                if(depth != ans){
                    break;
                }
                if(cur == k){
                    cnt++;
                }
            }

            if(!arrive){
                if(cur < 100000 && (visited[cur+1] == 0 || visited[cur+1] == depth+1)){
                    q.add(cur+1);
                    visited[cur+1] = depth+1;
                }
                if(cur > 0 && (visited[cur-1] == 0 || visited[cur-1] == depth+1)){
                    q.add(cur-1);
                    visited[cur-1] = depth+1;
                }
                if(cur * 2 <= 100000 && (visited[cur*2] == 0 || visited[cur*2] == depth+1)){
                    q.add(cur*2);
                    visited[cur*2] = depth+1;
                }
            }
        }

        System.out.println(ans-1);
        System.out.println(cnt);


    }
}
