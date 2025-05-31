import java.io.*;
import java.util.*;

public class bj2473 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int a = 0;
        int b = 0;
        int c = 0;

        int n = Integer.parseInt(br.readLine());
        long[] liquid = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            liquid[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(liquid);

        long sum = Long.MAX_VALUE;
        for(int i = 0; i<n; i++){
            int front = 0;
            int end = n-1;
            
            while(true){
                if(front == i) front++;
                if(end == i) end--;
                if(front>=end) break;

                long now = liquid[i] + liquid[front] + liquid[end];
                if(now == 0){
                    System.out.println(liquid[i] + " " + liquid[front] + " " + liquid[end]);
                    return;
                }

                if(Math.abs(now) < sum){
                    a = i;
                    b = front;
                    c = end;
                    sum = Math.abs(now);
                }

                if(now > 0){
                    end--;
                }
                else{
                    front++;
                }
            }
        }

        System.out.println(liquid[a] + " " + liquid[b] + " " + liquid[c]);
    }
}
