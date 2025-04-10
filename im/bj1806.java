import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1806 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++){
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }

        int front = 0;
        int end = 1;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        while(end <= n){
            sum = arr[end] - arr[front];

            if(sum < m){
                end++;
            }
            else{
                if(ans > end-front){
                    ans = end - front;
                }
                front++;
            }

        }

        if(ans == Integer.MAX_VALUE){
            System.out.println(0);
        }
        else{
            System.out.println(ans);
        }
    }
}
