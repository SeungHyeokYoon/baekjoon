import java.io.*;
import java.util.*;

public class bj11722 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        dp[0] = arr[0];

        int length = 1;

        for(int i = 1; i<n; i++){
            if(arr[i] < dp[length-1]){
                dp[length] = arr[i];
                length++;
            }
            else{
                int idx = length-1;
                while(idx>=0 && dp[idx] <= arr[i]){
                    idx--;
                }
                dp[idx+1] = arr[i];
            }
        }

        System.out.println(length);


    }
}
