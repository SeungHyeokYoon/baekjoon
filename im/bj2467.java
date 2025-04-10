import java.io.*;
import java.util.*;

public class bj2467 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int front = 0;
        int end = n-1;
        int ans1 = 0;
        int ans2 = 0;
        int ansadd = Integer.MAX_VALUE;
        int sum = 0;

        while(front<end){
            sum = arr[front] + arr[end];
            if(Math.abs(sum) <= Math.abs(ansadd)){
                ans1 = front;
                ans2 = end;
                ansadd = sum;
            }

            if(sum == 0){
                System.out.println(arr[front] + " " + arr[end]);
                return;
            }
            else if(sum > 0){
                end--;
            }
            else{
                front++;
            }
        }

        System.out.println(arr[ans1] + " " + arr[ans2]);
    }
}
