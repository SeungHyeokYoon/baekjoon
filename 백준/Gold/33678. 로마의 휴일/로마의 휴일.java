import java.io.*;
import java.util.*;

public class Main{

    static int n;
    static long k, x;
    static long[] pay, add, radd;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        x = Long.parseLong(st.nextToken());

        pay = new long[n];
        add = new long[n];
        radd = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            pay[i] = Long.parseLong(st.nextToken());
        }

        add[0] = pay[0]*x;
        radd[n-1] = pay[n-1];

        for(int i = 1; i<n; i++){
            add[i] = add[i-1] + pay[i]*x;
            radd[n-1-i] = radd[n-i] + pay[n-1-i];
        }

        if(add[n-1] <= k){
            System.out.println(-1);
            return;
        }

        int start = 0;
        int end = 0;
        int max = -1;

        while(end <= n-1){

            if(start > end){
                end = start;
                if(end == n) break;
            }

            long frontcost = (start != 0) ? add[start-1] : 0;
            long endcost = (end != n-1) ? radd[end+1] : 0;
            long cost = frontcost + endcost;

            if(cost >= k){
                max = Math.max(max, end-start+1);
                end++;
            }
            else{
                start++;
            }

        }

        System.out.println(max);
        
    }
}