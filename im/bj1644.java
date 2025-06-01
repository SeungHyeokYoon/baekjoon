import java.io.*;
import java.util.*;

public class bj1644 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[n+1];

        ArrayList<Integer> prime = new ArrayList<>();
        prime.add(0);

        for(int i = 2; i<=n; i++){
            if(!visited[i]){
                prime.add(i+prime.get(prime.size()-1));
                int now = i;
                while(now<=n){
                    visited[now] = true;
                    now += i;
                }
            }
            else{
                continue;
            }
        }

        int front = 0;
        int end = 1;

        int cnt = 0;
        while(end<=prime.size()-1){
            int now = prime.get(end)-prime.get(front);
            if(now == n){
                cnt++;
                end++;
            }
            else if(now > n){
                front++;
            }
            else{
                end++;
            }
        }

        System.out.println(cnt);
    }
}
