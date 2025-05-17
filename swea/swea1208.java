import java.io.*;
import java.util.*;

public class swea1208 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=10; t++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[100];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<100; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            while(n-- > 0){
                if(arr[99] - arr[0] <= 1){
                    break;
                }

                arr[0]++;
                arr[99]--;

                int leftidx = 1;
                int rightidx = 98;
                int temp = 0;

                while(arr[leftidx] < arr[0]){
                    leftidx++;
                }

                temp = arr[leftidx-1];
                arr[leftidx-1] = arr[0];
                arr[0] = temp;

                while(arr[rightidx] > arr[99]){
                    rightidx--;
                }

                temp = arr[rightidx+1];
                arr[rightidx+1] = arr[99];
                arr[99] = temp;

            }

            sb.append("#").append(t).append(" ").append(arr[99] - arr[0]).append("\n");

        }

        System.out.println(sb);
    }
}
